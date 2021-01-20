package org.mybaits.ex.base;

import java.util.List;

public interface InsertBatchMapper<T> extends ExtendMapper {
    /**
     * insertBatch 批量插入数据(按集合中第一条数据的有效列)
     * @param records 数据集合
     * @return 受影响条数
     */
    int insertBatch(List<T> records);

    /**
     * insertBatchSelect 批量插入非空数据数据
     * @param records 数据集合
     * 该方法必须在allowMultiQueries=true才能执行
     */
    void insertBatchSelect(List<T> records);
}