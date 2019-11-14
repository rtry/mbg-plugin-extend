package org.mybatis.generator.ex.mybatis_generator_maven_plugin;

public final class Version {

	public final static int MajorVersion = 0;
	public final static int MinorVersion = 0;
	public final static int RevisionVersion = 2;

	public static String getVersionNumber() {
		return Version.MajorVersion + "." + Version.MinorVersion + "." + Version.RevisionVersion;
	}
}
