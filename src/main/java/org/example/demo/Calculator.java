package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Calculator extends Application { // Класс калькулятора, наследующий от Application
    private double currentResult = 0; // Переменная для хранения текущего результата вычислений
    private String operator = ""; // Переменная для хранения текущего оператора
    private boolean start = true; // Флаг для отслеживания, нужно ли очищать текстовое поле

    @Override
    public void start(Stage stage) { // Метод для настройки пользовательского интерфейса
        GridPane gridPane = new GridPane(); // Создаем сеточный макет для размещения элементов

        TextField tField = new TextField(); // Создаем текстовое поле для отображения результата
        tField.setPrefHeight(70); // Устанавливаем предпочтительную высоту текстового поля
        gridPane.add(tField, 0, 5, 4, 1); // Добавляем текстовое поле в сетку
        tField.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Устанавливаем максимальный размер текстового поля

        // Создание кнопок для чисел
        Button[] numberButtons = new Button[10]; // Массив для хранения кнопок чисел от 0 до 9
        for (int i = 0; i < 10; i++) { // Цикл для создания кнопок
            numberButtons[i] = new Button(String.valueOf(i)); // Создаем кнопку с цифрой
            numberButtons[i].setPrefSize(70, 70); // Устанавливаем размер кнопок
            final int num = i; // Фиксируем значение i для использования в лямбда-выражении
            numberButtons[i].setOnAction(e -> handleNumberButton(tField, num)); // Устанавливаем обработчик нажатия кнопки
        }

        // Создание кнопок операторов
        Button bSum = createOperatorButton("+", tField); // Кнопка сложения
        Button bMin = createOperatorButton("-", tField); // Кнопка вычитания
        Button bMult = createOperatorButton("*", tField); // Кнопка умножения
        Button bDiv = createOperatorButton("/", tField); // Кнопка деления

        // Кнопка равенства
        Button bRes = new Button("="); // Создаем кнопку равенства
        bRes.setPrefSize(70, 70); // Устанавливаем размер кнопки
        bRes.setStyle("-fx-background-color: #4F658C; -fx-text-fill: white;"); // Устанавливаем стиль кнопки
        bRes.setOnAction(e -> calculate(tField)); // Устанавливаем обработчик для вычисления результата

        // Кнопка сброса
        Button bClear = new Button("C"); // Создаем кнопку сброса
        bClear.setPrefSize(70, 70); // Устанавливаем размер кнопки
        bClear.setStyle("-fx-background-color: #FF6F61; -fx-text-fill: white;"); // Устанавливаем стиль кнопки
        bClear.setOnAction(e -> clear(tField)); // Устанавливаем обработчик для сброса

        // Расположение кнопок в сетке
        gridPane.add(numberButtons[1], 0, 2); // Добавляем кнопку 1
        gridPane.add(numberButtons[2], 1, 2); // Добавляем кнопку 2
        gridPane.add(numberButtons[3], 2, 2); // Добавляем кнопку 3
        gridPane.add(bSum, 3, 2); // Добавляем кнопку сложения

        gridPane.add(numberButtons[4], 0, 1); // Добавляем кнопку 4
        gridPane.add(numberButtons[5], 1, 1); // Добавляем кнопку 5
        gridPane.add(numberButtons[6], 2, 1); // Добавляем кнопку 6
        gridPane.add(bMin, 3, 1); // Добавляем кнопку вычитания

        gridPane.add(numberButtons[7], 0, 0); // Добавляем кнопку 7
        gridPane.add(numberButtons[8], 1, 0); // Добавляем кнопку 8
        gridPane.add(numberButtons[9], 2, 0); // Добавляем кнопку 9
        gridPane.add(bMult, 3, 0); // Добавляем кнопку умножения

        gridPane.add(numberButtons[0], 0, 3); // Добавляем кнопку 0
        gridPane.add(bDiv, 2, 3); // Добавляем кнопку деления
        gridPane.add(bRes, 3, 3); // Добавляем кнопку равенства
        gridPane.add(bClear, 0, 4); // Добавляем кнопку сброса

        Scene scene = new Scene(gridPane); // Создаем сцену на основе сетки
        stage.setScene(scene); // Устанавливаем сцену в окно
        stage.setTitle("Калькулятор"); // Устанавливаем заголовок окна
        stage.setWidth(300); // Устанавливаем ширину окна
        stage.setHeight(400); // Устанавливаем высоту окна
        stage.show(); // Показываем окно на экране
    }

    private void handleNumberButton(TextField tField, int num) { // Обработчик нажатия кнопки числа
        if (start) { // Если это первый ввод
            tField.clear(); // Очищаем текстовое поле
            start = false; // Устанавливаем флаг, что ввод начался
        }
        tField.appendText(String.valueOf(num)); // Добавляем нажатую цифру в текстовое поле
    }

    private Button createOperatorButton(String op, TextField tField) { // Метод для создания кнопки оператора
        Button button = new Button(op); // Создаем кнопку с указанным оператором
        button.setPrefSize(70, 70); // Устанавливаем размер кнопки
        button.setOnAction(e -> handleOperator(tField.getText(), op)); // Устанавливаем обработчик события
        return button; // Возвращаем созданную кнопку
    }

    private void handleOperator(String text, String op) { // Обработчик нажатия кнопки оператора
        if (!text.isEmpty()) { // Если текстовое поле не пустое
            currentResult = Double.parseDouble(text); // Преобразуем текст в число и сохраняем в текущий результат
            operator = op; // Устанавливаем текущий оператор
            start = true; // Сбрасываем флаг для следующего ввода
        }
    }

    private void calculate(TextField tField) { // Метод для выполнения вычисления
        if (operator.isEmpty() || tField.getText().isEmpty()) return; // Если оператор или текстовое поле пустое, выходим

        double secondNumber = Double.parseDouble(tField.getText()); // Преобразуем текст из поля во второе число

        // Выполняем вычисление в зависимости от выбранного оператора
        switch (operator) {
            case "+" -> currentResult += secondNumber; // Сложение
            case "-" -> currentResult -= secondNumber; // Вычитание
            case "*" -> currentResult *= secondNumber; // Умножение
            case "/" -> { // Деление
                if (secondNumber == 0) { // Проверка на деление на ноль
                    tField.setText("Ошибка: Деление на ноль!"); // Выводим сообщение об ошибке
                    return; // Выходим из метода
                }
                currentResult /= secondNumber; // Деление
            }
            default -> {} // По умолчанию ничего не делаем
        }
        tField.setText(String.valueOf(currentResult)); // Обновляем текстовое поле с результатом
    }

    private void clear(TextField tField) { // Метод для сброса калькулятора
        currentResult = 0; // Сбрасываем текущий результат
        operator = ""; // Сбрасываем оператор
        start = true; // Сбрасываем флаг для ввода
        tField.clear(); // Очищаем текстовое поле
    }
    public static void main(String[] args) {
        launch();
    }
}
