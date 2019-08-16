package org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf;

public class Extend {

	private boolean insertBatch;

	private boolean selectOption;

	private boolean insertIfAbsent;

	public boolean isInsertBatch() {
		return insertBatch;
	}

	public void setInsertBatch(boolean insertBatch) {
		this.insertBatch = insertBatch;
	}

	public boolean isSelectOption() {
		return selectOption;
	}

	public void setSelectOption(boolean selectOption) {
		this.selectOption = selectOption;
	}

	public boolean isInsertIfAbsent() {
		return insertIfAbsent;
	}

	public void setInsertIfAbsent(boolean insertIfAbsent) {
		this.insertIfAbsent = insertIfAbsent;
	}

}
