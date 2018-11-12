package entity;

import java.sql.Connection;
import java.sql.Statement;

import util.DatabaseUtil;

public class test {

	public static void main(String[] args) {
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			System.out.println("conn");
			Statement st = conn.createStatement();
			System.out.println("1");
			Connection co = DatabaseUtil.getInstance().getConnection();
			Statement s = co.createStatement();
			System.out.println("2");
		} catch (Exception e) {
			System.out.println("fault");
		}
	}
}
