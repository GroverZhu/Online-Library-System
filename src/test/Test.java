package test;

import dao.ReaderDAO;

public class Test {
	public static void main(String[] args) {
		ReaderDAO r=new ReaderDAO();
		r.addReaderByName_Passowrd_State("hahhahaha", "123456", "unlock");
		
	}
}
