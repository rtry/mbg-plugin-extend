package org.mybatis.generator.ex.mybatis_generator_maven_plugin.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.BIConstant;
import org.mybatis.generator.ex.mybatis_generator_maven_plugin.generator.mybatis3.javamapper.extend.ExtendUtil;

/**
 * 类名称：RegexUtil <br>
 * 类描述: 正则表达式计算新旧接口 <br>
 * 创建人：felicity <br>
 * 创建时间：2019年9月3日 下午3:27:09 <br>
 * 备注:
 * @version
 * @see
 */
public class RegexUtil {

	// 泛型括号匹配
	public static final String regxFx = "\\<(.*?)\\>";
	// 接口 extends 到 { 之间的内容
	public static final String regx = "extends[\\s\\S]*\\{";
	// 打印
	private static final String br = "[info] ------------------------------------------------------------------------";

	/**
	 * log 打印日志
	 * @param str 日志内容
	 * @Exception 异常描述
	 */
	private static void log(String str) {
		System.out.println(br);
		System.out.println("[info] " + str);
	}

	/**
	 * getOldExtends 获取原文件的扩展
	 * @param fileCode 整个Mapper 字符串
	 * @return 接口字符数组
	 * @Exception 异常描述
	 */
	public static Set<String> getOldExtends(String fileCode) {
		Set<String> rt = new HashSet<String>();

		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(fileCode);
		while (matcher.find()) {
			String res = matcher.group();
			// 取出所有继承接口
			String t1 = res.substring("extends".length(), res.length() - 1).trim();
			String t2 = t1.replaceAll(regxFx, "");

			// 分割，判断
			String olds[] = t2.split(",");

			for (String old : olds)
				rt.add(old.trim());
			log("找到原Mapper 所有继承接口");
		}
		return rt;
	}

	/**
	 * remove 移除 目标指定接口
	 * @param fileCode 整个Mapper 字符串
	 * @param result 待移除接口字符数组
	 * @return
	 * @Exception 异常描述
	 */
	public static String remove(String fileCode, Set<String> result) {

		if (result.isEmpty())
			log("不用移除原Mapper任何接口");

		for (String it : result) {
			// 移除继承接口
			String regx1 = it + "\\<(.*?)\\>,?";
			fileCode = fileCode.replaceAll(regx1, "");
			// 移除引用
			String regx2 = "import.*" + it + ";";
			fileCode = fileCode.replaceAll(regx2, "");
			log("正在移除原Mapper[" + it + "]接口");
		}
		// 替换最后一个逗号
		String regx3 = ",\\s*\\{";
		fileCode = fileCode.replaceAll(regx3, " {");
		return fileCode;
	}

	/**
	 * add 新加 目标 指定接口
	 * @param filCode 整个Mapper 字符串
	 * @param result 指定接口字符集合
	 * @param gex
	 * @param eUtil
	 * @return
	 * @Exception 异常描述
	 */
	public static String add(String filCode, Set<String> result, Set<String> gex, ExtendUtil eUtil) {
		if (result.isEmpty())
			log("不用新加Mapper任何接口");

		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(filCode);
		int start = 0;
		StringBuffer sb = new StringBuffer(filCode);
		while (matcher.find()) {
			start = matcher.start();
			break;
		}
		if (start != 0) {

			Map<String, String> allPkg = new HashMap<>();
			allPkg.put(BIConstant.ExtendInsertIfAbsentClass, eUtil.getIfAbsentClassName());
			allPkg.put(BIConstant.ExtendSelectOptionClass, eUtil.getSelectOptionClassName());
			allPkg.put(BIConstant.ExtendInsertBatchClass, eUtil.getInsertBatchClassName());
			allPkg.put(BIConstant.ExtendUpdateByIdClass, eUtil.getUpdateBatchClassName());

			// 1 添加新的继承
			for (String iterf : result) {
				Optional<String> op = gex.stream().filter(e -> e.startsWith(iterf)).findFirst();
				if (op.isPresent()) {
					// 找到extends 属性，写到最前面
					String ext = " " + op.get() + ", ";
					sb.insert(start + "extends".length(), ext);

					log("正在新加Mapper[" + iterf + "]继承接口");

				}
			}
			// ------------------------------------------
			// 分两次循环因为前面的操作会导致 start 下标移位
			// ------------------------------------------
			// 2 添加新的引入
			for (String iterf : result) {
				String impt = allPkg.get(iterf);

				// 找到import
				String regx2 = "\nimport";
				Pattern pattern2 = Pattern.compile(regx2);
				Matcher matcher2 = pattern2.matcher(sb.toString());
				int start2 = 0;
				while (matcher2.find()) {
					start2 = matcher2.start();
					break;
				}
				if (start2 != 0) {
					sb.insert(start2, "\nimport " + impt + ";");
				}
				log("正在新加Mapper[" + iterf + "]接口引入");
			}
		}

		return sb.toString();
	}
}
