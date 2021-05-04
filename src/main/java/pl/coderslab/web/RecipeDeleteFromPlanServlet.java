package pl.coderslab.web;

import pl.coderslab.model.Meal;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/app/recipe/deleteFromPlan")
public class RecipeDeleteFromPlanServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plan plan = new Plan();
        if (req.getParameter("confirm") == null) {

            int id = Integer.parseInt(req.getParameter("id"));


            req.setAttribute("message", Meal.findById(id));
            req.setAttribute("okAction", "/app/recipe/deleteFromPlan?id=" + id + "&confirm=1");
            req.setAttribute("cancelAction", "/app/plan/details?id=" + plan.getId());
            req.setAttribute("component", "/app/question.jsp");
            getServletContext().getRequestDispatcher("/app/frame.jsp").forward(req, resp);

        }else if (req.getParameter("confirm").equals("1")) {

            int id = Integer.parseInt(req.getParameter("id"));
            Meal.remove(id);
            resp.sendRedirect("/app/plan/details?id=" + plan.getId() );
        }
    }
}
