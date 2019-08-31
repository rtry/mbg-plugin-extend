package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.xml;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

//=========================
// 批量添加对象，空字段不插入
//=========================

public class InsertBatchElementGenerator extends AbstractXmlElementGenerator {

	public InsertBatchElementGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("insert");

		answer.addAttribute(new Attribute("id", BIConstant.ExtendInsertMethodName));

		answer.addAttribute(new Attribute("parameterType", "java.util.List"));

		context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();

		sb.append("insert into ");
		sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
		answer.addElement(new TextElement(sb.toString()));

		// --------------
		XmlElement forElement = new XmlElement("foreach");
		forElement.addAttribute(new Attribute("collection", "list"));
		forElement.addAttribute(new Attribute("item", "item"));
		forElement.addAttribute(new Attribute("index", "index"));

		XmlElement ifElement = new XmlElement("if");
		ifElement.addAttribute(new Attribute("test", "index==0"));

		XmlElement insertTrimElement = new XmlElement("trim");
		insertTrimElement.addAttribute(new Attribute("prefix", "("));
		insertTrimElement.addAttribute(new Attribute("suffix", ")"));
		insertTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
		ifElement.addElement(insertTrimElement);
		forElement.addElement(ifElement);
		answer.addElement(forElement);

		// --------------
		answer.addElement(new TextElement("values"));

		// --------------
		XmlElement forElement2 = new XmlElement("foreach");
		forElement2.addAttribute(new Attribute("collection", "list"));
		forElement2.addAttribute(new Attribute("item", "item"));
		forElement2.addAttribute(new Attribute("index", "index"));
		forElement2.addAttribute(new Attribute("separator", ","));

		XmlElement valuesTrimElement = new XmlElement("trim");
		valuesTrimElement.addAttribute(new Attribute("prefix", "("));
		valuesTrimElement.addAttribute(new Attribute("suffix", ")"));
		valuesTrimElement.addAttribute(new Attribute("suffixOverrides", ","));
		forElement2.addElement(valuesTrimElement);
		answer.addElement(forElement2);

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
			sb.append("item." + introspectedColumn.getJavaProperty());
			sb.append(" != null");
			XmlElement insertNotNullElement = new XmlElement("if");
			insertNotNullElement.addAttribute(new Attribute("test", sb.toString()));

			sb.setLength(0);
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
			sb.append(',');
			insertNotNullElement.addElement(new TextElement(sb.toString()));
			insertTrimElement.addElement(insertNotNullElement);

			sb.setLength(0);
			sb.append("item." + introspectedColumn.getJavaProperty());
			sb.append(" != null");
			XmlElement valuesNotNullElement = new XmlElement("if");
			valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));

			sb.setLength(0);
			//调整自己的方法
			sb.append(getParameterClause(introspectedColumn,null));
			sb.append(',');
			valuesNotNullElement.addElement(new TextElement(sb.toString()));
			valuesTrimElement.addElement(valuesNotNullElement);
		}

		if (context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, introspectedTable)) {
			parentElement.addElement(answer);
		}
	}
	
	//自定义
    public static String getParameterClause(
            IntrospectedColumn introspectedColumn, String prefix) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{item."); //$NON-NLS-1$
        sb.append(introspectedColumn.getJavaProperty(prefix));
        sb.append(",jdbcType="); //$NON-NLS-1$
        sb.append(introspectedColumn.getJdbcTypeName());

        if (stringHasValue(introspectedColumn.getTypeHandler())) {
            sb.append(",typeHandler="); //$NON-NLS-1$
            sb.append(introspectedColumn.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }
}