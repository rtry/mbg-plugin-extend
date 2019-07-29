/**    
 * 文件名：SelectAllElementGenerator.java    
 *    
 * 版本信息：    
 * 日期：2019年7月29日    
 * Copyright Felicity Corporation 2019 版权所有   
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 类名称：SelectAllElementGenerator <br>
 * 类描述: 自定义的语句实现 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年7月29日 下午5:29:04 <br>
 * 备注:
 * https://www.jianshu.com/p/7cd68f451004
 * @version
 * @see
 */
public class SelectAllElementGenerator extends AbstractXmlElementGenerator {

	public SelectAllElementGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("select"); //$NON-NLS-1$
		answer.addAttribute(new Attribute("id", introspectedTable.getSelectAll()));
		answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId()));

		context.getCommentGenerator().addComment(answer);

		StringBuffer sb = new StringBuffer("select * form "
				+ introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		parentElement.addElement(answer);

	}

}
