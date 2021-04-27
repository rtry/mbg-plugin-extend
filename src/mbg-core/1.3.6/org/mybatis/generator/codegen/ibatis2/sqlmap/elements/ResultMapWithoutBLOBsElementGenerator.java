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

import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.ibatis2.Ibatis2FormattingUtilities;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * Generates the resultmap without BLOBs for iBatis2.
 * 
 * @author Jeff Butler
 * 
 */
public class ResultMapWithoutBLOBsElementGenerator extends AbstractXmlElementGenerator {

    public ResultMapWithoutBLOBsElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        boolean useColumnIndex = isTrue(
                introspectedTable.getTableConfigurationProperty(PropertyRegistry.TABLE_USE_COLUMN_INDEXES));
        XmlElement answer = new XmlElement("resultMap"); 
        answer.addAttribute(new Attribute("id", 
                introspectedTable.getBaseResultMapId()));

        String returnType;
        if (introspectedTable.getRules().generateBaseRecordClass()) {
            returnType = introspectedTable.getBaseRecordType();
        } else {
            returnType = introspectedTable.getPrimaryKeyType();
        }

        answer.addAttribute(new Attribute("class", 
                returnType));

        context.getCommentGenerator().addComment(answer);

        int i = 1;
        if (stringHasValue(introspectedTable.getSelectByPrimaryKeyQueryId())
                || stringHasValue(introspectedTable.getSelectByExampleQueryId())) {
            i++;
        }

        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonBLOBColumns()) {
            XmlElement resultElement = new XmlElement("result"); 

            if (useColumnIndex) {
                resultElement.addAttribute(new Attribute("columnIndex", Integer.toString(i++))); 
            } else {
                resultElement.addAttribute(new Attribute("column", 
                        Ibatis2FormattingUtilities.getRenamedColumnNameForResultMap(introspectedColumn)));
            }

            resultElement.addAttribute(new Attribute("property", introspectedColumn.getJavaProperty())); 
            resultElement.addAttribute(new Attribute("jdbcType", 
                    introspectedColumn.getJdbcTypeName()));

            if (stringHasValue(introspectedColumn.getTypeHandler())) {
                resultElement.addAttribute(new Attribute("typeHandler", introspectedColumn.getTypeHandler())); 
            }

            answer.addElement(resultElement);
        }

        if (context.getPlugins().sqlMapResultMapWithoutBLOBsElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
