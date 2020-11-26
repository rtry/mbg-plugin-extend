/**
 * FileName：GeneratorApplication
 * Version：
 * Date：2020/11/25
 * Copyright 马丁洛克 Corporation 2020 版权所有
 */
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
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.Config;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.dto.DataConvertSuper;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.ui.MainUI;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyBatisGeneratorEx;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyDefaultCommentGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.MyJavaTypeResolverConfiguration;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.util.ConfigConvertUtil;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.util.DataConvertImpl;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author panxw
 * @className GeneratorApplication
 * @describe  该程序用来生成测试所需的源文件
 * @date 2020/11/25 10:50
 */
public class GeneratorApplication {

    private static String sLine = "------------------------------------------------------------------------";

    private static String bLine = "========================================================================";

    private static Log log;

    private static String configName = "generatorConfig.json";

    //路径选择
    Set<String> sources = new HashSet<>();

    //项目路径
    String projectPath;

    boolean isTest;

    /**
     * GeneratorApplication 构造方法， 
     * @author panxw 
     */
    public GeneratorApplication() {

        log = new SystemStreamLog();

        //配置文件
        URL resource = GeneratorApplication.class.getClassLoader().getResource(configName);
        String path = resource.getPath();

        projectPath = path.substring(1, path.indexOf("/target") + 1);

        isTest = path.indexOf("/test-classes") != -1;

        if (isTest) {
            sources.add("src\\test\\java");
            sources.add("src\\test\\resources");
        } else {
            sources.add("src\\main\\java");
            sources.add("src\\main\\resources");
        }

    }

    public static void main(String[] args) {

        //先手动调用插件，生成xml, java 等文件
        GeneratorApplication generatorApplication = new GeneratorApplication();
        generatorApplication.mgePlugin();

    }

    private void mgePlugin() {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        try {

            log.info(bLine);
            log.info("欢迎切换 mybatis.generator 扩展插件.");
            log.info(bLine);
            log.info("加载自定义扩展：可视化配置");

            String file = projectPath + "src\\test\\resources\\" + configName;

            File cff = new File(file);
            ConfigConvertUtil configConvertUtil = new ConfigConvertUtil(cff, projectPath);

            Configuration config = null;
            // 启动配置
            DataConvertSuper dcs = new DataConvertImpl(configConvertUtil);

            CountDownLatch cdl = new CountDownLatch(1);
            MainUI.begin(dcs, cdl, sources);

            try {
                cdl.await();
                // 用户是否自动退出
                if (MainUI.getStartState() == -1) {
                    log.info("不想继续了? 下次我还在这里, Bye!!!!");
                    return;
                }
                // 自定义配置操作已经完成，转换数据
                Config selfConfig = dcs.tearViewDate();
                config = configConvertUtil.self2me(selfConfig, projectPath);
                // 自定义的配置需要保持数据文件中.
                configConvertUtil.writeJSONToFile(selfConfig);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (config == null) {
                log.info("实例化自定义配置失败....请联系管理员");
            }

            for (Context t : config.getContexts()) {
                log.info(sLine);

                try {
                    Field f = t.getClass().getDeclaredField("commentGenerator");
                    f.setAccessible(true);

                    log.info("加载自定义扩展：文档注释");
                    log.info(sLine);
                    MyDefaultCommentGenerator myComment = new MyDefaultCommentGenerator();
                    myComment.setLog(log);
                    f.set(t, myComment);

                    log.info("加载自定义扩展：类型转换");
                    log.info(sLine);

                    //根据配置显示是否需要转换
                    if (t.getJdbcConnectionConfiguration().getProperty(BIConstant.FILE_TINY2INT).equals("true")) {
                        MyJavaTypeResolverConfiguration myType = new MyJavaTypeResolverConfiguration();
                        t.setJavaTypeResolverConfiguration(myType);
                    } else {
                        t.setJavaTypeResolverConfiguration(new JavaTypeResolverConfiguration());
                    }

                } catch (NoSuchFieldException | SecurityException e) {
                    e.printStackTrace();
                }
            }
            ShellCallback callback = new DefaultShellCallback(overwrite);

            log.info("加载自定义扩展：mapper.xml 文件强制覆盖");
            log.info(sLine);

            // 自定义插件实现
            MyBatisGeneratorEx myBatisGenerator = new MyBatisGeneratorEx(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("生成完成..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
