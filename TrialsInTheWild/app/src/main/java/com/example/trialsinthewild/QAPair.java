package com.example.trialsinthewild;

public class QAPair {
    private int experiment_id;
    private String question;
    private String answer;

    public QAPair(String question, String answer, int experiment_id) {
        this.experiment_id=experiment_id;
        this.question=question;
        this.answer=answer;
    }

    public boolean hasAnswer() {
        return !answer.isEmpty();
    }

    public void addAnswer(String answer) {
        this.answer=answer;
    }

    public String getQuestion() {
        return question;
    }
}
