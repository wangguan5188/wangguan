package com.wangguan.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式(regular expression)
 * 参考地址：http://www.oschina.net/code/snippet_1263910_34253
 * @author zhangtb
 * @date 2015年10月21日11:18:26
 */
public class RegexValidateUtil {
	
	/**
	 * <p>验证邮箱地址是否合法</p>
	 * <pre>
     * RegexValidateUtil.isEmail(null) = false
     * RegexValidateUtil.isEmail("") = false
     * RegexValidateUtil.isEmail(" ") = false
     * RegexValidateUtil.isEmail("nihao12@163.com") = true
     * RegexValidateUtil.isEmail(" nihao12@163.com ") = true
	 * </pre>
	 * @param email 被验证的邮箱
	 * @return
	 */
	public static boolean isEmail(String email) {
		if(email == null) return false;
		if(email != null) email = email.trim();
		try {
			String check = "^([a-z0-9A-Z]+[_|-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			return matcher.matches();
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * <p>验证手机号码是否合法</p>
	 * <pre>
     * RegexValidateUtil.isPhoneNO(null) = false
     * RegexValidateUtil.isPhoneNO("") = false
     * RegexValidateUtil.isPhoneNO(" ") = false
     * RegexValidateUtil.isPhoneNO(" sd2342fsf ") = false
     * RegexValidateUtil.isPhoneNO("15098763126") = true
     * RegexValidateUtil.isPhoneNO(" 15098763126 ") = true
     * </pre>
	 * @param mobile 被验证的手机号码
	 * @return
	 */
	public static boolean isPhoneNO(String mobile) {
		if(mobile == null) return false;
		if(mobile != null) mobile = mobile.trim();
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(14[57])|(15[^4,\\D])|(18[0-9])|(17[6-8]))\\d{8}$");
			Matcher m = p.matcher(mobile);
			return m.matches();
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * <p>验证是否是纯数字</p>
	 * <pre>
     * RegexValidateUtil.isNumber(null) = false
     * RegexValidateUtil.isNumber("") = false
     * RegexValidateUtil.isNumber(" ") = false
     * RegexValidateUtil.isNumber("a1233124b") = false
     * RegexValidateUtil.isNumber(" a1233124b ") = false
     * RegexValidateUtil.isNumber("1233124") = true
     * RegexValidateUtil.isNumber(" 1233124 ") = true
     * </pre>
	 * @param number 被验证的数字串
	 * @return
	 */
	public static boolean isNumber(String number) {
		if(number == null) return false;
		if(number != null) number = number.trim();
		try {
			String regex = "[0-9]{1,}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(number);
			return matcher.matches();
		} catch (Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(isPhoneNO("15098763126"));
		//System.out.println(RegexValidateUtil.isEmail(" nihao12@qq.com "));
		System.out.println(isNumber(null));
		System.out.println(isNumber(" "));
		System.out.println(isNumber(" 24234 "));
	}

}
