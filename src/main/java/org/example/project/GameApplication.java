package org.example.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane; // помогает расположить элементы в нужных местах на окне(то же самое что pane, но с расширенным функционалом)
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ComboBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class GameApplication extends Application {
    private Button buttonstart; //кнопка старта
    private Label time; //отображение времени с момента нажатия кнопки старта
    private Label score; //отображение счета с момента нажатия кнопки старта
    private BorderPane gamePane;
    // счет будет считаться так:
    // если нажали на шарик, +1 к счету
    // если промахнулись, -1 к счету, пока что счёт не работает :)
    private ComboBox<String> modeComboBox;
    private Timeline gameTimer;
    private int time1;
    private int score1;
    private Circle ball;
    private int ballSize = 150;

    @Override
    public void start(Stage Stage1) {
        Stage1.setTitle("Aim lab");
        buttonstart = new Button("Начало");
        time = new Label("Время: 0");
        score = new Label("Счет: 0");
        FlowPane top = new FlowPane();
        gamePane = new BorderPane(); //
        modeComboBox = new ComboBox<>();
        modeComboBox.getItems().addAll("Простой", "Средний", "Сложный");
        modeComboBox.setValue("Простой"); // по дефолту какой режим
        buttonstart.setOnAction(e -> startgame());
        modeComboBox.setOnAction(e -> setMode(modeComboBox.getSelectionModel().getSelectedIndex()));
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

        timerNachalo();
    }
    private void setMode(int mode) {
        switch (mode) {
            case 0:
                ballSize = 150;
                break;
            case 1:
                ballSize = 50;
                break;
            case 2:
                ballSize = 30;
                break;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    private void timerNachalo() {
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e ->
        {
            time1++; // с помощью duration.seconds устанавливаем, спустя сколько времени +1 к времени
            time.setText("Время: " + time1);
            moveBall(); // перемещаем шар каждую секунду
        }
        ));
        gameTimer.setCycleCount(Timeline.INDEFINITE); // таймер работает пока мы не закроем программу
    }

    private void startgame() {
        score1 = 0;
        time1 = 0;
        time.setText("Время: 0");
        score.setText("Счет: 0");
        gameTimer.play();
    }

    private void moveBall() {
        if (ball != null) {
            gamePane.getChildren().remove(ball);
        }

        int x = new Random().nextInt((int) (gamePane.getWidth() - ballSize));
        int y = new Random().nextInt((int) (gamePane.getHeight() - ballSize));

        ball = new Circle(ballSize / 2);
        ball.setFill(Color.RED);
        ball.setLayoutX(x + ballSize / 2);
        ball.setLayoutY(y + ballSize / 2);

        ball.setOnMouseClicked(e -> {
            score1++;
            score.setText("Счет: " + score1);
            moveBall(); // перемещаем шар при клике на него
        });

        gamePane.setOnMouseClicked(e -> {
            if (e.getTarget() != ball) {
                score1--;
                score.setText("Счет: " + score1);
            }
        });


        gamePane.getChildren().add(ball);
    }
}

