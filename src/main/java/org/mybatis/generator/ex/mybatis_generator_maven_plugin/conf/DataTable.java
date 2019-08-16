package org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf;

public class DataTable {

	// 表名
	private String name;

	// 模型
	private String modelPkg = "";
	private String mapperPkg = "";
	private String xmlPkg = "";

	// 继承位置配置
	private SourceFolder folder;

	// 继承扩展配置
	private Extend extend;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SourceFolder getFolder() {
		return folder;
	}

	public void setFolder(SourceFolder folder) {
		this.folder = folder;
	}

	public String getModelPkg() {
		return modelPkg;
	}

	public void setModelPkg(String modelPkg) {
		this.modelPkg = modelPkg;
	}

	public String getMapperPkg() {
		return mapperPkg;
	}

	public void setMapperPkg(String mapperPkg) {
		this.mapperPkg = mapperPkg;
	}

	public String getXmlPkg() {
		return xmlPkg;
	}

	public void setXmlPkg(String xmlPkg) {
		this.xmlPkg = xmlPkg;
	}

	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}

}
