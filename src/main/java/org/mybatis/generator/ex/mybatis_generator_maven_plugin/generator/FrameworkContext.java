package org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator;

import java.util.Map;

/**
 * @author panxw
 * @className FrameworkContext
 * @describe 用户线程 - 上下文
 * @date 2020年7月26日 上午10:12:29
 */
public class FrameworkContext {

    /**
     * userContext:当前请求线程绑定的数据集合
     */
    private static ThreadLocal<Map<String, Object>> threadLocalMap = new ThreadLocal<>();

    /**
     * set 往上下文中存入key-value,
     *
     * @param key   存入值的key
     * @param value 存入的值
     * @author panxw
     */
    public static void set(String key, Object value) {
        threadLocalMap.get().putIfAbsent(key, value);
    }

    /**
     * get 从上下文拿去值
     *
     * @param key 放入上下文的KEY
     * @return java.lang.Object
     * @author panxw
     */
    public static Object get(String key) {
        return threadLocalMap.get().get(key);
    }

    /**
     * init 初始化上下文，必须是第一个调用，在系统内部调用，外部请勿调用
     *
     * @param initialContext 初始上下文
     * @author panxw
     */
    public static void init(Map<String, Object> initialContext) {
        //初始化时，一定要先清空，因为可能存在，线程池复用
        threadLocalMap.remove();
        //初始值
        threadLocalMap.set(initialContext);
    }

}
