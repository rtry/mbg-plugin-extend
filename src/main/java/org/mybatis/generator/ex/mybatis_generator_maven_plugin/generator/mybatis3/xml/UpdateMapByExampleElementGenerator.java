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
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.xml;

import static org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant.ExtendUpdateMapByExample;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class UpdateMapByExampleElementGenerator extends AbstractXmlElementGenerator {

    public UpdateMapByExampleElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("update");

        answer.addAttribute(new Attribute("id", ExtendUpdateMapByExample));

        answer.addAttribute(new Attribute("parameterType", "map"));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));

        XmlElement dynamicElement = new XmlElement("set");
        answer.addElement(dynamicElement);

        XmlElement forElement = new XmlElement("foreach");
        forElement.addAttribute(new Attribute("collection", "map"));
        forElement.addAttribute(new Attribute("item", "val"));
        forElement.addAttribute(new Attribute("index", "key"));
        forElement.addAttribute(new Attribute("separator", ","));

        dynamicElement.addElement(forElement);

        sb.setLength(0);
        sb.append("${key} = #{val}");
        forElement.addElement(new TextElement(sb.toString()));

        answer.addElement(getUpdateByExampleIncludeElement());

        if (context.getPlugins().sqlMapUpdateByExampleSelectiveElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
