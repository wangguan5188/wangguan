package com.wangguan.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库简单测试类
 * @author zhangtb
 * @date 2015年1月21日14:31:37
 */
public class TestDBConnection {

	public static void main(String[] args) {
		try{
			//将ojdbc6.jar资源载入类加载器
			//注册驱动类
			Class.forName("com.mysql.jdbc.Driver");
			//编写一个连接字符串
			//jdbc:oracle:thin:@IP:1521:SID
			String url = "jdbc:mysql://localhost:3306/jsd1402db?useUnicode=true&characterEncoding=utf8";
			//获取Connection对象
			//getConnection(连接字符串,用户名,密码)
			Connection con = DriverManager.getConnection(url,"root","1234");
			System.out.println(con);
			con.close();//释放连接资源
		}catch(Exception e){
			//e.printStackTrace();
			System.out.println("连接数据库失败.");
		}
	}

}
