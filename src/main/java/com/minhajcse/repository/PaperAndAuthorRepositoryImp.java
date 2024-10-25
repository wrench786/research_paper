package com.minhajcse.repository;

import com.minhajcse.model.Paper;
import com.minhajcse.model.PaperAndAuthor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaperAndAuthorRepositoryImp implements PaperAndAuthorRepository {

    private final JdbcTemplate jdbcTemplate;
    public PaperAndAuthorRepositoryImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<PaperAndAuthor> userRowMapper = new RowMapper<>() {
        @Override
        public PaperAndAuthor mapRow(ResultSet rs, int rowNum) throws SQLException {
            PaperAndAuthor paperAndAuthor = new PaperAndAuthor();
            paperAndAuthor.setPaperAuthorId(rs.getLong("mapping_id"));
            paperAndAuthor.setPaperId(rs.getLong("paper_id"));
            paperAndAuthor.setAuthorId(rs.getLong("author_id"));
            return paperAndAuthor;
        }
    };

    @Override
    public List<PaperAndAuthor> findAllByPaperId(Long paperId) {
        String sql = "SELECT * FROM paper_and_author WHERE paper_id=?";
        return jdbcTemplate.query(sql, userRowMapper, paperId);
    }

    @Override
    public List<PaperAndAuthor> findAllByAuthorId(Long authorId) {
        String sql = "SELECT * FROM paper_and_author WHERE author_id=?";
        return jdbcTemplate.query(sql, userRowMapper, authorId);
    }

    @Override
    public PaperAndAuthor findByPaperIdAndAuthorId(Long paperId, Long authorId) {
        String sql = "SELECT * FROM paper_and_author WHERE author_id=? AND paper_id=?";
        return jdbcTemplate.queryForObject(sql, userRowMapper, paperId, authorId);
    }

    @Override
    public Long save(PaperAndAuthor paperAndAuthor) {
        String sql = "INSERT INTO paper_and_author (paper_id, author_id) VALUES (?,?) RETURNING mapping_id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, paperAndAuthor.getPaperId(), paperAndAuthor.getAuthorId());
        return id;
    }
}
