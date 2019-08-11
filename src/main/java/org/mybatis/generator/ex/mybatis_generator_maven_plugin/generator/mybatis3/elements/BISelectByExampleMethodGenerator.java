/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class BISelectByExampleMethodGenerator extends AbstractJavaMapperMethodGenerator {

	public BISelectByExampleMethodGenerator() {
		super();
	}

	@Override
	public void addInterfaceElements(Interface interfaze) {

		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		// 返回值
		method.setReturnType(new FullyQualifiedJavaType("List<" + BIConstant.MODEL + ">"));
		// 方法名
		method.setName("selectByExample");
		// 参数
		method.addParameter(new Parameter(new FullyQualifiedJavaType(BIConstant.EXAMPLE), "example"));
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

		interfaze.addImportedType(new FullyQualifiedJavaType("java.util.List"));
		if (context.getPlugins().clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable)) {
			addExtraImports(interfaze);
			interfaze.addMethod(method);
		}
	}

	public void addMapperAnnotations(Interface interfaze, Method method) {
	}

	public void addExtraImports(Interface interfaze) {
	}
}
