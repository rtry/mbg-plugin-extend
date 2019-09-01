package org.mybatis.generator.ex.mybatis_generator_maven_plugin.util;

import java.security.MessageDigest;

public class MD5Util {

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {
		return MD5Encode(origin, null, 1, "UTF-8");
	}

	public static String MD5Encode(String origin, String salt, Integer hashIterations,
			String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 是否加盐
			if (salt != null && !salt.trim().equals("")) {
				md.reset();
				md.update(salt.getBytes());
			}

			byte[] source = null;
			if (charsetname == null || charsetname.trim().equals(""))
				source = resultString.getBytes();
			else
				source = resultString.getBytes(charsetname);

			// 循环次数
			byte[] hashed = md.digest(source);
			// already hashed once above
			int iterations = hashIterations - 1;
			// iterate remaining number:
			for (int i = 0; i < iterations; i++) {
				md.reset();
				hashed = md.digest(hashed);
			}

			resultString = byteArrayToHexString(hashed);

		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f" };

}