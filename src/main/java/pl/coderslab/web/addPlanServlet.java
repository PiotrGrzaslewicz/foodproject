package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "addPlanServlet", value = "/app/plan/add")
public class addPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("component", "/app/plan/addplan.jsp");
        request.setAttribute("clientName", session.getAttribute("clientName"));
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Plan plan = new Plan();
        request.setCharacterEncoding("UTF-8");
        plan.setAdminId((Integer) request.getSession().getAttribute("clientId"));
        plan.setName(request.getParameter("name"));
        plan.setDescription(request.getParameter("description"));
        if((new PlanDao()).createPlan(plan) > 0) response.sendRedirect("/app/schedules");
        else response.getWriter().append("Something went wrong...");
    }
}
