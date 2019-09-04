package org.mybaits.ex;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.Config;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.dto.DataConvertSuper;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.ui.MainUI;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyBatisGeneratorEx;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyDefaultCommentGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyJavaTypeResolverConfiguration;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.util.ConfigConvertUtil;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.util.DataConvertImpl;
import org.mybatis.generator.internal.DefaultShellCallback;

/**    
 * 文件名：TestGenerator.java    
 *    
 * 版本信息：    
 * 日期：2018年10月23日    
 * Copyright Felicity Corporation 2018 版权所有   
 */

/**
 * 类名称：TestGenerator <br>
 * 类描述: 内部测试用例，便于开发调试<br>
 * 创建人：felicity <br>
 * 创建时间：2018年10月23日 上午10:42:03 <br>
 * 备注:
 * 
 * @version
 * @see
 */
public class TestGenerator {

	private String sLine = "------------------------------------------------------------------------";
	private String bLine = "========================================================================";

	private Log log;

	private File configFile;

	@Before
	public void before() {
		URL is = TestGenerator.class.getClassLoader().getResource("generatorTestConfig.xml");
		String file = is.getFile();
		configFile = new File(file);
		System.out.println(configFile);
	}

	public Log getLog() {
		if (log == null) {
			log = new SystemStreamLog();
		}

		return log;
	}

	@Test
	public void test() throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		try {

			// ConfigurationParser cp = new ConfigurationParser(warnings);
			// ConfigConvertUtil util = new ConfigConvertUtil();
			// Configuration config = cp.parseConfiguration(configFile);
			// util.writeJSONToFile(util.me2self(config));

			getLog().info(bLine);
			getLog().info("欢迎切换 mybatis.generator 扩展插件.");
			getLog().info(bLine);
			getLog().info("加载自定义扩展：可视化配置");

			String path = "F:\\Git\\mbg-plugin-extend\\src\\test\\java\\generatorTestConfig.json";
			File cff = new File(path);
			ConfigConvertUtil ccutil = new ConfigConvertUtil(cff,"F:\\git\\felicity\\");
			// 源文件夹
			Set<String> sources = new HashSet<>();
			sources.add("src\\main\\java");
			sources.add("src\\main\\resources");
			Configuration config = null;
			// 启动配置
			DataConvertSuper dcs = new DataConvertImpl(ccutil);
			
			CountDownLatch cdl = new CountDownLatch(1);
			MainUI.begin(dcs, cdl,sources);
			try {
				cdl.await();
				// 用户是否自动退出
				if (MainUI.getStartState() == -1) {
					getLog().info("不想继续了? 下次我还在这里, Bye!!!!");
					return;
				}
				// 自定义配置操作已经完成，转换数据
				Config selfConfig = dcs.tearViewDate();
				String baseFile = "F:\\git\\felicity\\";
				config = ccutil.self2me(selfConfig, baseFile);
				// 自定义的配置需要保持数据文件中.
				ccutil.writeJSONToFile(selfConfig);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (config == null) {
				getLog().info("实例化自定义配置失败....请联系管理员");
			}

			// Configuration config = util.intiFormJSON();
			for (Context t : config.getContexts()) {
				getLog().info(sLine);

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
			ShellCallback callback = new DefaultShellCallback(overwrite);

			getLog().info("加载自定义扩展：mapper.xml 文件强制覆盖");
			getLog().info(sLine);

			// Configuration config = cp.parseConfiguration(configFile);
			// DefaultShellCallback callback = new
			// DefaultShellCallback(overwrite);
			// MyBatisGenerator myBatisGenerator = new
			// MyBatisGenerator(config,callback, warnings);

			// 自定义插件实现
			MyBatisGeneratorEx myBatisGenerator = new MyBatisGeneratorEx(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
