package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDAO {

    //ZAPYTANIA SQL
    private static final String READ_RECIPE_QUERY = "SELECT * from recipe where id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String FIND_ALL_RECIPES_BY_ADMIN_QUERY = "SELECT * FROM recipe where admin_id = ?;";
    private static final String CREATE_RECIPE_QUERY = "INSERT INTO recipe(name, ingredients, description, created, updated, preparation_time, preparation, admin_id) VALUES (?,?,?, ?, ?, ?, ?, ?);";
    private static final String DELETE_RECIPE_QUERY = "DELETE FROM recipe where id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ? , ingredients = ?, description = ?, updated = ?, preparation_time = ?, preparation = ?, admin_id = ? WHERE id = ?;";
    private static final String NUMBER_OF_RECIPES_ADDED_BY_ADMIN = "select count(*) as count from recipe where admin_id=?;";



    public Recipe read(Integer recipeId) {
        Recipe recipe = null;

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    recipe = buildFromSet(resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    private Recipe buildFromSet(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setName(resultSet.getString("name"));
        recipe.setIngredients(resultSet.getString("ingredients"));
        recipe.setDescription(resultSet.getString("description"));
        recipe.setCreated(resultSet.getTimestamp("created"));
        recipe.setUpdated(resultSet.getTimestamp("updated"));
        recipe.setPreparationTime(resultSet.getInt("preparation_time"));
        recipe.setPreparation(resultSet.getString("preparation"));
        recipe.setAdminId(resultSet.getInt("admin_id"));
        return recipe;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery();
        ) {

            while (resultSet.next()) {
                recipeList.add(buildFromSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    public List<Recipe> findAllByAdmin(int adminId) {
        List<Recipe> allRecipesByAdmin = new ArrayList<>();


        try (Connection conn = DbUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_ALL_RECIPES_BY_ADMIN_QUERY);
        ) {
            stmt.setInt(1, adminId);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                allRecipesByAdmin.add(buildFromSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allRecipesByAdmin;
    }

    public Recipe create(Recipe recipe) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_RECIPE_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {

            insertStm.setString(1, recipe.getName());
            insertStm.setString(2, recipe.getIngredients());
            insertStm.setString(3, recipe.getDescription());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            insertStm.setTimestamp(4, timestamp);
            insertStm.setTimestamp(5, timestamp);
            insertStm.setInt(6, recipe.getPreparationTime());
            insertStm.setString(7, recipe.getPreparation());
            insertStm.setInt(8, recipe.getAdminId());

            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {

                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;

                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Integer recipeId) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RECIPE_QUERY)) {

            statement.setInt(1, recipeId);
            int deleted = statement.executeUpdate();

            if (deleted == 0) {
                throw new NotFoundException("Recipe not found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Recipe recipe) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {

            statement.setInt(8, recipe.getId());
            statement.setString(1, recipe.getName());
            statement.setString(2, recipe.getIngredients());
            statement.setString(3, recipe.getDescription());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            statement.setTimestamp(4, timestamp);
            statement.setInt(5, recipe.getPreparationTime());
            statement.setString(6, recipe.getPreparation());
            statement.setInt(7, recipe.getAdminId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int numberOfRecipesByAdminId(int id) {

        int number = 0;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(NUMBER_OF_RECIPES_ADDED_BY_ADMIN, ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.first()) {
                number = resultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;
    }

    public int numberOfRecipes(){
        String sql = "SELECT COUNT(id) AS 'count' FROM recipe";

        try(Connection conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet set = stmt.executeQuery();
            if(set.next()) return set.getInt("count");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public List<Recipe> getWithLimit(int limit, int offset){
        String sql = "SELECT * FROM recipe ORDER BY updated desc LIMIT ? OFFSET ?";
        List<Recipe> list = new ArrayList<>();

        try(Connection conn = DbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, limit);
            stmt.setInt(2, offset);
            ResultSet set = stmt.executeQuery();
            while (set.next()){
                list.add(buildFromSet(set));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}