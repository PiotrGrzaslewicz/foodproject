package pl.coderslab.model;

import pl.coderslab.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class Registration extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Admin admin = new Admin();
        AdminDao adminDao = new AdminDao();

        String firstName = req.getParameter("name");
        String lastName = req.getParameter("surname");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String pass2 = req.getParameter("repassword");

        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setPassword(pass);
        if (pass.equals(pass2)) {
            adminDao.createAdmin(admin);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }else{
            resp.getWriter().println("Podane hasła nie są identyczne");
        }
    }
}
