package org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf;

import java.util.List;

public class Config {

	// 数据库配置
	private DataBase db;

	// 生成文件 默认文件夹位置
	private SourceFolder folder;

	// 历史存在的表配置
	private List<DataTable> history;

	// 当前需要执行的表配置
	private List<DataTable> currently;

	public DataBase getDb() {
		return db;
	}

	public void setDb(DataBase db) {
		this.db = db;
	}

	public SourceFolder getFolder() {
		return folder;
	}

	public void setFolder(SourceFolder folder) {
		this.folder = folder;
	}

	public List<DataTable> getHistory() {
		return history;
	}

	public void setHistory(List<DataTable> history) {
		this.history = history;
	}

	public List<DataTable> getCurrently() {
		return currently;
	}

	public void setCurrently(List<DataTable> currently) {
		this.currently = currently;
	}

}
