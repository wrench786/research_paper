package com.minhajcse.repository;

import com.minhajcse.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    public UserRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = new RowMapper<>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setFullName(rs.getString("full_name"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAge(rs.getInt("age"));
            user.setAuthorId(rs.getLong("author_id"));
            return user;
        }
    };

    @Override
    public Optional<User> findById(Long id) {
        String sql = "select * from user_table where user_id = ?";
        try {
            User user = jdbcTemplate.queryForObject(sql, userRowMapper, id);
            return Optional.of(user);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user_table";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Long save(User user) {
        String sql = "INSERT INTO user_table (user_name, full_name, email, password, age, author_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING user_id";
        Long generatedId = jdbcTemplate.queryForObject(sql, Long.class, user.getUserName(), user.getFullName(), user.getEmail(), user.getPassword(), user.getAge(), user.getAuthorId());
        return generatedId;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user_table SET user_name = ?, full_name = ?, email = ?, password = ?, age = ?, author_id = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUserName(), user.getFullName(), user.getEmail(), user.getPassword(), user.getAge(), user.getAuthorId(), user.getUserId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM user_table WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "select count(*) from user_table where user_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
        return (count!=null && count > 0);
    }
}
