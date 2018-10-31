public interface BaseMapper<T, PK extends Serializable, E> {

	/**
	 * 通过Example计算总数
	 */
	PK countByExample(E example);

	/**
	 * 通过Example删除
	 */
	int deleteByExample(E example);

	/**
	 * 通过主键删除
	 */
	int deleteByPrimaryKey(PK id);

	/**
	 * 插入数据
	 */

	int insert(T record);

	/**
	 * 有选择性的插入数据
	 */
	int insertSelective(T record);

	/**
	 * 通过Example查询条件集合
	 */

	List<T> selectByExample(E example);

	/**
	 * 通过主键查询单个对象
	 */
	T selectByPrimaryKey(PK id);

	/**
	 * 通过Example有选择性的更新数据
	 */
	int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	/**
	 * 通过Example更新数据
	 */
	int updateByExample(@Param("record") T record, @Param("example") E example);

	/**
	 * 通过主键有选择性的更新对象
	 */

	int updateByPrimaryKeySelective(T record);

	/**
	 * 通过主键更新对象
	 */
	int updateByPrimaryKey(T record);
}
