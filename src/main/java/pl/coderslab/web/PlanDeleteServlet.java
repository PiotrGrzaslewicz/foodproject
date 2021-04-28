package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PlanDeleteServlet", value = "/app/plan/delete")
public class PlanDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("confirm")==null) {
            int id = Integer.parseInt(request.getParameter("id"));
            PlanDao dao = new PlanDao();
            request.setAttribute("plan", dao.getById(id));
            request.setAttribute("component", "/app/plan/delete.jsp");
            getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
        } else if (request.getParameter("confirm").equals("1")) {
            int id = Integer.parseInt(request.getParameter("id"));
            PlanDao dao = new PlanDao();
            dao.deletePlan(id);
            response.sendRedirect("/app/plan/list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
