package com.code.duel.code.duel.Repository;


import com.code.duel.code.duel.Model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MatchRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new match
    public void save(Match match) {
        String sql = "INSERT INTO \"match\" (matchID, status) VALUES (?, ?)";
        jdbcTemplate.update(sql, match.getMatchID(), match.getStatus());
    }

    // Find a match by ID
    public Match findById(Long matchID) {
        String sql = "SELECT * FROM \"match\" WHERE matchID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{matchID}, (rs, rowNum) ->
                new Match(
                        rs.getLong("matchID"),
                        rs.getString("status")
                ));
    }

    // Find all matches
    public List<Match> findAll() {
        String sql = "SELECT * FROM \"match\"";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Match(
                        rs.getLong("matchID"),
                        rs.getString("status")
                ));
    }

    // Update a match
    public void update(Match match) {
        String sql = "UPDATE \"match\" SET status = ? WHERE matchID = ?";
        jdbcTemplate.update(sql, match.getStatus(), match.getMatchID());
    }

    // Delete a match by ID
    public void deleteById(Long matchID) {
        String sql = "DELETE FROM \"match\" WHERE matchID = ?";
        jdbcTemplate.update(sql, matchID);
    }
}