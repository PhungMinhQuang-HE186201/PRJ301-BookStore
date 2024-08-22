/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authen;

import controller.Email.Email;
import entity.Account;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import model.dal.AccountDAO;
import model.dal.OrderDAO;
import java.util.Iterator;
import java.util.Properties;
import java.util.Date;


/**
 *
 * @author PMQUANG
 */
public class AuthenController extends HttpServlet {

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
            out.println("<title>Servlet AuthenController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthenController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action") == null
                ? "" : request.getParameter("action");
        String url;
        switch (action) {
            case "login":
                url = "view/authen/login.jsp";
                break;
            case "logout":
                url = logOut(request, response);
                break;
            case "signUp":
                url = "view/authen/register.jsp";
                break;
            case "lostPass":
                url = "view/authen/lostPass.jsp";
                break;
            case "profile":
                url = "view/authen/profile.jsp";
                break;

            default:
                url = "home";
        }
        request.getRequestDispatcher(url).forward(request, response);
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
                ? "" : request.getParameter("action");
        String url;
        switch (action) {
            case "login":
                url = login(request, response);
                break;
            case "signUp":
                url = signUp(request, response);
                break;
            case "changeInfo":
                url = changeInfo(request);
                break;
            case "lostPass":
                url = lostPass(request);
                break;
            default:
                url = "home";
        }
        request.getRequestDispatcher(url).forward(request, response);
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

    private String login(HttpServletRequest request, HttpServletResponse response) {
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = null;
        Account accFounded = dao.findByUsernamePassword(username, password);
        if (accFounded == null) {
            request.setAttribute("error", "Username or password incorrect!!");
            url = "view/authen/login.jsp";
        } else {
            session.setAttribute("accFounded", accFounded);
            if (accFounded.getRoleId() == 1) {
                url = "product";
            } else if (accFounded.getRoleId() == 2) {
                url = "home";
            }
        }

        return url;
    }

    private String logOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("accFounded");
        return "home";
    }

    private String signUp(HttpServletRequest request, HttpServletResponse response) {
        AccountDAO dao = new AccountDAO();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String url = null;
        Account accExist = dao.findByAccountExist(username);
        if (accExist != null) {
            request.setAttribute("error", "Account exist!!");
            url = "view/authen/register.jsp";
        } else {
            int id = dao.getMaxId() + 1;
            dao.insertAccount(new Account(id, username, password, email, address, phone, 2));
            url = "view/authen/login.jsp";
        }

        return url;
    }

    private String changeInfo(HttpServletRequest request) {
        AccountDAO dao = new AccountDAO();
        String username = request.getParameter("username");
        String newPass = request.getParameter("newPass");
        String confirmPass = request.getParameter("confirmPass");
        String address = request.getParameter("address");
        boolean hasError = false;
        String url = "view/authen/profile.jsp"; // default url to redirect in case of error

        if (!confirmPass.equals(newPass)) {
            request.setAttribute("error", "Confirm password does not match new password");
            hasError = true;
        }

        if (!hasError) {
            if (newPass.equals("") || confirmPass.equals("")) {
                dao.updateAddress(new Account(username, address));
            } else {
                dao.updateInfo(new Account(username, confirmPass, address));
            }
            url = "home"; // URL to redirect after successful password change
        }

        return url;
    }

    private String lostPass(HttpServletRequest request) {
        AccountDAO dao = new AccountDAO();
        Email mail = new Email();
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String url = null;
        Account accExist = dao.findByAccountExist(username);
        if (accExist == null) {
            request.setAttribute("error", "Account not exist!!");
            url = "view/authen/lostPass.jsp";
        } else {
            String newPassword = dao.generateRandomString();
            dao.changePassword(email, newPassword);
            mail.sendEmail(email, newPassword);
            url = "view/authen/login.jsp";
        }

        return url;
    }

}
