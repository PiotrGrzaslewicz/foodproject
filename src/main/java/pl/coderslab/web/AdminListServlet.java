package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

@WebServlet(name = "AdminListServlet", value = "/app/super-admin-users")
public class AdminListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDao adminDao = new AdminDao();

        List<Admin> admins = adminDao.findAll();
        HashMap<Admin, Integer> adminMap = new HashMap<Admin, Integer>();


        for (Admin admin : admins) {
            Integer status;
            if (admin.getSuperAdmin() == 1) {
                status = 3;
            } else {
                status = admin.getEnable();
            }
            adminMap.put(admin, status);
        }

        TreeMap<Admin, Integer> sortedAdmins = new TreeMap<>(adminMap);


        request.setAttribute("component", "/app/admin/admins.jsp");
        request.setAttribute("adminMap", sortedAdmins);
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
