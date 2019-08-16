package org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf;

public class SourceFolder {

	//存放的源文件夹
	private String modelTarget = "";
	
	//存放的源文件夹
	private String mapperTarget = "";
	
	//存放路径
	private String xmlTarget = "";

	public String getModelTarget() {
		return modelTarget;
	}

	public void setModelTarget(String modelTarget) {
		this.modelTarget = modelTarget;
	}

	public String getMapperTarget() {
		return mapperTarget;
	}

	public void setMapperTarget(String mapperTarget) {
		this.mapperTarget = mapperTarget;
	}

	public String getXmlTarget() {
		return xmlTarget;
	}

	public void setXmlTarget(String xmlTarget) {
		this.xmlTarget = xmlTarget;
	}

}
