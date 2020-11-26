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
 * 类名称：BICountByExampleMethodGenerator <br>
 * 类描述: 复写【超类BaseMapper-8大基本方法】计算总数 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:45:52 <br>
 * 备注:
 * @version
 * @see
 */
public class BICountByExampleMethodGenerator extends AbstractJavaMapperMethodGenerator {

	public BICountByExampleMethodGenerator() {
		super();
	}

	/**
	 * Mapper.java，方法的生成
	 * */
	@Override
	public void addInterfaceElements(Interface interfaze) {
		FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(BIConstant.EXAMPLE);

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		importedTypes.add(fqjt);

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType("java.lang.Long"));
		method.setName("countByExample");
		method.addParameter(new Parameter(fqjt, "example"));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
		addMapperAnnotations(method);

		if (context.getPlugins().clientCountByExampleMethodGenerated(method, interfaze,
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
