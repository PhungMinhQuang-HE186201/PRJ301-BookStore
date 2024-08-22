/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import entity.Account;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;
import model.dal.OrderDAO;
import model.dal.OrderItemDAO;
import model.dal.ProductDAO;

/**
 *
 * @author PMQUANG
 */
public class CartController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/user/cart.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? ""
                : request.getParameter("action");
        switch (action) {
            case "addProduct":
                addProduct(request, response);
                break;
            case "deleteProduct":
                deleteProduct(request, response);
                break;
            case "deleteAllProduct":
                deleteAllProduct(request, response);
                break;
            case "changeQuantity":
                changeQuantity(request, response);
                break;
            case "checkOut":
                checkOut(request, response);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //session
        HttpSession session = request.getSession();
        //product id
        int id = Integer.parseInt(request.getParameter("id"));
        //quantity
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //lay cart o tren session
        Order cart = (Order) session.getAttribute("cart");
        Account acc = (Account) session.getAttribute("accFounded");
        if (cart == null) {
            cart = new Order();
        }
        OrderItem item = new OrderItem();
        item.setProduct_id(id);
        item.setQuantity(quantity);
        //them item vao cart
        addItemToOrder(item, cart);
        //set cart moi cho session
        session.setAttribute("cart", cart);
        response.sendRedirect("cart");

    }

    private void addItemToOrder(OrderItem item, Order cart) {
        boolean isAdd = false;
        for (OrderItem orderItem : cart.getListOrderItems()) {
            if (orderItem.getProduct_id() == item.getProduct_id()) {
                orderItem.setQuantity(orderItem.getQuantity() + item.getQuantity());
                isAdd = true;
            }
        }
        if (!isAdd) {
            cart.getListOrderItems().add(item);
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //session
        HttpSession session = request.getSession();
        //product id
        int id = Integer.parseInt(request.getParameter("id"));
        //lay cart
        Order cart = (Order) session.getAttribute("cart");
        OrderItem item = null;
        //gan item vao id product
        for (OrderItem orderItem : cart.getListOrderItems()) {
            if (orderItem.getProduct_id() == id) {
                item = orderItem;
            }
        }
        //xoa
        cart.getListOrderItems().remove(item);
        //set cart moi
        session.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    private void deleteAllProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //session
        HttpSession session = request.getSession();
        //lay cart
        Order cart = (Order) session.getAttribute("cart");
        //xoa
        cart.getListOrderItems().removeAllElements();
        //set cart moi
        session.setAttribute("cart", cart);
        response.sendRedirect("cart");
    }

    private void changeQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //session
        HttpSession session = request.getSession();
        //product id
        int id = Integer.parseInt(request.getParameter("id"));
        //quantity
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        //lay ra cart
        Order cart = (Order) session.getAttribute("cart");
        //trong list get ra quantity moi
        for (OrderItem orderitem : cart.getListOrderItems()) {
            if (orderitem.getProduct_id() == id) {
                orderitem.setQuantity(quantity);
            }
        }
        response.sendRedirect("cart");
    }

    private void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderdao = new OrderDAO();
        OrderItemDAO itemdao = new OrderItemDAO();
        ProductDAO productdao = new ProductDAO();
        //cart
        HttpSession session = request.getSession();
        Order cart = (Order) session.getAttribute("cart");
        //account
        Account acc = (Account) session.getAttribute("accFounded");
        //list product
        Vector<Product> vector = (Vector<Product>) session.getAttribute("vectorProduct");
        if (cart == null || vector == null) {
            response.sendRedirect("home");
            return;
        }
        if(acc == null){
            response.sendRedirect("authen?action=login");
            return;
        }
        //list price
        float list_price = calculatePrice(cart, vector);
        //set info
        cart.setAccountId(acc.getId());
        cart.setList_price(list_price);
        cart.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
        cart.setStatus("waiting");
        //insert
        int maxOrderId = orderdao.getMaxOrderId(acc.getId());
        int orderId = maxOrderId + 1;
        cart.setId(orderId);
        orderdao.insertOrder(cart);
        int itemId = 1;
        for (OrderItem orderitem : cart.getListOrderItems()) {
            Product p = new Product(orderitem.getProduct_id());
            Product product = productdao.findByID(p);
            orderitem.setOrder_id(orderId);
            orderitem.setItem_id(itemId);
            orderitem.setAccountId(acc.getId());
            float price = (float) findPriceById(vector, orderitem.getProduct_id());
            orderitem.setPrice_item(price);
            itemdao.insertOrderItem(orderitem);
            itemId++;
            //tong san pham tru di san pham mua
            if (product != null) {
                int newQuantity = product.getQuantity() - orderitem.getQuantity();
                product.setQuantity(newQuantity);
                productdao.updateProducts(product);
            }
        }
        //toi form check out
        request.getRequestDispatcher("view/user/checkOut.jsp").forward(request, response);
        //xoa cart
        session.removeAttribute("cart");
    }

    private float calculatePrice(Order cart, Vector<Product> vector) {
        int total = 0;
        for (OrderItem orderitem : cart.getListOrderItems()) {
            total += (orderitem.getQuantity() * findPriceById(vector, orderitem.getProduct_id()));
        }
        return total;
    }

    private double findPriceById(Vector<Product> vector, int product_id) {
        double price = 0;
        for (Product product : vector) {
            if (product.getId() == product_id) {
                return product.getPrice();
            }
        }
        return price;
    }

}
