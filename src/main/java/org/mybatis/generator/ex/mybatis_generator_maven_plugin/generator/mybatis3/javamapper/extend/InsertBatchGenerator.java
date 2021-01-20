package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

public class InsertBatchGenerator extends AbstractJavaClientGenerator {

    private ExtendUtil util;

    public InsertBatchGenerator(Context context, ExtendUtil util) {
        super(false);
        super.context = context;
        this.util = util;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {

        CommentGenerator commentGenerator = context.getCommentGenerator();

        // 类名
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                util.getInsertBatchClassName() + "<" + BIConstant.MODEL + ">");
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        FullyQualifiedJavaType siType = new FullyQualifiedJavaType(util.getExtendClassName());
        interfaze.addSuperInterface(siType);
        interfaze.addImportedType(siType);

        //批量插入所有字段
        addInsertBatchMethod(interfaze);
        //批量插入非空字段
        addInsertBatchSelectMethod(interfaze);

        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(interfaze);

        return answer;
    }

    private void addInsertBatchMethod(Interface interfaze) {
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(util.getInsertBatchMethodName());

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("List<" + BIConstant.MODEL + ">");

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        method.addParameter(new Parameter(parameterType, "records"));

        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

    private void addInsertBatchSelectMethod(Interface interfaze) {
        Method method = new Method();

//        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(util.getInsertBatchSelectMethodName());

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("List<" + BIConstant.MODEL + ">");

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        method.addParameter(new Parameter(parameterType, "records"));

        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

}
