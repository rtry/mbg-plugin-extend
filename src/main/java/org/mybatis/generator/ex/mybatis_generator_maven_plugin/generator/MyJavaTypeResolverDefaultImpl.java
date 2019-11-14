/**    
 * 文件名：MyJavaTypeResolverDefaultImpl.java    
 *    
 * 版本信息：    
 * 日期：2018年4月25日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import java.sql.Types;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

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

	// ======================================================
	// 该类继承 JavaTypeResolverDefaultImpl (默认的类型映射)
	// calculateJavaType()方法重写，目的在于是type映射集合得到调整
	// 达到类型映射的更改
	// ======================================================
	public MyJavaTypeResolverDefaultImpl() {

		// 功能1 tinyint( >2 )数据（Byte）调整为（Integer）类型
		// boolean tiny2int = super.context.getJdbcConnectionConfiguration()
		// .getProperty(BIConstant.FILE_TINY2INT).equals("true");
		//
		// // 功能1 smallint( >2 )数据（Short）调整为（Integer）类型
		// boolean small2int = super.context.getJdbcConnectionConfiguration()
		// .getProperty(BIConstant.FILE_SMALL2INT).equals("true");
		//
		// if (tiny2int) {
		// typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT",
		// new FullyQualifiedJavaType(Integer.class.getName())));
		// }
		//
		// if (small2int) {
		// typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT",
		// new FullyQualifiedJavaType(Integer.class.getName())));
		// }
	}
}
