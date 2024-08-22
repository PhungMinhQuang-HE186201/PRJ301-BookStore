/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.homepage;

import entity.Category;
import entity.OrderItem;
import entity.Page;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.dal.CategoryDAO;
import model.dal.OrderItemDAO;
import model.dal.ProductDAO;

/**
 *
 * @author PMQUANG
 */
public class HomeController extends HttpServlet {

    ProductDAO productDao = new ProductDAO();
    CategoryDAO categoryDao = new CategoryDAO();
    OrderItemDAO orderitemdao = new OrderItemDAO();

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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
        Page page = new Page();
        //list product
        Vector<Product> vectorProduct = findProduct(request, page);
        //list product all
        Vector<Product> listAllProduct = productDao.findAllPageProduct();
        //list category
        Vector<Category> vectorCategory = categoryDao.findAll();
        //list best seller
        Vector<OrderItem> bestSeller = orderitemdao.findBestSeller();
        //price range
        //set listProduct, listCategory
        HttpSession session = request.getSession();
        session.setAttribute("vectorProduct", vectorProduct);
        session.setAttribute("listAllProduct", listAllProduct);
        session.setAttribute("vectorCategory", vectorCategory);
        session.setAttribute("bestSeller", bestSeller);
        session.setAttribute("page", page);
        request.getRequestDispatcher("view/homepage/home.jsp").forward(request, response);
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
        response.sendRedirect("home");
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

    private Vector<Product> findProduct(HttpServletRequest request, Page page) {
        //get page
        String pageRaw = request.getParameter("pageIndex");
        int pageIndex;
        try {
            pageIndex = Integer.parseInt(pageRaw);
            if (pageIndex <= 0) {
                pageIndex = 1;
            }
        } catch (NumberFormatException e) {
            pageIndex = 1; // page String
        }
        //get search
        String search = request.getParameter("search") == null ? "default"
                : request.getParameter("search");
        //list product
        Vector<Product> vectorProduct;
        //get request URL
        String requestURL = request.getRequestURL().toString();
        //total record
        int totalRecord;
        switch (search) {
            case "category":
                int category_id = Integer.parseInt(request.getParameter("category_id"));
                totalRecord = productDao.findTotalByCategory(category_id);
                vectorProduct = productDao.findByCategory(category_id, pageIndex);
                page.setUrlPattern(requestURL + "?search=category&category_id=" + category_id + "&");
                break;
            case "searchByName":
                String keyword = request.getParameter("keyword");
                totalRecord = productDao.findTotalByName(keyword);
                vectorProduct = productDao.findByName(keyword, pageIndex);
                page.setUrlPattern(requestURL + "?search=searchByName&keyword=" + keyword + "&");
                break;
            case "price":
                String price = request.getParameter("price");
                String[] prices = price.split(":");
                double min = "0".equals(prices[0]) ? 0 : Double.parseDouble(prices[0]);
                double max = "max".equals(prices[1]) ? Double.MAX_VALUE : Double.parseDouble(prices[1]);
                totalRecord = productDao.findTotalByPrice(min, max);
                vectorProduct = productDao.findByPriceRange(min, max, pageIndex);
                page.setUrlPattern(requestURL + "?search=price&price=" + min + ":" + max + "&");
                break;
            case "Sort":
                String sortOption = request.getParameter("sortOption");
                if (sortOption == null) {
                    sortOption = "id_desc"; // Default sort option
                }
                String[] sortParams = sortOption.split("_");
                String sortBy = sortParams[0];
                String sortOrder = sortParams[1];
                totalRecord = productDao.findTotalRecord();
                vectorProduct = productDao.findAllSorted(sortBy, sortOrder, pageIndex);
                page.setUrlPattern(requestURL + "?search=Sort&sortOption=" + sortOption + "&");
                break;
            default:
                totalRecord = productDao.findTotalRecord();
                vectorProduct = productDao.findByPage(pageIndex);
                page.setUrlPattern(requestURL + "?");
        }
        //total page
        int recordPerPage = 12;
        int totalPage = (totalRecord % recordPerPage) == 0 ? (totalRecord / recordPerPage)
                : (totalRecord / recordPerPage) + 1;
        //set total record, totalpage, pageIndex
        page.setTotalRecord(totalRecord);
        page.setTotalPage(totalPage);
        page.setPageIndex(pageIndex);
        return vectorProduct;
    }

}
