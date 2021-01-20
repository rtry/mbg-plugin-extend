package org.mybaits.ex.base;

import java.util.List;

public interface UpdateBatchMapper<T> extends ExtendMapper {
    /**
     * updateBatchById 根据ID批量更新各对象非空的属性
     * records 数据集合 -【必须存在ID】
     * 该方法必须在allowMultiQueries=true才能执行
     */
    void updateBatchById(List<T> records);
}