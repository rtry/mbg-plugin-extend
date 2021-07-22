/**
 * Project Name:mybatis-generator-maven-plugin
 * File Name:MyMapperPlugin.java
 * Package Name:org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator
 * Date:2018年10月22日
 * Copyright (c) 2018, Felicity All Rights Reserved.
 *
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.comment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * ClassName: MyMapperPlugin <br>
 * Function: 自定义插件-继承 <br>
 * date: 2018年10月22日 <br>
 * @author Felicity
 * @since JDK 1.8
 */
public class MyMapperPlugin extends PluginAdapter {

    private static final String DEFAULT_DAO_SUPER_CLASS = "xyz.rtry.felicity.mapper.base.BaseMapper";

    private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "xyz.rtry.felicity.mapper.base.BaseExpandMapper";

    private String daoTargetDir;

    private String daoTargetPackage;

    private String daoSuperClass;

    // private String expandDaoTargetPackage;

    /**
     * 扩展
     */
    private String expandDaoSuperClass;

    private ShellCallback shellCallback = null;

    public MyMapperPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }

    protected boolean stringIsBlank(String str) {
        return str == null || str.trim().equals("");
    }

    @Override
    public boolean validate(List<String> warnings) {
        daoTargetDir = properties.getProperty("targetProject");
        boolean valid = stringIsBlank(daoTargetDir);

        daoTargetPackage = properties.getProperty("targetPackage");
        boolean valid2 = stringIsBlank(daoTargetPackage);

        daoSuperClass = properties.getProperty("daoSuperClass");
        if (stringIsBlank(daoSuperClass)) {
            daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
        }

        // expandDaoTargetPackage =
        // properties.getProperty("expandTargetPackage");
        expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
        if (stringIsBlank(expandDaoSuperClass)) {
            expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
        }
        return valid && valid2;
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {

            // mapper 是否需要继承接口
            String mapperInterfaceClass = context.getJavaClientGeneratorConfiguration().getImplementationPackage();

            if (stringIsBlank(mapperInterfaceClass)) {
                introspectedTable.getGeneratedJavaFiles().remove(javaFile);
            }

            CompilationUnit unit = javaFile.getCompilationUnit();
            FullyQualifiedJavaType baseModelJavaType = unit.getType();

            String shortName = baseModelJavaType.getShortName();

            GeneratedJavaFile mapperJavafile = null;

            String newMapperTargetDir = javaFile.getTargetProject();
            // int lastIndex = mapperInterfaceClass.lastIndexOf(".");
            // String newMapperTargetPackage = mapperInterfaceClass.substring(0,
            // lastIndex);
            String newMapperTargetPackage = baseModelJavaType.getPackageName();
            // daoTargetPackage = baseModelJavaType.getPackageName();
            if (shortName.endsWith("Mapper")) {
                if (!stringIsBlank(newMapperTargetPackage)) {
                    Interface mapperInterface = new Interface(
                            newMapperTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
                    mapperInterface.setVisibility(JavaVisibility.PUBLIC);
                    mapperInterface.addJavaDocLine("/**");
                    mapperInterface.addJavaDocLine(" * " + shortName + " 继承标准接口");
                    mapperInterface.addJavaDocLine(" */");

                    FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
                    mapperInterface.addImportedType(daoSuperType);
                    mapperInterface.addSuperInterface(daoSuperType);

                    mapperJavafile = new GeneratedJavaFile(mapperInterface, newMapperTargetDir, javaFormatter);
                    try {
                        File mapperDir = shellCallback.getDirectory(daoTargetDir, daoTargetPackage);
                        File mapperFile = new File(mapperDir, mapperJavafile.getFileName());
                        // 文件不存在
                        if (!mapperFile.exists()) {
                            mapperJavaFiles.add(mapperJavafile);
                        }
                    } catch (ShellException e) {
                        e.printStackTrace();
                    }
                }
            } else if (!shortName.endsWith("Example")) { // CRUD Mapper
                Interface mapperInterface = new Interface(daoTargetPackage + "." + shortName + "Mapper");

                mapperInterface.setVisibility(JavaVisibility.PUBLIC);
                mapperInterface.addJavaDocLine("/**");
                mapperInterface.addJavaDocLine(" * 由MyBatis Generator工具自动生成，请不要手动修改");
                mapperInterface.addJavaDocLine(" */");

                FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(daoSuperClass);
                // 添加泛型支持
                daoSuperType.addTypeArgument(baseModelJavaType);
                mapperInterface.addImportedType(baseModelJavaType);
                mapperInterface.addImportedType(daoSuperType);
                mapperInterface.addSuperInterface(daoSuperType);

                mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
                mapperJavaFiles.add(mapperJavafile);

            }
        }
        return mapperJavaFiles;
    }
}
