package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;

public class CheckBox extends Application { // Создаем класс CheckBox, который наследует Application
    @Override
    public void start(Stage stage) throws IOException { // Переопределяем метод start для настройки окна
        // Создаем метки для отображения символов
        Label w1 = new Label("&"); // Метка для символа "&"
        Label w2 = new Label("%"); // Метка для символа "%"
        Label w3 = new Label("$"); // Метка для символа "$"

        // Создаем чекбоксы и устанавливаем их в состояние "выбран"
        javafx.scene.control.CheckBox ch1 = new javafx.scene.control.CheckBox(); // Чекбокс для первого символа
        ch1.setSelected(true); // Устанавливаем его в состояние "выбран"
        javafx.scene.control.CheckBox ch2 = new javafx.scene.control.CheckBox(); // Чекбокс для второго символа
        ch2.setSelected(true); // Устанавливаем его в состояние "выбран"
        javafx.scene.control.CheckBox ch3 = new javafx.scene.control.CheckBox(); // Чекбокс для третьего символа
        ch3.setSelected(true); // Устанавливаем его в состояние "выбран"

        // Настраиваем заголовок окна и его размеры
        stage.setTitle("CheckBoxes"); // Устанавливаем заголовок окна
        stage.setWidth(300); // Устанавливаем ширину окна
        stage.setHeight(70); // Устанавливаем высоту окна
        stage.show(); // Показываем окно на экране

        // Создаем горизонтальный контейнер (HBox) с отступом 10 пикселей между элементами
        HBox hbox1 = new HBox(10); // 10 - отступ между элементами
        // Добавляем метки и чекбоксы в контейнер
        hbox1.getChildren().addAll(w1, ch1, w2, ch2, w3, ch3);

        // Устанавливаем обработчики событий для каждого чекбокса
        // Когда чекбокс изменяет состояние, соответствующая метка становится видимой или скрытой
        ch1.setOnAction(e -> w1.setVisible(ch1.isSelected())); // Обработчик для первого чекбокса
        ch2.setOnAction(e -> w2.setVisible(ch2.isSelected())); // Обработчик для второго чекбокса
        ch3.setOnAction(e -> w3.setVisible(ch3.isSelected())); // Обработчик для третьего чекбокса

        // Создаем сцену с ранее созданным HBox и устанавливаем ее на сцену
        Scene scene = new Scene(hbox1); // Создаем сцену на основе hbox1
        stage.setScene(scene); // Устанавливаем сцену в окно
    }

    public static void main(String[] args) {
        launch();
    }
}