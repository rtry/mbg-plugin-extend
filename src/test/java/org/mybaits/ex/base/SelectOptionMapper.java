package org.mybaits.ex.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface SelectOptionMapper<T, PK extends Serializable, E> extends ExtendMapper {
    /**
     * selectOptionToList 指定列查询到List集合
     * @param options 查询字段数组(表数据库字段)
     * @param example 查询条件
     * @return 表对象List集合
     */
    List<T> selectOptionToList(@Param("options") String[] options, @Param("example") E example);

    /**
     * selectOptionToOne 指定列查询一个表对象
     * @param options 查询字段数组(表数据库字段)
     * @param example 查询条件
     * @return 表对象
     */
    T selectOptionToOne(@Param("options") String[] options, @Param("example") E example);
}