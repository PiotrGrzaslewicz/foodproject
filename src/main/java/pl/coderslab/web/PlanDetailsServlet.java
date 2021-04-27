package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PlanDetailsServlet", value = "/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("plan", (new PlanDao()).getById(Integer.parseInt(request.getParameter("id"))));

        request.setAttribute("component", "/app/plan/details.jsp");
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
