package util;

import java.util.regex.Pattern;
import org.apache.tomcat.util.file.Matcher;

/**
 * @author Hu Yuxi
 * 
 * filterStr
 * isEmpty
 * isTelephone
 * isEmail
 * isName
 * isPassword
 * 
 * */
public class CharacterFilter {
	
	/**
	 * Use this to filter first when related to SQL
	 * We will return null if it cannot pass filter
	 * @param str
	 * @return
	 */
	public static final String filterStr(String str) {
		 String s1="(?:')|(?:;)|(?:&)|(?:<)|(?:>)|(?:/)|(?:%)|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)";
		 String s2="(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";	
		 Pattern sqlPattern1 = Pattern.compile(s1, Pattern.CASE_INSENSITIVE);
		 Pattern sqlPattern2 = Pattern.compile(s2, Pattern.CASE_INSENSITIVE); 
		// remove dangerous character
//		str = str.replaceAll(";", "");
//		str = str.replaceAll("&", "");
//		str = str.replaceAll("<", "");
//		str = str.replaceAll(">", "");
//		str = str.replaceAll("'", "");
//		str = str.replaceAll("--", "");
//		str = str.replaceAll("/", "");
//		str = str.replaceAll("%", "");
		// remove head and tail spaces
		str = str.trim();
		System.out.println(str);
		System.out.println("s1"+sqlPattern1.matcher(str).find());
		System.out.println("s2"+sqlPattern2.matcher(str).find());

		if (sqlPattern1.matcher(str).find()||sqlPattern2.matcher(str).find()) {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		return str;
	}
	/**
	 * 
	 * @param str
	 * @return if string is empty, return null
	 * else return string
	 */
	public static final String isEmpty(String str) {
		if(str == null || str.trim().length()== 0||str=="") {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @return if string is not a telephone number,return null
	 * else return this string
	 * 
	 * Regular expression
     * The mobile number consists of 11 digits.
     * Matching format: the first three fixed formats + the last eight arbitrary numbers
     * The first three digits of this method are:
     * 13 + any number
     * 15 + any number except 4
     * 18 + any number except 1 and 4
     * 17 + any number except 9
     * 147
     */
	public static final String isTelephone(String str) {
		if(str == null || str.trim().length()== 0) {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		String test="^((13[0-9])|(15[^4])|(18[0,1,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		if (!Pattern.matches(test,str)) {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @return if string is email address, return it
	 * else return null
	 */
	public static final String isEmail(String str) {
		if(str == null || str.trim().length()== 0||str=="") {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		String test="^\\\\w+((-\\\\w+)|(\\\\.\\\\w+))*\\\\@[A-Za-z0-9]+((\\\\.|-)[A-Za-z0-9]+)*\\\\.[A-Za-z0-9]+$";
		if (Pattern.matches(test,str)) {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		return str;
	}
	
	
	
	/**
	 * 
	 * @param str
	 * @return if string is a name, return it
	 * else return null
	 * (there can be a-z,A-Z,Chinese,space)
	 */
	public static final String isName(String str) {
		if(str == null || str.trim().length()== 0||str=="") {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		String test="[\\u4e00-\\u9fa5_a-zA-Z ]{4,40}";
		if (!Pattern.matches(test, str)) {
			System.out.println("cannot pass filter : "+str);		
			return null;
		}		
		return str;
	}
	
	/**
	 * 
	 * @param str
	 * @return if string is a password, return it
	 * else return null
	 * password length is between 6 to 32
	 */
	public static String isPassword(String str) {
		if(str == null || str.trim().length()== 0||str=="") {
			System.out.println("cannot pass filter : "+str);
			return null;
		}
		String test="^[a-zA-Z0-9_]{6,32}$";
		if (!Pattern.matches(test,str)) {
			System.out.println("cannot pass filter : "+str);
			return null;
		}		
		return str;		
	}
	
	//test main
	public static void main(String[] args) {
		String str1="          ";
		String str2="";
		String str3="";
		String str4="";
		String str5="";
		String str6="";
		
		filterStr("asdfg");
		filterStr("dd12");
		filterStr("1  3");
		filterStr("'");
		filterStr(".");		
//		
//		isEmpty(str2);
//		isTelephone(str4);
//		isEmail(str5);
//		isEmail(str6);
//		isName("Hu Yuxi");
//		isPassword("abcdef");
	}
}
