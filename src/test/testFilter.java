package test;

import util.CharacterFilter;

class testFilter {

	public static void main(String[] args) {
		CharacterFilter cf = new CharacterFilter();
		System.out.println(cf.isEmail("374522830@qq.com"));
		System.out.println(cf.isName("Huyuxi"));
		System.out.println(cf.isPassword("hyx123456"));
		System.out.println(cf.isTelephone("18189107830"));
		System.out.println(cf.isEmpty(" "));
	}

}