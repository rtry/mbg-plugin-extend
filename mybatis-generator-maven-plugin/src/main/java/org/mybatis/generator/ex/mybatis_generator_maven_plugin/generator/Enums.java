/**    
 * 文件名：Enums.java    
 *    
 * 版本信息：    
 * 日期：2018年4月25日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.Enums.ExampleProperty;

/**
 * 类名称：Enums <br>
 * 类描述: 枚举集合(属性或方法上的注解)<br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月25日 上午10:03:10 <br>
 * 修改人：felicity <br>
 * 修改时间：2018年4月25日 上午10:03:10 <br>
 * 修改备注:
 * @version
 * @see
 */
public interface Enums {

	public enum ExampleProperty {

		orderByClause("orderByClause", "排序方式"),

		distinct("distinct", "是否去重"),

		oredCriteria("oredCriteria", "查询条件集合");

		private String name;

		private String des;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}

		ExampleProperty(String name, String str) {
			this.name = name;
			this.des = str;
		}

		public static ExampleProperty get(String name) {
			ExampleProperty[] es = ExampleProperty.values();
			for (ExampleProperty e : es) {
				if (e.getName().equals(name)) {
					return e;
				}
			}
			return null;
		}

	}

	public enum MapperMethod {

		countByExample("countByExample", "通过Example计算总数"),

		deleteByExample("deleteByExample", "通过Example删除"),

		deleteByPrimaryKey("deleteByPrimaryKey", "通过主键删除"),

		insert("insert", "插入数据"),

		insertSelective("insertSelective", "有选择性的插入数据"),

		selectByExample("selectByExample", "通过Example查询条件集合"),

		selectByPrimaryKey("selectByPrimaryKey", "通过主键查询单个对象"),

		updateByExampleSelective("updateByExampleSelective", "通过Example有选择性的更新数据"),

		updateByExample("updateByExample", "通过Example更新数据"),

		updateByPrimaryKeySelective("updateByPrimaryKeySelective", "通过主键有选择性的更新对象"),

		updateByPrimaryKey("updateByPrimaryKey", "通过主键更新对象");

		private String name;

		private String des;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDes() {
			return des;
		}

		public void setDes(String des) {
			this.des = des;
		}

		MapperMethod(String name, String str) {
			this.name = name;
			this.des = str;
		}

		public static MapperMethod get(String name) {
			MapperMethod[] es = MapperMethod.values();
			for (MapperMethod e : es) {
				if (e.getName().equals(name)) {
					return e;
				}
			}
			return null;
		}

	}
}
