package testdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestUserDAO {

    /**
     * ユーザー名とパスワードで絞り込んで検索
     */
    public void select(String name, String password) {
        String sql = "SELECT * FROM test_table WHERE user_name = ? AND password = ?";
        try (
            Connection con = new DBConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("user_name: " + rs.getString("user_name"));
                    System.out.println("password:  " + rs.getString("password"));
                } else {
                    System.out.println("該当するユーザーが見つかりませんでした。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 全件検索
     */
    public void selectAll() {
        String sql = "SELECT * FROM test_table";
        try (
            Connection con = new DBConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println("user_name: " + rs.getString("user_name"));
                System.out.println("password:  " + rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ユーザー名で検索
     */
    public void selectByName(String name) {
        String sql = "SELECT * FROM test_table WHERE user_name = ?";
        try (
            Connection con = new DBConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("user_name: " + rs.getString("user_name"));
                    System.out.println("password:  " + rs.getString("password"));
                }
                if (!found) {
                    System.out.println("該当するユーザーが見つかりませんでした。");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ユーザー名を更新
     */
    public void updateUserNameByUserName(String oldName, String newName) {
        String sql = "UPDATE test_table SET user_name = ? WHERE user_name = ?";
        try (
            Connection con = new DBConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, newName);
            ps.setString(2, oldName);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(count + " 件更新されました");
            } else {
                System.out.println("該当するデータはありませんでした");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * レコードを挿入
     */
    public void insert(int userId, String name, String password) {
        String sql = "INSERT INTO test_table(user_id, user_name, password) VALUES(?, ?, ?)";
        try (
            Connection con = new DBConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setString(3, password);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(count + " 件登録されました");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ユーザー名でレコードを削除
     */
    public void delete(String name) {
        String sql = "DELETE FROM test_table WHERE user_name = ?";
        try (
            Connection con = new DBConnector().getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, name);
            int count = ps.executeUpdate();
            if (count > 0) {
                System.out.println(count + " 件削除されました");
            } else {
                System.out.println("該当するデータはありませんでした");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}