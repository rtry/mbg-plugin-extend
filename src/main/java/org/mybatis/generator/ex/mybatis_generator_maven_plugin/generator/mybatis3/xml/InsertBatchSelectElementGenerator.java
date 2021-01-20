package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.xml;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

/**
 * 类名称：InsertBatchSelectElementGenerator <br>
 * 类描述: XML节点操作【批量添加对象，空字段不插入 】<br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:37:30 <br>
 * 备注:
 * @version
 * @see
 */
public class InsertBatchSelectElementGenerator extends AbstractXmlElementGenerator {

    /**
     * 批量插入方法生成对应的XML 节点逻辑
     */
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

        answer.addElement(new TextElement("values"));

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

        //判断主键 是否自增
        IntrospectedColumn pkColumn = null;
        List<IntrospectedColumn> cls = introspectedTable.getPrimaryKeyColumns();
        if (!cls.isEmpty()) {
            String pkName = cls.get(0).getActualColumnName();
            pkColumn = introspectedTable.getColumn(pkName);
        }
        // 默认 自增
        boolean autoIncrement = true;
        if (pkColumn != null)
            autoIncrement = pkColumn.isAutoIncrement();

        //根据自增与否 ，生成XML
        List<IntrospectedColumn> columns = null;
        if (autoIncrement) {
            columns = ListUtilities
                    .removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getNonPrimaryKeyColumns());
        } else {
            columns = introspectedTable.getAllColumns();
        }

        for (IntrospectedColumn introspectedColumn : columns) {
            if (introspectedColumn.isSequenceColumn() || introspectedColumn.getFullyQualifiedJavaType().isPrimitive()) {
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
            //			insertTrimElement.addElement(insertNotNullElement);
            insertTrimElement.addElement(new TextElement(sb.toString()));

            sb.setLength(0);
            sb.append("item." + introspectedColumn.getJavaProperty());
            sb.append(" != null");
            XmlElement valuesNotNullElement = new XmlElement("if");
            valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));

            sb.setLength(0);
            // 调整自己的方法
            sb.append(getParameterClause(introspectedColumn, null));
            sb.append(',');
            valuesNotNullElement.addElement(new TextElement(sb.toString()));
            //			valuesTrimElement.addElement(valuesNotNullElement);
            valuesTrimElement.addElement(new TextElement(sb.toString()));
        }

        if (context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }

    /**
     * getParameterClause 自定义 获取不为空的字段
     * @param introspectedColumn 字段对象
     * @param prefix 前缀
     */
    public static String getParameterClause(IntrospectedColumn introspectedColumn, String prefix) {
        StringBuilder sb = new StringBuilder();

        sb.append("#{item.");
        sb.append(introspectedColumn.getJavaProperty(prefix));
        sb.append(",jdbcType=");
        sb.append(introspectedColumn.getJdbcTypeName());

        if (stringHasValue(introspectedColumn.getTypeHandler())) {
            sb.append(",typeHandler=");
            sb.append(introspectedColumn.getTypeHandler());
        }

        sb.append('}');

        return sb.toString();
    }
}
