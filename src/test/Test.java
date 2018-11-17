package test;



import dao.AdministratorDAO;
import dao.ReaderDAO;

public class Test {
	public static void main(String[] args) {
//		ReaderDAO r=new ReaderDAO();
//		r.addReaderByName_Passowrd_State("hahhahaha", "123456", "unlock");
		AdministratorDAO ad = new AdministratorDAO();
		ad.updateAdmin(1, "root", util.SecurityUtil.md5("123456"));
	}
}
