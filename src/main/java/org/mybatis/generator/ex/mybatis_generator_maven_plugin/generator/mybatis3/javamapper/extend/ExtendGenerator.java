package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;

public class ExtendGenerator {

	private ExtendUtil util;

	private Context context;

	public ExtendGenerator(ExtendUtil util, Context context) {
		this.util = util;
		this.context = context;
	}

	public List<CompilationUnit> getCompilationUnits() {
		// 类名
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(util.getExtendClassName());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		answer.add(interfaze);

		return answer;
	}

	/**
	 * getExtendMapper 获取扩展超类
	 * @return
	 * @Exception 异常描述
	 */
	public Collection<? extends GeneratedJavaFile> getExtendMapper() {
		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

		List<CompilationUnit> compilationUnits = getCompilationUnits();
		for (CompilationUnit compilationUnit : compilationUnits) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit, context
					.getJavaClientGeneratorConfiguration().getTargetProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
					context.getJavaFormatter());
			answer.add(gjf);
		}
		return answer;
	}

	/**
	 * getInsertBatchMapper 获取批量插入Mapper
	 * @return
	 * @Exception 异常描述
	 */
	public Collection<? extends GeneratedJavaFile> getInsertBatchMapper() {
		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

		InsertBatchGenerator ibj = new InsertBatchGenerator(context, util);
		List<CompilationUnit> compilationUnits = ibj.getCompilationUnits();
		for (CompilationUnit compilationUnit : compilationUnits) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit, context
					.getJavaClientGeneratorConfiguration().getTargetProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING),
					context.getJavaFormatter());
			answer.add(gjf);
		}
		return answer;
	}
}
