/**    
 * 文件名：Enums.java    
 *    
 * 版本信息：    
 * 日期：2018年4月25日    
 * Copyright Felicity Corporation 2018 版权所有   
 */
package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

/**
 * 类名称：Enums <br>
 * 类描述: 枚举集合(属性或方法上的注解)<br>
 * 创建人：felicity <br>
 * 创建时间：2018年4月25日 上午10:03:10 <br>
 * 修改人：felicity <br>
 * 修改时间：2018年4月25日 上午10:03:10 <br>
 */
public interface Enums {

    /**
     * 类名称：ExampleProperty <br>
     * 类描述: Example属性注解 <br>
     * 创建人：felicity <br>
     * 创建时间：2019年7月26日 上午10:27:45 <br>
     * 备注:
     */
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
}
