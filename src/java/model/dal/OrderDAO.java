/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.Order;
import model.DBConnect;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;

/**
 *
 * @author PMQUANG
 */
public class OrderDAO extends DBConnect {

    public Vector<Order> findAll() {
        Vector<Order> found = new Vector<>();
        //truy van sql
        String sql = "select * from [Order]\n"
                + "order by accountId,id";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                float list_price = resultSet.getFloat("list_price");
                int accountId = resultSet.getInt("accountId");
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                String status = resultSet.getString("status");
                Order order = new Order(id, list_price, accountId, orderDate, status);
                found.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<Order> findAllByAccount() {
        Vector<Order> found = new Vector<>();
        //truy van sql
        String sql = "select oi.order_id as [bill_id],a.username,o.orderDate,list_price as total,o.status from [order] as o\n"
                + "inner join [OrderItem] as oi on o.id = oi.order_id\n"
                + "inner join Account as a on o.accountId = a.id\n"
                + "group by oi.order_id,a.username,o.orderDate,list_price,o.status,o.accountId\n"
                + "order by o.status";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int bill_id = resultSet.getInt("bill_id");
                String username = resultSet.getString("username");
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                float total = resultSet.getFloat("total");
                String status = resultSet.getString("status");
                Order order = new Order(bill_id, username, orderDate, total, status);
                found.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<Order> searchBillByName(String username) {
        Vector<Order> found = new Vector<>();
        //truy van sql
        String sql = "select oi.order_id as [bill_id], a.username, o.orderDate, o.list_price as total, o.status "
                + "from [order] as o "
                + "inner join [OrderItem] as oi on o.id = oi.order_id "
                + "inner join Account as a on o.accountId = a.id "
                + "where a.username like ? "
                + "group by oi.order_id, a.username, o.orderDate, o.list_price, o.status, o.accountId "
                + "order by o.accountId";

        try {
            // tao doi tuong prepareStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            // set paramater
            statement.setString(1, "%" + username + "%");
            // thuc thi cau lenh
            ResultSet resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int bill_id = resultSet.getInt("bill_id");
                String uname = resultSet.getString("username"); // Sử dụng tên khác để tránh ghi đè
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                float total = resultSet.getFloat("total");
                String status = resultSet.getString("status");
                Order order = new Order(bill_id, uname, orderDate, total, status);
                found.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }
    
        public Vector<Order> searchBillByStatus(String status) {
        Vector<Order> found = new Vector<>();
        //truy van sql
        String sql = "select oi.order_id as [bill_id], a.username, o.orderDate, o.list_price as total, o.status "
                + "from [order] as o "
                + "inner join [OrderItem] as oi on o.id = oi.order_id "
                + "inner join Account as a on o.accountId = a.id "
                + "where o.status like ? "
                + "group by oi.order_id, a.username, o.orderDate, o.list_price, o.status, o.accountId "
                + "order by o.accountId";

        try {
            // tao doi tuong prepareStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            // set paramater
            statement.setString(1, "%" + status + "%");
            // thuc thi cau lenh
            ResultSet resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int bill_id = resultSet.getInt("bill_id");
                String uname = resultSet.getString("username"); // Sử dụng tên khác để tránh ghi đè
                Timestamp orderDate = resultSet.getTimestamp("orderDate");
                float total = resultSet.getFloat("total");
                status = resultSet.getString("status");
                Order order = new Order(bill_id, uname, orderDate, total, status);
                found.add(order);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public void insert(Order order) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([id]\n"
                + "           ,[list_price]\n"
                + "           ,[accountId]\n"
                + "           ,[orderDate]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, order.getId());
            pre.setFloat(2, order.getList_price());
            pre.setInt(3, order.getAccountId());
            pre.setTimestamp(4, order.getOrderDate());
            pre.setString(5, order.getStatus());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int insertOrder(Order order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([id]\n"
                + "           ,[list_price]\n"
                + "           ,[accountId]\n"
                + "           ,[orderDate]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, order.getId());
            pre.setFloat(2, order.getList_price());
            pre.setInt(3, order.getAccountId());
            pre.setTimestamp(4, order.getOrderDate());
            pre.setString(5, order.getStatus());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Order CompleteBill(Order order) {
        int n = 0;
        String sql = "UPDATE o\n"
                + "SET o.[status] = 'done'\n"
                + "FROM [dbo].[Order] AS o\n"
                + "INNER JOIN Account AS a ON a.id = o.accountId\n"
                + "WHERE o.id = ? AND a.username = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, order.getId());
            pre.setString(2, order.getUsername());
            n = pre.executeUpdate();
            if (n > 0) {
                order.setStatus("done");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return order;
    }

    public int getMaxOrderId(int accountId) {
        int maxId = 0;
        String sql = "SELECT MAX(id) AS max_id FROM [dbo].[Order] where accountId=?";

        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setInt(1, accountId);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maxId = resultSet.getInt("max_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ tại đây nếu cần
        }

        return maxId;
    }

    public Vector<Order> getOrdersByAccountId(int accountId) {
        Vector<Order> found = new Vector<>();
        String sql = "select oi.order_id as [bill_id],a.username,o.orderDate,list_price as total,o.status from [order] as o\n"
                + "inner join [OrderItem] as oi on o.id = oi.order_id\n"
                + "inner join Account as a on o.accountId = a.id\n"
                + "where o.accountId = ?\n"
                + "group by oi.order_id,a.username,o.orderDate,list_price,o.status";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int bill_id = resultSet.getInt("bill_id");
                    String username = resultSet.getString("username");
                    Timestamp orderDate = resultSet.getTimestamp("orderDate");
                    float total = resultSet.getFloat("total");
                    String status = resultSet.getString("status");

                    Order order = new Order(bill_id, username, orderDate, total, status);
                    found.add(order);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Order getOrdersByOrderAndAccount(int id, String username) {
        Order order = null;
        String sql = "select o.id,a.username,o.status from [Order] as o\n"
                + "inner join Account as a on a.id = o.accountId\n"
                + "where o.id = ? and a.username = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    username = resultSet.getString("username");
                    String status = resultSet.getString("status");

                    order = new Order();
                    order.setId(id);
                    order.setUsername(username);
                    order.setStatus(status);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return order;
    }

    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        String status = "waiting";
        System.out.println(dao.searchBillByStatus(status));
    }
}
