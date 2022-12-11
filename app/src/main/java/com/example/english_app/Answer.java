package com.example.english_app;

public class Answer {
    private int optionA, optionB, optionC, optionD, questionId, answerId;

    public Answer(int questionID, int optionA, int optionB, int optionC, int optionD, int answerID) {
        this.questionId = questionID;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answerId = answerID;
    }


    public int getOptionA() {
        return optionA;
    }

    public int getOptionB() {
        return optionB;
    }

    public int getOptionC() {
        return optionC;
    }

    public int getOptionD() {
        return optionD;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getAnswerId() {
        return answerId;
    }
}
