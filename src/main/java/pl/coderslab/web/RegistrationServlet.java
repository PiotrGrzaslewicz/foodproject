package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Admin admin = new Admin();
        AdminDao adminDao = new AdminDao();
        resp.setContentType("text/html;charset=UTF-8");

        String firstName = req.getParameter("name");
        String lastName = req.getParameter("surname");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String pass2 = req.getParameter("repassword");

        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmail(email);
        admin.setPassword(pass);

        Admin checkAdmin = adminDao.findByEmail(email);

        if (checkAdmin.getId() > 0) {

            String errorMsg = "Użytkownik o podanym mailu już istnieje";
            req.setAttribute("errorMsg", errorMsg);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);

        }else if(firstName == null || lastName == null || email == null || pass == null || pass2 == null){

            String errorMsg3 = "Należy wypełnić wszystkie pola";
            req.setAttribute("errorMsg3", errorMsg3);
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);

        }else{

            if (pass.equals(pass2)) {

                adminDao.createAdmin(admin);
                getServletContext().getRequestDispatcher("/login").forward(req, resp);

            } else {

                String errorMsg2 = "Podane hasła nie są identyczne";
                req.setAttribute("errorMsg2",errorMsg2);
                getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
            }
        }
    }
}
