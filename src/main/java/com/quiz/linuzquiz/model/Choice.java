package com.quiz.linuzquiz.model;

public class Choice {
    private Boolean result;
    private String response;
    private Boolean clicked = false;

    public Boolean getClicked() {
        return clicked;
    }

    public void setClicked(Boolean clicked) {
        this.clicked = clicked;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int compareTo(Choice choice) {
        return  (Math.random() < 0.5) ? 1 : -1 ;
    }

}
