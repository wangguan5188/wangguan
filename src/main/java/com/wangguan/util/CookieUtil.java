package com.wangguan.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie相关操作工具类
 * 
 * @author zhangtb
 * @date 2016-7-2 15:23:38
 * @since 1.0.0
 */
public class CookieUtil {
	
	/**
     * <p>往客户端浏览器添加一个Cookie</p>
     * @param name cookie的key
     * @param value cookie的value
     * @param maxAge  最长存活时间（单位为秒）。负值表示当关闭浏览器时，该Cookie将被清除；0表示必须立即清除该Cookie。
     * @param response
     */
	public static void addCookie(String name, String value, int maxAge, HttpServletResponse response){
		addCookie(name, value, maxAge, "/", response);
    }
	
	/**
     * <p>往客户端浏览器添加一个Cookie</p>
     * @param name cookie名
     * @param value cookie值
     * @param path 存储路径："/"表示根路径
     * @param maxAge  最长存活时间（单位为秒）
     * @param response
     */
	public static void addCookie(String name, String value, int maxAge, String path, HttpServletResponse response){
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);
    }
	
	/**
	 * <p>从客户端浏览器中清除指定name的Cookie</p>
	 * @param name cookie名
	 * @param response
	 */
	public static void delCookie(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * <p>清除所有Cookie</p>
	 * @param request
	 * @param response
	 */
	public static void clear(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length>0) {
			for (int i=0;i<cookies.length;i++) {
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
	}
	
	/**
	 * <p>获取指定name的Cookie</p>
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(String name, HttpServletRequest request) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			return cookieMap.get(name);
		}
		return null;
	}

	/**
	 * <p>将Cookie封装到Map里面</p>
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

}
