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
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

/**
 * 类名称：UpdateBatchElementGenerator <br>
 * 类描述: XML节点操作【批量更新对象，空字段不插入 】<br>
 * 创建人：felicity <br>
 * 创建时间：2021年1月19日 23:37:30 <br>
 * 备注:
 */
public class UpdateBatchElementGenerator extends AbstractXmlElementGenerator {

    /**
     * 批量插入方法生成对应的XML 节点逻辑
     */
    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("update");

        answer.addAttribute(new Attribute("id", BIConstant.ExtendUpdateByIdName));

        answer.addAttribute(new Attribute("parameterType", "java.util.List"));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();

        XmlElement forElement = new XmlElement("foreach");
        forElement.addAttribute(new Attribute("collection", "list"));
        forElement.addAttribute(new Attribute("item", "item"));
        forElement.addAttribute(new Attribute("index", "index"));
        forElement.addAttribute(new Attribute("separator", ";"));

        answer.addElement(forElement);

        sb.append("update ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        forElement.addElement(new TextElement(sb.toString()));

        XmlElement setElement = new XmlElement("set");
        forElement.addElement(setElement);

        //判断主键(移除主键)
        List<IntrospectedColumn> columns = ListUtilities
                .removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getNonPrimaryKeyColumns());
        for (IntrospectedColumn introspectedColumn : columns) {
            if (introspectedColumn.isSequenceColumn() || introspectedColumn.getFullyQualifiedJavaType().isPrimitive()) {
                sb.setLength(0);
                sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                sb.append(',');

                sb.setLength(0);
                sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
                sb.append(',');
                setElement.addElement(new TextElement(sb.toString()));
                continue;
            }

            sb.setLength(0);
            sb.append("item." + introspectedColumn.getJavaProperty());
            sb.append(" != null");
            XmlElement valuesNotNullElement = new XmlElement("if");
            valuesNotNullElement.addAttribute(new Attribute("test", sb.toString()));

            sb.setLength(0);
            sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ");
            sb.append(getParameterClause(introspectedColumn, null));
            sb.append(',');
            valuesNotNullElement.addElement(new TextElement(sb.toString()));
            setElement.addElement(valuesNotNullElement);

        }
        //where 条件
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null) {

            IntrospectedColumn pk = introspectedTable.getColumn(gk.getColumn());
            //******************************
            // 修复如果主键不叫默认id
            //******************************
            if (pk == null) {
                List<IntrospectedColumn> cls = introspectedTable.getPrimaryKeyColumns();
                if (!cls.isEmpty()) {
                    String pkName = cls.get(0).getActualColumnName();
                    pk = introspectedTable.getColumn(pkName);
                }
            }
            sb.setLength(0);
            sb.append("where " + MyBatis3FormattingUtilities.getEscapedColumnName(pk) + " = "
                    + getParameterClause(pk, null));
            forElement.addElement(new TextElement(sb.toString()));
        }

        if (context.getPlugins().sqlMapInsertSelectiveElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }

    /**
     * getParameterClause 自定义 获取不为空的字段
     * @param introspectedColumn
     * @param prefix
     * @return
     * @Exception 异常描述
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
