package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper;

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
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BICountByExampleMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BIDeleteByExampleMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BIDeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BIInsertSelectiveMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BISelectByExampleMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BISelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BIUpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements.BIUpdateByPrimaryKeySelectiveMethodGenerator;

public class BaseInterfaceGenerator extends AbstractJavaClientGenerator {

	private String baseInterfaceName;

	public BaseInterfaceGenerator(boolean requiresXMLGenerator, Context context) {
		super(false);
		super.context = context;
		baseInterfaceName = context.getJavaClientGeneratorConfiguration().getProperty(
				"supportCustomInterface");
		baseInterfaceName = baseInterfaceName.concat("<T, PK extends Serializable, E>");
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		return new XMLMapperGenerator();
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {

		CommentGenerator commentGenerator = context.getCommentGenerator();

		// 类名
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(baseInterfaceName);
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		// ===========================================
		// 标准接口方法 8个（默认 单 主键 存在）
		// ===========================================
		// 计算总数
		addCountByExampleMethod(interfaze);

		// 删除，按需求
		addDeleteByExampleMethod(interfaze);
		// 删除，按主键
		addDeleteByPrimaryKeyMethod(interfaze);

		// 插入，按需求
		addInsertSelectiveMethod(interfaze);

		// 查询，按需求
		addSelectByExampleMethod(interfaze);
		// 查询，按主键
		addSelectByPrimaryKeyMethod(interfaze);

		// 更新，按需求
		addUpdateByExampleSelectiveMethod(interfaze);
		// 更新，按主键
		addUpdateByPrimaryKeySelectiveMethod(interfaze);
		// ===========================================

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(interfaze);

		return answer;
	}

	protected void addCountByExampleMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BICountByExampleMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addDeleteByExampleMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BIDeleteByExampleMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BIDeleteByPrimaryKeyMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addInsertMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addInsertSelectiveMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BIInsertSelectiveMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addSelectByExampleMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BISelectByExampleMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BISelectByPrimaryKeyMethodGenerator();
		initializeAndExecuteGenerator(methodGenerator, interfaze);
	}

	protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
		AbstractJavaMapperMethodGenerator methodGenerator = new BIUpdateByExampleSelectiveMethodGenerator();
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
		AbstractJavaMapperMethodGenerator methodGenerator = new BIUpdateByPrimaryKeySelectiveMethodGenerator();
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
		it.setExampleType(baseInterfaceName);
		methodGenerator.setIntrospectedTable(it);
		methodGenerator.addInterfaceElements(interfaze);
	}
}
