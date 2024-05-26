package org.example.project;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane; // помогает расположить элементы в нужным местах на окне(то же самое что pane, но с расширенным функционалом)
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.ComboBox;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
public class GameApplication extends Application {
    private Button buttonstart; //кнопка старта
    private Label time; //отображение времени с момента нажатия кнопки старта
    private Label score; //отображение счета с момента нажатия кнопки старта
    // счет будет считаться так:
// если нажали на шарик, +1 к счету
// если промахнулись, -1 к счету
    private BorderPane gamePane;
    private ComboBox<String> modeComboBox;
    private Timeline gameTimer;
    private int time1;
    private int score1;
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
        buttonstart.setOnAction(e -> startgame());
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

    public static void main(String[] args) {
        launch(args);
    }

    private void timerNachalo() {
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), e ->
        {
            time1++; // с помощью duration.seconds устанавливаем, спустя сколько времени +1 к времени
            time.setText("Время: " + time1);
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
}
