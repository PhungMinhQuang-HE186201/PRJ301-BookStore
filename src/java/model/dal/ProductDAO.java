/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dal;

import entity.Product;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import model.DBConnect;

/**
 *
 * @author PMQUANG
 */
public class ProductDAO extends DBConnect {

    public Vector<Product> findAll() {
        Vector<Product> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * FROM [dbo].[Product]";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name"), image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, category_id);
                found.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<Product> findAllPageProduct() {
        Vector<Product> found = new Vector<>();
        //truy van sql
        String sql = "SELECT * FROM [dbo].[Product] where quantity > 0";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name"), image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, category_id);
                found.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Product findByID(Product product) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[image]\n"
                + "      ,[quantity]\n"
                + "      ,[price]\n"
                + "      ,[description]\n"
                + "      ,[category_id]\n"
                + "  FROM [dbo].[Product]\n"
                + "  where id = ? and quantity>0";
        try {
            // Statement: execute command
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            statement.setObject(1, product.getId());
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");

                return new Product(id, name, image, quantity, price, description, category_id);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Vector<Product> findByCategory(int categoryId, int page) {
        Vector<Product> found = new Vector<>();
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Product]\n"
                + "  where category_id=? and quantity>0\n"
                + "  order by id desc\n"
                + "  offset ? rows\n"
                + "  fetch next ? rows only";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            int recordPerPage = 12;
            statement.setInt(1, categoryId);
            statement.setInt(2, (page - 1) * recordPerPage);
            statement.setInt(3, recordPerPage);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, category_id);
                found.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public int findTotalByCategory(int categoryId) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM Product WHERE category_id = ?";
        try {
            // Tạo đối tượng prepareStatement
            statement = connection.prepareStatement(sql);
            // Đặt tham số cho câu lệnh SQL
            statement.setInt(1, categoryId);
            // Thực thi câu lệnh
            resultSet = statement.executeQuery();
            // Lấy kết quả
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int findTotalByName(String keyword) {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM Product WHERE name like ?";
        try {
            // Tạo đối tượng prepareStatement
            statement = connection.prepareStatement(sql);
            // Đặt tham số cho câu lệnh SQL
            statement.setString(1, "%" + keyword + "%");
            // Thực thi câu lệnh
            resultSet = statement.executeQuery();
            // Lấy kết quả
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int findTotalByPrice(double min, double max) {
        int total = 0;
        String sql = "select COUNT(*) AS total from Product\n"
                + "where price between ? and ?";
        try {
            // Tạo đối tượng prepareStatement
            statement = connection.prepareStatement(sql);
            // Đặt tham số cho câu lệnh SQL
            statement.setDouble(1, min);
            statement.setDouble(2, max);
            // Thực thi câu lệnh
            resultSet = statement.executeQuery();
            // Lấy kết quả
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public int findTotalRecord() {
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM Product";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return total;
    }

    public Vector<Product> findByName(String keyword, int page) {
        Vector<Product> found = new Vector<>();
        //truy van sql
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Product]\n"
                + "  where name like ? and quantity>0\n"
                + "  order by id desc\n"
                + "  offset ? rows\n"
                + "  fetch next ? rows only";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            int recordPerPage = 12;
            statement.setString(1, "%" + keyword + "%");
            statement.setInt(2, (page - 1) * recordPerPage);
            statement.setInt(3, recordPerPage);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, category_id);
                found.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<Product> findByPage(int page) {
        Vector<Product> found = new Vector<>();
        //truy van sql
        String sql = "SELECT *\n"
                + "  FROM [dbo].[Product] where quantity>0\n"
                + "  order by id desc\n"
                + "  offset ? rows\n"
                + "  fetch next ? rows only";
        try {
            // tao doi tuong prepareStatement
            statement = connection.prepareStatement(sql);
            // set paramater(optional)
            int recordPerPage = 12;
            statement.setInt(1, (page - 1) * recordPerPage);
            statement.setInt(2, recordPerPage);
            // thuc thi cau lenh
            resultSet = statement.executeQuery();
            //tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int category_id = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, category_id);
                found.add(product);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return found;
    }

    public Vector<Product> findByPriceRange(double minPrice, double maxPrice, int page) {
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM Product \n"
                + "WHERE price BETWEEN ? AND ? and quantity>0\n"
                + "order by id desc\n"
                + "offset ? rows\n"
                + "fetch next ? rows only";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            int recordPerPage = 12;
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            statement.setInt(3, (page - 1) * recordPerPage);
            statement.setInt(4, recordPerPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, categoryId);
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public Vector<Product> findBySortedPrice(String sortOrder, int page) {
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM product where quantity>0"
                + "ORDER BY price " + sortOrder + ", name "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            statement = connection.prepareStatement(sql);
            int recordPerPage = 12;
            statement.setInt(1, (page - 1) * recordPerPage);
            statement.setInt(2, recordPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Vector<Product> findBySortedName(String sortOrder, int page) {
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM product where quantity>0"
                + "ORDER BY name " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            statement = connection.prepareStatement(sql);
            int recordPerPage = 12;
            statement.setInt(1, (page - 1) * recordPerPage);
            statement.setInt(2, recordPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Vector<Product> findBySortedID(String sortOrder, int page) {
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM product where quantity>0"
                + "ORDER BY id " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            statement = connection.prepareStatement(sql);
            int recordPerPage = 12;
            statement.setInt(1, (page - 1) * recordPerPage);
            statement.setInt(2, recordPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Vector<Product> findAllSorted(String sortBy, String sortOrder, int page) {
        Vector<Product> products = new Vector<>();
        String sql = "SELECT * FROM product where quantity>0"
                + "ORDER BY " + sortBy + " " + sortOrder + " "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            statement = connection.prepareStatement(sql);
            int recordPerPage = 12;
            statement.setInt(1, (page - 1) * recordPerPage);
            statement.setInt(2, recordPerPage);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, categoryId);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Vector<Product> getProducts(String sql) {
        Vector<Product> vector = new Vector<Product>();
        try {
            Statement state = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name"),
                        image = rs.getString("image");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                int category_id = rs.getInt("category_id");
                Product product = new Product(id, name, image, quantity, price, description, category_id);
                vector.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public void insertProducts(Product product) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([id]\n"
                + "           ,[name]\n"
                + "           ,[image]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[category_id])\n"
                + "     VALUES\n"
                + "           (?,?,? ,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, product.getId());
            pre.setString(2, product.getName());
            pre.setString(3, product.getImage());
            pre.setInt(4, product.getQuantity());
            pre.setDouble(5, product.getPrice());
            pre.setString(6, product.getDescription());
            pre.setInt(7, product.getCategory_id());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProducts(Product product) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[category_id] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);

            pre.setString(1, product.getName());
            pre.setString(2, product.getImage());
            pre.setInt(3, product.getQuantity());
            pre.setDouble(4, product.getPrice());
            pre.setString(5, product.getDescription());
            pre.setInt(6, product.getCategory_id());
            pre.setInt(7, product.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int deleteProducts(Product product) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, product.getId());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
//        System.out.println(dao.findByName("naruto")); 
//        Product product = new Product(51);
//        Random random = new Random();
//        for (int id = 51; id <= 70; id++) {
//            int quantity = random.nextInt(10) + 1; // Tạo số lượng ngẫu nhiên từ 1 đến 10
//            double price = 40000; // Tạo giá ngẫu nhiên từ 20000 đến 30000
//            int category_id = random.nextInt(4) + 1; // Tạo category_id ngẫu nhiên từ 1 đến 4
//            Product product = new Product(51, "abc", "abc.jpg", 2, 20, "abc", 1);
//            int n = dao.insertProducts(product);
//        }
//        int n = dao.updateProducts(product);
//        int n = dao.deleteProducts(product);
//        System.out.println(dao.findByPriceRange(25000, 25001,1));
//        System.out.println(dao.findAll());
//        System.out.println(dao.findBySortedID("desc", 1));
//                    int id = 51;
//            String name = "name",
//                    image = "image";
//            int quantity = 2;
//            double price = 2;
//            String description = "abc";
//            int category_id = 1;
//            Product product = new Product(id,name, image, quantity, price, description, category_id);
//            dao.insertProducts(product);
//        System.out.println(dao.getProducts("select * from Product\n"
//                + "where id = 2"));
//        Product product = new Product(51, "abc", "3.jpg", 2, 20000, "abc", 1);
//        dao.updateProducts(product);
        System.out.println(dao.findByName("Naruto", 1));
    }

}
