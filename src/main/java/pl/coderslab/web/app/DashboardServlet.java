package pl.coderslab.web.app;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlanDao planDao = new PlanDao();
        RecipeDAO recipeDAO = new RecipeDAO();

        HttpSession session = request.getSession();
        int id = (Integer) session.getAttribute("adminId");
        Plan lastPlan = planDao.getLastPlan(id);
        request.setAttribute("numberAddedPlans", planDao.numberOfPlansByAdminId(id));
        request.setAttribute("numberAddedRecipes", recipeDAO.numberOfRecipesByAdminId(id));
        if (lastPlan == null) {
            request.setAttribute("errorMsg", "Na razie nie masz dodanych żadnych planów");
        } else {
            request.setAttribute("plan", lastPlan);
            request.setAttribute("details", lastPlan.getDetails());
        }
        request.setAttribute("component", "/app/dashboard.jsp");
        getServletContext().getRequestDispatcher("/app/frame.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}