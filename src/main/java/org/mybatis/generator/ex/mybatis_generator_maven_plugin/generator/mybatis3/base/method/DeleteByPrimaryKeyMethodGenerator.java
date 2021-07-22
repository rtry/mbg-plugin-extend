package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;

/**
 * 类名称：BIDeleteByPrimaryKeyMethodGenerator <br>
 * 类描述:复写【超类BaseMapper-8大基本方法】按主键删除  BaseMapper.java 中 deleteByPrimaryKey() 生成  <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:50:07 <br>
 */
public class DeleteByPrimaryKeyMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName("deleteByPrimaryKey");
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(BIConstant.PK);
        method.addParameter(new Parameter(type, "pk"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

}
