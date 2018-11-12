package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Publisher;
import util.DatabaseUtil;

public class PublisherDAO {
	/**
	 * 根据PublisherID获取Publisher实体
	 * 
	 * @param id
	 * @return
	 */
	public static Publisher getPublisherById(int id) {
		Publisher publisher = new Publisher();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from publisher where publisher_id=" + id;
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String name = rs.getString("publisher_name");
				String description = rs.getString("publisher_description");

				publisher.setDescription(description);
				publisher.setId(id);
				publisher.setName(name);
				rs.close();
				st.close();
				conn.close();
				return publisher;
			} else {
				System.out.println("--PublisherDAO--,getPublisherById() publisher is null");
				rs.close();
				st.close();
				conn.close();
				return null;
			}
		} catch (Exception e) {
			System.out.println("--PublisherDAO--,getPublisherById() suffers exception");
			return null;
		}
	}

	/**
	 * 根据相似PublisherName获取的Publisher实体列表
	 * 
	 * @param name
	 * @return
	 */
	public static List<Publisher> getPublisherByPublisherName(String name) {
		List<Publisher> list = new ArrayList<Publisher>();
		try {
			Connection conn = DatabaseUtil.getInstance().getConnection();
			Statement st = conn.createStatement();
			String sql = "select * from publisher where publisher_name like '%" + name + "%'";// 获取相似name的Publisher_ID
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				list.add(PublisherDAO.getPublisherById(rs.getInt("publisher_id")));
			}
			if (list.isEmpty()) {
				System.out.println("No such Publisher that Publisher Name contains " + name);
			}
			rs.close();
			st.close();
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("--PublisherDAO--,getPublisherByPublisherName() suffers exception");
			return null;
		}
	}

	public static void main(String[] args) {
		List<Publisher> list = getPublisherByPublisherName("北京");
		for (Publisher i : list) {
			System.out.println(i.toString());
		}
	}
}
