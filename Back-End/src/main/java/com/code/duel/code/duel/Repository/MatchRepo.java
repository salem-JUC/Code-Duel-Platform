package com.code.duel.code.duel.Repository;


import com.code.duel.code.duel.Model.Difficulty;
import com.code.duel.code.duel.Model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class MatchRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new match
    public void save(Match match) {
        String sql = "INSERT INTO \"match\" (matchID, current_challenge_id, difficulty, programmingLanguage,status ) VALUES (?,?,? ,?, ?)";

        jdbcTemplate.update(sql,
                match.getMatchID(),
                match.getCurrentChallengeId(),
                match.getDifficulty(),
                match.getProgrammingLanguage(),
                match.getStatus());
    }

    // Find a match by ID
    public Match findById(Long matchID) {
        String sql = "SELECT * FROM \"match\" WHERE matchID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{matchID}, (rs, rowNum) ->
                new Match(
                        rs.getLong("matchID"),
                        rs.getLong("current_challenge_id"),
                        rs.getString("difficulty"),
                        rs.getString("programmingLanguage"),
                        rs.getString("status")
                ));

    }

    // Find all matches
    public List<Match> findAll() {
        String sql = "SELECT * FROM \"match\"";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Match(
                        rs.getLong("matchID"),
                        rs.getLong("current_challenge_id"),
                        rs.getString("difficulty"),
                        rs.getString("programmingLanguage"),
                        rs.getString("status")
                ));
    }

    // Update a match (including challenge ID)
    public void update(Match match) {
        String sql = "UPDATE \"match\" SET status = ?, current_challenge_id = ? WHERE matchID = ?";
        jdbcTemplate.update(sql,
                match.getStatus(),
                match.getCurrentChallengeId(),
                match.getMatchID());
    }


    // Update only the current challenge ID
    public void updateChallenge(Long matchId, Long challengeId) {
        String sql = "UPDATE \"match\" SET current_challenge_id = ? WHERE matchID = ?";
        jdbcTemplate.update(sql, challengeId, matchId);
    }

    // Delete a match by ID
    public void deleteById(Long matchID) {
        String sql = "DELETE FROM \"match\" WHERE matchID = ?";
        jdbcTemplate.update(sql, matchID);
    }

    // Find matches by status
    public List<Match> findByStatus(String status) {
        String sql = "SELECT * FROM \"match\" WHERE status = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Match(
                        rs.getLong("matchID"),
                        rs.getLong("current_challenge_id"),
                        rs.getString("difficulty"),
                        rs.getString("programmingLanguage"),
                        rs.getString("status")
                ));
    }

    // Find first pending match
    public Optional<Match> findFirstPending() {
        String sql = "SELECT * FROM \"match\" WHERE status = 'PENDING' ORDER BY matchID ASC LIMIT 1";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Match(
                        rs.getLong("matchID"),
                        rs.getLong("current_challenge_id"),
                        rs.getString("difficulty"),
                        rs.getString("programmingLanguage"),
                        rs.getString("status")
                )).stream().findFirst();
    }
}