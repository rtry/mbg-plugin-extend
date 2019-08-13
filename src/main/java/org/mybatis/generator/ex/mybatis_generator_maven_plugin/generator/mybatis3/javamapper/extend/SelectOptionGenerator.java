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

public class SelectOptionGenerator extends AbstractJavaClientGenerator {

	private ExtendUtil util;

	public SelectOptionGenerator(Context context, ExtendUtil util) {
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
		StringBuffer sb = new StringBuffer();
		sb.append("<");
		sb.append(BIConstant.MODEL).append(",");
		sb.append(BIConstant.PK).append(" extends Serializable,");
		sb.append(BIConstant.EXAMPLE).append(",");
		sb.append(">");
		// 类名
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(util.getSelectOptionClassName()
				+ sb.toString());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		FullyQualifiedJavaType siType = new FullyQualifiedJavaType(util.getExtendClassName());
		interfaze.addSuperInterface(siType);
		interfaze.addImportedType(siType);
		interfaze.addImportedType(new FullyQualifiedJavaType("java.io.Serializable"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
		interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.MapKey"));

		// 查询指定列到对象中
		addSelectOptionToListMethod(interfaze);

		// 查询指定列到Map中，以主键为KEY
		addSelectOptionToMapNameMethod(interfaze);

		// 查询指定列到一个对象
		addSelectOptionToOneNameMethod(interfaze);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(interfaze);

		return answer;
	}

	private void addSelectOptionToListMethod(Interface interfaze) {
		Method method = new Method();

		method.setReturnType(new FullyQualifiedJavaType("List<T>"));
		method.setVisibility(JavaVisibility.PUBLIC);

		method.setName(util.getSelectOptionToListMethodName());

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		method.addParameter(new Parameter(new FullyQualifiedJavaType("String[]"), "options",
				"@Param(\"options\")"));

		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(BIConstant.EXAMPLE);
		method.addParameter(new Parameter(exampleType, "example", "@Param(\"example\")"));

		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	private void addSelectOptionToMapNameMethod(Interface interfaze) {
		Method method = new Method();

		method.setReturnType(new FullyQualifiedJavaType("Map<PK,T>"));
		method.setVisibility(JavaVisibility.PUBLIC);

		//FIXME  可能主键不会统一叫id
		method.addAnnotation("@MapKey(\"id\")");
		method.setName(util.getSelectOptionToMapMethodName());

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		method.addParameter(new Parameter(new FullyQualifiedJavaType("String[]"), "options",
				"@Param(\"options\")"));

		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(BIConstant.EXAMPLE);
		method.addParameter(new Parameter(exampleType, "example", "@Param(\"example\")"));

		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Map"));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

	private void addSelectOptionToOneNameMethod(Interface interfaze) {
		Method method = new Method();

		method.setReturnType(new FullyQualifiedJavaType("T"));
		method.setVisibility(JavaVisibility.PUBLIC);

		method.setName(util.getSelectOptionToOneMethodName());

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		method.addParameter(new Parameter(new FullyQualifiedJavaType("String[]"), "options",
				"@Param(\"options\")"));

		FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(BIConstant.EXAMPLE);
		method.addParameter(new Parameter(exampleType, "example", "@Param(\"example\")"));

		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Map"));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
