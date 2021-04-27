package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    public int createPlan(Plan plan){
        String sql = "INSERT INTO plan VALUES (NULL, ?, ?, ?, ?)";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, plan.getName());
            stmt.setString(2, plan.getDescription());
            if(plan.getCreated() != null) stmt.setObject(3, plan.getCreated());
            else stmt.setObject(3, LocalDateTime.now());
            stmt.setInt(4, plan.getAdminId());
            stmt.executeUpdate();
            ResultSet set = stmt.getGeneratedKeys();
            if(set.next()) return set.getInt(1);
            return 0;
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public int updatePlan(Plan plan){
        String sql = "UPDATE plan SET name = ?, description = ? WHERE id = ?";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)){
            stmt.setString(1, plan.getName());
            stmt.setString(2, plan.getDescription());
            stmt.setInt(3, plan.getId());
            return stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    public Plan getById(int id){
        Plan plan = null;
        String sql = "SELECT * FROM plan WHERE id = ?";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()){
                plan = buildFromSet(set);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return plan;
    }

    public List<Plan> getAll(){
        List<Plan> list = new ArrayList<>();
        String sql = "SELECT * FROM plan";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)){
            ResultSet set = stmt.executeQuery();
            while (set.next()){
                list.add(buildFromSet(set));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Plan> getByAdminId(int adminId){
        List<Plan> list = new ArrayList<>();
        String sql = "SELECT * FROM plan WHERE admin_id = ?";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)){
            stmt.setInt(1, adminId);
            ResultSet set = stmt.executeQuery();
            while (set.next()){
                list.add(buildFromSet(set));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public int deletePlan(Plan plan){
        String sql = "DELETE FROM plan WHERE id = ?";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)){
            stmt.setInt(1, plan.getId());
            return stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    private Plan buildFromSet(ResultSet set) throws SQLException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Plan plan = new Plan();
        plan.setId(set.getInt("id"));
        plan.setName(set.getString("name"));
        plan.setDescription(set.getString("description"));
        plan.setCreated(set.getObject("created", LocalDateTime.class));
        plan.setAdminId(set.getInt("admin_id"));
        return plan;
    }

    public Plan getLastPlan(int id){
        Plan plan = null;
        String sql = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
                "            FROM recipe_plan\n" +
                "            JOIN day_name on day_name.id=day_name_id\n" +
                "            JOIN recipe on recipe.id=recipe_id WHERE\n" +
                "            recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
                "            ORDER by day_name.display_order, recipe_plan.display_order;";
        try(PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet set = stmt.executeQuery();
            if (set.next()){
                plan = buildFromSet(set);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return plan;
    }

    public int numberOfPlansByAdminId (int id) {
        String sql = "select count(*) as count from plan where admin_id = ?";
        int number = 0;

        try (PreparedStatement stmt = DbUtil.getConnection().prepareStatement(sql)) {
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.first()) {
                number = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return number;
    }
}
//