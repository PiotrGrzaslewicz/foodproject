package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admin implements Comparable<Admin> {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int superAdmin;
    private int enable;

    public int compareTo(Admin admin) {
        return (int) (this.id - admin.getId());
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin(String firstName, String lastName, String email, String password, int superAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superAdmin = superAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String hashAndSetPassword () {
        this.password = hashPassword(password);
        return password;
    }

    public int getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(int superAdmin) {
        this.superAdmin = superAdmin;
    }

    @Override
    public String toString(){
        return id + " " + firstName + " " + lastName + " " + email + " " + password + " " + superAdmin + "" + enable;
    }
    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public void setEnable(int enabled) {
        this.enable = enabled;
    }

    public int getEnable() {
        return enable;
    }
}
