package com.wangguan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密工具类，采用标准md5算法
 * @author zhangtb
 * @date 2015年6月19日11:39:53
 */
public class MD5Util {
	
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static MessageDigest messagedigest = null;
	
	static {
		try {
			messagedigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException nsaex) {
			nsaex.printStackTrace();
			System.err.println(MD5Util.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
		}
	}
	
	/**
	 * <p>标准md5密码加密</p>
	 * MD5Util.encode(null) = "";
	 * MD5Util.encode("") = "";
	 * MD5Util.encode(" ") = "";
	 * MD5Util.encode("123456") = "e10adc3949ba59abbe56e057f20f883e";
	 * MD5Util.encode(" 123456 ") = "e10adc3949ba59abbe56e057f20f883e";
	 * @param text 要进行加密的文本字符串
	 * @return
	 */
	public static String encode(String text) {
		if(text != null) text = text.trim();
		if(text==null || "".equals(text)) return "";
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(text.getBytes());
			StringBuffer sb = new StringBuffer();
			for(byte b : result) {
				int number = b&0xff;
				String hex = Integer.toHexString(number);
				if(hex.length() == 1) {
					sb.append("0" + hex);
				} else {
					sb.append(hex);
				}
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException nsae) {
			//nsae.printStackTrace();
			return "";
		}
	}
	
	/**
	 * <p>加盐版的MD5，返回格式为MD5(密码+{盐值})</p>
	 * @param password 要加密的文本字符串
	 * @param salt 盐值
	 * @return
	 */
	public static String getMD5StringWithSalt(String password, String salt) {
		if (password == null) {
			throw new IllegalArgumentException("password不能为null");
		}
		if (salt!=null && !"".equals(salt)) {
			throw new IllegalArgumentException("salt不能为空");
		}
		if ((salt.toString().lastIndexOf("{") != -1) || (salt.toString().lastIndexOf("}") != -1)) {
			throw new IllegalArgumentException("salt中不能包含 { 或者 }");
		}
		return getMD5String(password + "{" + salt.toString() + "}");
	}

	/**
	 * <p>得到一个文件的md5值</p>
	 * @param file 文件对象
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String getFileMD5String(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}

	/**
	 * <p>得到一个字符串的MD5值</p>
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
		return getMD5String(str.getBytes());
	}

	private static String getMD5String(byte[] bytes) {
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}
	
	public static void main(String[] args) {
		//e10adc3949ba59abbe56e057f20f883e
		System.out.println(MD5Util.encode(" "));
		//System.out.println(MD5Util.encode(null));
		//System.out.println(MD5Util.encode("123456"));
		//String result = "dj" + MD5Util.encode("123456") + "dj";
		//32613ee690862a01891cbd66a8e20566
		//System.out.println(MD5Util.encode(result));
	}

}
