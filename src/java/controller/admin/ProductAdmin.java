/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import entity.Category;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dal.CategoryDAO;
import model.dal.ProductDAO;

/**
 *
 * @author PMQUANG
 */
@MultipartConfig
public class ProductAdmin extends HttpServlet {

    ProductDAO productdao = new ProductDAO();
    CategoryDAO categorydao = new CategoryDAO();

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
            out.println("<title>Servlet ProductAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductAdmin at " + request.getContextPath() + "</h1>");
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
        // set enconding UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        //session
        HttpSession session = request.getSession();
        //action
        String action = request.getParameter("action") == null
                ? action = "" : request.getParameter("action");
        switch (action) {
            case "add":
                addProduct(request);
                break;
            case "delete":
                deleteProduct(request);
                break;
            case "update":
                updateProduct(request);
                break;
            default:

        }
        response.sendRedirect("dashboard");
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

    private void addProduct(HttpServletRequest request) {
        try {
            //get data
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            Part part = request.getPart("image");
            String imagePath = null;
            if (part == null
                    || part.getSubmittedFileName().trim().isEmpty()
                    || part.getSubmittedFileName() == null) {
                imagePath = null;
            } else {
                // duong dan luu anh
                String path = request.getServletContext().getRealPath("/img");
                File folder = new File(path);
                // xem duongd an nay ton tai chua
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                File image = new File(folder, part.getSubmittedFileName());
                // ghi file vao trong duong dan
                part.write(image.getAbsolutePath());
                // lay ra cai context path cua project
                imagePath = image.getName();
            }
//            String image = request.getParameter("image");
            productdao.insertProducts(new Product(id, name, imagePath, quantity, price, description, category_id));
        } catch (NumberFormatException | IOException | ServletException ex) {
//        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Product product = new Product(id);
            productdao.deleteProducts(product);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request) {
        try {
            //get data
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            int category_id = Integer.parseInt(request.getParameter("category_id"));
            Part part = request.getPart("image");
            String imagePath = null;
            if (part == null
                    || part.getSubmittedFileName().trim().isEmpty()
                    || part.getSubmittedFileName() == null) {
                imagePath = request.getParameter("currentImage");
            } else {
                // duong dan luu anh
                String path = request.getServletContext().getRealPath("/img");
                File folder = new File(path);
                // xem duongd an nay ton tai chua
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                File image = new File(folder, part.getSubmittedFileName());
                // ghi file vao trong duong dan
                part.write(image.getAbsolutePath());
                // lay ra cai context path cua project
                imagePath = image.getName();
            }
            productdao.updateProducts(new Product(id, name, imagePath, quantity, price, description, category_id));
        } catch (NumberFormatException | IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

}
