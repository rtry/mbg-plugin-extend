/**    
 * 文件名：MyBatisGeneratorExImpl.java    
 *    
 * 版本信息：    
 * 日期：2018年9月21日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import static org.mybatis.generator.internal.util.ClassloaderUtility.getCustomClassloader;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.ExtendUtil;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.util.RegexUtil;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.NullProgressCallback;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.XmlFileMergerJaxp;

/**
 * 类名称：MyBatisGeneratorExImpl <br>
 * 类描述: 重写生成逻辑<br>
 * 创建人：felicity <br>
 * 创建时间：2018年9月21日 下午2:51:03 <br>
 */
public class MyBatisGeneratorEx {

    /** The configuration. */
    private Configuration configuration;

    /** The shell callback. */
    private ShellCallback shellCallback;

    /** The generated java files. */
    private List<GeneratedJavaFile> generatedJavaFiles;

    /** The generated xml files. */
    private List<GeneratedXmlFile> generatedXmlFiles;

    /** The warnings. */
    private List<String> warnings;

    /** The projects. */
    private Set<String> projects;

    /**
     * Constructs a MyBatisGenerator object.
     * 
     * @param configuration
     *        The configuration for this invocation
     * @param shellCallback
     *        an instance of a ShellCallback interface. You may specify
     *        <code>null</code> in which case the DefaultShellCallback will
     *        be used.
     * @param warnings
     *        Any warnings generated during execution will be added to this
     *        list. Warnings do not affect the running of the tool, but they
     *        may affect the results. A typical warning is an unsupported
     *        data type. In that case, the column will be ignored and
     *        generation will continue. You may specify <code>null</code> if
     *        you do not want warnings returned.
     * @throws InvalidConfigurationException
     *         if the specified configuration is invalid
     */
    public MyBatisGeneratorEx(Configuration configuration, ShellCallback shellCallback, List<String> warnings)
            throws InvalidConfigurationException {
        super();
        if (configuration == null) {
            throw new IllegalArgumentException(getString("RuntimeError.2"));
        } else {
            this.configuration = configuration;
        }

        if (shellCallback == null) {
            this.shellCallback = new DefaultShellCallback(false);
        } else {
            this.shellCallback = shellCallback;
        }

        if (warnings == null) {
            this.warnings = new ArrayList<String>();
        } else {
            this.warnings = warnings;
        }
        generatedJavaFiles = new ArrayList<GeneratedJavaFile>();
        generatedXmlFiles = new ArrayList<GeneratedXmlFile>();
        projects = new HashSet<String>();

        this.configuration.validate();
    }

    /**
     * This is the main method for generating code. This method is long running,
     * but progress can be provided and the method can be canceled through the
     * ProgressCallback interface. This version of the method runs all
     * configured contexts.
     *
     * @param callback
     *        an instance of the ProgressCallback interface, or
     *        <code>null</code> if you do not require progress information
     * @throws SQLException
     *         the SQL exception
     * @throws IOException
     *         Signals that an I/O exception has occurred.
     * @throws InterruptedException
     *         if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback) throws SQLException, IOException, InterruptedException {
        generate(callback, null, null, true);
    }

    /**
     * This is the main method for generating code. This method is long running,
     * but progress can be provided and the method can be canceled through the
     * ProgressCallback interface.
     *
     * @param callback
     *        an instance of the ProgressCallback interface, or
     *        <code>null</code> if you do not require progress information
     * @param contextIds
     *        a set of Strings containing context ids to run. Only the
     *        contexts with an id specified in this list will be run. If the
     *        list is null or empty, than all contexts are run.
     * @throws SQLException
     *         the SQL exception
     * @throws IOException
     *         Signals that an I/O exception has occurred.
     * @throws InterruptedException
     *         if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback, Set<String> contextIds)
            throws SQLException, IOException, InterruptedException {
        generate(callback, contextIds, null, true);
    }

    /**
     * This is the main method for generating code. This method is long running,
     * but progress can be provided and the method can be cancelled through the
     * ProgressCallback interface.
     *
     * @param callback
     *        an instance of the ProgressCallback interface, or
     *        <code>null</code> if you do not require progress information
     * @param contextIds
     *        a set of Strings containing context ids to run. Only the
     *        contexts with an id specified in this list will be run. If the
     *        list is null or empty, than all contexts are run.
     * @param fullyQualifiedTableNames
     *        a set of table names to generate. The elements of the set must
     *        be Strings that exactly match what's specified in the
     *        configuration. For example, if table name = "foo" and schema =
     *        "bar", then the fully qualified table name is "foo.bar". If
     *        the Set is null or empty, then all tables in the configuration
     *        will be used for code generation.
     * @throws SQLException
     *         the SQL exception
     * @throws IOException
     *         Signals that an I/O exception has occurred.
     * @throws InterruptedException
     *         if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback, Set<String> contextIds, Set<String> fullyQualifiedTableNames)
            throws SQLException, IOException, InterruptedException {
        generate(callback, contextIds, fullyQualifiedTableNames, true);
    }

    /**
     * This is the main method for generating code. This method is long running,
     * but progress can be provided and the method can be cancelled through the
     * ProgressCallback interface.
     *
     * @param callback
     *        an instance of the ProgressCallback interface, or
     *        <code>null</code> if you do not require progress information
     * @param contextIds
     *        a set of Strings containing context ids to run. Only the
     *        contexts with an id specified in this list will be run. If the
     *        list is null or empty, than all contexts are run.
     * @param fullyQualifiedTableNames
     *        a set of table names to generate. The elements of the set must
     *        be Strings that exactly match what's specified in the
     *        configuration. For example, if table name = "foo" and schema =
     *        "bar", then the fully qualified table name is "foo.bar". If
     *        the Set is null or empty, then all tables in the configuration
     *        will be used for code generation.
     * @param writeFiles
     *        if true, then the generated files will be written to disk. If
     *        false, then the generator runs but nothing is written
     * @throws SQLException
     *         the SQL exception
     * @throws IOException
     *         Signals that an I/O exception has occurred.
     * @throws InterruptedException
     *         if the method is canceled through the ProgressCallback
     */
    public void generate(ProgressCallback callback, Set<String> contextIds, Set<String> fullyQualifiedTableNames,
            boolean writeFiles) throws SQLException, IOException, InterruptedException {

        if (callback == null) {
            callback = new NullProgressCallback();
        }

        // 清理JAVA文件（存放待生成的数据）
        generatedJavaFiles.clear();

        // 清理XML文件（存放待生成的数据）
        generatedXmlFiles.clear();

        ObjectFactory.reset();
        RootClassInfo.reset();

        // 计算要运行的上下文
        List<Context> contextsToRun;
        if (contextIds == null || contextIds.size() == 0) {
            contextsToRun = configuration.getContexts();
        } else {
            contextsToRun = new ArrayList<Context>();
            for (Context context : configuration.getContexts()) {
                if (contextIds.contains(context.getId())) {
                    contextsToRun.add(context);
                }
            }
        }

        // 如果需要，设置自定义类加载器
        if (configuration.getClassPathEntries().size() > 0) {
            ClassLoader classLoader = getCustomClassloader(configuration.getClassPathEntries());
            ObjectFactory.addExternalClassLoader(classLoader);
        }

        // 开始执行Introspection
        int totalSteps = 0;
        for (Context context : contextsToRun) {
            totalSteps += context.getIntrospectionSteps();
        }
        callback.introspectionStarted(totalSteps);

        for (Context context : contextsToRun) {
            context.introspectTables(callback, warnings, fullyQualifiedTableNames);
        }

        // 开始执行生成器
        totalSteps = 0;
        for (Context context : contextsToRun) {
            totalSteps += context.getGenerationSteps();
        }
        callback.generationStarted(totalSteps);

        // 默认添加自己定义的插件（不用显示的在xml中配置插件，自动默认加载）
        // PluginConfiguration pluginExtend = new PluginConfiguration();
        // pluginExtend.setConfigurationType(JavaTypePlugin.class.getName());

        List<GeneratedJavaFile> adds = new ArrayList<>();
        List<GeneratedJavaFile> removes = new ArrayList<>();

        for (Context context : contextsToRun) {

            // 添加自定义的 插件
            // context.addPluginConfiguration(pluginExtend);

            // 生成待生成的文件
            context.generateFiles(callback, generatedJavaFiles, generatedXmlFiles, warnings);

            // ==============================================================================
            // 针对: 根据最新配置生成新的Mapper文件，判断原Mapper是否存在
            // 方式：如果路径中没有文档，生成新的文档（继承指定接口），存在则跳过
            // ==============================================================================

            Set<String> domainMapperClassNames = context.getTableConfigurations().stream().map(e -> {
                return e.getDomainObjectName() + BIConstant.MAPPER;
            }).collect(Collectors.toSet());

            for (GeneratedJavaFile javaFile : generatedJavaFiles) {

                CompilationUnit unit = javaFile.getCompilationUnit();
                FullyQualifiedJavaType baseModelJavaType = unit.getType();
                String shortName = baseModelJavaType.getShortName();

                // 只针对于单表产生的Mapper文件进行处理
                if (domainMapperClassNames.contains(shortName)) {
                    try {

                        String newMapperTargetPackage = baseModelJavaType.getPackageName();
                        String daoTargetDir = javaFile.getTargetProject();

                        // --------------------------------------------------------------------------
                        // 单表Mapper.java文件 如果存在，会根据最新配置需要生成的文件，和原来存在的合并

                        // 单表Mapper.java文件对象
                        File file = new File(shellCallback.getDirectory(daoTargetDir, newMapperTargetPackage),
                                javaFile.getFileName());

                        if (file.exists()) {
                            // 存在
                            // 读文件，进行合并操作 [通过正则 进行合并文件]
                            String filcode = readFile(file);
                            // System.out.println("====================================原来=============");
                            // System.out.println(filcode);

                            // 在支持的3个接口中进行比较
                            Set<String> all = new HashSet<>();
                            all.add(BIConstant.ExtendInsertBatchClass);
                            all.add(BIConstant.ExtendInsertIfAbsentClass);
                            all.add(BIConstant.ExtendSelectOptionClass);
                            all.add(BIConstant.ExtendUpdateByIdClass);

                            // 旧有接口
                            Set<String> temp1 = new HashSet<>();
                            Set<String> olds = RegexUtil.getOldExtends(filcode);
                            for (String old : olds) {
                                if (all.contains(old)) {
                                    temp1.add(old);
                                }
                            }
                            olds = temp1;

                            // 新的接口
                            Set<String> temp2 = new HashSet<String>();
                            Set<String> news = unit.getSuperInterfaceTypes().stream()
                                    .map(e -> e.getShortName().replaceAll(RegexUtil.regxFx, ""))
                                    .collect(Collectors.toSet());
                            for (String n : news) {
                                if (all.contains(n)) {
                                    temp2.add(n);
                                }
                            }
                            news = temp2;

                            // old 需要移除的结果result
                            Set<String> result = new HashSet<>();
                            result.clear();
                            result.addAll(olds);
                            result.removeAll(news);
                            // System.out.println("old 需要移除：" + result);
                            filcode = RegexUtil.remove(filcode, result);

                            // System.out.println("====================================移除后=============");
                            // System.out.println(filcode);

                            // 需要新增的结果result
                            result.clear();
                            result.addAll(news);
                            result.removeAll(olds);
                            // System.out.println("old 需要新增：" + result);

                            Set<String> gex = unit.getSuperInterfaceTypes().stream().map(e -> e.getShortName())
                                    .collect(Collectors.toSet());
                            String baseInterfaceName = context.getJdbcConnectionConfiguration()
                                    .getProperty(BIConstant.SCI);
                            ExtendUtil eUtil = new ExtendUtil(
                                    baseInterfaceName.substring(0, baseInterfaceName.lastIndexOf(".")));
                            filcode = RegexUtil.add(filcode, result, gex, eUtil);

                            // System.out.println("====================================新增后=============");
                            // System.out.println(filcode);

                            // 添加 移除标记，该Mapper.java文件不会自动生成
                            removes.add(javaFile);
                            // 手动替代写入
                            writeFile(file, filcode, "UTF-8");
                        } else {
                            // 文件不存在，表示要新生成，跳过逻辑
                            break;
                        }

                        // --------------------------------------------------------------------------

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (shortName.equals("AbstractExample")) {
                    //处理 AbstractExample 类的特殊生成
                    try {
                        String newMapperTargetPackage = baseModelJavaType.getPackageName();
                        String daoTargetDir = javaFile.getTargetProject();
                        File file = new File(shellCallback.getDirectory(daoTargetDir, newMapperTargetPackage),
                                javaFile.getFileName());

                        StringBuffer buffer = new StringBuffer();
                        buffer.append("package ").append(newMapperTargetPackage).append("; \n");

                        InputStream resourceAsStream = this.getClass().getClassLoader()
                                .getResourceAsStream("AbstractExample.template");
                        BufferedReader in = new BufferedReader(new InputStreamReader(resourceAsStream));
                        String line = "";
                        while ((line = in.readLine()) != null) {
                            buffer.append(line).append("\n");

                        }
                        String input = buffer.toString();

                        writeFile(file, input, "UTF-8");

                        //移除自动写
                        removes.add(javaFile);

                    } catch (ShellException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        for (GeneratedJavaFile f : removes) {
            generatedJavaFiles.remove(f);
        }
        for (GeneratedJavaFile f : adds) {
            generatedJavaFiles.add(f);
        }

        // ========================
        // 功能点：XML 强制覆盖原文件
        // ========================
        for (GeneratedXmlFile f : generatedXmlFiles)
            f.setMergeable(false);

        // ========================
        // 功能点：JAVA 文件覆盖
        // ========================
        if (writeFiles) {
            callback.saveStarted(generatedXmlFiles.size() + generatedJavaFiles.size());

            for (GeneratedXmlFile gxf : generatedXmlFiles) {
                projects.add(gxf.getTargetProject());
                writeGeneratedXmlFile(gxf, callback);
            }

            for (GeneratedJavaFile gjf : generatedJavaFiles) {
                projects.add(gjf.getTargetProject());
                writeGeneratedJavaFile(gjf, callback);
            }

            for (String project : projects) {
                shellCallback.refreshProject(project);
            }
        }

        // ========================
        // 功能点：AbstractExample.java 文件 按模板输出
        // ========================

        callback.done();
    }

    public String readFile(File file) {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString).append("\n");
            }
            reader.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void writeGeneratedJavaFile(GeneratedJavaFile gjf, ProgressCallback callback)
            throws InterruptedException, IOException {
        File targetFile;
        String source;
        try {
            File directory = shellCallback.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage());
            targetFile = new File(directory, gjf.getFileName());
            if (targetFile.exists()) {
                if (shellCallback.isMergeSupported()) {
                    source = shellCallback.mergeJavaFile(gjf.getFormattedContent(), targetFile,
                            MergeConstants.OLD_ELEMENT_TAGS, gjf.getFileEncoding());
                } else if (shellCallback.isOverwriteEnabled()) {
                    source = gjf.getFormattedContent();
                    warnings.add(getString("Warning.11", targetFile.getAbsolutePath()));
                } else {
                    source = gjf.getFormattedContent();
                    targetFile = getUniqueFileName(directory, gjf.getFileName());
                    warnings.add(getString("Warning.2", targetFile.getAbsolutePath()));
                }
            } else {
                source = gjf.getFormattedContent();
            }

            callback.checkCancel();
            callback.startTask(getString("Progress.15", targetFile.getName()));
            writeFile(targetFile, source, gjf.getFileEncoding());
        } catch (ShellException e) {
            warnings.add(e.getMessage());
        }
    }

    private void writeGeneratedXmlFile(GeneratedXmlFile gxf, ProgressCallback callback)
            throws InterruptedException, IOException {
        File targetFile;
        String source;
        try {
            File directory = shellCallback.getDirectory(gxf.getTargetProject(), gxf.getTargetPackage());
            targetFile = new File(directory, gxf.getFileName());
            if (targetFile.exists()) {
                if (gxf.isMergeable()) {
                    source = XmlFileMergerJaxp.getMergedSource(gxf, targetFile);
                } else if (shellCallback.isOverwriteEnabled()) {
                    source = gxf.getFormattedContent();
                    warnings.add(getString("Warning.11", targetFile.getAbsolutePath()));
                } else {
                    source = gxf.getFormattedContent();
                    targetFile = getUniqueFileName(directory, gxf.getFileName());
                    warnings.add(getString("Warning.2", targetFile.getAbsolutePath()));
                }
            } else {
                source = gxf.getFormattedContent();
            }

            callback.checkCancel();
            callback.startTask(getString("Progress.15", targetFile.getName()));
            writeFile(targetFile, source, "UTF-8");
        } catch (ShellException e) {
            warnings.add(e.getMessage());
        }
    }

    /**
     * Writes, or overwrites, the contents of the specified file.
     *
     * @param file
     *        the file
     * @param content
     *        the content
     * @param fileEncoding
     *        the file encoding
     * @throws IOException
     *         Signals that an I/O exception has occurred.
     */
    private void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }

    /**
     * Gets the unique file name.
     *
     * @param directory
     *        the directory
     * @param fileName
     *        the file name
     * @return the unique file name
     */
    private File getUniqueFileName(File directory, String fileName) {
        File answer = null;

        // try up to 1000 times to generate a unique file name
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 1000; i++) {
            sb.setLength(0);
            sb.append(fileName);
            sb.append('.');
            sb.append(i);

            File testFile = new File(directory, sb.toString());
            if (!testFile.exists()) {
                answer = testFile;
                break;
            }
        }

        if (answer == null) {
            throw new RuntimeException(getString("RuntimeError.3", directory.getAbsolutePath()));
        }

        return answer;
    }

    /**
     * Returns the list of generated Java files after a call to one of the
     * generate methods. This is useful if you prefer to process the generated
     * files yourself and do not want the generator to write them to disk.
     * 
     * @return the list of generated Java files
     */
    public List<GeneratedJavaFile> getGeneratedJavaFiles() {
        return generatedJavaFiles;
    }

    /**
     * Returns the list of generated XML files after a call to one of the
     * generate methods. This is useful if you prefer to process the generated
     * files yourself and do not want the generator to write them to disk.
     * 
     * @return the list of generated XML files
     */
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        return generatedXmlFiles;
    }
}
