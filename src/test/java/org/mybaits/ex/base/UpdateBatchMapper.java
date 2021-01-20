package org.mybaits.ex.base;

import java.util.List;

public interface UpdateBatchMapper<T> extends ExtendMapper {
    /**
     * updateBatchById 更新ID批量更新各对象的属性
     * records 数据集合 -【必须存在ID】
     */
    void updateBatchById(List<T> records);
}