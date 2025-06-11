package com.code.duel.code.duel.Mappers.ResponseMapper;

public class SubmissionResponse {
    private boolean accepted;
    private String message;
    public SubmissionResponse(boolean accepted, String message) {
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
