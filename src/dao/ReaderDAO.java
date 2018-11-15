package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entity.Reader;
import util.DatabaseUtil;

public class ReaderDAO {
    /**
     * 根据Reader ID获取Reader实例
     *
     * @param id
     * @return
     * @author zengyaoNPU
     */
    public Reader getReaderById(int id) {
        Reader reader = new Reader();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getInstance().getConnection();
            st = conn.createStatement();
            String sql = "select * from reader where reader_id=" + id;
            rs = st.executeQuery(sql);
            if (rs.next()) {
                //获取数据
                String name = rs.getString("reader_name");
                String password = rs.getString("reader_password");
                String email = rs.getString("reader_email");
                String state = rs.getString("state");
                //封装实体
                reader.setId(id);
                reader.setEmail(email);
                reader.setName(name);
                reader.setPassword(password);
                reader.setState(state);
                rs.close();
                st.close();
                conn.close();
                return reader;
            } else {
                System.out.println("--Reader--,--getReaderById()--,Cannot find the Reader by id=" + id);
                rs.close();
                st.close();
                conn.close();
                return null;
            }
        } catch (Exception e) {
            System.out.println("--Reader--,--getReaderById()--,suffers exception");
            return null;
        }
    }

    /**
     * 修改用户信息
     *
     * @param id       readerID，该值不被改动
     * @param name     reader_name
     * @param password reader_password
     * @param email    reader_email
     * @return
     * @author zengyaoNPU
     */
    public boolean updateData(int id, String name, String password, String email, String state) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = DatabaseUtil.getInstance().getConnection();
            st = conn.createStatement();
            //更新Reader的信息
            String sql = "UPDATE reader SET reader_name='" + name + "',reader_password='" + password + "',reader_email='" + email + "',state='" + state + "' WHERE reader_id=" + id;
            st.executeUpdate(sql);
            int row = st.executeUpdate(sql);
            st.close();
            conn.close();
            return true;
        } catch (Exception e) {
            System.out.println("--Reader--,--updateData()--,suffers exception");
            return false;
        }
    }

}
