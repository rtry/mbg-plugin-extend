package org.mybatis.generator.ex.mybatis_generator_maven_plugin.util;

import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.Config;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.dto.DataConvertSuper;

public class DataConvertImpl extends DataConvertSuper {

    private ConfigConvertUtil configConvertUtil;

    public DataConvertImpl(ConfigConvertUtil configConvertUtil) {
        this.configConvertUtil = configConvertUtil;
    }

    @Override
    public Config initConf() {
        return configConvertUtil.readJSONFromFile();
    }

}
