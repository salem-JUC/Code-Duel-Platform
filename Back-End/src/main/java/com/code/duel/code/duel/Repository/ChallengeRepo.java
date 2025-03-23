package com.code.duel.code.duel.Repository;

import com.code.duel.code.duel.Model.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChallengeRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new challenge
    public void save(Challenge challenge) {
        String sql = "INSERT INTO Challenge (ChallengeID, Title, Description, Difficulty, Sample) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, challenge.getChallengeID(), challenge.getTitle(), challenge.getDescription(), challenge.getDifficulty(), challenge.getSample());
    }

    // Find a challenge by ID
    public Challenge findById(Long challengeID) {
        String sql = "SELECT * FROM Challenge WHERE ChallengeID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{challengeID}, (rs, rowNum) ->
                new Challenge(
                        rs.getLong("ChallengeID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Difficulty"),
                        rs.getString("Sample")
                ));
    }

    // Find all challenges
    public List<Challenge> findAll() {
        String sql = "SELECT * FROM Challenge";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Challenge(
                        rs.getLong("ChallengeID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Difficulty"),
                        rs.getString("Sample")
                ));
    }

    // Update a challenge
    public void update(Challenge challenge) {
        String sql = "UPDATE Challenge SET Title = ?, Description = ?, Difficulty = ?, Sample = ? WHERE ChallengeID = ?";
        jdbcTemplate.update(sql, challenge.getTitle(), challenge.getDescription(), challenge.getDifficulty(), challenge.getSample(), challenge.getChallengeID());
    }

    // Delete a challenge by ID
    public void deleteById(Long challengeID) {
        String sql = "DELETE FROM Challenge WHERE ChallengeID = ?";
        jdbcTemplate.update(sql, challengeID);
    }

    public Optional<Challenge> findRandom(){
        String sql ="SELECT * FROM Challenge\n" +
                "WHERE ChallengeID >= (SELECT FLOOR(RAND() * (SELECT MAX(ChallengeID) FROM Challenge))\n" +
                "LIMIT 1;";
        return Optional.of(jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new Challenge(
                        rs.getLong("ChallengeID"),
                        rs.getString("Title"),
                        rs.getString("Description"),
                        rs.getString("Difficulty"),
                        rs.getString("Sample")
                )));
    }
}
