package dao;

import entity.Administrator;
import entity.Librarian;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdministratorDAO {
    /**
     * 根据ID获取administrator实体
     *
     * @param id
     * @return
     * @author Liu Zhuocheng
     */

    public Administrator getAdministratorById(int id) {
        Administrator administrator = new Administrator();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "select * from administrator where administrator_id=" + id;
            rs = st.executeQuery(sql);
            if (rs.next()) {
                //获取数据
                String name = rs.getString("administrator_name");
                String password = rs.getString("administrator_password");
                //封装实体
                administrator.setId(id);
                administrator.setName(name);
                administrator.setPassword(password);
                rs.close();
                st.close();
                conn.close();
                return administrator;
            } else {
                System.out.println("--Administrator--,--getAdministratorById()--,Cannot find the Administrator by id=" + id);
                rs.close();
                st.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            System.out.println("--Administrator--,--getAdministratorById()--,suffers exception");
            return null;
        }
    }
}
