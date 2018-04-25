/**    
 * 文件名：MyJavaTypeResolverConfiguration.java    
 *    
 * 版本信息：    
 * 日期：2018年4月25日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;

/**
 * 类名称：MyJavaTypeResolverConfiguration <br>
 * 类描述: <br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月25日 下午2:25:27 <br>
 * 修改人：felicity <br>
 * 修改时间：2018年4月25日 下午2:25:27 <br>
 * 修改备注:
 * @version
 * @see
 */
public class MyJavaTypeResolverConfiguration extends JavaTypeResolverConfiguration {

	public String getConfigurationType() {
		System.out.println("绑定自定义类型转换");
		// return super.getConfigurationType();
		return MyJavaTypeResolverDefaultImpl.class.getName();
	}

}
