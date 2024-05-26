package org.example.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane; // помогает расположить элементы в нужным местах на окне
import javafx.stage.Stage;

public class GameApplication extends Application {
    @Override
    public void start(Stage Stage1) {
        Stage1.setTitle("Game");

        Pane pane = new Pane(); // панель
        Scene scene = new Scene(pane, 800, 600);
        Stage1.setScene(scene);
        Stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}