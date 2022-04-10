package com.quiz.linuzquiz.model;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.List;

public class Slide {
    private String title;
    private Choices choices;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }

    public void selectThisOption(ActionEvent event){
      // resets all choices to false
        List<Choice> listOfChoices = this.getChoices().getChoices();
        listOfChoices.forEach( option -> option.setClicked(false));

        Button btn = (Button) event.getSource();
        Pane pane = (Pane) btn.getParent();
        // removes all blue css class
        pane.getChildren().forEach( son -> son.getStyleClass().removeAll("blue"));

        int whichChoice = (int)Integer.valueOf(btn.getId().split("")[3]) - 1;
        Choice choice = listOfChoices.get(whichChoice);
        choice.setClicked(true);
        btn.getStyleClass().addAll("blue");
    }

    public void enableResultsButton(ActionEvent event) {
        Node node = (Node) event.getSource();
        Scene thisScene = (Scene) node.getScene();
        Button result = (Button) thisScene.lookup("#result");
        result.setDisable(false);
    }

    public int compareTo(Slide slide) {
        return  (Math.random() < 0.5) ? 1 : -1 ;
    }
}
