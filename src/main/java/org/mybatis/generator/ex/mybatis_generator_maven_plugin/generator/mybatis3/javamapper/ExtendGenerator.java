package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.AbstractExampleGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.InsertBatchGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.InsertIfAbsentGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.SelectOptionGenerator;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.UpdateBatchGenerator;

/**
 * @className ExtendGenerator
 * @describe  API 相关 .java 文件生成 总的入口
 * @author rtry
 * @date  2021/07/22 10:59
 * @version 0.0.1
 */
public class ExtendGenerator {

    private ExtendUtil util;

    private Context context;

    public ExtendGenerator(ExtendUtil util, Context context) {
        this.util = util;
        this.context = context;
    }

    public List<CompilationUnit> getCompilationUnits() {
        // 类名
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(util.getExtendClassName());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        answer.add(interfaze);

        return answer;
    }

    /**
     * getExtendMapper 获取扩展超类
     */
    public Collection<? extends GeneratedJavaFile> getExtendMapper() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();

        List<CompilationUnit> compilationUnits = getCompilationUnits();
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaClientGeneratorConfiguration().getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
            answer.add(gjf);
        }
        return answer;
    }

    /**
     * getInsertBatchMapper 获取批量插入Mapper
     */
    public Collection<? extends GeneratedJavaFile> getInsertBatchMapper() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        InsertBatchGenerator ibj = new InsertBatchGenerator(context, util);
        return getGeneratedJavaFiles(answer, ibj.getCompilationUnits());
    }

    /**
     * getInsertBatchMapper 获取 按列查询Mapper
     */
    public Collection<? extends GeneratedJavaFile> getSelectOptionMapper() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        SelectOptionGenerator ibj = new SelectOptionGenerator(context, util);
        return getGeneratedJavaFiles(answer, ibj.getCompilationUnits());
    }

    /**
     * getInsertIfAbsentMapper 获取 不存在则插入
     */
    public Collection<? extends GeneratedJavaFile> getInsertIfAbsentMapper() {
        List<GeneratedJavaFile> answer = new ArrayList<GeneratedJavaFile>();
        InsertIfAbsentGenerator ibj = new InsertIfAbsentGenerator(context, util);
        return getGeneratedJavaFiles(answer, ibj.getCompilationUnits());
    }

    /**
     * getUpdateBatchMapper 获取 按ID 批量更新
     */
    public Collection<? extends GeneratedJavaFile> getUpdateBatchMapper() {
        List<GeneratedJavaFile> answer = new ArrayList<>();
        UpdateBatchGenerator ibj = new UpdateBatchGenerator(context, util);
        return getGeneratedJavaFiles(answer, ibj.getCompilationUnits());
    }

    /**
     * getAbstractExampleClass 获取 Example 超类
     */
    public Collection<? extends GeneratedJavaFile> getAbstractExampleClass() {
        List<GeneratedJavaFile> answer = new ArrayList<>();
        AbstractExampleGenerator ibj = new AbstractExampleGenerator(context, util);
        return getGeneratedJavaFiles(answer, ibj.getCompilationUnits());
    }

    /**
     * getGeneratedJavaFiles 根据java对象生成集合 
     * @author rtry
     * @param answer
     * @param compilationUnits 
     */
    private Collection<? extends GeneratedJavaFile> getGeneratedJavaFiles(List<GeneratedJavaFile> answer,
            List<CompilationUnit> compilationUnits) {
        for (CompilationUnit compilationUnit : compilationUnits) {
            GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
                    context.getJavaClientGeneratorConfiguration().getTargetProject(),
                    context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
            answer.add(gjf);
        }
        return answer;
    }
}
