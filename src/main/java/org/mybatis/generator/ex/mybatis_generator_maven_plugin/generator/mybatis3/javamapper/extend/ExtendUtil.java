package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

public class ExtendUtil {

    private String pkgName;

    private String brk = ".";

    public ExtendUtil(String pkgName) {
        super();
        this.pkgName = pkgName;
    }

    public String getExtendClassName() {
        return pkgName.concat(brk).concat(BIConstant.ExtendSuperClass);
    }

    public String getSelectOptionClassName() {
        return pkgName.concat(brk).concat(BIConstant.ExtendSelectOptionClass);
    }

    public String getInsertBatchClassName() {
        return pkgName.concat(brk).concat(BIConstant.ExtendInsertBatchClass);
    }

    public String getIfAbsentClassName() {
        return pkgName.concat(brk).concat(BIConstant.ExtendInsertIfAbsentClass);
    }

    public String getInsertBatchMethodName() {
        return BIConstant.ExtendInsertMethodName;
    }

    public String getInsertBatchSelectMethodName() {
        return BIConstant.ExtendInsertSelectMethodName;
    }

    public String getSelectOptionToListMethodName() {
        return BIConstant.ExtendSelectOptionToListName;
    }

    public String getSelectOptionToMapMethodName() {
        return BIConstant.ExtendSelectOptionToMapName;
    }

    public String getSelectOptionToOneMethodName() {
        return BIConstant.ExtendSelectOptionToOneName;
    }

    public String getInsertIfAbsentMethodName() {
        return BIConstant.ExtendInsertIfAbsentName;
    }

    public String getUpdateBatchClassName() {
        return pkgName.concat(brk).concat(BIConstant.ExtendUpdateByIdClass);
    }

    public String getUpdateBatchMethodName() {
        return BIConstant.ExtendUpdateByIdName;
    }
}
