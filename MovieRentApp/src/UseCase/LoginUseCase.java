package UseCase;

import Model.User;
import Config.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginUseCase {

    public boolean loginValidate(User user) {
        String query = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error during login validation.");
            ex.printStackTrace();
        }

        return false;
    }

    public void loginPassed() {
        System.out.println("Login berhasil!");
    }

    public boolean changePassword(String userId, User user, String newPassword) {
        String validateQuery = "SELECT * FROM Users WHERE userId = ? AND password = ?";
        String updateQuery = "UPDATE Users SET password = ? WHERE userId = ?";

        try (Connection conn = connection.getConnection(); PreparedStatement validateStmt = conn.prepareStatement(validateQuery)) {

            validateStmt.setString(1, userId);
            validateStmt.setString(2, user.getPassword());

            try (ResultSet rs = validateStmt.executeQuery()) {
                if (rs.next()) {
                    // password lama valid, ubah dengan yang baru
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                        updateStmt.setString(1, newPassword);
                        updateStmt.setString(2, userId);
                        updateStmt.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error during password change.");
            ex.printStackTrace();
        }

        return false;
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error during user addition.");
            ex.printStackTrace();
        }

        return false;
    }

    public List<User> listUsers() {
        String query = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();

        try (Connection conn = connection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    users.add(new User(userId, username, password));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error during user listing.");
            ex.printStackTrace();
        }

        return users;
    }

}
