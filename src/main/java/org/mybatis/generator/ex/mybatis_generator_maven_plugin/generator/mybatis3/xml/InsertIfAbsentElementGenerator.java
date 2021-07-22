package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.xml;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;

/**
 * 类名称：InsertIfAbsentElementGenerator <br>
 * 类描述:XML节点操作【不存在则插入，必须确定唯一性】 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:40:52 <br>
 * 备注:
 */
public class InsertIfAbsentElementGenerator extends AbstractXmlElementGenerator {

	// 不存在则插入，XML节点操作
	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("insert");

		answer.addAttribute(new Attribute("id", BIConstant.ExtendInsertIfAbsentName));

		FullyQualifiedJavaType parameterType = introspectedTable.getRules()
				.calculateAllFieldsClass();

		answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));

		context.getCommentGenerator().addComment(answer);

		GeneratedKey gk = introspectedTable.getGeneratedKey();
		if (gk != null) {
			IntrospectedColumn introspectedColumn = introspectedTable.getColumn(gk.getColumn());
			if (introspectedColumn != null) {
				if (gk.isJdbcStandard()) {
					answer.addAttribute(new Attribute("useGeneratedKeys", "true"));
					answer.addAttribute(new Attribute("keyProperty", introspectedColumn
							.getJavaProperty()));
					answer.addAttribute(new Attribute("keyColumn", introspectedColumn
							.getActualColumnName()));
				} else {
					answer.addElement(getSelectKey(introspectedColumn, gk));
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append("insert ignore into ");
		sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		XmlElement insertTrimElement = new XmlElement("trim");
		insertTrimElement.addAttribute(new Attribute("prefix", "("));
		insertTrimElement.addAttribute(new Attribute("suffix", ")"));
		insertTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
		answer.addElement(insertTrimElement);

		XmlElement valuesTrimElement = new XmlElement("trim");
		valuesTrimElement.addAttribute(new Attribute("prefix", "values ("));
		valuesTrimElement.addAttribute(new Attribute("suffix", ")"));
		valuesTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
		answer.addElement(valuesTrimElement);

		for (IntrospectedColumn introspectedColumn : ListUtilities
				.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns())) {

			if (introspectedColumn.isSequenceColumn()
					|| introspectedColumn.getFullyQualifiedJavaType().isPrimitive()) {
				sb.setLength(0);
				sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
				sb.append(',');
				insertTrimElement.addElement(new TextElement(sb.toString()));

				sb.setLength(0);
				sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
				sb.append(',');
				valuesTrimElement.addElement(new TextElement(sb.toString()));

				continue;
			}

			sb.setLength(0);
			sb.append(introspectedColumn.getJavaProperty());
			sb.append(" != null");
			XmlElement insertNotNullElement = new XmlElement("if");
			insertNotNullElement.addAttribute(new Attribute("test", sb.toString()));

			sb.setLength(0);
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
			sb.append(',');
			insertNotNullElement.addElement(new TextElement(sb.toString()));
			insertTrimElement.addElement(insertNotNullElement);

			sb.setLength(0);
			sb.append(introspectedColumn.getJavaProperty());
			sb.append(" != null");
			XmlElement valuesNotNullElement = new XmlElement("if");
			valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));

			sb.setLength(0);
			sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
			sb.append(',');
			valuesNotNullElement.addElement(new TextElement(sb.toString()));
			valuesTrimElement.addElement(valuesNotNullElement);
		}

		if (context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}
}
