package com.minhajcse.repository;

import com.minhajcse.model.Author;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryImp implements AuthorRepository {
    private final JdbcTemplate jdbcTemplate;
    public AuthorRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Author> userRowMapper = new RowMapper<>() {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Author author = new Author();
            author.setAuthorId(rs.getLong("author_id"));
            author.setBio(rs.getString("bio"));
            author.setInstitution(rs.getString("institution"));
            return author;
        }
    };

    @Override
    public Optional<Author> findById(Long id) {
        String sql = "SELECT * FROM author WHERE author_id = ?";
        try {
            Author author = jdbcTemplate.queryForObject(sql, userRowMapper, id);
            return Optional.of(author);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> findAll() {
        String sql = "SELECT * FROM author";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Long save(Author author) {
        String sql = "INSERT INTO author (bio, institution) VALUES (?, ?) RETURNING author_id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, author.getBio(), author.getInstitution());
        return id;
    }

    @Override
    public void update(Author author) {
        String sql = "UPDATE author SET bio = ?, institution = ? WHERE author_id = ?";
        jdbcTemplate.update(sql, author.getBio(), author.getInstitution(), author.getAuthorId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM author WHERE author_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM author WHERE author_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
        return (count!=null && count > 0);
    }
}
