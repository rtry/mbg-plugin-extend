package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3;

public class BIConstant {

	// =======================================
	// 枚举括号里面的东西
	// =======================================
	public static final String MODEL = "T";
	public static final String PK = "PK";
	public static final String EXAMPLE = "E";

	// =======================================
	// 扩展功能 命名
	// =======================================
	public static final String ExtendSuperClass = "ExtendMapper";

	// 批量插入
	public static final String ExtendInsertBatchClass = "InsertBatchMapper";
	public static final String ExtendInsertMethodName = "insertBatch";

	// 按列查询
	public static final String ExtendSelectOptionClass = "SelectOptionMapper";
	// 查询指定列 -> List
	public static final String ExtendSelectOptionToListName = "selectOptionToList";
	// 查询指定列 -> Map
	public static final String ExtendSelectOptionToMapName = "selectOptionToMap";
	// 查询指定列 -> One
	public static final String ExtendSelectOptionToOneName = "selectOptionToOne";

}
