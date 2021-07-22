package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method;

import java.util.HashSet;
import java.util.Set;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;

/**
 * 类名称：BIUpdateByExampleSelectiveMethodGenerator <br>
 * 类描述: 复写【超类BaseMapper-8大基本方法】更新不为空的数据  BaseMapper.java 中 updateByExampleSelective() 生成  <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:51:42 <br>
 */
public class UpdateByExampleSelectiveMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName("updateByExampleSelective");

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(BIConstant.MODEL);
        method.addParameter(new Parameter(parameterType, "record", "@Param(\"record\")"));

        FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(BIConstant.EXAMPLE);
        method.addParameter(new Parameter(exampleType, "example", "@Param(\"example\")"));

        Set<FullyQualifiedJavaType> importedTypes = new HashSet<FullyQualifiedJavaType>();
        importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        if (context.getPlugins().clientUpdateByExampleSelectiveMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}
