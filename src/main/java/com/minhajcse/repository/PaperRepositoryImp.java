package com.minhajcse.repository;

import com.minhajcse.model.Author;
import com.minhajcse.model.Paper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PaperRepositoryImp implements PaperRepository {
    private final JdbcTemplate jdbcTemplate;
    public PaperRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Paper> userRowMapper = new RowMapper<>() {
        @Override
        public Paper mapRow(ResultSet rs, int rowNum) throws SQLException {
            Paper paper = new Paper();
            paper.setPaperId(rs.getLong("paper_id"));
            paper.setTitle(rs.getString("title"));
            paper.setAbstractOfPaper(rs.getString("abstract_of_paper"));
            paper.setPublicationDate(rs.getDate("publication_date"));
            paper.setStatus(rs.getString("status"));
            paper.setFileUrl(rs.getString("file_url"));
            return paper;
        }
    };

    @Override
    public Optional<Paper> findById(Long id) {
        String sql = "SELECT * FROM paper WHERE paper_id = ?";
        try {
            Paper paper = jdbcTemplate.queryForObject(sql, userRowMapper, id);
            return Optional.of(paper);
        }
        catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Paper> findAll() {
        String sql = "SELECT * FROM paper";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    @Override
    public Long save(Paper paper) {
        String sql = "INSERT INTO paper (title, abstract_of_paper, publication_date, status, file_url) VALUES (?, ?, ?, ?, ?) RETURNING author_id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, paper.getTitle(), paper.getAbstractOfPaper(), paper.getPublicationDate(), paper.getStatus(), paper.getFileUrl());
        return id;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM paper WHERE paper_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Paper paper) {
        String sql = "UPDATE paper SET title = ?, abstract_of_paper = ?, publication_date = ?, status = ?, file_url = ? WHERE author_id = ?";
        jdbcTemplate.update(sql, paper.getTitle(), paper.getAbstractOfPaper(), paper.getPublicationDate(), paper.getStatus(), paper.getFileUrl());
    }

    @Override
    public boolean existsById(Long id){
        String sql = "SELECT COUNT(*) FROM paper WHERE paper_id = ?";
        Long count = jdbcTemplate.queryForObject(sql, Long.class, id);
        return (count!=null && count > 0);
    }
}
