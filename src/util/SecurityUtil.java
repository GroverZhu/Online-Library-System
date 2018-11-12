package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 实现数据加密
 * 
 * @author GroverZhu
 * @date 2018-11-12 22:42:00
 */
public class SecurityUtil {

	/**
	 * 将明文字符串通过MD5加密算法转化为一串MD5码
	 * 
	 * @param message
	 * @return 加密后的MD5码字符串
	 */
	public static String md5(String message) {
		try {
			MessageDigest md5 = null;
			md5 = MessageDigest.getInstance("MD5");
			char[] charArray = message.toCharArray();
			byte[] byteArray = new byte[charArray.length];
			for (int i = 0; i < charArray.length; i++)
				byteArray[i] = (byte) charArray[i];
			byte[] md5Bytes = md5.digest(byteArray);
			// 转为16进制
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return message;
	}

}
