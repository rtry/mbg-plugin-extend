package org.mybaits.ex.base;

public interface InsertIfAbsentMapper<T> extends ExtendMapper {
    /**
     * insertIfAbsent 如果不存在，则插入
     * @param record 表对象 -【对象数据中，必须包含判断存在的标准(唯一性约束)】
     * @return 受影响条数
     */
    int insertIfAbsent(T record);
}