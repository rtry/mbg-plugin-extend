package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;

public class BaseInterfaceGenerator extends AbstractJavaClientGenerator {

	public BaseInterfaceGenerator(boolean requiresXMLGenerator, Context context) {
		super(false);
		super.context = context;
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		return new XMLMapperGenerator();
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {

		CommentGenerator commentGenerator = context.getCommentGenerator();

		// 类名
		String baseInterfaceName = context.getJavaClientGeneratorConfiguration().getProperty(
				"supportCustomInterface");
		baseInterfaceName.concat("<T, PK extends Serializable, E>");
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(baseInterfaceName);
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		// ===========================================
		// 标准接口方法
		// ===========================================

		// 计算总数
		addCountByExampleMethod(interfaze);

		// 按条件物理删除
		addDeleteByExampleMethod(interfaze);
		// 按主键物理删除
		addDeleteByPrimaryKeyMethod(interfaze);

		// 非空插入
		addInsertSelectiveMethod(interfaze);

		// 查询，按需求
		addSelectByExampleWithoutBLOBsMethod(interfaze);
		// 查询，按主键
		addSelectByPrimaryKeyMethod(interfaze);

		addUpdateByExampleSelectiveMethod(interfaze);
		addUpdateByExampleWithoutBLOBsMethod(interfaze);
		addUpdateByPrimaryKeySelectiveMethod(interfaze);
		addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);

		// ===========================================

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(interfaze);

		return answer;
	}

	protected void addCountByExampleMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addDeleteByExampleMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator(
				false);
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addInsertMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addInsertSelectiveMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addSelectByExampleWithoutBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithoutBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator(
				false);
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByExampleWithBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByExampleWithoutBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator,
			Interface interfaze) {
		methodGenerator.setContext(context);
		IntrospectedTableMyBatis3SimpleImpl it = new IntrospectedTableMyBatis3SimpleImpl();
		methodGenerator.setIntrospectedTable(it);
//		methodGenerator.setProgressCallback(progressCallback);
//		methodGenerator.setWarnings(warnings);
		methodGenerator.addInterfaceElements(interfaze);
	}
}
