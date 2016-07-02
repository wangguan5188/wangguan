package com.wangguan.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * 未完成，有待测试
 * @author zhangtb
 * @date 2015年10月21日18:39:45
 */
public class TreeMapUtil {
	
	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>();
		int num = 1000000;
		String key, value;
		// 存放100万条数据
		for (int i = 1; i <= num; i++) {
			key = "" + i;
			value = "value";
			map.put(key, value);
		}
		// -------------------遍历 方法时间比较--------------------
		long s = System.currentTimeMillis();
		Iterator<String> iter1 = map.keySet().iterator();
		while (iter1.hasNext()) {
			key = iter1.next();
			value = map.get(key);
		}
		// 测试时间：200
		for (String key1 : map.keySet()) {
			value = map.get(key1);
		}
		// 测试时间：219
		Iterator<Entry<String, String>> iter2 = map.entrySet().iterator();
		Entry<String, String> entry;
		while (iter2.hasNext()) {
			entry = iter2.next();
			key = entry.getKey();
			value = entry.getValue();
		}
		// 测试时间：41
		for (Entry<String, String> entry1 : map.entrySet()) {
			key = entry1.getKey();
			value = entry1.getValue();
		}
		// 测试时间：40
		long e = System.currentTimeMillis();
		System.out.println("测试时间：" + (e - s));
	}

}
