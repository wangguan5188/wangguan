package com.wangguan.util;

import java.util.UUID;

/**
 * JDK自带的获取32位随机字符串工具类
 * 
 * @author zhangtb
 * @date 2016-7-2 15:22:49
 * @since 1.0.0
 */
public class UUIDUtil {
	
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		// return uuid.toString();
		return uuid.toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getUUID());
	}
	
}
