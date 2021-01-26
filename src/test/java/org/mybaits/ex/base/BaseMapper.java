package org.mybaits.ex.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T, PK extends Serializable, E> {
    /**
     * countByExample:通过Example计算总数
     * @param example 查询条件
     * @return 总条数
     */
    Long countByExample(E example);

    /**
     * deleteByExample:通过Example删除
     * @param example 刪除条件
     * @return 受影响条数
     */
    int deleteByExample(E example);

    /**
     * deleteByPrimaryKey:通过主键删除
     * @param pk 表的主键
     * @return 受影响条数
     */
    int deleteByPrimaryKey(PK pk);

    /**
     * insertSelective:有选择性的插入数据(空数据不会插入)
     * @param record 表对象
     * @return 受影响条数
     */
    int insertSelective(T record);

    /**
     * selectByExample:通过Example查询条件集合
     * @param example 查询条件
     * @return 对象集合
     */
    List<T> selectByExample(E example);

    /**
     * selectByPrimaryKey:通过主键查询单个对象
     * @param id 单表的主键
     * @return 表对象
     */
    T selectByPrimaryKey(PK id);

    /**
     * updateByExampleSelective:通过Example有选择性的更新数据
     * @param record 表对象（不为空的数据都会更新）
     * @param example 更新的条件
     * @return 受影响的条数
     */
    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    /**
     * updateByPrimaryKeySelective:通过主键有选择性的更新对象
     * @param record  表对象（不为空的数据都会更新）对象中必须含有主键
     * @return 受影响条数
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * updateMapByExample 根据条件和Map更新数据
     * @param map key->数据库中字段 value->要改的值
     * @param example 更新的条件
     * @return 受影响条数
     */
    int updateMapByExample(@Param("map") Map<String, Object> map, @Param("example") E example);
}