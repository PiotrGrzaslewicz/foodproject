package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Meal;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

@WebServlet(name = "PlanDetailsServlet", value = "/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Plan plan = (new PlanDao()).getById(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("plan", plan);
        request.setAttribute("details", plan.getDetails().entrySet());
        request.setAttribute("component", "/app/plan/details.jsp");
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
