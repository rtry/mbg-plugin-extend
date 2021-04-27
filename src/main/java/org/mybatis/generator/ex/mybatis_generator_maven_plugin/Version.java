package org.mybatis.generator.ex.mybatis_generator_maven_plugin;

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
