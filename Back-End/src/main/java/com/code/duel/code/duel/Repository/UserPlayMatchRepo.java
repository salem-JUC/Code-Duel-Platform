package com.code.duel.code.duel.Repository;


import com.code.duel.code.duel.Model.UserPlayMatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserPlayMatchRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new user_play_match entry
    public void save(UserPlayMatch userPlayMatch) {
        String sql = "INSERT INTO user_play_match (userID, matchID, userScore, result) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, userPlayMatch.getUserID(), userPlayMatch.getMatchID(), userPlayMatch.getUserScore(), userPlayMatch.getResult());
    }

    // Find all entries for a specific user
    public List<UserPlayMatch> findByUserId(Long userID) {
        String sql = "SELECT * FROM user_play_match WHERE userID = ?";
        return jdbcTemplate.query(sql, new Object[]{userID}, (rs, rowNum) ->
                new UserPlayMatch(
                        rs.getLong("userID"),
                        rs.getLong("matchID"),
                        rs.getInt("userScore"),
                        rs.getString("result")
                ));
    }

    // Find all entries for a specific match
    public List<UserPlayMatch> findByMatchId(Long matchID) {
        String sql = "SELECT * FROM user_play_match WHERE matchID = ?";
        return jdbcTemplate.query(sql, new Object[]{matchID}, (rs, rowNum) ->
                new UserPlayMatch(
                        rs.getLong("userID"),
                        rs.getLong("matchID"),
                        rs.getInt("userScore"),
                        rs.getString("result")
                ));
    }

    // Update a user_play_match entry
    public void update(UserPlayMatch userPlayMatch) {
        String sql = "UPDATE user_play_match SET userScore = ?, result = ? WHERE userID = ? AND matchID = ?";
        jdbcTemplate.update(sql, userPlayMatch.getUserScore(), userPlayMatch.getResult(), userPlayMatch.getUserID(), userPlayMatch.getMatchID());
    }

    // Delete a user_play_match entry
    public void delete(Long userID, Long matchID) {
        String sql = "DELETE FROM user_play_match WHERE userID = ? AND matchID = ?";
        jdbcTemplate.update(sql, userID, matchID);
    }
}
