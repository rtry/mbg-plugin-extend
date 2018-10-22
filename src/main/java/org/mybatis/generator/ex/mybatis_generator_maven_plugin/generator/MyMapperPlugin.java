/**
 * Project Name:mybatis-generator-maven-plugin
 * File Name:MyMapperPlugin.java
 * Package Name:org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator
 * Date:2018年10月22日
 * Copyright (c) 2018, Felicity All Rights Reserved.
 *
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * ClassName: MyMapperPlugin <br>
 * Function:   <br>
 * date: 2018年10月22日  <br>
 *
 * @author Felicity
 * @version 
 * @since JDK 1.8
 */
public class MyMapperPlugin extends PluginAdapter {
//	https://blog.csdn.net/zsy3313422/article/details/53190613/

    private static final String DEFAULT_DAO_SUPER_CLASS = "xyz.rtry.felicity.mapper.base.BaseMapper";
    private static final String DEFAULT_EXPAND_DAO_SUPER_CLASS = "xyz.rtry.felicity.mapper.base.BaseExpandMapper";
    private String daoTargetDir;
    private String daoTargetPackage;

    private String daoSuperClass;

	   // 扩展
    private String expandDaoTargetPackage;
    private String expandDaoSuperClass;
    
    private ShellCallback shellCallback = null;

    public MyMapperPlugin() {
        shellCallback = new DefaultShellCallback(false);
    }

	@Override
	public boolean validate(List<String> warnings) {
	     daoTargetDir = properties.getProperty("targetProject");
	        boolean valid = StringUtils.isNotBlank(daoTargetDir);

	        daoTargetPackage = properties.getProperty("targetPackage");
	        boolean valid2 = StringUtils.isNotBlank(daoTargetPackage);

	        daoSuperClass = properties.getProperty("daoSuperClass");
	        if (StringUtils.isBlank(daoSuperClass)) {
	            daoSuperClass = DEFAULT_DAO_SUPER_CLASS;
	        }

	        expandDaoTargetPackage = properties.getProperty("expandTargetPackage");
	        expandDaoSuperClass = properties.getProperty("expandDaoSuperClass");
	        if (StringUtils.isNotBlank(expandDaoSuperClass)) {
	            expandDaoSuperClass = DEFAULT_EXPAND_DAO_SUPER_CLASS;
	        }
	        return valid && valid2;
	}
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "selectAll"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
        select.addElement(new TextElement(" select * from "+ introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement parentElement = document.getRootElement();
        parentElement.addElement(select);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        JavaFormatter javaFormatter = context.getJavaFormatter();
        List<GeneratedJavaFile> mapperJavaFiles = new ArrayList<GeneratedJavaFile>();
        for (GeneratedJavaFile javaFile : introspectedTable.getGeneratedJavaFiles()) {
            CompilationUnit unit = javaFile.getCompilationUnit();
            FullyQualifiedJavaType baseModelJavaType = unit.getType();

            String shortName = baseModelJavaType.getShortName();

            GeneratedJavaFile mapperJavafile = null;

            if (shortName.endsWith("Mapper")) { // 扩展Mapper
                if (StringUtils.isNotBlank(expandDaoTargetPackage)) {
                    Interface mapperInterface = new Interface(
                            expandDaoTargetPackage + "." + shortName.replace("Mapper", "ExpandMapper"));
                    mapperInterface.setVisibility(JavaVisibility.PUBLIC);
                    mapperInterface.addJavaDocLine("/**");
                    mapperInterface.addJavaDocLine(" * " + shortName + "扩展");
                    mapperInterface.addJavaDocLine(" */");

                    FullyQualifiedJavaType daoSuperType = new FullyQualifiedJavaType(expandDaoSuperClass);
                    mapperInterface.addImportedType(daoSuperType);
                    mapperInterface.addSuperInterface(daoSuperType);

                    mapperJavafile = new GeneratedJavaFile(mapperInterface, daoTargetDir, javaFormatter);
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
