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

public class InsertIfAbsentGenerator extends AbstractJavaClientGenerator {

	private ExtendUtil util;

	public InsertIfAbsentGenerator(Context context, ExtendUtil util) {
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

		// 类名
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(util.getIfAbsentClassName() + "<"
				+ BIConstant.MODEL + ">");
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		FullyQualifiedJavaType siType = new FullyQualifiedJavaType(util.getExtendClassName());
		interfaze.addSuperInterface(siType);
		interfaze.addImportedType(siType);

		addInsertIfAbsentMethod(interfaze);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(interfaze);

		return answer;
	}

	private void addInsertIfAbsentMethod(Interface interfaze) {
		Method method = new Method();

		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setVisibility(JavaVisibility.PUBLIC);

		method.setName(util.getInsertIfAbsentMethodName());

		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(BIConstant.MODEL);

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		importedTypes.add(parameterType);
		method.addParameter(new Parameter(parameterType, "record"));

		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedTypes(importedTypes);
		interfaze.addMethod(method);
	}

}
