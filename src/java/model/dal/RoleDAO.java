/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.Role;
import java.sql.SQLException;
import java.util.Vector;
import model.DBConnect;

/**
 *
 * @author PMQUANG
 */
public class RoleDAO extends DBConnect {

    public Vector<Role> findAll() {
        Vector<Role> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * FROM [dbo].[Role]";
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
                Role role = new Role(id, name);
                found.add(role);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }
}
