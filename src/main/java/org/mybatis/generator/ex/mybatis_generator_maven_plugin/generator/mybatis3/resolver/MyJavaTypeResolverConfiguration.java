/**    
 * 文件名：MyJavaTypeResolverConfiguration.java    
 *    
 * 版本信息：    
 * 日期：2018年4月25日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.resolver;

import org.mybatis.generator.config.JavaTypeResolverConfiguration;

/**
 * 类名称：MyJavaTypeResolverConfiguration <br>
 * 类描述: 自定义类型转换设置<br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月25日 下午2:25:27 <br>
 */
public class MyJavaTypeResolverConfiguration extends JavaTypeResolverConfiguration {

	public String getConfigurationType() {
		return MyJavaTypeResolverDefaultImpl.class.getName();
	}

}