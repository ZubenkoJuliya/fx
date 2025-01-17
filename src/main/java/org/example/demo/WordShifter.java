package org.example.demo;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;


public class WordShifter extends Application {
    private boolean isFirstToSecond = true; // Флаг для отслеживания направления (из первого текстового поля во второе)

    @Override
    public void start(Stage stage) throws IOException {
        // Создаем первое текстовое поле для ввода текста
        TextField textField1 = new TextField();
        textField1.setPrefColumnCount(10); // Устанавливаем количество колонок для ширины

        // Создаем второе текстовое поле, которое будет отображать текст из первого
        TextField textField2 = new TextField();
        textField2.setPrefColumnCount(10); // Устанавливаем количество колонок для ширины
        textField2.setEditable(false); // Делаем второе текстовое поле только для чтения

        // Создаем кнопку для обмена текстом между полями
        Button swapButton = new Button();
        updateButtonArrow(swapButton); // Устанавливаем начальный текст кнопки

        // Обработчик нажатия кнопки
        swapButton.setOnAction(event -> {
            // Если направление обмена из первого поля во второе
            if (isFirstToSecond) {
                textField2.setText(textField1.getText()); // Копируем текст из первого поля во второе
                textField1.clear(); // Очищаем первое поле
            } else {
                // Если направление обмена из второго поля в первое
                textField1.setText(textField2.getText()); // Копируем текст из второго поля в первое
                textField2.clear(); // Очищаем второе поле
            }
            isFirstToSecond = !isFirstToSecond; // Меняем направление обмена
            updateButtonArrow(swapButton); // Обновляем текст кнопки в зависимости от направления
        });

        // Настройки окна приложения
        stage.setTitle("Перекидыватель слов"); // Заголовок окна
        stage.setWidth(330); // Ширина окна
        stage.setHeight(70); // Высота окна
        stage.show(); // Показываем окно

        // Создаем панель для размещения элементов
        FlowPane root = new FlowPane(textField1, swapButton, textField2);
        Scene scene = new Scene(root); // Создаем сцену с корневым элементом
        stage.setScene(scene); // Устанавливаем сцену в окно
    }

    // Метод для обновления текста кнопки в зависимости от направления обмена
    private void updateButtonArrow(Button button) {
        if (isFirstToSecond) {
            button.setText("---->"); // Текст для направления из первого в второе
        } else {
            button.setText("<----"); // Текст для направления из второго в первое
        }
    }


    public static void main(String[] args) {
        launch();
    }
}
