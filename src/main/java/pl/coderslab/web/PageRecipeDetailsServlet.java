package pl.coderslab.web;

import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "PageRecipeDetailsServlet", value = "/details")
public class PageRecipeDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipe recipe = (new RecipeDAO()).read(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("recipe", recipe);
        String[] list = ((Recipe) request.getAttribute("recipe")).getIngredients().split("\n");
        request.setAttribute("ingredients", list);
        getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
