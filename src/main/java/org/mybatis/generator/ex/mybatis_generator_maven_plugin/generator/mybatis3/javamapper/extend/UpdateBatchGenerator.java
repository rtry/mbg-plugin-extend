/**
 * FileName：UpdateBatchGenerator
 * Version：
 * Date：2021/1/19
 * Copyright 马丁洛克 Corporation 2021 版权所有
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.ExtendUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @className UpdateBatchGenerator
 * @describe 批量更新JAVA类生成器
 * @author rtry
 * @date 2021/1/19 19:24
 */
public class UpdateBatchGenerator extends AbstractJavaClientGenerator {

    private ExtendUtil util;

    public UpdateBatchGenerator(Context context, ExtendUtil util) {
        super(false);
        super.context = context;
        this.util = util;
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {

        CommentGenerator commentGenerator = context.getCommentGenerator();

        // 类名
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                util.getUpdateBatchClassName() + "<" + BIConstant.MODEL + ">");
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        FullyQualifiedJavaType siType = new FullyQualifiedJavaType(util.getExtendClassName());
        interfaze.addSuperInterface(siType);
        interfaze.addImportedType(siType);

        addUpdateBatchMethod(interfaze);

        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(interfaze);

        return answer;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }

    private void addUpdateBatchMethod(Interface interfaze) {
        Method method = new Method();

        //        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);

        method.setName(util.getUpdateBatchMethodName());

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("List<" + BIConstant.MODEL + ">");

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        method.addParameter(new Parameter(parameterType, "records"));

        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedTypes(importedTypes);
        interfaze.addMethod(method);
    }

}
