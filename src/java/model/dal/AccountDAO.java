/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.Account;
import model.DBConnect;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PMQUANG
 */
public class AccountDAO extends DBConnect {

    public Vector<Account> getAccount(String sql) {
        Vector<Account> vector = new Vector<Account>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username"),
                        password = resultSet.getString("password"),
                        email = resultSet.getString("email"),
                        address = resultSet.getString("address"),
                        phone = resultSet.getString("phone");
                int roleId = resultSet.getInt("roleId");
                Account account = new Account(id, username, password, email, address, phone, roleId);
                vector.add(account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public Vector<Account> findAll() {
        Vector<Account> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * FROM [dbo].[Account]";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username"),
                        password = resultSet.getString("password"),
                        email = resultSet.getString("email"),
                        address = resultSet.getString("address"),
                        phone = resultSet.getString("phone");
                int roleId = resultSet.getInt("roleId");
                Account account = new Account(id, username, password, email, address, phone, roleId);
                found.add(account);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<Account> findAllCustomer() {
        Vector<Account> found = new Vector<>();
        //truy van sql
        String sql = "select (a.id-1) as [id],a.username,a.password,a.email,a.address,a.phone from account as a\n"
                + "where a.roleId = 2";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username"),
                        password = resultSet.getString("password"),
                        email = resultSet.getString("email"),
                        address = resultSet.getString("address"),
                        phone = resultSet.getString("phone");
                Account account = new Account(id, username, password, email, address, phone);
                found.add(account);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public void insertAccount(Account account) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([id]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[address]\n"
                + "           ,[phone]\n"
                + "           ,[roleId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, account.getId());
            pre.setString(2, account.getUsername());
            pre.setString(3, account.getPassword());
            pre.setString(4, account.getEmail());
            pre.setString(5, account.getAddress());
            pre.setString(6, account.getPhone());
            pre.setInt(7, account.getRoleId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateInfo(Account account) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET\n"
                + "      [password] = ?\n"
                + "      ,[address] = ?\n"
                + " WHERE username = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, account.getPassword());
            pre.setString(2, account.getAddress());
            pre.setString(3, account.getUsername());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAddress(Account account) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET\n"
                + "      [address] = ?\n"
                + " WHERE username = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, account.getAddress());
            pre.setString(2, account.getUsername());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Account findByUsernamePassword(String username, String password) {
        Vector<Account> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * \n"
                + "  FROM [dbo].[Account]\n"
                + "  where username = ? and [password] = ?";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setObject(1, username);
            statement.setObject(2, password);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username_Found = resultSet.getString("username").trim();
                String password_Found = resultSet.getString("password").trim();
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                int roleId = resultSet.getInt("roleId");
                Account account = new Account(id, username_Found, password_Found, email, address, phone, roleId);
                found.add(account);
                return found.get(0);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int getMaxId() {
        String sql = "select MAX(id) as id from account";
        int maxId = 0;
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            if (resultSet.next()) {
                maxId = resultSet.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return maxId;
    }

    public Account findByAccountExist(String username) {
        Vector<Account> found = new Vector<>();
        //truy van sql
        String sql = "select * from account\n"
                + "where username = ?";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setObject(1, username);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username_Found = resultSet.getString("username").trim();
                String password = resultSet.getString("password").trim();
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                int roleId = resultSet.getInt("roleId");
                Account account = new Account(id, username_Found, password, email, address, phone, roleId);
                found.add(account);
                return found.get(0);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public int deleteAccount(Account account) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Account]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, account.getId() + 1);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public String findPassByUserName(String username) {
        //truy van sql
        String sql = "select username,password from account\n"
                + "where username = ?";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setObject(1, username);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            if (resultSet.next()) {
                String username_Found = resultSet.getString("username").trim();
                String password_Found = resultSet.getString("password").trim();
                Account account = new Account(username_Found, password_Found);
                return account.getPassword();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String generateRandomString() {
        StringBuilder sb = new StringBuilder(8);
        Random r = new Random();
        for (int i = 0; i < 8; i++) {
            int index = r.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public void changePassword(String email, String newPassword) {
        String updateSql = "UPDATE [dbo].[Account] SET password = ? WHERE email = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(updateSql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.findPassByUserName("quangdz"));
    }

}
