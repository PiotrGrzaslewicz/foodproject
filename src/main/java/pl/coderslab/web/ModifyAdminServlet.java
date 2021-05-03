package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BlockAdminServlet", value = "/app/modifyadmin")
public class ModifyAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        AdminDao adminDao = new AdminDao();
        int id = (Integer.parseInt(request.getParameter("id")));

        if ((Integer) session.getAttribute("superAdmin") == 1) {

            if (request.getParameter("action").equals("block")) {

                Admin admin = adminDao.findById(id);
                admin.setEnable(0);
                adminDao.updateAdmin(admin);
            } else if (request.getParameter("action").equals("unblock")) {
                Admin admin = adminDao.findById(id);
                admin.setEnable(1);
                adminDao.updateAdmin(admin);
            }
        }
        response.sendRedirect("/app/super-admin-users");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
