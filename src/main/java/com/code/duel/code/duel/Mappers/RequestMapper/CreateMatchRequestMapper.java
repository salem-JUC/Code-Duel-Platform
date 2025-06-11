package com.code.duel.code.duel.Mappers.RequestMapper;

public class CreateMatchRequestMapper {

    String difficulty;
    String programmingLanguage;

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
}
