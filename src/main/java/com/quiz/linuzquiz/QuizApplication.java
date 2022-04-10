package com.quiz.linuzquiz;

import com.quiz.linuzquiz.controllers.QuizController;
import com.quiz.linuzquiz.controllers.Selector;
import com.quiz.linuzquiz.model.Choice;
import com.quiz.linuzquiz.model.Slide;
import com.quiz.linuzquiz.service.FourQScene;
import com.quiz.linuzquiz.service.ReadJSON;
import javafx.application.Application;
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
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class QuizApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

         Object style =  Objects.requireNonNull(getClass().getResource("css/Quiz.css"))
                .toExternalForm();
       // reads json in src/main/resources/com/quiz/linuzquiz/batteries/battery.json and forms the Slides
       List<Slide> slides = (new ReadJSON()).reading();
       // randomly orders the slides
        slides.sort(Slide::compareTo);
        // randomly orders the Choices
        slides.forEach( slide -> slide.getChoices().getChoices().sort(Choice::compareTo));

       Selector selector = new Selector(slides);

        FXMLLoader fxmlLoader = new FXMLLoader(QuizApplication.class.getResource("view/quiz-view.fxml"));
        // remove default minimize, maximize and close frame
        stage.initStyle(StageStyle.UNDECORATED);

        fxmlLoader.setController(new QuizController(selector, style));

        Scene scene = new Scene(fxmlLoader.load());
        // inject css styles on scene
        scene.getStylesheets()
                .add(Objects.requireNonNull(getClass().getResource("css/Quiz.css"))
                        .toExternalForm());
        // get first slide and set its values on initial scene.
        Slide slide = slides.get(0);

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

        Text text = (Text) scene.lookup("#question");
        text.setText(slide.getTitle());
        List<Choice> choicesList = slide.getChoices().getChoices();

        // this section creates the button for each Choice
        for (int i = 0; i < choicesList.size(); i++) {
            Button btn = (Button) scene.lookup("#btn" + (i + 1));
            Choice currentChoice = choicesList.get(i);
            btn.setText(currentChoice.getResponse());
        }

        // btn to get results
        Button result = (Button) scene.lookup("#result");
        result.setDisable(true);
        // mount and display scene
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

/*
 ├──java
     ├── controllers
        ├──Screen1controller.java
        ├──Screen2controller.java
     ├── service
        ├──Service1.java
     ├── dao
        ├── SaveProducts.java
  ├──resources
     ├──view
        ├──screen1.fxml
        ├──screen2.fxml
     ├──css
        ├──style.css
     ├──images
        ├──img1.jpg
        ├──img2.jpg
 */