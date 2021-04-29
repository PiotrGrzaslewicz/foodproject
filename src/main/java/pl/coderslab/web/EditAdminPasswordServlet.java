package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditAdminServlet", value = "/app/edit-password")
public class EditAdminPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("component", "/app/admin/editadminpassword.jsp");
        int id = ((Integer) session.getAttribute("adminId"));
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.findById(id);
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        if (!password.equals(repassword)) {
            String errorMsg = "Podane hasła nie są identyczne";
            request.setAttribute("errorMsg", errorMsg);
            request.setAttribute("component", "/app/admin/editadminpassword.jsp");
            getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
        } else {
            AdminDao adminDao = new AdminDao();
            int id = (Integer) session.getAttribute("adminId");
            Admin admin = adminDao.findById(id);
            admin.setPassword(password);
            admin.hashAndSetPassword();
            adminDao.updateAdmin(admin);
            response.sendRedirect("/app/dashboard");
        }


    }
}
