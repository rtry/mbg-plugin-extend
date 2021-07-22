package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.xml;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;

/**
 * 类名称：SelectOptionElementGenerator <br>
 * 类描述: XML节点操作【按需查询，指定列 】 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:43:30 <br>
 * 备注:
 */
public class SelectOptionElementGenerator extends AbstractXmlElementGenerator {

    public SelectOptionElementGenerator() {
        super();
    }

    /**
     * addOptionColumnList XML 自定义查询块
     * @param parentElement
     */
    public void addOptionColumnListElements(XmlElement parentElement) {
        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", "Option_Column_List"));

        XmlElement forElement = new XmlElement("foreach");
        forElement.addAttribute(new Attribute("collection", "options"));
        forElement.addAttribute(new Attribute("item", "item"));
        forElement.addAttribute(new Attribute("separator", ","));
        forElement.addElement(new TextElement("${item}"));

        sql.addElement(forElement);
        parentElement.addElement(sql);
    }

    /**
     * addSelectOptionToListElements 按自定义列查询List集合
     * @param parentElement
     */
    public void addSelectOptionToOneElements(XmlElement parentElement) {
        addSelectOptionElements(BIConstant.ExtendSelectOptionToOneName, parentElement, true);
    }

    public void addSelectOptionToListElements(XmlElement parentElement) {
        addSelectOptionElements(BIConstant.ExtendSelectOptionToListName, parentElement, false);
    }

    private void addSelectOptionToMapElements(XmlElement parentElement) {
        addSelectOptionElements(BIConstant.ExtendSelectOptionToMapName, parentElement, false);
    }

    public void addSelectOptionElements(String name, XmlElement parentElement, boolean limitOne) {
        XmlElement select = new XmlElement("select");

        select.addAttribute(new Attribute("id", name));

        select.addAttribute(new Attribute("parameterType", "map"));
        select.addAttribute(new Attribute("resultMap", "BaseResultMap"));

        select.addElement(new TextElement("select"));

        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "Option_Column_List"));
        select.addElement(include);

        select.addElement(new TextElement("from " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));

        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "_parameter != null"));
        XmlElement include2 = new XmlElement("include");
        include2.addAttribute(new Attribute("refid", "Update_By_Example_Where_Clause"));
        ifElement.addElement(include2);
        select.addElement(ifElement);

        XmlElement ifElement2 = new XmlElement("if");
        ifElement2.addAttribute(new Attribute("test", "example.orderByClause != null"));
        ifElement2.addElement(new TextElement("order by ${example.orderByClause}"));
        select.addElement(ifElement2);
        if (limitOne) {
            select.addElement(new TextElement("limit 1"));

        }

        parentElement.addElement(select);
    }

    @Override
    public void addElements(XmlElement parentElement) {
        this.addOptionColumnListElements(parentElement);
        this.addSelectOptionToListElements(parentElement);
        this.addSelectOptionToOneElements(parentElement);
        //		this.addSelectOptionToMapElements(parentElement);
    }

    // 自定义
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
