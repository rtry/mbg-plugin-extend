/*  
 * @file org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.AbstractExample.java
 * @date 2021/7/22
 * @author panxw
 * @copyright 马丁洛克 Corporation 2021 版权所有
 * @version 0.0.1  
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.ExtendUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @className AbstractExample
 * @describe AbstractExample.java 类生成器 [只是站位，按AbstractExample.tmp 模板输出]
 * @author panxw
 * @date 2021/7/22 13:24
 * @version 0.0.1
 */
public class AbstractExampleGenerator extends AbstractJavaClientGenerator {

    private ExtendUtil util;

    public AbstractExampleGenerator(Context context, ExtendUtil util) {
        super(false);
        super.context = context;
        this.util = util;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return null;
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {

        CommentGenerator commentGenerator = context.getCommentGenerator();

        // 类名
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(util.getAbstractExampleName());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(interfaze);

        return answer;
    }
}
