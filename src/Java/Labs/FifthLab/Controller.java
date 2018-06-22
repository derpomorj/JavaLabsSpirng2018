package Java.Labs.FifthLab;

import Java.Labs.FifthLab.Functional.FourthLab.FourthLab;
import Java.Labs.FifthLab.Functional.ThirdLab.ThirdLab;
import Java.Labs.FirstLab.FirstLab;
import Java.Labs.SecondLab.SecondLab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ComboBox comboBox;

    @FXML
    private TextArea textArea;

    @FXML
    private HBox paneForArgs;

    @FXML
    private Label firstArgLabel;

    @FXML
    private Label secondArgLabel;

    @FXML
    private TextField firstArgField;

    private StringBuilder tempString;

    @FXML
    private TextField secondArgField;

    private List<String> comboBoxItems = new ArrayList<String>(4);

    @FXML
    void initialize() {
        comboBoxItems.add("First Lab");
        comboBoxItems.add("Second Lab");
        comboBoxItems.add("Thrid Lab");
        comboBoxItems.add("Fourth Lab");

        comboBox.getItems().addAll(comboBoxItems);
        PrintStream consoleStream = new PrintStream(new OutputStream() {
            public void write(int b) {
            }

            public synchronized void write(byte[] b, int off, int len) {
                if (b != null && tempString != null) {
                    tempString.append(new String(b, off, len));
                }
            }
        });
        System.setOut(consoleStream);
        System.setErr(consoleStream);

    }

    public void startButton(ActionEvent actionEvent) {
        Object value = comboBox.getValue();
        if (value != null) {
            tempString = new StringBuilder();
            textArea.clear();
            if (value.equals(comboBoxItems.get(0))) {
                FirstLab.main(new String[]{firstArgField.getText(), secondArgField.getText()});
            } else if (value.equals(comboBoxItems.get(1))) {
                SecondLab.main(new String[]{});

            } else if (value.equals(comboBoxItems.get(2))) {
                ThirdLab.main(new String[]{firstArgField.getText()});
            } else if (value.equals(comboBoxItems.get(3))) {
                FourthLab.main(new String[]{firstArgField.getText(), secondArgField.getText()});
            }
            textArea.setText(tempString.toString());
        }
    }

    public void itemChanged(ActionEvent actionEvent) {
        Object value = comboBox.getValue();
        if (value != null) {
            if (value.equals(comboBoxItems.get(0))) {
                firstArgLabel.setText("Money Value:");
                secondArgLabel.setText("Percents Value:");
                firstArgField.clear();
                secondArgField.clear();
                firstArgLabel.setVisible(true);
                secondArgLabel.setVisible(true);
                firstArgField.setVisible(true);
                secondArgField.setVisible(true);
                textArea.setText("Создать приложение с 2 параметрами, которое вычислит количество лет ,\n" +
                        "за которые сумма денег,положенная в банк, должна удвоится. В качестве параметров задаются\n" +
                        "любые  неотрицательные  числа. 1-ое число - количество денег, 2-ое число-годовой  процент.\n" +
                        "Учесть, что проценты начисляются раз в месяц и капитализируются.Сосчитать и вывести на дисплей\n" +
                        "результат подсчета.");
            } else if (value.equals(comboBoxItems.get(1))) {
                firstArgLabel.setVisible(false);
                secondArgLabel.setVisible(false);
                firstArgField.setVisible(false);
                secondArgField.setVisible(false);
                textArea.setText("1)Создать приложение с 3 классами-Артист , Циркач, Танцор и  интерфейсами Выступающий , \n" +
                        "   Гастролер.\n" +
                        "2)Класс Артист является родительским для классов Циркач и Танцор , Интерфейс Выступающий   \n" +
                        "   является родительским для интерфейса Гастролер.\n" +
                        "   Класс Циркач реализует интерфейс Гастролер. \n" +
                        "   Класс Танцор реализует интерфейс Выступающий .\n" +
                        "3)Интерфейс Выступающий  содержит метод \"Выступать на концерте\", \n" +
                        "   класс Артист содержит метод \"Работать для публики\", Интерфейс Гастролер содержит метод   \n" +
                        "   Гастролировать. Класс Циркач  \n" +
                        "   содержит метод \"Выступать на арене\".\n" +
                        "   Класс Танцор  содержит метод Танцевать.\n" +
                        "4)Все методы выводят строку с именем своего класса или интерфейса и именем метода.\n" +
                        "   Создать минимальное число объектов для выполнения всех указанных 5 методов и выполнить все \n" +
                        "   эти методы. Привести эти объекты к общему типу и опять выполнить все методы");
            } else if (value.equals(comboBoxItems.get(2))) {
                firstArgLabel.setText("Iteration Count:");
                firstArgField.clear();
                firstArgLabel.setVisible(true);
                firstArgField.setVisible(true);

                secondArgLabel.setVisible(false);
                secondArgField.setVisible(false);
                textArea.setText("3. Создать 2 потока, один из которых генерирует случайным образом 2  числа Фибоначчи  в разделенные между потоками переменные , а другой считывает эти числа , и выводит на печать сумму этих чисел.\n" +
                        "Задать с помощью параметра сколько раз выполняется цикл в программе.\n" +
                        "Выводить записываемые числа   для первого потока и сумму для второго потока. Выполнить задание   с использованием конструкции synchronized.\n" +
                        "Не использовать в этом задании флаги для синхронизации потоков, а только методы wait и notify.\n" +
                        "Также не использовать любые задержки для потоков после начала их работы в виде методов sleep, yield или wait c параметром.\n" +
                        "(Числа Фибоначчи — элементы числовой последовательности 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765,  … в которой каждое последующее число равно сумме двух предыдущих чисел. )");
            } else if (value.equals(comboBoxItems.get(3))) {
                firstArgLabel.setText("Workers Count:");
                secondArgLabel.setText("Details Count:");
                firstArgField.clear();
                secondArgField.clear();
                firstArgLabel.setVisible(true);
                secondArgLabel.setVisible(true);
                firstArgField.setVisible(true);
                secondArgField.setVisible(true);
                textArea.setText("Создать многопотоковое приложение с 2 параметрами. Количество потоков \"Рабочий\" задано параметром.\n" +
                        "    Первый рабочий вытачивает деталь и кладет ее на конвейер. Каждый следующий рабочий берет ее с конвейера,\n" +
                        "   модифицирует и снова отправляет на конвейер следующему работнику.\n" +
                        "        Количество деталей тоже задается параметром. Каждый рабочий не берет следующую деталь, пока не закончил с предыдущей.\n" +
                        "        Использовать ограничения из задания 3.\n" +
                        "        Выводить на дисплей название детали и имя рабочего.Вывод может быть например такой:\n" +
                        "        деталь1- рабочий1\n" +
                        "        деталь2 -рабочий1\n" +
                        "        деталь3 -рабочий1\n" +
                        "        деталь1- рабочий2\n" +
                        "        деталь1 -рабочий3\n" +
                        "        деталь2 -рабочий2\n" +
                        "        деталь1 -рабочий4\n" +
                        "        деталь2 -рабочий3\n" +
                        "        ................");
            }
        }
    }
}