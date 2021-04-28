package pl.coderslab.dao;

import pl.coderslab.model.LastPlan;
import pl.coderslab.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateLastPlan {

    private LastPlan buildLastPlanFromSet(ResultSet set) throws SQLException {
        LastPlan lastPlan = new LastPlan();
        lastPlan.setPlanName(set.getString("name"));
        lastPlan.setDayName(set.getString("day_name"));
        lastPlan.setMealName(set.getString("meal_name"));
        lastPlan.setRecipeName(set.getString("recipe_name"));
        lastPlan.setRecipeDescription(set.getString("recipe_description"));

        return lastPlan;
    }

    public LastPlan getLastPlan(int id) {
        LastPlan lastPlan = null;
        String sql = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
                "            FROM recipe_plan\n" +
                "            JOIN day_name on day_name.id=day_name_id\n" +
                "            JOIN recipe on recipe.id=recipe_id WHERE\n" +
                "            recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
                "            ORDER by day_name.display_order, recipe_plan.display_order;";
        try (PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()) {
                lastPlan = buildLastPlanFromSet(set);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastPlan;
    }
}
