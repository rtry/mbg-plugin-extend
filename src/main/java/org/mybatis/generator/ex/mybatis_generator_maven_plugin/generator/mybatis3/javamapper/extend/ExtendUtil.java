package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend;

import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

public class ExtendUtil {

	private String pkgName;
	private String brk = ".";

	private String extendClassName = BIConstant.ExtendSuperClass;

	private String insertBatchClassName = BIConstant.ExtendInsertBatchClass;

	private String insertBatchMethodName = BIConstant.ExtendInsertMethodName;

	public ExtendUtil(String pkgName) {
		super();
		this.pkgName = pkgName;
	}

	public String getExtendClassName() {
		return pkgName.concat(brk).concat(extendClassName);
	}

	public String getInsertBatchClassName() {
		return pkgName.concat(brk).concat(insertBatchClassName);
	}

	public String getInsertBatchMethodName() {
		return insertBatchMethodName;
	}

}
