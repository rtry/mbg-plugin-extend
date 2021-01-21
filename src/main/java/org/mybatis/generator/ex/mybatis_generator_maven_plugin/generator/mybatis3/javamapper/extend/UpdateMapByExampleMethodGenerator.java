package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import java.util.HashSet;
import java.util.Set;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

import static org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant.ExtendUpdateMapByExample;

/**
 * 类名称：UpdateMapByExampleMethodGenerator <br>
 * 类描述: 根据Example 更新map中的所有属性 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:51:42 <br>
 * 备注:
 */
public class UpdateMapByExampleMethodGenerator extends AbstractJavaMapperMethodGenerator {

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(ExtendUpdateMapByExample);

        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("Map");
        method.addParameter(new Parameter(parameterType, "map", "@Param(\"map\")"));

        FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(BIConstant.EXAMPLE);
        method.addParameter(new Parameter(exampleType, "example", "@Param(\"example\")"));

        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();
        importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        importedTypes.add(new FullyQualifiedJavaType("java.util.Map"));

        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

        addMapperAnnotations(method);

        if (context.getPlugins().clientUpdateByExampleSelectiveMethodGenerated(method, interfaze, introspectedTable)) {
            addExtraImports(interfaze);
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Method method) {
    }

    public void addExtraImports(Interface interfaze) {
    }
}
