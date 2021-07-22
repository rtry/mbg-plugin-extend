package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：BICommentConstant <br>
 * 类描述: 方法注解控制 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:32:29 <br>
 */
public class BICommentConstant {

    /**
     * createComment:创建者声明
     * @since Ver 1.1
     */
    public static final String CREATE_COMMENT = "由 MBG(mybatis generator plug) 生成";

    /**
     * 方法的注解集合
     */
    private static final Map<String, String[]> COMMENTS = new HashMap<>();

    // 初始化注释对象
    static {

        // =======================
        // 8个基本方法
        // =======================
        String[] countByExample = { "countByExample:通过Example计算总数", "@param example 查询条件", "@return 总条数" };
        String[] deleteByExample = { "deleteByExample:通过Example删除", "@param example 刪除条件", "@return 受影响条数" };
        String[] deleteByPrimaryKey = { "deleteByPrimaryKey:通过主键删除", "@param pk 表的主键", "@return 受影响条数" };
        String[] insertSelective = { "insertSelective:有选择性的插入数据(空数据不会插入)", "@param record 表对象", "@return 受影响条数" };
        String[] selectByExample = { "selectByExample:通过Example查询条件集合", "@param example 查询条件", "@return 对象集合" };
        String[] selectByPrimaryKey = { "selectByPrimaryKey:通过主键查询单个对象", "@param id 单表的主键", "@return 表对象" };
        String[] updateByExampleSelective = { "updateByExampleSelective:通过Example有选择性的更新数据",
                "@param record 表对象（不为空的数据都会更新）", "@param example 更新的条件", "@return 受影响的条数" };
        String[] updateByPrimaryKeySelective = { "updateByPrimaryKeySelective:通过主键有选择性的更新对象",
                "@param record  表对象（不为空的数据都会更新）对象中必须含有主键", "@return 受影响条数" };

        COMMENTS.put("countByExample", countByExample);
        COMMENTS.put("deleteByExample", deleteByExample);
        COMMENTS.put("deleteByPrimaryKey", deleteByPrimaryKey);
        COMMENTS.put("insertSelective", insertSelective);
        COMMENTS.put("selectByExample", selectByExample);
        COMMENTS.put("selectByPrimaryKey", selectByPrimaryKey);
        COMMENTS.put("updateByExampleSelective", updateByExampleSelective);
        COMMENTS.put("updateByPrimaryKeySelective", updateByPrimaryKeySelective);

        // =======================
        // 扩展方法-更新空属性
        // =======================
        String[] updateMapByExample = { "updateMapByExample 根据条件和Map更新数据", "@param map key->数据库中字段 value->要改的值",
                "@param example 更新的条件", "@return 受影响条数" };
        COMMENTS.put("updateMapByExample", updateMapByExample);

        // =======================
        // 扩展方法-批量插入
        // =======================
        String[] insertBatch = { "insertBatch 批量插入数据(按集合中第一条数据的有效列)", "@param records 数据集合", "@return 受影响条数" };
        COMMENTS.put("insertBatch", insertBatch);
        String[] insertBatchSelect = { "insertBatchSelect 批量插入非空数据数据", "@param records 数据集合",
                "特别：该方法必须在allowMultiQueries=true才能执行" };
        COMMENTS.put("insertBatchSelect", insertBatchSelect);

        // =======================
        // 扩展方法-指定列查询
        // =======================
        String[] selectOptionToList = { "selectOptionToList 指定列查询到List集合", "@param options 查询字段数组(表数据库字段)",
                "@param example 查询条件", "@return 表对象List集合" };
        String[] selectOptionToOne = { "selectOptionToOne 指定列查询一个表对象", "@param options 查询字段数组(表数据库字段)",
                "@param example 查询条件", "@return 表对象" };
        COMMENTS.put("selectOptionToList", selectOptionToList);
        COMMENTS.put("selectOptionToOne", selectOptionToOne);

        // =======================
        // 扩展方法-指定列查询
        // =======================
        String[] insertIfAbsent = { "insertIfAbsent 如果不存在，则插入", "@param record 表对象 -【对象数据中，必须包含判断存在的标准(唯一性约束)】",
                "@return 受影响条数" };
        COMMENTS.put("insertIfAbsent", insertIfAbsent);

        // =======================
        // 扩展方法-根据ID批量更新
        // =======================
        String[] updateBatchById = { "updateBatchById 根据ID批量更新各对象非空的属性", "records 数据集合 -【必须存在ID】",
                "特别：该方法必须在allowMultiQueries=true才能执行" };
        COMMENTS.put("updateBatchById", updateBatchById);

    }

    /**
     * getComments 通过方法名，获取方法注解
     * @param key Mapper 方法名
     */
    public static String[] getComments(String key) {
        if (COMMENTS.containsKey(key)) {
            return COMMENTS.get(key);
        } else {
            String[] empty = {};
            return empty;
        }
    }

}
