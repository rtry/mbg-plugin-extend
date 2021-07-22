package org.mybatis.generator.ex.mybatis_generator_maven_plugin;

/**
 * @className Version
 * @describe 版本展示
 * @date  2021/07/22 10:32
 */
public final class Version {

    /**
     * 主版本
     */
    public final static int MAJOR_VERSION = 0;

    /**
     * 中版本
     */
    public final static int MINOR_VERSION = 0;

    /**
     * 小版本
     */
    public final static int REVISION_VERSION = 6;

    public static String getVersionNumber() {
        return Version.MAJOR_VERSION + "." + Version.MINOR_VERSION + "." + Version.REVISION_VERSION;
    }
}
