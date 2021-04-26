package pl.coderslab.model;

import java.sql.Timestamp;

public class Recipe {

    private int id;
    private String name;
    private String ingredients;
    private String description;
    private java.sql.Timestamp created;
    private java.sql.Timestamp updated;
    private int preparationTime;
    private String preparation;
    private int adminId;

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", preparationTime=" + preparationTime +
                ", preparation='" + preparation + '\'' +
                ", adminId=" + adminId +
                '}';
    }

    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public String getPreparation() {
        return preparation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
