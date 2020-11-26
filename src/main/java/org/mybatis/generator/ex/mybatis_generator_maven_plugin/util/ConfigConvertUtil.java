package org.mybatis.generator.ex.mybatis_generator_maven_plugin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ConfigConvertUtil {

	private File cff;
	String baseFile = "";

	public ConfigConvertUtil(File cff, String baseFile) {
		super();
		this.cff = cff;
		this.baseFile = baseFile;
	}

	/**
	 * me2self:将MG配置对象转换为自定义配置对象. <br>
	 *
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
	 *
	 * @author Felicity
	 * @return
	 * @since JDK 1.8
	 */
	public Configuration self2me(Config config, String baseFile) {

		// 基本校验
		if (config == null || config.getDb() == null || config.getCurrently() == null
				|| config.getCurrently().size() == 0 || baseFile == null || baseFile.trim() == "")
			return null;

		// 待返回对象
		Configuration cfg = new Configuration();

		StringBuffer sb = new StringBuffer();
		// 进行分组
		Map<String, List<DataTable>> rt = config
				.getCurrently()
				.stream()
				.collect(
						Collectors.groupingBy(e -> {
							sb.setLength(0);
							sb.append(e.getMapperPkg()).append(e.getModelPkg())
									.append(e.getXmlPkg()).append(e.getExtend().isInsertBatch())
									.append(e.getExtend().isInsertIfAbsent())
									.append(e.getExtend().isSelectOption())
									.append(e.getFolder().getMapperTarget())
									.append(e.getFolder().getModelTarget())
									.append(e.getFolder().getXmlTarget());
							String md5 = MD5Util.MD5Encode(sb.toString());
							return md5;
						}));

		// 基本-CommentGeneratorConfiguration
		CommentGeneratorConfiguration commentGeneratorConfiguration = new CommentGeneratorConfiguration();
		commentGeneratorConfiguration.addProperty("suppressDate", "true");
		commentGeneratorConfiguration.addProperty("suppressAllComments", "true");

		// 基本-JDBCConnectionConfiguration
		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(config.getDb().getUrl());
		jdbcConnectionConfiguration.setUserId(config.getDb().getUser());
		jdbcConnectionConfiguration.setPassword(config.getDb().getPw());
		jdbcConnectionConfiguration.setDriverClass(config.getDb().getDriver());
		// 基本-JDBCConnectionConfiguration 中增加自定义数据
		jdbcConnectionConfiguration.addProperty(BIConstant.SCI, config.getDb().getMapperClass());
		jdbcConnectionConfiguration.addProperty(BIConstant.FILE_TINY2INT, config.getDb().isTiny2int() + "");
		jdbcConnectionConfiguration.addProperty(BIConstant.FILE_SMALL2INT, config.getDb().isSmall2int()+"");
		jdbcConnectionConfiguration.addProperty(BIConstant.supportLombok, config.getDb().isSupportLombok()+"");
		jdbcConnectionConfiguration.addProperty(BIConstant.notBuildBaseMapper, config.getDb().isNotBuildBaseMapper()+"");

		// 基本-JavaTypeResolverConfiguration
		JavaTypeResolverConfiguration javaTypeResolverConfiguration = new JavaTypeResolverConfiguration();
		javaTypeResolverConfiguration.addProperty("forceBigDecimals", "true");

		// 遍历组
		Set<String> keys = rt.keySet();
		for (String key : keys) {
			List<DataTable> values = rt.get(key);
			// 一个key 对应一个context
			Context context = new Context(null);
			cfg.addContext(context);
			context.setId(key);
			context.setTargetRuntime("MyBatis3");

			context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);
			context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
			context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

			JavaModelGeneratorConfiguration jmgc = this.getjavaModelGeneratorConfiguration(key,
					values, baseFile);
			context.setJavaModelGeneratorConfiguration(jmgc);

			SqlMapGeneratorConfiguration cgc = this.getsqlMapGeneratorConfiguration(key, values,
					baseFile);
			context.setSqlMapGeneratorConfiguration(cgc);

			JavaClientGeneratorConfiguration jcgc = this.getJavaClientGeneratorConfiguration(key,
					values, baseFile);
			context.setJavaClientGeneratorConfiguration(jcgc);

			boolean flag = !config.getDb().isAutoFile();
			// 所包含的相同的表配置文件
			for (DataTable dt : values) {
				TableConfiguration tc = new TableConfiguration(context);
				tc.setTableName(dt.getTableName());
				tc.setDomainObjectName(dt.getClassName());
				tc.addProperty("useActualColumnNames", flag + "");
				
                //FIXME 此处，需要根据 表SQL 查找到主键 
				tc.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
                
				tc.addProperty("insertBatch", dt.getExtend().isInsertBatch() + "");
				tc.addProperty("insertIfAbsent", dt.getExtend().isInsertIfAbsent() + "");
				tc.addProperty("selectOption", dt.getExtend().isSelectOption() + "");
				context.addTableConfiguration(tc);
			}
		}

		return cfg;
	}

	Map<String, JavaClientGeneratorConfiguration> javaClientGeneratorConfigurations = new HashMap<String, JavaClientGeneratorConfiguration>();
	Map<String, SqlMapGeneratorConfiguration> sqlMapGeneratorConfigurations = new HashMap<String, SqlMapGeneratorConfiguration>();
	Map<String, JavaModelGeneratorConfiguration> javaModelGeneratorConfigurations = new HashMap<String, JavaModelGeneratorConfiguration>();

	private JavaClientGeneratorConfiguration getJavaClientGeneratorConfiguration(String key,
			List<DataTable> values, String baseFile) {
		if (javaClientGeneratorConfigurations.containsKey(key))
			return javaClientGeneratorConfigurations.get(key);
		else {
			DataTable dt = values.get(0);
			JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
			javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
			javaClientGeneratorConfiguration.setTargetPackage(dt.getMapperPkg());
			javaClientGeneratorConfiguration.setTargetProject(baseFile
					+ dt.getFolder().getMapperTarget());
			javaClientGeneratorConfiguration.addProperty("enableSubPackages", "false");
			return javaClientGeneratorConfiguration;
		}
	}

	private SqlMapGeneratorConfiguration getsqlMapGeneratorConfiguration(String key,
			List<DataTable> values, String baseFile) {
		if (sqlMapGeneratorConfigurations.containsKey(key))
			return sqlMapGeneratorConfigurations.get(key);
		else {
			DataTable dt = values.get(0);
			SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
			sqlMapGeneratorConfiguration.setTargetPackage(dt.getXmlPkg());
			sqlMapGeneratorConfiguration.setTargetProject(baseFile + dt.getFolder().getXmlTarget());
			sqlMapGeneratorConfiguration.addProperty("enableSubPackages", "false");
			sqlMapGeneratorConfigurations.put(key, sqlMapGeneratorConfiguration);
			return sqlMapGeneratorConfiguration;
		}
	}

	private JavaModelGeneratorConfiguration getjavaModelGeneratorConfiguration(String key,
			List<DataTable> values, String baseFile) {
		if (javaModelGeneratorConfigurations.containsKey(key))
			return javaModelGeneratorConfigurations.get(key);
		else {
			DataTable dt = values.get(0);
			JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
			javaModelGeneratorConfiguration.setTargetPackage(dt.getModelPkg());
			javaModelGeneratorConfiguration.setTargetProject(baseFile
					+ dt.getFolder().getModelTarget());
			javaModelGeneratorConfiguration.addProperty("enableSubPackages", "false");
			javaModelGeneratorConfiguration.addProperty("trimStrings", "false");
			javaModelGeneratorConfigurations.put(key, javaModelGeneratorConfiguration);
			return javaModelGeneratorConfiguration;
		}
	}

	/**
	 * writeJSONToFile:将JSON写到文档. <br>
	 *
	 * @author Felicity
	 * @param cfg
	 * @since JDK 1.8
	 */
	public void writeJSONToFile(Config cfg) {
		String json = JSON.toJSONString(cfg);

		// 美化配置
		JSONObject object = JSONObject.parseObject(json);
		String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat,
				SerializerFeature.WriteMapNullValue, SerializerFeature.WriteDateUseDateFormat);

		FileWriter fw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter(cff);
			out = new PrintWriter(fw);
//			out.write(json);
			out.write(pretty);
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
	 *
	 * @author Felicity
	 * @return
	 * @since JDK 1.8
	 */
	public Config readJSONFromFile() {
		if (!cff.exists()) {
			System.out.println("项目第一次创建：新建文件");
			return new Config();
		}
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(cff);
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
}
