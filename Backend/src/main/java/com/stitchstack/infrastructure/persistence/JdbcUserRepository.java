package com.stitchstack.infrastructure.persistence;

import com.stitchstack.domain.model.User;
import com.stitchstack.domain.ports.UserRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final DataSource dataSource;

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = """
                SELECT 1
                FROM users
                WHERE username = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to check username", e);
        }
    }

    @Override
    public User save(User user) {
        String sql = """
                INSERT INTO users (id, username, password_hash, email)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows != 1) {
                throw new RuntimeException("Failed to save user");
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }
}