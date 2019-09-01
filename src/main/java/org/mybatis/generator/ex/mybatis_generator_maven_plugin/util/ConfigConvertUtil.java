package org.mybatis.generator.ex.mybatis_generator_maven_plugin.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.Config;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.DataBase;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.DataTable;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.Extend;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.SourceFolder;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.dto.DataConvertSuper;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.conf.dto.DataConvertTestImpl;

import com.alibaba.fastjson.JSON;

public class ConfigConvertUtil {

	String path = "F:\\Git\\mbg-plugin-extend\\src\\test\\java\\generatorTestConfig.json";

	/**
	 * cz:将MG配置对象转换为自定义配置对象. <br>
	 * @author Felicity
	 * @param xml
	 * @return
	 * @since JDK 1.8
	 */
	public Config me2self(Configuration xml) {
		String id = "mge";
		Context context = xml.getContext(id);
		
		Config conf = new Config();
		DataBase db = new DataBase();
		db.setPw(context.getJdbcConnectionConfiguration().getPassword());
		db.setUser(context.getJdbcConnectionConfiguration().getUserId());
		db.setUrl(context.getJdbcConnectionConfiguration().getConnectionURL());
		conf.setDb(db);
		List<DataTable> datas = new ArrayList<>();
		DataTable table = new DataTable();
		datas.add(table);



		Extend extend = new Extend();
		extend.setInsertBatch(false);
		extend.setInsertIfAbsent(true);
		extend.setSelectOption(false);
		table.setExtend(extend);

		SourceFolder folder = new SourceFolder();
		folder.setMapperTarget(context.getJavaClientGeneratorConfiguration().getTargetProject());
		folder.setModelTarget(context.getJavaModelGeneratorConfiguration().getTargetProject());
		folder.setXmlTarget(context.getSqlMapGeneratorConfiguration().getTargetProject());
		table.setFolder(folder);

		table.setClassName(context.getTableConfigurations().get(0).getDomainObjectName());
		table.setTableName(context.getTableConfigurations().get(0).getTableName());

		table.setMapperPkg(context.getJavaClientGeneratorConfiguration().getTargetPackage());
		table.setModelPkg(context.getJavaModelGeneratorConfiguration().getTargetPackage());
		table.setXmlPkg(context.getSqlMapGeneratorConfiguration().getTargetPackage());

		conf.setCurrently(datas);
		conf.setHistory(null);
		return conf;
	}

	/**
	 * self2me:將自定义配置对象转换为ME配置对象
	 * @author Felicity
	 * @return
	 * @since JDK 1.8
	 */
	public Configuration self2me(Config config) {

		// 从文档中读取JSON

		// 将JSON转换为Configuration
		Configuration cfg = new Configuration();
		Context context = new Context(null);
		cfg.addContext(context);

		context.setId("mge");
		context.setTargetRuntime("MyBatis3");

		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.addProperty("suppressDate", "true");
		commentGeneratorConfiguration.addProperty("suppressAllComments", "true");
		context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL("jdbc:mysql://localhost:3306/ace");
		jdbcConnectionConfiguration.setUserId("root");
		jdbcConnectionConfiguration.setPassword("123456");
		jdbcConnectionConfiguration.setDriverClass("com.mysql.jdbc.Driver");
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", "true");
		context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetPackage("xyz.rtry.felicity.business.showcase.model");
		javaModelGeneratorConfiguration.setTargetProject("F:\\git\\felicity\\src\\main\\java");
		javaModelGeneratorConfiguration.addProperty("enableSubPackages", "false");
		javaModelGeneratorConfiguration.addProperty("trimStrings", "false");
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetPackage("mybatis\\mapper\\business\\showcase");
		sqlMapGeneratorConfiguration.setTargetProject("F:\\git\\felicity\\src\\main\\resources");
		sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "false");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		javaClientGeneratorConfiguration.setTargetPackage("xyz.rtry.felicity.business.showcase.mapper");
		javaClientGeneratorConfiguration.setTargetProject("F:\\git\\felicity\\src\\main\\java");
		javaClientGeneratorConfiguration.addProperty("enableSubPackages", "false");
		javaClientGeneratorConfiguration.addProperty("supportCustomInterface",
				"xyz.rtry.felicity.common.web.daosss.BaseInterfaceMapper");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

		TableConfiguration tc = new TableConfiguration(context);
		tc.setTableName("goooogle_paly");
		tc.setDomainObjectName("GooooglePaly");
		tc.addProperty("useActualColumnNames", "false");
		tc.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
		context.addTableConfiguration(tc);
		return cfg;
	}

	/**
	 * writeJSONToFile:将JSON写到文档. <br>
	 * @author Felicity
	 * @param cfg
	 * @since JDK 1.8
	 */
	public void writeJSONToFile(Config cfg) {
		String json = JSON.toJSONString(cfg);
		FileWriter fw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter(path);
			out = new PrintWriter(fw);
			out.write(json);
			out.println();
			fw.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * readJSONFromFile:从文档中读取JSON. <br>
	 * @author Felicity
	 * @return
	 * @since JDK 1.8
	 */
	public Config readJSONFromFile() {
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString);
			}
			reader.close();
			Config f = JSON.parseObject(sb.toString(), Config.class);
			return f;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		DataConvertSuper dcs = new DataConvertTestImpl();
		Config cfg = dcs.initConf();
		ConfigConvertUtil u = new ConfigConvertUtil();
		// u.writeJSONToFile(cfg);
		Config cfg2 = u.readJSONFromFile();
		System.out.println(cfg2);
	}
}
