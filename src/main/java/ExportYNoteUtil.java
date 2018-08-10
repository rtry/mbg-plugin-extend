/**
 * Project Name:pt-springboot-ace
 * File Name:ExportUtil.java
 * Package Name:pt.sicau.edu.cn.springboot.ace.common
 * Date:2018年3月18日
 * Copyright (c) 2018, Felicity All Rights Reserved.
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * ClassName: ExportUtil <br>
 * Function: 有道云笔记，导出所有md文件<br>
 * 该文件的对应class 放到git路径下<br>
 * 运行环境jre>=1.7.x<br>
 * 转换时，手动执行 java ExportYNoteUtil 命令<br>
 * date: 2018年3月18日 <br>
 *
 * @author Felicity
 * @version
 * @since JDK 1.8
 */
public class ExportYNoteUtil {

	// work
	private static final String work_storagePath = "C:/Users/Administrator/AppData/Local/YNote/Data/dzpanxiwei@163.com";
	private static final String work_outPath = "F:/git/rtry.github.io/source/_posts";

	// home
	private static final String home_storagePath = "C:/Users/PLZ/AppData/Local/YNote/data/dzpanxiwei@163.com";
	private static final String home_outPath = "F:/Git/rtry.github.io/source/_posts";

	public static void export(String storagePath, String outPath) throws IOException {
		File storagefile = new File(storagePath);
		File outFile = new File(outPath);
		outFile.mkdirs();
		File[] outs = outFile.listFiles();
		if (outs != null && outs.length > 0) {
			for (File temp : outs) {
				temp.delete();
			}
			println("[" + outPath + "] is clear");
		}
		if (!storagefile.isDirectory()) {
			println("[" + storagePath + "] is not directory");
			return;
		}
		File[] subs = storagefile.listFiles();
		for (File sub : subs) {
			if (!sub.isDirectory()) {
				println("[" + sub.getName() + "] is not directory");
				continue;
			}
			File[] files = sub.listFiles();
			for (File file : files) {
				if (file.isFile()) {
					String name = file.getName();
					if (name.endsWith(".md")) {
						String outFileName = outPath + "/" + name;
						// 1.去除临时文件
						if (name.startsWith("(冲突)") || name.startsWith("无标题")) {
							println("[" + file.getName() + "] is auto mk , skip it");
							continue;

						}

						// 2.去除系统生成的空文件
						FileInputStream in = new FileInputStream(file);
						if (in.available() == 0) {
							println("[" + file.getName() + "] is empty , skip it");
							continue;
						}

						// 3.去除不想发布的内容
						FileReader fr = new FileReader(file);
						BufferedReader rd = new BufferedReader(fr);
						if (!rd.readLine().equals("---")) {
							println("[" + file.getName() + "] Not allow public");
							rd.close();
							fr.close();
							continue;
						}
						rd.close();
						fr.close();

						FileOutputStream out = new FileOutputStream(outFileName);
						byte[] b = new byte[1024];
						int n = 0;
						while ((n = in.read(b)) != -1) {
							out.write(b, 0, n);
						}
						out.close();
						in.close();
					}
				}
			}
		}
	}

	private static void println(String str) {
		if (str != null) {
			try {
				System.out.println(URLEncoder.encode(str, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			String storagePath;
			String outPath;
			if (args == null || args.length == 0 || args[0].equalsIgnoreCase("w")) {
				println("工作空间...");
				storagePath = work_storagePath;
				outPath = work_outPath;
			} else if (args[0].equalsIgnoreCase("h")) {
				println("家庭空间...");
				storagePath = home_storagePath;
				outPath = home_outPath;
			} else {
				println("工作空间...");
				storagePath = work_storagePath;
				outPath = work_outPath;
			}
			ExportYNoteUtil.export(storagePath, outPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
