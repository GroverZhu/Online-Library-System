package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据正则表达式来判断输入的格式是否满足要求 判断是否为数(isNumeric) 过滤不正常的字符(filterStr) 判空(isEmpty)
 * 判断是否为电话号码(isTelephone) 判断是否为邮箱(isEmail) 判断是否为姓名(isName) 判断是否为密码(isPassword)
 * 
 * @author 刘卓程
 * @date 2018-11-15
 */
public class CharacterFilterUtil {

	/**
	 * Use this to filter first when related to SQL We will return null if it
	 * cannot pass filter
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static final String filterStr(String str) {
		String s1 = "(?:')|(?:;)|(?:&)|(?:<)|(?:>)|(?:/)|(?:%)|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)";
		String s2 = "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
		Pattern sqlPattern1 = Pattern.compile(s1, Pattern.CASE_INSENSITIVE);
		Pattern sqlPattern2 = Pattern.compile(s2, Pattern.CASE_INSENSITIVE);
		str = str.trim();
		System.out.println(str);
		System.out.println("s1" + sqlPattern1.matcher(str).find());
		System.out.println("s2" + sqlPattern2.matcher(str).find());

		if (sqlPattern1.matcher(str).find() || sqlPattern2.matcher(str).find()) {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return if string is empty, return null else return string
	 */
	public static final String isEmpty(String str) {
		if (str == null || str.trim().length() == 0 || str == "") {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return if string is not a telephone number,return null else return this
	 *         string Regular expression The mobile number consists of 11
	 *         digits. Matching format: the first three fixed formats + the last
	 *         eight arbitrary numbers The first three digits of this method
	 *         are: 13 + any number 15 + any number except 4 18 + any number
	 *         except 1 and 4 17 + any number except 9 147
	 */
	public static final String isTelephone(String str) {
		if (str == null || str.trim().length() == 0) {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		String test = "^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		if (!Pattern.matches(test, str)) {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return if string is email address, return it else return null
	 */
	public static final String isEmail(String str) {
		if (str == null || str.trim().length() == 0 || str == "") {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		String test = "^\\\\w+((-\\\\w+)|(\\\\.\\\\w+))*\\\\@[A-Za-z0-9]+((\\\\.|-)[A-Za-z0-9]+)*\\\\.[A-Za-z0-9]+$";
		String test1 = "^([a-z0-9A-Z]+[-|_|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$";
		// if (Pattern.matches(test1, str)) {
		// System.out.println("cannot pass filter : " + str);
		// return null;
		// }
		// return str;
		try {
			Pattern regex = Pattern.compile(test1);
			Matcher matcher = regex.matcher(str);
			matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return if string is a name, return it else return null (there can be
	 *         a-z,A-Z,Chinese,space)
	 */
	public static final String isName(String str) {
		if (str == null || str.trim().length() == 0 || str == "") {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		String test = "[\\u4e00-\\u9fa5_a-zA-Z ]{4,40}";
		if (!Pattern.matches(test, str)) {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		return str;
	}

	/**
	 * @param str
	 * @return if string is a password, return it else return null password
	 *         length is between 6 to 32
	 */
	public static String isPassword(String str) {
		if (str == null || str.trim().length() == 0 || str == "") {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		String test = "^[a-zA-Z0-9_]{6,32}$";
		if (!Pattern.matches(test, str)) {
			System.out.println("cannot pass filter : " + str);
			return null;
		}
		return str;
	}
}