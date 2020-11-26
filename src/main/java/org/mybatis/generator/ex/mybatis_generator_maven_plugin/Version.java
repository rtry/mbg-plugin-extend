package org.mybatis.generator.ex.mybatis_generator_maven_plugin;

public final class Version {

	//主版本
	public final static int MajorVersion = 0;

	//中版本
	public final static int MinorVersion = 0;

	//小版本
	public final static int RevisionVersion = 4;

	public static String getVersionNumber() {
		return Version.MajorVersion + "." + Version.MinorVersion + "." + Version.RevisionVersion;
	}
}
