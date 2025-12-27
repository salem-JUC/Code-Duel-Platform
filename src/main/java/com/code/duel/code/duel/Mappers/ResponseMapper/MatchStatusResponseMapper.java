package com.code.duel.code.duel.Mappers.ResponseMapper;

import com.code.duel.code.duel.Model.Challenge;
import com.code.duel.code.duel.Model.Match;
import com.code.duel.code.duel.Model.UserPlayMatch;

public class MatchStatusResponseMapper {
    // this class is data structe for the match status , should contain userplayematch
    // for each user in match and the match status

//    select m.DIFFICULTY , m.PROGRAMMINGLANGUAGE , c.TITLE , c.DESCRIPTION , c.DIFFICULTY , c.SAMPLE , fupm.USERNAME as PlayerName , fupm.USERSCORE as PlayerScore , supm.USERNAME as SecondName , supm.USERSCORE  as SecondScore from "match" as m
//    join CHALLENGE as c ON c.CHALLENGEID = m.CURRENT_CHALLENGE_ID
//    join USER_PLAY_MATCH  as fupm ON fupm.MATCHID = m.MATCHID AND fupm.USERID = 3
//    join USER_PLAY_MATCH  as supm ON supm.MATCHID = m.MATCHID AND supm.USERID != 3
//    where m.MATCHID = 1000;

    private String difficulty;
    private String programmingLanguage;
    private String title;
    private String description;
    private String sample;
    private String playerName;
    private int playerScore;
    private Long playerId;
    private String secondName;
    private int secondScore;
    private Long secondId;

    public MatchStatusResponseMapper(String difficulty, String programmingLanguage, String title, String description, String sample, String playerName, int playerScore,Long playerId, String secondName, int secondScore , Long secondId) {
        this.difficulty = difficulty;
        this.programmingLanguage = programmingLanguage;
        this.title = title;
        this.description = description;
        this.sample = sample;
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.playerId = playerId;
        this.secondName = secondName;
        this.secondScore = secondScore;
        this.secondId = secondId;
    }

    public MatchStatusResponseMapper() {
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(int secondScore) {
        this.secondScore = secondScore;
    }

    public Long getSecondId() {
        return secondId;
    }

    public void setSecondId(Long secondId) {
        this.secondId = secondId;
    }

    @Override
    public String toString() {
        return "MatchStatusResponseMapper{" +
                "difficulty='" + difficulty + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sample='" + sample + '\'' +
                ", playerName='" + playerName + '\'' +
                ", playerScore=" + playerScore +
                ", playerId=" + playerId +
                ", secondName='" + secondName + '\'' +
                ", secondScore=" + secondScore +
                ", secondId=" + secondId +
                '}';
    }
}
