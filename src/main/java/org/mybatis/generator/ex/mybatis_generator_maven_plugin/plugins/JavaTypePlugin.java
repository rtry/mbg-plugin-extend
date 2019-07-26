/**    
 * 文件名：JavaTypePlugin.java    
 *    
 * 版本信息：    
 * 日期：2019年7月26日    
 * Copyright Felicity Corporation 2019 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.plugins;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 类名称：JavaTypePlugin <br>
 * 类描述: 数据库与Java数据类型转换<br>
 * 创建人：felicity <br>
 * 创建时间：2019年7月26日 下午5:51:23 <br>
 * 备注:
 * @version
 * @see
 */
public class JavaTypePlugin extends PluginAdapter {

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	// 调整字段的生成逻辑
	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable,
			ModelClassType modelClassType) {

		System.out.println(introspectedColumn);
		
		// 功能1 tinyint( >2 )数据（Byte）调整为（Integer）类型
		String typeShortName = field.getType().getShortName();
		String byteSimpleName = Byte.class.getSimpleName();
		if (typeShortName.equals(byteSimpleName)) {
			field.setType(new FullyQualifiedJavaType(Integer.class.getName()));
		}
		
		return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
				introspectedTable, modelClassType);
	}

}
