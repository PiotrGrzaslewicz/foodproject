package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_USER_QUERY = "INSERT INTO admins(first_name, last_name, email, password, superadmin)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM admins WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM admins";
    private static final String READ_USERS_QUERY = "SELECT * FROM admins WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE admins SET first_name = ?, last_name = ?," +
            "email = ?, password = ? WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM admins WHERE email = ?";

    public int createAdmin(Admin admin) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, admin.getFirstName());
            stm.setString(2, admin.getLastName());
            stm.setString(3, admin.getEmail());
            stm.setString(4, hashPassword(admin.getPassword()));
            stm.setString(5, String.valueOf(admin.getSuperAdmin()));
            stm.executeUpdate();
            ResultSet resultSet = stm.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteAdmin(int adminId) {

        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(DELETE_USER_QUERY);
            stm.setInt(1, adminId);

            boolean deleted = stm.execute();
            if (!deleted) {
                throw new NotFoundException("Nie udało się usunąć");
            }
            return stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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
                adminToAdd.setSuperAdmin(resultSet.getInt("superadmin"));
                adminList.add(adminToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public Admin findByEmail(String email) {

        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(FIND_BY_EMAIL_QUERY);
            stm.setString(1, email);
            try (ResultSet resultSet = stm.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setSuperAdmin(resultSet.getInt("superadmin"));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public Admin findById(int adminId) {

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
                    admin.setSuperAdmin(resultSet.getInt("superadmin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    public int updateAdmin(Admin admin) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement stm = conn.prepareStatement(UPDATE_USER_QUERY);
            stm.setString(1, admin.getFirstName());
            stm.setString(2, admin.getLastName());
            stm.setString(3, admin.getEmail());
            stm.setString(4, admin.getPassword());
            stm.setInt(5, admin.getSuperAdmin());
            return stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

}
