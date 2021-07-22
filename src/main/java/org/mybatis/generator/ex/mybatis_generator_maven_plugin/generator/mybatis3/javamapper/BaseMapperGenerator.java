package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3SimpleImpl;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeyWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.constant.BIConstant;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.CountByExampleMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.DeleteByExampleMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.InsertSelectiveMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.SelectByExampleMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.base.method.UpdateMapByExampleMethodGenerator;

/**
 * @className BaseInterfaceGenerator
 * @describe BaseMapper.java 文件生成
 * @author rtry
 * @date  2021/07/22 11:05
 * @version 0.0.1
 */
public class BaseMapperGenerator extends AbstractJavaClientGenerator {

    private String baseInterfaceName;

    public BaseMapperGenerator(Context context) {
        super(false);
        super.context = context;
        baseInterfaceName = context.getJdbcConnectionConfiguration().getProperty(BIConstant.SCI);
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(BIConstant.MODEL).append(",");
        sb.append(BIConstant.PK).append(" extends Serializable,");
        sb.append(BIConstant.EXAMPLE).append(",");
        sb.append(">");
        baseInterfaceName = baseInterfaceName.concat(sb.toString());
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {

        CommentGenerator commentGenerator = context.getCommentGenerator();

        // 类名
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(baseInterfaceName);
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        // ===========================================
        // 标准接口方法 8个（默认 单 主键 存在）
        // ===========================================
        // 计算总数
        addCountByExampleMethod(interfaze);

        // 删除，按需求
        addDeleteByExampleMethod(interfaze);
        // 删除，按主键
        addDeleteByPrimaryKeyMethod(interfaze);

        // 插入，按需求
        addInsertSelectiveMethod(interfaze);

        // 查询，按需求
        addSelectByExampleMethod(interfaze);
        // 查询，按主键
        addSelectByPrimaryKeyMethod(interfaze);

        // 更新，按需求
        addUpdateByExampleSelectiveMethod(interfaze);
        // 更新，按主键
        addUpdateByPrimaryKeySelectiveMethod(interfaze);

        // ===========================================
        // 基础接口 附加部分 1个
        // ===========================================

        // 更新，按需求 更新map的值
        addUpdateMapByExampleMethod(interfaze);

        List<CompilationUnit> answer = new ArrayList<>();
        answer.add(interfaze);

        return answer;
    }

    private void addUpdateMapByExampleMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateMapByExampleMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addCountByExampleMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addDeleteByExampleMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addInsertMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addInsertSelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addSelectByExampleMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByExampleWithBLOBsMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByExampleWithoutBLOBsMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator,
            Interface interfaze) {
        methodGenerator.setContext(context);
        IntrospectedTableMyBatis3SimpleImpl it = new IntrospectedTableMyBatis3SimpleImpl();
        it.setExampleType(baseInterfaceName);
        methodGenerator.setIntrospectedTable(it);
        methodGenerator.addInterfaceElements(interfaze);
    }
}
