package com.wangguan.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangtb
 * @date 2016-7-2 15:22:58
 * @since 1.0.0
 */
public class WebUtil {
	
	/**
	 * <p>获取request请求的参数</p>
	 * @param request
	 * @param params 表单的name值数组
	 * @return
	 */
	public static Map<String, Object> getRequestParameter(String[] params, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String param : params) {
			String value = request.getParameter(param);
			if (value != null) {
				map.put(param, value);
			}
		}
		return map;
	}
	
}
