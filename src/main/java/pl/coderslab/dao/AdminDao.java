package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO admins(first_name, last_name, email, password)" +
            "VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM admins WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM admins";
    private static final String READ_USERS_QUERY = "SELECT * FROM admins WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE admins SET first_name = ?, last_name = ?," +
            "email = ?, password = ? WHERE id = ?";

    public Admin create(Admin admin) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, admin.getFirstName());
            stm.setString(2, admin.getLastName());
            stm.setString(3, admin.getEmail());
            stm.setString(4, hashPassword(admin.getPassword()));
            stm.executeUpdate();
            ResultSet resultSet = stm.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int adminId) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(DELETE_USER_QUERY);
            stm.setInt(1, adminId);
            stm.executeUpdate();

            boolean deleted = stm.execute();
            if (!deleted) {
                throw new NotFoundException("Nie udało się usunąć");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminList.add(adminToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public Admin read(int adminId) {

        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(READ_USERS_QUERY);
            stm.setInt(1, adminId);
            try (ResultSet resultSet = stm.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public void update(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(UPDATE_USER_QUERY);
            stm.setInt(4, admin.getId());
            stm.setString(1, admin.getFirstName());
            stm.setString(2, admin.getLastName());
            stm.setString(3, admin.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
