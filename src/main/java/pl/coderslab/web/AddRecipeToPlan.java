package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/*Po wejściu metodą POST na adres /app/recipe/plan/add ,
 aplikacja powinna:

pobrać dane, a nastepnie zapisać je przy pomocy klasy Dao do bazy,
przekierować użytkownika na adres /app/recipe/plan/add,
umożliwiając tym samym dodanie kolejnego przepisu do planu.
*/
@WebServlet("/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {

    private static final String ADD_RECIPE_TO_PLAN = "INSERT INTO recipe_plan(recipe_id, meal_name, display_order, day_name_id, plan_id) VALUES (?,?,?, ?, ?);";

    public void addrecipetoplan(int recipe_id, String meal_name, int display_order, int day_name_id, int plan_id) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(ADD_RECIPE_TO_PLAN, PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setInt(1, recipe_id);
            insertStm.setString(2, meal_name);
            insertStm.setInt(3, display_order);
            insertStm.setInt(4, day_name_id);
            insertStm.setInt(5, plan_id);

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = (Integer) session.getAttribute("adminId");
        PlanDao planDao = new PlanDao();
        RecipeDAO recipeDAO = new RecipeDAO();
        List<Plan> plan = planDao.getByAdminId(id);
        request.setAttribute("plan", plan);

        List<Recipe> recipe = recipeDAO.findAllByAdmin(id);
        request.setAttribute("recipe", recipe);

        request.setAttribute("component", "/app/recipe/plan/add.jsp");

        getServletContext().getRequestDispatcher("/app/frame.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plan"));
        int recipeId = Integer.parseInt(request.getParameter("recipe"));
        String meal = request.getParameter("mealname");
        int day = Integer.parseInt(request.getParameter("day"));
        int disOrder = Integer.parseInt(request.getParameter("displayorder"));


        addrecipetoplan(recipeId, meal, disOrder, day, planId);



        response.sendRedirect("/app/recipe/plan/add");
    }


}
