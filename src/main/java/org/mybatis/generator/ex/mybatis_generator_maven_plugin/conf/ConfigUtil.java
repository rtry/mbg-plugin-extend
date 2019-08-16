package org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf;

public class ConfigUtil {

	private static Config config = null;

	public void loading() {
		if (config == null) {
			// 加载以前的配置
		}
		if (config == null) {
			// 还是空,初始
			config = new Config();
		}
	}

}
