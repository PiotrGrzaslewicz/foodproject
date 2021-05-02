package pl.coderslab.web;

import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.Search;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "PageRecipesServlet", value = "/recipes")
public class PageRecipesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Recipe> list;
        if(request.getParameterMap().isEmpty()){
            list = (new RecipeDAO()).findAll();
            Comparator<Recipe> comparator = (r1, r2) -> r2.getUpdated().compareTo(r1.getUpdated());
            list.sort(comparator);
            request.setAttribute("name", "checked");
            request.setAttribute("description", "checked");
            request.setAttribute("ingredients", "checked");
            request.setAttribute("preparation", "checked");
        }
        else {
            Search<Recipe> search = new Search<>(Recipe.class, "recipe");
            list = search.inColumns(request.getParameter("searchTxt"), request.getParameterMap().get("columns"));
            for (String key : request.getParameterMap().get("columns")){
                request.setAttribute(key, "checked");
            }
            request.setAttribute("queryTxt", request.getParameter("searchTxt"));
        }
        request.setAttribute("results", list);
        getServletContext().getRequestDispatcher("/recipes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
