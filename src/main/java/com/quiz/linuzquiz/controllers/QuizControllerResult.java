package com.quiz.linuzquiz.controllers;

import com.quiz.linuzquiz.model.Choice;
import com.quiz.linuzquiz.model.Slide;
import com.quiz.linuzquiz.service.FourQScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class QuizControllerResult {

    private Selector selector;
    private Slide slide;
    Object style;

    public QuizControllerResult(Selector selector, Object style) {
        this.selector = selector;
        this.slide = selector.getSlide();
        this.style = style;
    }

    @FXML private Button btn1, btn2 , btn3, btn4;
    @FXML private Button btnNext, btnPrevious;
    @FXML private Text question;
    @FXML private Pane pane;

    // previous question
    @FXML private void previousScene(ActionEvent event) throws IOException {
        Slide slide = this.selector.previous();
        (new FourQScene()).makeFourQuestionSceneResult(event, this.selector, slide, style);
    }
    // next question
    @FXML private void nextScene(ActionEvent event) throws IOException {
        Slide slide = this.selector.next();
        (new FourQScene()).makeFourQuestionSceneResult(event, this.selector, slide, style);
    }
    // closes app
    @FXML private void closeApp(ActionEvent event){
        javafx.application.Platform.exit();
    }
}
