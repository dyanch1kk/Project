package org.example.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane; // помогает расположить элементы в нужным местах на окне(то же самое что pane, но с расширенным функционалом)
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ComboBox;

public class GameApplication extends Application {
    private Button buttonstart; //кнопка старта
    private Label time; //отображение времени с момента нажатия кнопки старта
    private Label score; //отображение счета с момента нажатия кнопки старта
    // счет будет считаться так:
    // если нажали на шарик, +1 к счету
    // если промахнулись, -1 к счету
    private BorderPane gamePane;
    private ComboBox<String> modeComboBox;
    @Override
    public void start(Stage Stage1) {
        Stage1.setTitle("Aim lab");
        buttonstart = new Button("Начало");
        time = new Label("Время: 0");
        score = new Label("Счет: 0");
        FlowPane top = new FlowPane();
        gamePane = new BorderPane();
        modeComboBox = new ComboBox<>();
        modeComboBox.getItems().addAll("Простой", "Средний", "Сложный");
        modeComboBox.setValue("Простой"); // по дефолту какой режим
        top.setHgap(200); //горизонтальное расстояние между элементами
        top.getChildren().addAll(buttonstart, time, score);
        FlowPane bottomPanel = new FlowPane();
        bottomPanel.setHgap(10);
        bottomPanel.getChildren().addAll(new Label("Мод:"), modeComboBox);
        BorderPane pane = new BorderPane(); // панель
        pane.setTop(top); // отступ
        pane.setBottom(bottomPanel);
        pane.setCenter(gamePane);

        Scene scene = new Scene(pane, 800, 600);
        Stage1.setScene(scene);
        Stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
