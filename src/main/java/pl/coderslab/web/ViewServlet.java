package pl.coderslab.web;

import pl.coderslab.dao.CreateLastPlan;
import pl.coderslab.dao.PlanDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateLastPlan lastPlanDao = new CreateLastPlan();
        request.setAttribute("lastPlan", lastPlanDao.getLastPlan(1));

        getServletContext().getRequestDispatcher("/dashboard.html")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
