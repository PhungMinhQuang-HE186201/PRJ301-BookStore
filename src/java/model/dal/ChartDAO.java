/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.Chart;
import model.DBConnect;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author PMQUANG
 */
public class ChartDAO extends DBConnect {

    public Vector<Chart> findTopFive() {
        Vector<Chart> found = new Vector<>();
        String sql = "SELECT TOP 5 \n"
                + "    \n"
                + "    CASE \n"
                + "        WHEN CHARINDEX('-', p.name) > 0 \n"
                + "        THEN LEFT(p.name, CHARINDEX('-', p.name) - 1) \n"
                + "        ELSE p.name \n"
                + "    END AS name,\n"
                + "    SUM(oi.quantity) AS [Number Sold] \n"
                + "FROM Product AS p\n"
                + "INNER JOIN OrderItem AS oi ON p.id = oi.product_id\n"
                + "INNER JOIN [Order] AS o ON o.id = oi.order_id AND o.accountId = oi.accountId\n"
                + "GROUP BY \n"
                + "         CASE \n"
                + "             WHEN CHARINDEX('-', p.name) > 0 \n"
                + "             THEN LEFT(p.name, CHARINDEX('-', p.name) - 1) \n"
                + "             ELSE p.name \n"
                + "         END\n"
                + "ORDER BY [Number Sold] DESC";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                String product_name = resultSet.getString("name");
                int numberSold = resultSet.getInt("Number Sold");
                found.add(new Chart(product_name, numberSold));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public static void main(String[] args) {
        ChartDAO dao = new ChartDAO();
        for (Chart chart : dao.findTopFive()) {
            System.out.println(chart.getLabel());
        }
    }
}
