package pl.coderslab.utils;

import com.mysql.cj.jdbc.MysqlDataSource;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDAO;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DbContentGenerator {

    private static final String uri = "src/main/resources/mock";
    private List<Recipe> recipes = getRecipes();
    private List<Admin> admins;

    public void populate(int userLimit) {
        DbUtil.setDataSource(getDataSource());
        admins = generateAdmins(userLimit);
        Random random = new Random();
        RecipeDAO dao = new RecipeDAO();
        for (Admin admin : admins) {
            System.out.println("Populating " + admin.getFirstName() + " " + admin.getLastName());
            for (int i = 0; i < (random.nextInt(15) + 5); i++) {
                Recipe recipe = recipes.get(random.nextInt(recipes.size()));
                recipe.setAdminId(admin.getId());
                dao.create(recipe);
            }
        }
        System.out.println("Finished populating");
    }

    private List<Admin> generateAdmins(int limit) {
        System.out.print("Generating admins");
        List<String[]> names = getUserNames();
        List<Admin> list = new ArrayList<>();
        AdminDao dao = new AdminDao();
        int counter = 0;
        for (String[] name : names) {
            counter++;
            if (counter > limit) break;
            Admin admin = new Admin();
            admin.setEmail(name[0].toLowerCase() + "." + name[1].toLowerCase() + "@gmail.com");
            Admin checkAdmin = dao.findByEmail(admin.getEmail());
            if (checkAdmin.getId() > 0) admin = checkAdmin;
            else {
                admin.setFirstName(name[0]);
                admin.setLastName(name[1]);
                admin.setPassword("1234");
                admin.setSuperAdmin(0);
                admin.setEnable(1);
                admin.setId(dao.createAdmin(admin));
            }
            list.add(admin);
            System.out.print(".");
        }
        System.out.println("finished");
        return list;
    }

    private List<String[]> getUserNames() {
        List<String[]> res = null;
        try {
            List<String> list = Files.readAllLines(Paths.get(uri + "/userName"));
            res = list.stream()
                    .map(s -> s.replaceAll("[0-9]", ""))
                    .map(s -> s.trim())
                    .map(s -> s.split(" "))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private List<Recipe> getRecipes() {
        List<Recipe> list = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(uri + "/recipes"))) {
            if (scan.hasNextLine()) scan.nextLine();
            while (scan.hasNextLine()) {
                if (scan.nextLine().equals("#recipe name:")) {
                    Recipe recipe = new Recipe();
                    String val = "";
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        if (line.equals("#description:")) break;
                        val += line + "\n";
                    }
                    recipe.setName(val);
                    val = "";
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        if (line.equals("#ingredients:")) break;
                        val += line + "\n";
                    }
                    recipe.setDescription(val);
                    val = "";
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        if (line.equals("#preparation:")) break;
                        val += line + "\n";
                    }
                    recipe.setIngredients(val);
                    val = "";
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        if (line.equals("#end")) break;
                        val += line + "\n";
                    }
                    recipe.setPreparation(val);
                    recipe.setPreparationTime((int) (Math.random() * 120 + 30));
                    list.add(recipe);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private DataSource getDataSource() {
        MysqlDataSource source = new MysqlDataSource();
        source.setUrl("jdbc:mysql://localhost:3306/scrumlab");
        source.setUser("root");
        source.setPassword("coderslab");
        try {
            source.setConnectionAttributes("useUnicode=yes;characterEncoding=utf8;useSSL=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return source;
    }

    public static void main(String[] args) {
        DbContentGenerator gen = new DbContentGenerator();
        gen.populate(10);
    }
}
