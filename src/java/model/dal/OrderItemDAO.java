/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.OrderItem;
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
public class OrderItemDAO extends DBConnect {

    public Vector<OrderItem> findAll() {
        Vector<OrderItem> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * FROM [dbo].[OrderItem]";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int item_id = resultSet.getInt("item_id"),
                        order_id = resultSet.getInt("order_id"),
                        product_id = resultSet.getInt("product_id"),
                        quantity = resultSet.getInt("quantity");
                OrderItem orderItem = new OrderItem(item_id, order_id, product_id, quantity);
                found.add(orderItem);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public int insertOrderItem(OrderItem orderItem) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[OrderItem]\n"
                + "           ([item_id]\n"
                + "           ,[order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[price_item]\n"
                + "           ,[accountId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, orderItem.getItem_id());
            pre.setInt(2, orderItem.getOrder_id());
            pre.setInt(3, orderItem.getProduct_id());
            pre.setInt(4, orderItem.getQuantity());
            pre.setFloat(5, orderItem.getPrice_item());
            pre.setInt(6, orderItem.getAccountId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int getMaxItemId() {
        int maxItemId = 0;
        String sql = "SELECT MAX(item_id) AS max_item_id FROM [dbo].[OrderItem]";

        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maxItemId = resultSet.getInt("max_item_id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ tại đây nếu cần
        }

        return maxItemId;
    }

    public float getPriceItem(int product_id) {
        float priceItem = 0;
        String sql = "select oi.price_item from OrderItem as oi\n"
                + "inner join Product as p on p.id = oi.product_id\n"
                + "where oi.product_id = ?";

        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setInt(1, product_id);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                priceItem = resultSet.getInt("price_item");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ tại đây nếu cần
        }

        return priceItem;
    }

    public Vector<OrderItem> findItemByOrderAndAccount(int order_id, String username) {
        Vector<OrderItem> found = new Vector<>();
        //truy van sql
        String sql = "select distinct oi.item_id,p.name as [product_name],oi.quantity,oi.price_item,(p.price*oi.quantity) as [total_item],a.username from OrderItem as oi\n"
                + "inner join Product as p on p.id = oi.product_id\n"
                + "inner join [Order] as o on o.accountId = oi.accountId\n"
                + "inner join Account as a on a.id = o.accountId\n"
                + "where oi.order_id = ? and a.username=?";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setInt(1, order_id);
            statement.setString(2, username);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int item_id = resultSet.getInt("item_id"),
                        quantity = resultSet.getInt("quantity"),
                        total_item = resultSet.getInt("total_item");
                float price = resultSet.getFloat("price_item");
                String product_name = resultSet.getString("product_name");
                OrderItem orderItem = new OrderItem(item_id, product_name, quantity, price, total_item);
                found.add(orderItem);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<OrderItem> findBestSeller() {
        Vector<OrderItem> found = new Vector<>();
        String sql = "select top 3 oi.product_id,p.name,p.image,p.category_id,SUM(oi.quantity) as [Number Sold] from Product as p\n"
                + "inner join OrderItem as oi on p.id = oi.product_id\n"
                + "inner join [Order] as o on o.id = oi.order_id and o.accountId = oi.accountId\n"
                + "group by oi.product_id,p.name,p.image,p.category_id\n"
                + "order by [Number Sold] desc";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int product_id = resultSet.getInt("product_id");
                String product_name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int category_id = resultSet.getInt("category_id");
                int numberSold = resultSet.getInt("Number Sold");
                found.add(new OrderItem(product_id, product_name, image, category_id, numberSold));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public static void main(String[] args) {
        OrderItemDAO dao = new OrderItemDAO();
//        for (OrderItem orderItem : dao.findBestSeller()) {
//            System.out.println(orderItem);
//        }
        System.out.println(dao.getPriceItem(42));
    }

}
