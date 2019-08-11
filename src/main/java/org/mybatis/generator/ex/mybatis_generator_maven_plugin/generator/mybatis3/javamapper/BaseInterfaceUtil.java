/**    
 * 文件名：BaseInterfaceUtil.java    
 *    
 * 版本信息：    
 * 日期：2019年8月10日    
 * Copyright Felicity Corporation 2019 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * 类名称：BaseInterfaceUtil <br>
 * 类描述: <br>
 * 创建人：felicity <br>
 * 创建时间：2019年8月10日 下午3:33:57 <br>
 * 备注:
 * @version
 * @see
 */
public class BaseInterfaceUtil {

	private Context context;

	public BaseInterfaceUtil(Context context) {
		this.context = context;
	}

	public Collection<? extends GeneratedJavaFile> getGenerated() {
		List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

		// A.生成 BaseInterface Mapper
		BaseInterfaceGenerator javaGenerator = new BaseInterfaceGenerator(false,context);
		List<CompilationUnit> compilationUnits = javaGenerator.getCompilationUnits();
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
