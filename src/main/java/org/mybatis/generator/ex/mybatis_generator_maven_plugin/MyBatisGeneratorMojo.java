/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Resource;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyBatisGeneratorEx;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyDefaultCommentGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyJavaTypeResolverConfiguration;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.ClassloaderUtility;
import org.mybatis.generator.internal.util.StringUtility;
import org.mybatis.generator.internal.util.messages.Messages;
import org.mybatis.generator.logging.LogFactory;

/**
 * 类名称：MyMojo <br>
 * 类描述: 覆盖原mybatis插件,(注解,insert后ID,全局指定类型转换) <br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月24日 下午5:37:54 <br>
 * 修改人：felicity <br>
 * 修改时间：2018年4月24日 下午5:37:54 <br>
 * 修改备注:
 * @version
 * @see
 */
@Mojo(name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresDependencyResolution = ResolutionScope.TEST)
public class MyBatisGeneratorMojo extends AbstractMojo {

	private ThreadLocal<ClassLoader> savedClassloader = new ThreadLocal<ClassLoader>();

	/**
	 * Maven Project.
	 *
	 */
	@Parameter(property = "project", required = true, readonly = true)
	private MavenProject project;

	/**
	 * Output Directory.
	 */
	@Parameter(property = "mybatis.generator.outputDirectory", defaultValue = "${project.build.directory}/generated-sources/mybatis-generator", required = true)
	private File outputDirectory;

	/**
	 * Location of the configuration file.
	 */
	@Parameter(property = "mybatis.generator.configurationFile", defaultValue = "${project.basedir}/src/main/resources/generatorConfig.xml", required = true)
	private File configurationFile;

	@Parameter(property = "mybatis.generator.cfg.json", defaultValue = "${project.basedir}/src/main/resources/generatorConfig.json")
	private File cfgJsonFile;

	/**
	 * Specifies whether the mojo writes progress messages to the log.
	 */
	@Parameter(property = "mybatis.generator.verbose", defaultValue = "false")
	private boolean verbose;

	/**
	 * Specifies whether the mojo overwrites existing Java files. Default is
	 * false. <br>
	 * Note that XML files are always merged.
	 * ========================================
	 * 强制修改为TRUE，JAVA Model 文件会自动覆盖
	 * ========================================
	 */
	@Parameter(property = "mybatis.generator.overwrite", defaultValue = "true")
	// @Parameter(property = "mybatis.generator.overwrite", defaultValue =
	// "false")
	private boolean overwrite;

	/**
	 * Location of a SQL script file to run before generating code. If null,
	 * then no script will be run. If not null, then jdbcDriver, jdbcURL must be
	 * supplied also, and jdbcUserId and jdbcPassword may be supplied.
	 */
	@Parameter(property = "mybatis.generator.sqlScript")
	private String sqlScript;

	/**
	 * JDBC Driver to use if a sql.script.file is specified.
	 */
	@Parameter(property = "mybatis.generator.jdbcDriver")
	private String jdbcDriver;

	/**
	 * JDBC URL to use if a sql.script.file is specified.
	 */
	@Parameter(property = "mybatis.generator.jdbcURL")
	private String jdbcURL;

	/**
	 * JDBC user ID to use if a sql.script.file is specified.
	 */
	@Parameter(property = "mybatis.generator.jdbcUserId")
	private String jdbcUserId;

	/**
	 * JDBC password to use if a sql.script.file is specified.
	 */
	@Parameter(property = "mybatis.generator.jdbcPassword")
	private String jdbcPassword;

	/**
	 * Comma delimited list of table names to generate.
	 */
	@Parameter(property = "mybatis.generator.tableNames")
	private String tableNames;

	/**
	 * Comma delimited list of contexts to generate.
	 */
	@Parameter(property = "mybatis.generator.contexts")
	private String contexts;

	/**
	 * Skip generator.
	 */
	@Parameter(property = "mybatis.generator.skip", defaultValue = "false")
	private boolean skip;

	/**
	 * If true, then dependencies in scope compile, provided, and system scopes
	 * will be
	 * added to the classpath of the generator. These dependencies will be
	 * searched for
	 * JDBC drivers, root classes, root interfaces, generator plugins, etc.
	 */
	@Parameter(property = "mybatis.generator.includeCompileDependencies", defaultValue = "false")
	private boolean includeCompileDependencies;

	/**
	 * If true, then dependencies in all scopes will be
	 * added to the classpath of the generator. These dependencies will be
	 * searched for
	 * JDBC drivers, root classes, root interfaces, generator plugins, etc.
	 */
	@Parameter(property = "mybatis.generator.includeAllDependencies", defaultValue = "false")
	private boolean includeAllDependencies;

	private String sLine = "------------------------------------------------------------------------";
	private String bLine = "========================================================================";

	@Override
	public String toString() {
		return "MyMojo [savedClassloader=" + savedClassloader + ", project=" + project
				+ ", outputDirectory=" + outputDirectory + ", configurationFile="
				+ configurationFile + ", verbose=" + verbose + ", overwrite=" + overwrite
				+ ", sqlScript=" + sqlScript + ", jdbcDriver=" + jdbcDriver + ", jdbcURL="
				+ jdbcURL + ", jdbcUserId=" + jdbcUserId + ", jdbcPassword=" + jdbcPassword
				+ ", tableNames=" + tableNames + ", contexts=" + contexts + ", skip=" + skip
				+ ", includeCompileDependencies=" + includeCompileDependencies
				+ ", includeAllDependencies=" + includeAllDependencies + "]";
	}

	public void execute() throws MojoExecutionException {
		if (skip) {
			getLog().info("MyBatis generator is skipped.");
			return;
		}

		//保存类加载器到 线程中
		saveClassLoader();

		//设置日志
		LogFactory.setLogFactory(new MavenLogFactory(this));

		calculateClassPath();

		// add resource directories to the classpath. This is required to
		// support
		// use of a properties file in the build. Typically, the properties file
		// is in the project's source tree, but the plugin classpath does not
		// include the project classpath.
		List<Resource> resources = project.getResources();
		List<String> resourceDirectories = new ArrayList<String>();
		for (Resource resource : resources) {
			resourceDirectories.add(resource.getDirectory());
			System.out.println("resource:" + resource.toString());
		}
		ClassLoader cl = ClassloaderUtility.getCustomClassloader(resourceDirectories);
		ObjectFactory.addExternalClassLoader(cl);

		if (configurationFile == null) {
			throw new MojoExecutionException(Messages.getString("RuntimeError.0")); //$NON-NLS-1$
		}

		List<String> warnings = new ArrayList<String>();

		if (!configurationFile.exists()) {
			throw new MojoExecutionException(Messages.getString(
					"RuntimeError.1", configurationFile.toString())); //$NON-NLS-1$
		}

		runScriptIfNecessary();

		Set<String> fullyqualifiedTables = new HashSet<String>();
		if (StringUtility.stringHasValue(tableNames)) {
			StringTokenizer st = new StringTokenizer(tableNames, ","); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				String s = st.nextToken().trim();
				if (s.length() > 0) {
					fullyqualifiedTables.add(s);
				}
			}
		}

		Set<String> contextsToRun = new HashSet<String>();
		if (StringUtility.stringHasValue(contexts)) {
			StringTokenizer st = new StringTokenizer(contexts, ","); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				String s = st.nextToken().trim();
				if (s.length() > 0) {
					contextsToRun.add(s);
				}
			}
		}

		try {
			ConfigurationParser cp = new ConfigurationParser(project.getProperties(), warnings);
			Configuration config = cp.parseConfiguration(configurationFile);
			for (Context t : config.getContexts()) {
				getLog().info(bLine);
				getLog().info("欢迎切换 mybatis.generator 扩展插件.");
				getLog().info(bLine);

				try {
					Field f = t.getClass().getDeclaredField("commentGenerator");
					f.setAccessible(true);

					getLog().info("加载自定义扩展：文档注释");
					getLog().info(sLine);
					MyDefaultCommentGenerator myComment = new MyDefaultCommentGenerator();
					myComment.setLog(getLog());
					f.set(t, myComment);

					getLog().info("加载自定义扩展：类型转换");
					getLog().info(sLine);

					MyJavaTypeResolverConfiguration myType = new MyJavaTypeResolverConfiguration();
					t.setJavaTypeResolverConfiguration(myType);

				} catch (NoSuchFieldException | SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			ShellCallback callback = new MavenShellCallback(this, overwrite);

			getLog().info("加载自定义扩展：mapper.xml 文件强制覆盖");
			getLog().info(sLine);

			// 自定义的生成器
			MyBatisGeneratorEx myBatisGenerator = new MyBatisGeneratorEx(config, callback, warnings);

			myBatisGenerator.generate(new MavenProgressCallback(getLog(), verbose), contextsToRun,
					fullyqualifiedTables);

		} catch (XMLParserException e) {
			for (String error : e.getErrors()) {
				getLog().error(error);
			}

			throw new MojoExecutionException(e.getMessage());
		} catch (SQLException e) {
			throw new MojoExecutionException(e.getMessage());
		} catch (IOException e) {
			throw new MojoExecutionException(e.getMessage());
		} catch (InvalidConfigurationException e) {
			for (String error : e.getErrors()) {
				getLog().error(error);
			}

			throw new MojoExecutionException(e.getMessage());
		} catch (InterruptedException e) {
			// ignore (will never happen with the DefaultShellCallback)
		}

		for (String error : warnings) {
			getLog().warn(error);
		}

		if (project != null && outputDirectory != null && outputDirectory.exists()) {
			project.addCompileSourceRoot(outputDirectory.getAbsolutePath());

			Resource resource = new Resource();
			resource.setDirectory(outputDirectory.getAbsolutePath());
			resource.addInclude("**/*.xml");
			project.addResource(resource);
		}

		restoreClassLoader();
		getLog().info(sLine);
		getLog().info("All OVER :-) ");
	}

	private void calculateClassPath() throws MojoExecutionException {
		if (includeCompileDependencies || includeAllDependencies) {
			try {
				// add the project compile classpath to the plugin classpath,
				// so that the project dependency classes can be found
				// directly, without adding the classpath to configuration's
				// classPathEntries
				// repeatedly.Examples are JDBC drivers, root classes, root
				// interfaces, etc.
				Set<String> entries = new HashSet<String>();
				if (includeCompileDependencies) {
					entries.addAll(project.getCompileClasspathElements());
				}

				if (includeAllDependencies) {
					entries.addAll(project.getTestClasspathElements());
				}

				// remove the output directories (target/classes and
				// target/test-classes)
				// because this mojo runs in the generate-sources phase and
				// those directories have not been created yet (typically)
				entries.remove(project.getBuild().getOutputDirectory());
				entries.remove(project.getBuild().getTestOutputDirectory());

				// 设置自定义的类加载器
				ClassLoader contextClassLoader = ClassloaderUtility.getCustomClassloader(entries);
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			} catch (DependencyResolutionRequiredException e) {
				throw new MojoExecutionException("Dependency Resolution Required", e);
			}
		}
	}

	private void runScriptIfNecessary() throws MojoExecutionException {
		if (sqlScript == null) {
			return;
		}

		SqlScriptRunner scriptRunner = new SqlScriptRunner(sqlScript, jdbcDriver, jdbcURL,
				jdbcUserId, jdbcPassword);
		scriptRunner.setLog(getLog());
		scriptRunner.executeScript();
	}

	public File getOutputDirectory() {
		return outputDirectory;
	}

	private void saveClassLoader() {
		savedClassloader.set(Thread.currentThread().getContextClassLoader());
	}

	private void restoreClassLoader() {
		Thread.currentThread().setContextClassLoader(savedClassloader.get());
	}
}
