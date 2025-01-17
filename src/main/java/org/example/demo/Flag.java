package org.example.demo;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Flag extends Application {
    @Override
    public void start(Stage stage) {
        // Создаем метку для выбора первого цвета
        Label color1 = new Label("Выберете первый цвет:");
        // Создаем радиокнопки для выбора первого цвета
        RadioButton white = new RadioButton("Белый");
        RadioButton blue = new RadioButton("Синий");
        RadioButton red = new RadioButton("Красный");
        RadioButton pink = new RadioButton("Розовый");
        RadioButton purple = new RadioButton("Фиолетовый");

        // Создаем группу переключателей для первого цвета
        ToggleGroup group1 = new ToggleGroup();
        // Устанавливаем радиокнопки в группу
        white.setToggleGroup(group1);
        blue.setToggleGroup(group1);
        red.setToggleGroup(group1);
        pink.setToggleGroup(group1);
        purple.setToggleGroup(group1);

        // Создаем метку для выбора второго цвета
        Label color2 = new Label("Выберете второй цвет:");
        // Создаем радиокнопки для выбора второго цвета
        RadioButton white2 = new RadioButton("Белый");
        RadioButton blue2 = new RadioButton("Синий");
        RadioButton red2 = new RadioButton("Красный");
        RadioButton pink2 = new RadioButton("Розовый");
        RadioButton purple2 = new RadioButton("Фиолетовый");
        // Создаем группу переключателей для второго цвета
        ToggleGroup group2 = new ToggleGroup();
        // Устанавливаем радиокнопки в группу
        white2.setToggleGroup(group2);
        blue2.setToggleGroup(group2);
        red2.setToggleGroup(group2);
        pink2.setToggleGroup(group2);
        purple2.setToggleGroup(group2);

        // Создаем метку для выбора третьего цвета
        Label color3 = new Label("Выберете третий цвет:");
        // Создаем радиокнопки для выбора третьего цвета
        RadioButton white3 = new RadioButton("Белый");
        RadioButton blue3 = new RadioButton("Синий");
        RadioButton red3 = new RadioButton("Красный");
        RadioButton pink3 = new RadioButton("Розовый");
        RadioButton purple3 = new RadioButton("Фиолетовый");
        // Создаем группу переключателей для третьего цвета
        ToggleGroup group3 = new ToggleGroup();
        // Устанавливаем радиокнопки в группу
        white3.setToggleGroup(group3);
        blue3.setToggleGroup(group3);
        red3.setToggleGroup(group3);
        pink3.setToggleGroup(group3);
        purple3.setToggleGroup(group3);

        // Создаем метку для отображения выбранных цветов
        Label resultLabel = new Label();
        // Создаем кнопку для нарисования флага
        Button drawButton = new Button("Нарисовать");
        // Обработчик события нажатия на кнопку
        drawButton.setOnAction(e -> {
            // Получаем текст выбранного цвета из каждой группы
            String resColor1 = ((RadioButton) group1.getSelectedToggle()).getText();
            String resColor2 = ((RadioButton) group2.getSelectedToggle()).getText();
            String resColor3 = ((RadioButton) group3.getSelectedToggle()).getText();
            // Обновляем метку с выбранными цветами
            resultLabel.setText("Выбранные цвета: " + resColor1 + ", " + resColor2 + ", " + resColor3);
        });

        // Создаем основной контейнер для размещения элементов
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10);
        // Добавляем все элементы в контейнер
        root.getChildren().addAll(color1, white, blue, red, pink, purple,
                color2, white2, blue2, red2, pink2, purple2,
                color3, white3, blue3, red3, pink3, purple3,
                drawButton, resultLabel);

        // Создаем сцену с заданным размером
        Scene scene = new Scene(root, 700, 180);

        // Устанавливаем сцену в окно
        stage.setScene(scene);
        // Устанавливаем заголовок окна
        stage.setTitle("Текстовый флаг");
        // Запрещаем изменение размера окна
        stage.setResizable(false);
        // Показываем окно
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }


}