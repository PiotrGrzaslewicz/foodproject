package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Admin admin = new Admin();

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        admin.setEmail(email);
        admin.setPassword(pass);

        AdminDao adminDao = new AdminDao();

        Admin checkAdmin = adminDao.findByEmail(email);

        if (email.equals("")){

        String errorMsg = "Proszę podać wszystkie dane";
        req.setAttribute("errorMsg", errorMsg);
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }else if (!checkAdmin.getEmail().equals(email)) {

            String errorMsg = "Niepoprawny email. Spróbuj ponownie";
            req.setAttribute("errorMsg1", errorMsg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }else if (!BCrypt.checkpw(pass, checkAdmin.getPassword())){

            String errorMsg = "Niepoprawne hasło. Spróbuj ponownie";
            req.setAttribute("errorMsg2", errorMsg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }else{
            getServletContext().getRequestDispatcher("/app/dashboard").forward(req, resp);
        }
    }
}
