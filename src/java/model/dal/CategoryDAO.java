/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.Category;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.DBConnect;

/**
 *
 * @author PMQUANG
 */
public class CategoryDAO extends DBConnect {

    public Vector<Category> findAll() {
        Vector<Category> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * FROM [dbo].[Category]";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Category category = new Category(id, name);
                found.add(category);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Category findByID(Category category) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [BookStore].[dbo].[Category]\n"
                + "  where id = ?";
        try {
            // Statement: execute command
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setObject(1, category.getId());
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                return new Category(id, name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
        public Vector<Category> getCategory(String sql) {
        Vector<Category> vector = new Vector<Category>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                vector.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    public static void main(String[] args) {
        CategoryDAO dao = new CategoryDAO();
        Category category = new Category(2);
//        dao.findAll();
        System.out.println(dao.findByID(category));
    }
}
