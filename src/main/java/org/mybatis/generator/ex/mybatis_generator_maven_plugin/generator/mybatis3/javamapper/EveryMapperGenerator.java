package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;

public class EveryMapperGenerator extends AbstractJavaClientGenerator {

	public EveryMapperGenerator(Context context, IntrospectedTable introspectedTable,
			boolean requiresXMLGenerator) {
		super(requiresXMLGenerator);
		this.introspectedTable = introspectedTable;
		this.context = context;
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		CommentGenerator commentGenerator = context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(
				introspectedTable.getMyBatis3JavaMapperType());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		String rootInterface = introspectedTable
				.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface)) {
			rootInterface = context.getJavaClientGeneratorConfiguration().getProperty(
					PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		String mapperInterfaceClass = context.getJavaClientGeneratorConfiguration().getProperty(
				"supportCustomInterface");
		FullyQualifiedJavaType si = new FullyQualifiedJavaType(mapperInterfaceClass);
		String sampleClass = mapperInterfaceClass.substring(
				mapperInterfaceClass.lastIndexOf(".") + 1, mapperInterfaceClass.length());

		FullyQualifiedJavaType pkType = null;

		List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			pkType = introspectedColumn.getFullyQualifiedJavaType();
			interfaze.addImportedType(pkType);
		}

		interfaze
				.addImportedType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		interfaze.addImportedType(new FullyQualifiedJavaType(introspectedTable.getExampleType()));

		String temp = sampleClass.concat("<" + introspectedTable.getBaseRecordType() + ", "
				+ pkType.getFullyQualifiedName() + ", " + introspectedTable.getExampleType() + ">");

		FullyQualifiedJavaType siType = new FullyQualifiedJavaType(temp);
		interfaze.addSuperInterface(siType);
		interfaze.addImportedType(si);

		if (stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			interfaze.addSuperInterface(fqjt);
			interfaze.addImportedType(fqjt);
		}

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (context.getPlugins().clientGenerated(interfaze, null, introspectedTable)) {
			answer.add(interfaze);
		}

		List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
		if (extraCompilationUnits != null) {
			answer.addAll(extraCompilationUnits);
		}

		return answer;
	}

	public List<CompilationUnit> getExtraCompilationUnits() {
		return null;
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		return new XMLMapperGenerator();
	}
}
