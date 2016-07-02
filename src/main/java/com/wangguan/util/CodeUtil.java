package com.wangguan.util;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.StringTokenizer;

/**
 * 码处理工具类
 */
public class CodeUtil {
	public static final String SPACER = " ", EMPTYstr = "", TAB = "\t";

	private static final char[] Digit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	private static final String[][] h2t = { // specReplacecode
	{ "<", "&lt;" }, { ">", "&gt;" }, { " ", "&nbsp;" }, { "\"", "&quot;" },
			{ "\n", "<br>" }, { "'", "&#39;" }, { ":", "&#58;" },
			{ "\"", "&#34;" } };

	// private static final String[][] replacecode = {
	//			{ "[b]", "<b>" },{ "[/b]", "</b>" },
	//			{ "[i]", "<i>" },{ "[/i]", "</i>" },
	//			{ "[u]", "<u>" },{ "[/u]", "</u>" },
	//			{ "[s]", "<s>" },{ "[/s]", "</s>" },
	//			{ "[sup]", "<sup>" },{ "[/sup]", "</sup>" },
	//			{ "[sub]", "<sub>" },{ "[/sub]", "</sub>" },
	//			{ "[img]", "<img src=" },{ "[/img]", " border=0>" },
	//			{ "[list]", "<ul>" },{ "[/list]", "</ul>" },
	//			{ "[*]", "<li>" },{ "[/*]", "</li>" },
	//			{ "\n", "<br>" },
	//			// ----------------------------------------------------------------
	//			{ "[fly]", "<marquee width=90% behavior=alternate scrollamount=3>" },
	//			{ "[/fly]", "</marquee><input type=hidden name=replace_fly>" },
	//			{ "[move]", "<marquee width=90% scrollamount=3>" },
	//			{ "[/move]", "</marquee><input type=hidden name=replace_move>" },
	//			{ "[FLIPH]", "<table style=filter:flipH>" },
	//			{ "[/FLIPH]", "</table><input type=hidden name=replace_fliph>" },
	//			{ "[FLIPV]", "<table style=filter:flipV>" },
	//			{ "[/FLIPV]", "</table><input type=hidden name=replace_flipv>" },
	//			{ "[INVERT]", "<table style=filter:invert>" },
	//			{ "[/INVERT]", "</table><input type=hidden name=replace_invert>" },
	//			{ "[XRAY]", "<table style=filter:xray>" },
	//			{ "[/XRAY]", "</table><input type=hidden name=replace_xray>" },
	//			{ "[quote]","<table cellpadding=5 cellspacing=1 border=0><tr><td bgcolor=#EFF5F3>" },
	//			{ "[/quote]", "</td></tr></table>" },
	//			// -------------------------------------------------------------------
	//			{ "[sound]", "<bgsound src=" },
	//			{ "[/sound]", "loop=1>" },
	//			{ "[code]","<br><blockquote><font face=宋体>代码：</font><hr><font face=宋体><pre>" },
	//			{ "[/code]", "</pre></font><hr></blockquote><br>" },
	//			{ "[hr]", "<hr width=40% align=left>" }
	//	 	};
	//	 private static final String[][] s = { //specReplacecode
	//			{ "[url=", "[/url]", "<a href=", " target=_blank>", "</a>" },
	//			{ "[email=", "[/email]", "<a href=mailto:", ">", "</a>" },
	//			{ "[size=", "[/size]", "<font size=", ">", "</font>" },
	//			{ "[font=", "[/font]", "<font face=", ">", "</font>" },
	//			{ "[color=", "[/color]", "<font color=", ">", "</font>" },
	//			{ "[align=", "[/align]", "<div align=", ">", "</div>" },
	//		};
	//	public static String NpCode2Htm(String s1) {		
	//		for (int i = 0; i < replacecode.length; i++)
	//			s1 = Replace(s1, replacecode[i][0], replacecode[i][1]);
	//		String s27, s28, strEnd = "]";
	//		int i, j, k, l;
	//		for (int m = 0; m < s.length; m++) {
	//			s27 = s[m][0];
	//			s28 = s[m][1];
	//			i = s27.length();
	//			j = s28.length();
	//			k = 0;
	//			while ((l = s1.indexOf(s27, k)) >= 0) {
	//				k = l + i;
	//				int l1 = s1.indexOf(s28, k);
	//				String s6 = s1.substring(l, l1) + s28;
	//				int j3 = s6.indexOf(strEnd, 0);
	//				String s15 = s6.substring(i, j3);
	//				String s22 = s6.substring(j3 + 1, s6.length() - j);
	//				s1 = Replace(s1, s6, s[m][2] + s15 + s[m][3] + s22 + s[m][4]);
	//			}
	//		}
	//		return s1;
	//	}
	//	public static String Htm2NpCode(String s) {
	//		for (int i = 0; i < replacecode.length; i++)
	//			s = Replace(s, replacecode[i][1], replacecode[i][0]);
	//		return s;
	//	}

	// ZhangLei: I think this guy can work well for my blog after a rigorous test
	public static String safeToHTML(String s) {
		if (s == null)
			return SPACER;
		for (int i = 0; i < h2t.length; i++)
			s = Replace(s, h2t[i][0], h2t[i][1]);
		return s;
	}

	// ZhangLei: undo safeReplaceToHTML()
	public static String undoToHtml(String s) {
		if (s == null)
			return SPACER;
		for (int i = 0; i < h2t.length; i++)
			s = Replace(s, h2t[i][1], h2t[i][0]);
		return s;
	}
/*
 * XML-illegal Code: x00~~x08; x0b~~x0c; x0e~~x1f
 */
	public static String needReplaced[] = { "\u0000", "\u0001", "\u0002",
			"\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\u0008",
			"\u000b", "\u000c", "\u000e", "\u000f", "\u0010", "\u0011",
			"\u0012", "\u0013", "\u0014", "\u0015", "\u0016", "\u0017",
			"\u0018", "\u0019", "\u001a", "\u001b", "\u001c", "\u001d",
			"\u001e", "\u001f" };
	public static String safeToXML(String s) {
		if (s != null)
			for (int i = 0; i < needReplaced.length; i++)
				s = Replace(s, needReplaced[i], SPACER);
		return s;
	}

	public static String Replace(String src, String replaced, String replace) {
		if (src == null)
			return SPACER;
		StringBuffer strbuf = new StringBuffer();
		int i = src.length(), j = replaced.length(), k, l;
		for (k = 0; (l = src.indexOf(replaced, k)) >= 0; k = l + j) {
			strbuf.append(src.substring(k, l));
			strbuf.append(replace);
		}
		if (k < i)
			strbuf.append(src.substring(k));
		return strbuf.toString();
	}

	public static String cutString(String input, int length) {
		if (input == null)
			return null;
		int j = input.length();
		if (j > length)
			input = input.substring(0, length) + "...";
		return input;
	}

	public static String getLinkTitle(String input, int length) {
		if (input == null)
			return SPACER;
		if (input.getBytes().length > 2 * length)
			return input;
		else
			return SPACER;

	}

	public static boolean verifyNumFormat(String s) {
		if (s != null)
			try {
				Long.parseLong(s.trim());
				return true;
			} catch (Exception exception) {
			}
		return false;
	}

	public static int s2i(String s) {
		int re = 0;
		if (s != null)
		try {
			re = Integer.parseInt(s.trim());
		} catch (Exception exception) {
		}
		return re;
	}

	public static long s2l(String s) {
		long i = 0;
		if (s != null)
			try {
				i = Long.parseLong(s.trim());
			} catch (Exception exception) {
			}
		return i;
	}

	public static String ipTrim(String ipput, int level) {
		if (ipput == null)
			return ipput;
		if ((level > 4) || (level < 1))
			return ipput;
		try {
			StringTokenizer st = new StringTokenizer(ipput, ".");
			switch (level) {
			case 1:
				return st.nextToken() + ".*.*.*";
			case 2:
				return st.nextToken() + "." + st.nextToken() + ".*.*";
			case 3:
				return st.nextToken() + "." + st.nextToken() + "." + st.nextToken() + ".*";
			case 4:
				return ipput;
			default:
				return ipput;
			}
		} catch (Exception e) {
			e = null;
			return ipput;
		}
		//ipput.substring(0,ipput.indexOf(".",ipput.indexOf(".")+1))+".*.*";
	}

	public static String convert(String text, String charset) {
		String ctext = null;
		if (text != null)
			try {
				ctext = new String(text.getBytes("8859_1"), charset);
			} catch (UnsupportedEncodingException e) {
				try {
					ctext = new String(text.getBytes("8859_1"), "UTF-8");
				} catch (UnsupportedEncodingException ex) {
					try {
						ctext = new String(text.getBytes("8859_1"), "GBK");
					} catch (UnsupportedEncodingException exp) {
					}
				}
			} catch (Exception x) {
				x.printStackTrace();
			}
		return ctext;
	}

	public static String convert(String text) {
		String ctext = null;
		if (text != null)
			text = text.trim();
		ctext = convert(text, "UTF-8");
		return ctext;
	}

	public static String clip(String line) {
		int i = line.indexOf("?");
		return (i > 0) ? line.substring(0, i) : line;
	}

	public static String mD5(String Str) {
		return hashCode(Str,"MD5");
	}
	public static String hashCode(String Str) {
		return hashCode(Str,"SHA-1");
	}
	public static String hashCode(String str, String algorithm) {
		byte[] dst = new byte[0];
		if (str != null) {
			dst = digest(str, algorithm);
			if(dst.length==0)
				dst = digest(str, "SHA-256");			
			StringBuffer sb = new StringBuffer(EMPTYstr);
			for (int i = 0; i < dst.length; i++)
				sb.append(byteHEX(dst[i]));
			return sb.toString();
		}
		return EMPTYstr;
	}

	/*
	 * MessageDigest.getInstance(String algorithm);
	 * Param algorithm, the name of the algorithm requested.(MD5,SHA-1,SHA-256,etc...)
	 */
	public static byte[] digest(String str, String algorithm) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(str.getBytes());
			return digest.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

	public static char[] byteHEX(byte ib) {
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		return ob;
	}

	public static String getBodyString(String html) {
		String ret = null;
		if (html == null)
			return ret;
		try {
			int idx1 = html.indexOf("<body");
			int idx2 = html.indexOf(">", idx1);
			int idx3 = html.indexOf("</body>", idx2);
			if (idx2 < idx3)
				ret = html.substring(idx2 + 1, idx3);
		} catch (Exception e) {
		}
		try {
			int idx1 = html.indexOf("<BODY");
			int idx2 = html.indexOf(">", idx1);
			int idx3 = html.indexOf("</BODY>", idx2);
			if (idx2 < idx3)
				ret = html.substring(idx2 + 1, idx3);
		} catch (Exception e) {
		}
		ret = ret.replaceAll("<script[^>]*>[\\d\\D]*?</script>", EMPTYstr);
		ret = ret.replaceAll("<style[^>]*>[\\d\\D]*?</style>", EMPTYstr);
		ret = htm2txtByStream(ret);
		ret = Replace(ret, SPACER, EMPTYstr);
		ret = Replace(ret, TAB, EMPTYstr);
		ret = Replace(ret, "&nbsp;", EMPTYstr);
		ret = Replace(ret, "\r\n", EMPTYstr);
		return Replace(ret, "\n", EMPTYstr);
	}

	public static String modifyBase(String html, String baseUrl) {
		int bo = html.indexOf("<base");
		if (bo == -1)
			bo = html.indexOf("<BASE");
		if (bo >= 0) //original has base tag
			return html;
		else { //no base, insert
			String base = "<base href=\"" + baseUrl + "\">";
			int hc = html.indexOf("</head>");
			if (hc == -1)
				hc = html.indexOf("</HEAD>");
			if (hc == -1)
				return base + html;
			return html.substring(0, hc) + base + html.substring(hc);
		}
	}

	public static String addStyle(String html, String ctx) {
		String ss = "<link rel=\"stylesheet\" href=\"" + ctx
				+ "/style.css\" type=\"text/css\" >";
		int hc = html.indexOf("</head>");
		if (hc == -1)
			hc = html.indexOf("</HEAD>");
		if (hc == -1) //no head?
			return ss + html;
		return html.substring(0, hc) + ss + html.substring(hc);
	}

	public static String htm2txt(String s) {
		if (s != null)
			try {
				char chr;
				for (int i = 0; i < s.length(); i++) {
					chr = s.charAt(i);
					if (chr == '<') {
						StringBuffer tag = new StringBuffer(EMPTYstr);
						for (int j = 1; (i + j) < s.length(); j++) {
							if ((chr = s.charAt(i + j)) == '>') {
								i--;
								s = Replace(s, "<" + tag + ">", EMPTYstr);
								break;
							}
							tag = tag.append(chr);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return s;
	}

	/**
	 * @deprecated
	 */
	public static String htm2txtByPatn(String s) {
		if (s != null)
			s = s.replaceAll("<[^>]*>", SPACER);
		return s;
	}

	/**
	 * @deprecated
	 */
	public static String htm2txtByStream(String s) {
		try {
			BufferedReader in = new BufferedReader(new StringReader(s));
			int chr;
			while ((chr = in.read()) != -1) {
				StringBuffer tag = new StringBuffer("");
				if (chr == '<') 
					while ((chr = in.read()) != '>') {
						if (chr == -1) 
							return s;						
						tag = tag.append((char) chr);
					}				
				s = Replace(s, "<" + tag + ">", "");
			}
		} catch (Exception e) {
		}
		return s;
	}

	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length(), value = 0;
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						if (aChar >= '0' && aChar <= '9') {
							value = (value << 4) + aChar - '0';
						} else if (aChar >= 'a' && aChar <= 'f')
							value = (value << 4) + 10 + aChar - 'a';
						else if (aChar >= 'A' && aChar <= 'F')
							value = (value << 4) + 10 + aChar - 'A';
//						else throw new IllegalArgumentException("Encoding Err.");
					}
					outBuffer.append((char) value);
				} else
					outBuffer.append((aChar == 't') ? '\t'
							: ((aChar == 'r') ? '\r' : ((aChar == 'n') ? '\n'
									: ((aChar == 'f') ? '\f' : aChar))));
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}
	//
	public static String ruleUrl(String s) {
		if (s != null)
			s = s.trim();
		int idx = s.indexOf(SPACER);
		if (idx > 0)
			s = s.substring(0, idx);
		idx = s.indexOf("\"");
		if (idx > 0)
			s = s.substring(0, idx);
		if (s.length() > 768)
			s = s.substring(0, 768);
		return s;
	}

	public static boolean isValidEmail(String str) {
		if (str == null || str.indexOf('@') < 1 || str.indexOf('.') < 3)
			return false;
		// TODO validate domain as well?
		return true;
	}
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	/**
	 * 把给定的str转换为10进制表示的unicode，目前只是用于mht模板的转码
	 * @param str
	 * @return
	 */
	public static String encode2HtmlUnicode(String str) {

		if (str == null)
			return "";
		StringBuilder sb = new StringBuilder(str.length() * 2);
		for (int i = 0; i < str.length(); i++) {
			sb.append(encode2HtmlUnicode(str.charAt(i)));
		}
		return sb.toString();
	}
	
	public static String encode2HtmlUnicode(char character) {
		if (character > 255) {
			return "&#" + (character & 0xffff) + ";";
		} else {
			return String.valueOf(character);
		}
	}
	
	public static String rpaSpecialStr(String str){
		str=str.replace("&amp;","&");
		str=str.replace("=","=3D");
		return str;
	}
}