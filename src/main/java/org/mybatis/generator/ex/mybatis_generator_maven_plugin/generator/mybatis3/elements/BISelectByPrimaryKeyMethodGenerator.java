package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

/**
 * 类名称：BISelectByPrimaryKeyMethodGenerator <br>
 * 类描述: 复写【超类BaseMapper-8大基本方法】按主键查询 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:51:25 <br>
 * 备注:
 * @version
 * @see
 */
public class BISelectByPrimaryKeyMethodGenerator extends AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);

		// 返回参数
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(BIConstant.MODEL);
		method.setReturnType(returnType);
		importedTypes.add(returnType);

		// 方法名称
		method.setName("selectByPrimaryKey");

		//
		FullyQualifiedJavaType PKType = new FullyQualifiedJavaType(BIConstant.PK);
		method.addParameter(new Parameter(PKType, "id"));

		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze,
				introspectedTable)) {
			addExtraImports(interfaze);
			interfaze.addImportedTypes(importedTypes);
			interfaze.addMethod(method);
		}
	}

	public void addMapperAnnotations(Interface interfaze, Method method) {
	}

	public void addExtraImports(Interface interfaze) {
	}
}
