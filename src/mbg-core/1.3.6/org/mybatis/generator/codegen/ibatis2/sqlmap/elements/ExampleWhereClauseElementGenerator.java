/**
 *    Copyright 2006-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.codegen.ibatis2.sqlmap.elements;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * Generates the example where clause for iBatis2.
 * 
 * @author Jeff Butler
 * 
 */
public class ExampleWhereClauseElementGenerator extends AbstractXmlElementGenerator {

    public ExampleWhereClauseElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); 

        answer.addAttribute(new Attribute("id", introspectedTable.getExampleWhereClauseId())); 

        context.getCommentGenerator().addComment(answer);

        XmlElement outerIterateElement = new XmlElement("iterate"); 
        outerIterateElement.addAttribute(new Attribute("property", "oredCriteria"));  //$NON-NLS-2$
        outerIterateElement.addAttribute(new Attribute("conjunction", "or"));  //$NON-NLS-2$
        outerIterateElement.addAttribute(new Attribute("prepend", "where"));  //$NON-NLS-2$
        outerIterateElement.addAttribute(new Attribute("removeFirstPrepend", "iterate"));  //$NON-NLS-2$
        answer.addElement(outerIterateElement);

        XmlElement isEqualElement = new XmlElement("isEqual"); 
        isEqualElement.addAttribute(new Attribute("property", "oredCriteria[].valid"));  //$NON-NLS-2$
        isEqualElement.addAttribute(new Attribute("compareValue", "true"));  //$NON-NLS-2$
        outerIterateElement.addElement(isEqualElement);

        isEqualElement.addElement(new TextElement("(")); 

        XmlElement innerIterateElement = new XmlElement("iterate"); 
        innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("property", "oredCriteria[].criteriaWithoutValue"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$
        innerIterateElement.addElement(new TextElement("$oredCriteria[].criteriaWithoutValue[]$")); 
        isEqualElement.addElement(innerIterateElement);

        innerIterateElement = new XmlElement("iterate"); 
        innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("property", "oredCriteria[].criteriaWithSingleValue"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$
        innerIterateElement.addElement(new TextElement(
                "$oredCriteria[].criteriaWithSingleValue[].condition$ #oredCriteria[].criteriaWithSingleValue[].value#")); 
        isEqualElement.addElement(innerIterateElement);

        innerIterateElement = new XmlElement("iterate"); 
        innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("property", "oredCriteria[].criteriaWithListValue"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$
        innerIterateElement.addElement(new TextElement("$oredCriteria[].criteriaWithListValue[].condition$")); 
        XmlElement innerInnerIterateElement = new XmlElement("iterate"); 
        innerInnerIterateElement.addAttribute(new Attribute("property", 
                "oredCriteria[].criteriaWithListValue[].values")); 
        innerInnerIterateElement.addAttribute(new Attribute("open", "("));  //$NON-NLS-2$
        innerInnerIterateElement.addAttribute(new Attribute("close", ")"));  //$NON-NLS-2$
        innerInnerIterateElement.addAttribute(new Attribute("conjunction", ","));  //$NON-NLS-2$
        innerInnerIterateElement.addElement(new TextElement("#oredCriteria[].criteriaWithListValue[].values[]#")); 
        innerIterateElement.addElement(innerInnerIterateElement);
        isEqualElement.addElement(innerIterateElement);

        innerIterateElement = new XmlElement("iterate"); 
        innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("property", "oredCriteria[].criteriaWithBetweenValue"));  //$NON-NLS-2$
        innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$
        innerIterateElement.addElement(new TextElement("$oredCriteria[].criteriaWithBetweenValue[].condition$")); 
        innerIterateElement.addElement(new TextElement("#oredCriteria[].criteriaWithBetweenValue[].values[0]# and")); 
        innerIterateElement.addElement(new TextElement("#oredCriteria[].criteriaWithBetweenValue[].values[1]#")); 
        isEqualElement.addElement(innerIterateElement);

        // if any of the columns have a user defined type handler, then we need
        // to add additional inner iterate elements that specify the type
        // handler
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonBLOBColumns()) {
            if (stringHasValue(introspectedColumn.getTypeHandler())) {
                // name the property based on the column name, then
                // add the type handler to the parameter declaration
                StringBuilder sb1 = new StringBuilder();
                innerIterateElement = new XmlElement("iterate"); 
                innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$

                sb1.append("oredCriteria[]."); 
                sb1.append(introspectedColumn.getJavaProperty());
                sb1.append("CriteriaWithSingleValue"); 

                innerIterateElement.addAttribute(new Attribute("property", sb1.toString())); 
                innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$

                StringBuilder sb2 = new StringBuilder();
                sb2.append(sb1);

                sb1.insert(0, '$');
                sb1.append("[].condition$ ");

                sb2.insert(0, '#');
                sb2.append("[].value,handler=");
                sb2.append(introspectedColumn.getTypeHandler());
                sb2.append('#');

                sb1.append(sb2);

                innerIterateElement.addElement(new TextElement(sb1.toString()));
                isEqualElement.addElement(innerIterateElement);

                sb1.setLength(0);
                sb2.setLength(0);
                sb1.append("oredCriteria[]."); 
                sb1.append(introspectedColumn.getJavaProperty());
                sb1.append("CriteriaWithListValue"); 

                innerIterateElement = new XmlElement("iterate"); 
                innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$
                innerIterateElement.addAttribute(new Attribute("property", sb1.toString())); 
                innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$

                sb2.append('$');
                sb2.append(sb1);
                sb2.append("[].condition$"); 

                innerIterateElement.addElement(new TextElement(sb2.toString()));

                sb2.setLength(0);
                sb2.append(sb1);
                sb2.append("[].values"); 

                innerInnerIterateElement = new XmlElement("iterate"); 
                innerInnerIterateElement.addAttribute(new Attribute("property", 
                        sb2.toString()));
                innerInnerIterateElement.addAttribute(new Attribute("open", "("));  //$NON-NLS-2$
                innerInnerIterateElement.addAttribute(new Attribute("close", ")"));  //$NON-NLS-2$
                innerInnerIterateElement.addAttribute(new Attribute("conjunction", ","));  //$NON-NLS-2$

                sb2.setLength(0);
                sb2.append('#');
                sb2.append(sb1);
                sb2.append("[].values[],handler="); 
                sb2.append(introspectedColumn.getTypeHandler());
                sb2.append('#');

                innerInnerIterateElement.addElement(new TextElement(sb2.toString()));
                innerIterateElement.addElement(innerInnerIterateElement);
                isEqualElement.addElement(innerIterateElement);

                sb1.setLength(0);
                sb2.setLength(0);
                sb1.append("oredCriteria[]."); 
                sb1.append(introspectedColumn.getJavaProperty());
                sb1.append("CriteriaWithBetweenValue"); 

                innerIterateElement = new XmlElement("iterate"); 
                innerIterateElement.addAttribute(new Attribute("prepend", "and"));  //$NON-NLS-2$
                innerIterateElement.addAttribute(new Attribute("property", sb1.toString())); 
                innerIterateElement.addAttribute(new Attribute("conjunction", "and"));  //$NON-NLS-2$

                sb2.append('$');
                sb2.append(sb1);
                sb2.append("[].condition$"); 

                innerIterateElement.addElement(new TextElement(sb2.toString()));

                sb2.setLength(0);
                sb2.append(sb1);

                sb1.insert(0, '#');
                sb1.append("[].values[0],handler="); 
                sb1.append(introspectedColumn.getTypeHandler());
                sb1.append("# and"); 

                sb2.insert(0, '#');
                sb2.append("[].values[1],handler="); 
                sb2.append(introspectedColumn.getTypeHandler());
                sb2.append('#');

                innerIterateElement.addElement(new TextElement(sb1.toString()));
                innerIterateElement.addElement(new TextElement(sb2.toString()));
                isEqualElement.addElement(innerIterateElement);
            }
        }

        isEqualElement.addElement(new TextElement(")")); 

        if (context.getPlugins().sqlMapExampleWhereClauseElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
