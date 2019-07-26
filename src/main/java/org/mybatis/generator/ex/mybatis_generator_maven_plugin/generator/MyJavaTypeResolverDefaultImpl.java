/**    
 * 文件名：MyJavaTypeResolverDefaultImpl.java    
 *    
 * 版本信息：    
 * 日期：2018年4月25日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import java.sql.Types;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl.JdbcTypeInformation;

/**
 * 类名称：MyJavaTypeResolverDefaultImpl <br>
 * 类描述: 自定义类型转换实现<br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月25日 下午2:44:29 <br>
 * 修改人：felicity <br>
 * 修改时间：2018年4月25日 下午2:44:29 <br>
 * 修改备注:
 * @version
 * @see
 */
public class MyJavaTypeResolverDefaultImpl extends JavaTypeResolverDefaultImpl {

	@Override
	public FullyQualifiedJavaType calculateJavaType(IntrospectedColumn introspectedColumn) {
		FullyQualifiedJavaType answer = null;

		// ======================自定义实现开始======================

		// 功能1 tinyint( >2 )数据（Byte）调整为（Integer）类型
		typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(
				Integer.class.getName())));

		// 功能2 int ( >10 ) 数据（Integer）调整为（Long）类型
		JdbcTypeInformation jdbcTypeInformation = typeMap.get(introspectedColumn.getJdbcType());

		int columnType = introspectedColumn.getJdbcType();
		int length = introspectedColumn.getLength();
		System.out.println(introspectedColumn);
		if (columnType == Types.INTEGER && length >= 10) {
			jdbcTypeInformation = typeMap.get(Types.BIGINT);
		}
		// ======================自定义实现结束======================

		if (jdbcTypeInformation != null) {
			answer = jdbcTypeInformation.getFullyQualifiedJavaType();
			answer = overrideDefaultType(introspectedColumn, answer);
		}

		return answer;
	}

}
