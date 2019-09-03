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
 * 类名称：BIDeleteByExampleMethodGenerator <br>
 * 类描述: 复写【超类BaseMapper-8大基本方法】按条件删除 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:49:42 <br>
 * 备注:
 * @version
 * @see
 */
public class BIDeleteByExampleMethodGenerator extends AbstractJavaMapperMethodGenerator {

	public BIDeleteByExampleMethodGenerator() {
		super();
	}

	@Override
	public void addInterfaceElements(Interface interfaze) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(BIConstant.EXAMPLE);
		importedTypes.add(type);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("deleteByExample");
		method.addParameter(new Parameter(type, "example"));

		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		addMapperAnnotations(method);

		if (context.getPlugins().clientDeleteByExampleMethodGenerated(method, interfaze,
				introspectedTable)) {
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
