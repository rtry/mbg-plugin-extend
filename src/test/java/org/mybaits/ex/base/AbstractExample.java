package org.mybaits.ex.base; 

import java.util.ArrayList;
import java.util.List;

/**
 * @className AbstractExample
 * @describe 查询条件超类df
 * @author 由 MBG(mybatis generator plug) 生成<br>
 */
public abstract class AbstractExample {

    /**
     * 排序方式
     */
    protected String orderByClause;

    /**
     * 是否去重
     */
    protected boolean distinct;

    /**
     * 查询条件集合
     */
    protected List<AbstractCriteria> oredCriteria;

    /**
     * 构造方法
     */
    public AbstractExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * getOredCriteria
     */
    public List<AbstractCriteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * setOrderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * getOrderByClause
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * setDistinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * isDistinct
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * clear
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * @className AbstractExample
     * @describe 静态类 内部构造条件
     * @author 由 MBG(mybatis generator plug) 生成<br>
     */
    public static class AbstractCriteria {

        protected List<Criterion> criteria;

        public AbstractCriteria() {
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

    }

    /**
     * @className AbstractExample
     * @describe 内部静态类
     * @author 由 MBG(mybatis generator plug) 生成<br>
     */
    public static class Criterion {

        /**
         * 条件
         */
        private String condition;

        /**
         * 值
         */
        private Object value;

        /**
         * 第二值
         */
        private Object secondValue;

        /**
         * 没值
         */
        private boolean noValue;

        /**
         * 单独值
         */
        private boolean singleValue;

        /**
         * between
         */
        private boolean betweenValue;

        /**
         * list
         */
        private boolean listValue;

        /**
         * type
         */
        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        public Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        public Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
