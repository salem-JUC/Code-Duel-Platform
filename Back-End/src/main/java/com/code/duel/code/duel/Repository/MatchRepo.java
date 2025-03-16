package com.example.matchmaking.repository;

import com.example.matchmaking.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class MatchRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MatchRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to map a row in the ResultSet to a Match object
    private final RowMapper<Match> matchRowMapper = new RowMapper<Match>() {
        @Override
        public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
            Match match = new Match();
            match.setMatchId(rs.getInt("match_id"));
            match.setPlayer1Id(rs.getInt("player1_id"));
            match.setPlayer2Id(rs.getInt("player2_id"));
            match.setChallengeId(rs.getInt("challenge_id"));
            match.setWinnerId(rs.getInt("winner_id"));
            match.setStatus(rs.getString("status"));
            match.setStartedAt(rs.getTimestamp("started_at").toLocalDateTime());
            match.setEndedAt(rs.getTimestamp("ended_at") != null ? rs.getTimestamp("ended_at").toLocalDateTime() : null);
            return match;
        }
    };

    // Create a new match
    public void createMatch(Match match) {
        String sql = "INSERT INTO matches (player1_id, player2_id, challenge_id, winner_id, status, started_at, ended_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, match.getPlayer1Id(), match.getPlayer2Id(), match.getChallengeId(),
                match.getWinnerId(), match.getStatus(), match.getStartedAt(), match.getEndedAt());
    }

    // Update an existing match
    public void updateMatch(Match match) {
        String sql = "UPDATE matches SET player1_id = ?, player2_id = ?, challenge_id = ?, winner_id = ?, " +
                "status = ?, started_at = ?, ended_at = ? WHERE match_id = ?";
        jdbcTemplate.update(sql, match.getPlayer1Id(), match.getPlayer2Id(), match.getChallengeId(),
                match.getWinnerId(), match.getStatus(), match.getStartedAt(), match.getEndedAt(), match.getMatchId());
    }

    // Find a match by ID
    public Optional<Match> findMatchById(int matchId) {
        String sql = "SELECT * FROM matches WHERE match_id = ?";
        return jdbcTemplate.query(sql, matchRowMapper, matchId).stream().findFirst();
    }

    // Find all matches
    public List<Match> findAllMatches() {
        String sql = "SELECT * FROM matches";
        return jdbcTemplate.query(sql, matchRowMapper);
    }

    // Find all matches played by a specific player
    public List<Match> findMatchesByPlayerId(int playerId) {
        String sql = "SELECT * FROM matches WHERE player1_id = ? OR player2_id = ?";
        return jdbcTemplate.query(sql, matchRowMapper, playerId, playerId);
    }

    // Delete a match by ID
    public void deleteMatch(int matchId) {
        String sql = "DELETE FROM matches WHERE match_id = ?";
        jdbcTemplate.update(sql, matchId);
    }
}