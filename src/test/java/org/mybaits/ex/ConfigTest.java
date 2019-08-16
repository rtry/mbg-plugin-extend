package org.mybaits.ex;

import org.junit.Test;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.Config;

import com.alibaba.fastjson.JSON;

public class ConfigTest {

	@Test
	public void testJSON() {

		Config config = new Config();

		System.out.println(JSON.toJSONString(config));

	}
}
