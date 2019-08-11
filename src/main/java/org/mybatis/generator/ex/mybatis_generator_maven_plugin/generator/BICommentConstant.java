package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import java.util.HashMap;
import java.util.Map;

public class BICommentConstant {

	private static final Map<String, String[]> coms = new HashMap<>();

	static {

		String[] countByExample = { "countByExample:通过Example计算总数", "@param example 查询条件", "@return 总条数" };
		String[] deleteByExample = { "deleteByExample:通过Example删除", "@param example 刪除条件", "@return 受影响条数" };
		String[] deleteByPrimaryKey = { "deleteByPrimaryKey:通过主键删除", "@param id 表的主键", "@return 受影响条数" };
		String[] insertSelective = { "insertSelective:有选择性的插入数据(空数据不会插入)", "@param record 表对象", "@return 受影响条数" };
		String[] selectByExample = { "selectByExample:通过Example查询条件集合", "@param example 查询条件", "@return 对象集合" };
		String[] selectByPrimaryKey = { "selectByPrimaryKey:通过主键查询单个对象", "@param id 单表的主键", "@return 表对象" };
		String[] updateByExampleSelective = { "updateByExampleSelective:通过Example有选择性的更新数据",
				"@param record 表对象（不为空的数据都会更新）", "@param example 更新的条件", "@return 受影响的条数" };
		String[] updateByPrimaryKeySelective = { "updateByPrimaryKeySelective:通过主键有选择性的更新对象",
				"@param record  表对象（不为空的数据都会更新）对象中必须含有主键", "@return 受影响条数" };

		coms.put("countByExample", countByExample);
		coms.put("deleteByExample", deleteByExample);
		coms.put("deleteByPrimaryKey", deleteByPrimaryKey);
		coms.put("insertSelective", insertSelective);
		coms.put("selectByExample", selectByExample);
		coms.put("selectByPrimaryKey", selectByPrimaryKey);
		coms.put("updateByExampleSelective", updateByExampleSelective);
		coms.put("updateByPrimaryKeySelective", updateByPrimaryKeySelective);
	}

	public static String[] getComments(String key) {
		if (coms.containsKey(key)) {
			return coms.get(key);
		} else {
			String[] empty = {};
			return empty;
		}
	}
}