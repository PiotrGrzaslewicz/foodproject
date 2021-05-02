package pl.coderslab.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.taglibs.standard.tag.common.sql.DataSourceUtil;
import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Recipe;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DbContentGenerator {

    private static final String uri = "/home/falcon/Kurs/ScrumLab/mock";

    private List<Recipe> recipes = getRecipes();

    private List<Recipe> getRecipes(){
        List<Recipe> list = new ArrayList<>();
        try(Scanner scan = new Scanner(new File(uri + "/recipes"))) {
            if(scan.hasNextLine()) scan.nextLine();
            while (scan.hasNextLine()){
                if(scan.nextLine().equals("#recipe name:")){
                    Recipe recipe = new Recipe();
                    String val = "";
                    while (scan.hasNextLine()){
                        String line = scan.nextLine();
                        if(line.equals("#description:")) break;
                        val += line + "\n";
                    }
                    recipe.setName(val);
                    val = "";
                    while (scan.hasNextLine()){
                        String line = scan.nextLine();
                        if(line.equals("#ingredients:")) break;
                        val += line + "\n";
                    }
                    recipe.setDescription(val);
                    val = "";
                    while (scan.hasNextLine()){
                        String line = scan.nextLine();
                        if(line.equals("#preparation:")) break;
                        val += line + "\n";
                    }
                    recipe.setIngredients(val);
                    val = "";
                    while (scan.hasNextLine()){
                        String line = scan.nextLine();
                        if(line.equals("#end")) break;
                        val += line + "\n";
                    }
                    recipe.setPreparation(val);
                    recipe.setPreparationTime((int)(Math.random()*120 + 30));
                    list.add(recipe);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return list;
    }

    private DataSource getDataSource(){
        MysqlDataSource source = new MysqlDataSource();
        source.setUrl("jdbc:mysql://localhost:3306/scrumlab");
        source.setUser("root");
        source.setPassword("coderslab");
        try {
            source.setConnectionAttributes("useUnicode=yes;characterEncoding=utf8;useSSL=false");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return source;
    }

    public static void main(String[] args) {
        DbContentGenerator gen = new DbContentGenerator();
        DbUtil.setDataSource(gen.getDataSource());
        RecipeDAO recipeDAO = new RecipeDAO();
        for (Recipe recipe : gen.recipes){
            recipe.setAdminId(1);
            recipeDAO.create(recipe);
        }
    }
}
