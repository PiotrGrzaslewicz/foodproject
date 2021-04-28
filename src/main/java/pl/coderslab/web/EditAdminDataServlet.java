package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserDataServlet", value = "/app/edit-admin-data")
public class EditAdminDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("component", "/app/admin/editadmindata.jsp");
        int id = ((Integer) session.getAttribute("adminId"));
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.findById(id);
        request.setAttribute("admin",admin);
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.findById((Integer) session.getAttribute("adminId"));
        admin.setFirstName(request.getParameter("firstName"));
        admin.setLastName(request.getParameter("lastName"));
        admin.setEmail(request.getParameter("email"));
        System.out.println(admin.toString());
        adminDao.updateAdmin(admin);
        response.sendRedirect("/app/dashboard");
    }
}