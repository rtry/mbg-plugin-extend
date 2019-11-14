package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3;

/**
 * 类名称：BIConstant <br>
 * 类描述: 命名字符 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:20:36 <br>
 * 备注:
 * @version
 * @see
 */
public class BIConstant {

	// =======================================
	// 枚举括号里面变量名
	// =======================================
	public static final String MODEL = "T";
	public static final String PK = "PK";
	public static final String EXAMPLE = "E";

	// Mapper类的结尾字符名
	public static final String MAPPER = "Mapper";

	// =======================================
	// 扩展功能 命名
	// =======================================
	public static final String ExtendSuperClass = "Extend" + MAPPER;

	// =======================================
	// 批量插入
	// =======================================
	public static final String ExtendInsertBatchClass = "InsertBatch" + MAPPER;
	// 方法：批量插入
	public static final String ExtendInsertMethodName = "insertBatch";

	// =======================================
	// 按列查询
	// =======================================
	public static final String ExtendSelectOptionClass = "SelectOption" + MAPPER;
	// 方法：查询指定列 -> List
	public static final String ExtendSelectOptionToListName = "selectOptionToList";
	// 方法：查询指定列 -> Map
	public static final String ExtendSelectOptionToMapName = "selectOptionToMap";
	// 方法：查询指定列 -> One
	public static final String ExtendSelectOptionToOneName = "selectOptionToOne";

	// =======================================
	// 不存在则插入
	// =======================================
	public static final String ExtendInsertIfAbsentClass = "InsertIfAbsent" + MAPPER;
	// 方法：不存在则插入
	public static final String ExtendInsertIfAbsentName = "insertIfAbsent";

	// =======================================
	// 特殊配置
	// =======================================
	public static final String SCI = "supportCustomInterface";
	public static final String FILE_TINY2INT = "filetiny2int";
	public static final String FILE_SMALL2INT = "filesmall2int";
	
	
	
}
