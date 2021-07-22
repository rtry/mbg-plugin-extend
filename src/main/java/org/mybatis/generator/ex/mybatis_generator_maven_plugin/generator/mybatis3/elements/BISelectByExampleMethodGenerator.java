package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

/**
 * 类名称：BISelectByExampleMethodGenerator <br>
 * 类描述:复写【超类BaseMapper-8大基本方法】 按条件查询 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:51:07 <br>
 */
public class BISelectByExampleMethodGenerator extends AbstractJavaMapperMethodGenerator {

    public BISelectByExampleMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        // 返回值
        method.setReturnType(new FullyQualifiedJavaType("List<" + BIConstant.MODEL + ">"));
        // 方法名
        method.setName("selectByExample");
        // 参数
        method.addParameter(new Parameter(new FullyQualifiedJavaType(BIConstant.EXAMPLE), "example"));
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        if (context.getPlugins().clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable)) {
            interfaze.addMethod(method);
        }
    }
}
