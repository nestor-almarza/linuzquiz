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

public class QuizController {

    private Selector selector;
    private Slide slide;
    Object style;
    private Boolean showResults = false;

    public QuizController(Selector selector, Object style) {
        this.selector = selector;
        this.slide = selector.getSlide();
        this.style = style;
        this.showResults =  this.selector.getBattery().
                stream()
                .flatMap(slide -> slide.getChoices().getChoices().stream()
                        .filter(Choice::getClicked)
                ).toList().size() == this.selector.getBattery().size();
    }

    @FXML private Button btn1, btn2 , btn3, btn4;
    @FXML private Button btnNext, btnPrevious;
    @FXML private Button result;
    @FXML private Text question;
    @FXML private Pane pane;

    @FXML private void answer1(ActionEvent event){
        this.slide.selectThisOption(event);
       if(this.selector.checkAllSlidesHaveAnswer()){
           this.showResults = true;
        this.slide.enableResultsButton(event) ;}
                   ;
  }
    @FXML private void answer2(ActionEvent event){
        this.slide.selectThisOption(event);
        if(this.selector.checkAllSlidesHaveAnswer()){
            this.showResults = true;
            this.slide.enableResultsButton(event) ;}
    }
    @FXML private void answer3(ActionEvent event){
        this.slide.selectThisOption(event);
        if(this.selector.checkAllSlidesHaveAnswer()){
            this.showResults = true;
            this.slide.enableResultsButton(event) ;}
    }
    @FXML private void answer4(ActionEvent event){
        this.slide.selectThisOption(event);
        if(this.selector.checkAllSlidesHaveAnswer()){
            this.showResults = true;
            this.slide.enableResultsButton(event) ;}
    }
    // previous question
    @FXML private void previousScene(ActionEvent event) throws IOException {
        Slide slide = this.selector.previous();
         (new FourQScene()).makeFourQuestionScene(event, this.selector, slide, style, this.showResults);
    }
    // next question
    @FXML private void nextScene(ActionEvent event) throws IOException {
        Slide slide = this.selector.next();
        (new FourQScene()).makeFourQuestionScene(event, this.selector, slide, style, this.showResults);
    }
// closes app
    @FXML private void closeApp(ActionEvent event){
        javafx.application.Platform.exit();
    }
// gets results
    @FXML private void getResults(ActionEvent event) throws IOException {
        (new FourQScene()).makeFourQuestionSceneResult(event, this.selector, slide, style);
    }


}
