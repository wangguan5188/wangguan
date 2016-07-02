package com.wangguan.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session相关操作工具类
 * 
 * @author zhangtb
 * @date 2016-7-2 15:23:24
 * @since 1.0.0
 */
public class SessionUtil {

	/**
	 * <pre>获取session</pre>
	 * @param request
	 * @return
	 */
	public static HttpSession getSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession();
		}
		return session;
	}

	/**
	 * <pre>将相关的数据绑定到session</pre>
	 * @param request
	 * @param name
	 * @param value
	 * @param time
	 */
	public static void set(HttpServletRequest request, String name, Object value, int time) {
		HttpSession session = getSession(request);
		session.setAttribute(name, value);
		session.setMaxInactiveInterval(time);
	}

	/**
	 * <pre>从session中获取绑定的数据</pre>
	 * @param <T>
	 * @param request
	 * @param name 绑定名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(HttpServletRequest request, String name) {
		HttpSession session = getSession(request);
		return (T) session.getAttribute(name);
	}

}
