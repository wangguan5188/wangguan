package com.wangguan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取属性文件数据工具类
 * 
 * @author zhangtb
 * @date 2016-7-2 15:21:55
 * @since 1.0.0
 */
public class PropertiesUtil {

    // 系统变量存放区
    private static Properties properties = null;

    // 系统资源字符串
    private static final String PROPERTIES_RESOURCES = "application.properties";

    private static void init() {
    	properties = new Properties();
        ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
        // 加载数据字典分类初始化文件
        InputStream stream = classLoader.getResourceAsStream(PROPERTIES_RESOURCES);
        try {
        	properties.load(stream);
        } catch (IOException e) {
            System.err.println("加载properties属性文件失败。");
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static String getProperties(String key){
        if (properties == null) {
        	init();
        }
        String value = properties.getProperty(key);
        return value == null ? "" : value;
    }
    
    public static void main(String[] args) {
		String myapp = PropertiesUtil.getProperties("myapp");
		System.out.println(myapp);
	}
    
}
