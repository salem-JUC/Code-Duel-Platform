package com.code.duel.code.duel.Repository;

import com.code.duel.code.duel.Model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubmissionRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save a new submission
    public void save(Submission submission) {
        String sql = "INSERT INTO Submission (submissionID, ChallengeID, submitterID, Result, Code, ProgrammingLanguage) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, submission.getSubmissionID(), submission.getChallengeID(), submission.getSubmitterID(), submission.getResult(), submission.getCode(), submission.getProgrammingLanguage());
    }

    // Find a submission by ID
    public Submission findById(Long submissionID) {
        String sql = "SELECT * FROM Submission WHERE submissionID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{submissionID}, (rs, rowNum) ->
                new Submission(
                        rs.getLong("submissionID"),
                        rs.getLong("ChallengeID"),
                        rs.getLong("submitterID"),
                        rs.getString("Result"),
                        rs.getString("Code"),
                        rs.getString("ProgrammingLanguage")
                ));
    }

    // Find all submissions for a challenge
    public List<Submission> findByChallengeId(Long challengeID) {
        String sql = "SELECT * FROM Submission WHERE ChallengeID = ?";
        return jdbcTemplate.query(sql, new Object[]{challengeID}, (rs, rowNum) ->
                new Submission(
                        rs.getLong("submissionID"),
                        rs.getLong("ChallengeID"),
                        rs.getLong("submitterID"),
                        rs.getString("Result"),
                        rs.getString("Code"),
                        rs.getString("ProgrammingLanguage")
                ));
    }

    public List<Submission> findBysubmitterId(Long submitterId) {
        String sql = "SELECT * FROM Submission WHERE SUBMITTERID  = ?";
        return jdbcTemplate.query(sql, new Object[]{submitterId}, (rs, rowNum) ->
                new Submission(
                        rs.getLong("submissionID"),
                        rs.getLong("ChallengeID"),
                        rs.getLong("submitterID"),
                        rs.getString("Result"),
                        rs.getString("Code"),
                        rs.getString("ProgrammingLanguage")
                ));
    }

    // Update a submission
    public void update(Submission submission) {
        String sql = "UPDATE Submission SET ChallengeID = ?, submitterID = ?, Result = ?, Code = ?, ProgrammingLanguage = ? WHERE submissionID = ?";
        jdbcTemplate.update(sql, submission.getChallengeID(), submission.getSubmitterID(), submission.getResult(), submission.getCode(), submission.getProgrammingLanguage(), submission.getSubmissionID());
    }

    // Delete a submission by ID
    public void deleteById(Long submissionID) {
        String sql = "DELETE FROM Submission WHERE submissionID = ?";
        jdbcTemplate.update(sql, submissionID);
    }
}
