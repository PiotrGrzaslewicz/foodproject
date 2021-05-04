package pl.coderslab.web;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        AdminDao adminDao = new AdminDao();

        Admin checkAdmin = adminDao.findByEmail(email);

        if (email.equals("")) {

            String errorMsg = "Proszę podać wszystkie dane";
            req.setAttribute("errorMsg", errorMsg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        } else if (checkAdmin.getPassword() == null) {

            String errorMsg = "Użytkownik nie istnieje. Spróbuj ponownie";
            req.setAttribute("errorMsg1", errorMsg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        } else if (!BCrypt.checkpw(pass, checkAdmin.getPassword())) {

            String errorMsg = "Niepoprawne hasło. Spróbuj ponownie";
            req.setAttribute("errorMsg2", errorMsg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        } else if (checkAdmin.getEnable() == 0) {

            String errorMsg = "Administrator zablokował Twoje konto.";
            req.setAttribute("errorMsg", errorMsg);
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);

        }
        else {

            HttpSession session = req.getSession();
            session.setAttribute("adminId", checkAdmin.getId());
            session.setAttribute("adminName", checkAdmin.getFirstName());
            session.setAttribute("enable", checkAdmin.getEnable());
            if (checkAdmin.getSuperadmin()==1)
            {
                session.setAttribute("superAdmin", checkAdmin.getSuperadmin());
            }
            resp.sendRedirect("/app/dashboard");
        }
    }
}
