/**
 * Project Name:pt-springboot-ace
 * File Name:ExportUtil.java
 * Package Name:pt.sicau.edu.cn.springboot.ace.common
 * Date:2018年3月18日
 * Copyright (c) 2018, Felicity All Rights Reserved.
 *
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

	private static final String upload_path = "uploads";

	// 转存成功所有路径
	public static List<String> dirs = new ArrayList<String>();

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
							// println("[" + file.getName() +
							// "] is auto mk , skip it");
							continue;
						}

						// 2.去除系统生成的空文件
						FileInputStream in = new FileInputStream(file);
						if (in.available() == 0) {
							// println("[" + file.getName() +
							// "] is empty , skip it");
							continue;
						}
						in.close();

						String saveDirBase = outPath.substring(0, outPath.length() - 6);
						// 3.去除不想发布的内容
//						FileReader fr = new FileReader(file);
						InputStreamReader fr = new InputStreamReader(new FileInputStream(file), "UTF-8");
						BufferedReader rd = new BufferedReader(fr);
						String line = "";
						if (!(line = rd.readLine()).equals("---")) {
							// 展示不转换的文件
							rd.close();
							fr.close();
							continue;
						} else {
							// 单个需要转换的文件
//							FileWriter fw = new FileWriter(outFileName);
							OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(outFileName),"UTF-8");
							BufferedWriter wb = new BufferedWriter(fw);
							String nName = name.toLowerCase().replaceAll("[\u4e00-\u9fa5]", "");
							nName = nName.substring(0, nName.length() - 3).trim();

							String saveDir = saveDirBase + upload_path + "/" + nName;
							println("正在处理文件：" + name);
							do {

								if (line.indexOf("![") != -1) {
									int start = line.indexOf("](");
									int last = line.indexOf(")");
									// IMG URL 地址
									String url = line.substring(start + 2, last);
									// IMG Simple Name
									String fileName = url.substring(url.lastIndexOf("/") + 1,
											url.length());
									// 修改后的单行数据

									String modifyLine = line.substring(0, start) + "](/"
											+ upload_path + "/" + nName + "/" + fileName
											+ line.substring(last, line.length());

									download(url, saveDir, fileName);

									line = modifyLine;
									// System.out.println(modifyLine);
								}
								// 写之前转换回来
								wb.write(line);
								wb.newLine();
							} while (((line = rd.readLine()) != null));
							System.out.println("================================");
							wb.close();
							fw.close();

						}
						rd.close();
						fr.close();
						dirs.add(name);
					}
				}
			}
		}	
		println("==========成功转换" + dirs.size() + "============");
		// for (String s : dirs) {
		// println(s);
		// }
	}

	private static void println(String str) {
		try {
			System.out.println(new String(str.getBytes("UTF-8"), Charset.defaultCharset()));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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

	public static void download(String url, String saveDir, String fileName) {

		if(!url.startsWith("http")){
			return;
		}
		
		BufferedOutputStream bos = null;
		InputStream is = null;
		try {
			File file = new File(saveDir, fileName);
			if (file.exists()) {
				return;
			}
			println("=========正在下载：" + fileName + "=========");
			file.getParentFile().mkdirs();

			byte[] buff = new byte[2048];
			URLConnection conn = new URL(url).openConnection();
			conn.connect();
			is = conn.getInputStream();
			bos = new BufferedOutputStream(new FileOutputStream(file));
			int count = 0;
			while ((count = is.read(buff)) != -1) {
				bos.write(buff, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
