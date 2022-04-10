package com.quiz.linuzquiz.service;

import com.quiz.linuzquiz.QuizApplication;
import com.quiz.linuzquiz.controllers.QuizController;
import com.quiz.linuzquiz.controllers.QuizControllerResult;
import com.quiz.linuzquiz.controllers.Selector;
import com.quiz.linuzquiz.model.Choice;
import com.quiz.linuzquiz.model.Slide;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventObject;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FourQScene {

    public void makeFourQuestionScene(
            ActionEvent event, Selector selector, Slide slide, Object style, Boolean showResults
    ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("view/quiz-view.fxml"));
        fxmlLoader.setController(new QuizController(selector, style));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add((String) style);

        Text text = (Text) scene.lookup("#question");
        text.setText(slide.getTitle());
        List<Choice> choicesList = slide.getChoices().getChoices();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // this section creates the button for each Choice
        for (int i = 0; i < choicesList.size(); i++) {
            Button btn = (Button) scene.lookup("#btn" + (i + 1));
            Choice currentChoice = choicesList.get(i);
            btn.setText(currentChoice.getResponse());
            if(currentChoice.getClicked()){
                btn.getStyleClass().addAll("blue");
            }
        }

        Button result = (Button) scene.lookup("#result");
            result.setDisable(!showResults);

        Pane pane = (Pane) scene.lookup("#pane");
        // allows to grab the stage
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double xOffset = event.getScreenX();
                double yOffset = event.getScreenY();
            }
        });
        // allows to drag the stage
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - 100);
                stage.setY(event.getScreenY() - 100 );
            }
        });

        stage.setScene(scene);
    }

    public void makeFourQuestionSceneResult(
            ActionEvent event, Selector selector, Slide slide, Object style
    ) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("view/quiz-result.fxml"));
        // new Controller for results
        fxmlLoader.setController(new QuizControllerResult(selector, style));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add((String) style);

        Text text = (Text) scene.lookup("#question");
        text.setText(slide.getTitle());

        Pane pane = (Pane) scene.lookup("#pane");

        List<Choice> choicesList = slide.getChoices().getChoices();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        // this section creates the button for each Choice
        for (int i = 0; i < choicesList.size(); i++) {
            Button btn = (Button) scene.lookup("#btn" + (i + 1));
            Choice currentChoice = choicesList.get(i);
            btn.setText(currentChoice.getResponse());
            if( currentChoice.getClicked() || currentChoice.getResult() ){
                if( currentChoice.getClicked() && !currentChoice.getResult() ){
                    btn.getStyleClass().addAll("red");
                }
                if( !currentChoice.getClicked() && currentChoice.getResult() ){
                    btn.getStyleClass().addAll("green");
                }
                if( currentChoice.getClicked() && currentChoice.getResult() ){
                    btn.getStyleClass().addAll("green");
                }
            }
        }

        // allows to grab the stage
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double xOffset = event.getScreenX();
                double yOffset = event.getScreenY();
            }
        });
        // allows to drag the stage
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - 100);
                stage.setY(event.getScreenY() - 100 );
            }
        });

        stage.setScene(scene);
    }
}
