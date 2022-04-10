module com.quiz.linuzquiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.quiz.linuzquiz to javafx.fxml;
    exports com.quiz.linuzquiz;
    exports com.quiz.linuzquiz.controllers;
    opens com.quiz.linuzquiz.controllers to javafx.fxml;

    exports com.quiz.linuzquiz.service;
    exports com.quiz.linuzquiz.model;
}