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
 * 类名称：BIInsertSelectiveMethodGenerator <br>
 * 类描述: 复写【超类BaseMapper-8大基本方法】插入不为空的数据  BaseMapper.java 中 insertSelective() 生成  <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:50:35 <br>
 */
public class InsertSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    public InsertSelectiveMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("insertSelective");

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(BIConstant.MODEL);

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        if (context.getPlugins().clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

}
