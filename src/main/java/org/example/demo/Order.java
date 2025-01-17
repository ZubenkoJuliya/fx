package org.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class Order extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Создаем сетку для размещения элементов
        GridPane gridPane = new GridPane();

        // Массив с названиями блюд и их ценами
        String[] menuItems = {"Паста", "Пицца", "Салат", "Чизкейк", "Суп", "Стейк", "Суши", "Роллы", "Бургер", "Картошка Фри", "Круассан", "Кекс"};
        double[] prices = {150, 200, 100, 80, 120, 300, 300, 250, 150, 80, 120, 50};

        // Списки для хранения чекбоксов и полей ввода количества
        List<CheckBox> checkBoxes = new ArrayList<>();
        List<TextField> quantityFields = new ArrayList<>();

        // Создаем чекбоксы и поля ввода для каждого блюда
        for (int i = 0; i < menuItems.length; i++) {
            // Создаем чекбокс для каждого блюда с указанием цены
            CheckBox checkBox = new CheckBox(menuItems[i] + " - " + prices[i] + " руб.");
            checkBoxes.add(checkBox); // Добавляем чекбокс в список
            gridPane.add(checkBox, 0, i); // Добавляем чекбокс в сетку

            // Создаем поле ввода для количества порций
            TextField quantityField = new TextField("0");
            quantityField.setPrefWidth(50); // Устанавливаем ширину поля
            quantityField.setDisable(true); // Делаем поле неактивным по умолчанию
            gridPane.add(quantityField, 1, i); // Добавляем поле ввода в сетку
            quantityFields.add(quantityField); // Добавляем поле ввода в список

            // Создаем текст для указания "порций"
            Text t = new Text(" порций");
            gridPane.add(t, 2, i); // Добавляем текст в сетку

            // Обработчик события для чекбокса
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    quantityField.setText("1"); // Устанавливаем количество в 1, если чекбокс выбран
                    quantityField.setDisable(false); // Активируем поле ввода
                } else {
                    quantityField.setText("0"); // Устанавливаем количество в 0, если чекбокс не выбран
                    quantityField.setDisable(true); // Делаем поле ввода неактивным
                }
            });
        }

        // Создаем кнопку для заказа
        Button toOrder = new Button("Заказать");
        gridPane.add(toOrder, 3, menuItems.length); // Добавляем кнопку в сетку
        // Обработчик события для кнопки заказа
        toOrder.setOnAction(event -> showOrderSummary(menuItems, prices, checkBoxes, quantityFields));

        // Создаем прокручиваемую панель для размещения сетки
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane); // Устанавливаем сетку как содержимое прокручиваемой панели
        scrollPane.setFitToWidth(true); // Устанавливаем подгонку по ширине

        // Создаем сцену с прокручиваемой панелью
        Scene scene = new Scene(scrollPane, 300, 300);

        // Устанавливаем сцену в окно и отображаем его
        stage.setScene(scene);
        stage.setTitle("Заказ"); // Устанавливаем заголовок окна
        stage.show(); // Показываем окно
    }

    // Метод для отображения сводки заказа
    private void showOrderSummary(String[] menuItems, double[] prices,
                                  List<CheckBox> checkBoxes, List<TextField> quantityFields) {
        StringBuilder summary = new StringBuilder(); // Строка для хранения сводки заказа
        double totalCost = 0; // Переменная для хранения общей стоимости

        // Проходим по всем блюдам
        for (int i = 0; i < menuItems.length; i++) {
            // Если чекбокс выбран
            if (checkBoxes.get(i).isSelected()) {
                int quantity = Integer.parseInt(quantityFields.get(i).getText()); // Получаем количество порций
                double itemCost = prices[i] * quantity; // Рассчитываем стоимость блюда
                totalCost += itemCost; // Добавляем стоимость к общей
                // Добавляем информацию о блюде в сводку
                summary.append(menuItems[i])
                        .append(" - ")
                        .append(quantity)
                        .append(" порций - ")
                        .append(itemCost)
                        .append(" руб.\n");
            }
        }

        // Добавляем общую стоимость в сводку
        summary.append("Общая стоимость: ").append(totalCost).append(" руб.");

        // Создаем новое окно для отображения сводки заказа
        Stage summaryStage = new Stage();
        VBox vbox = new VBox(); // Создаем вертикальную панель для размещения элементов
        TextArea textArea = new TextArea(summary.toString()); // Создаем текстовую область для отображения сводки
        textArea.setEditable(false); // Делаем текстовую область только для чтения
        vbox.getChildren().add(textArea); // Добавляем текстовую область в вертикальную панель

        // Создаем сцену для сводки заказа
        Scene summaryScene = new Scene(vbox, 300, 200);
        summaryStage.setScene(summaryScene); // Устанавливаем сцену в новое окно
        summaryStage.setTitle("Чек"); // Устанавливаем заголовок окна
        summaryStage.show(); // Показываем окно со сводкой
    }
}