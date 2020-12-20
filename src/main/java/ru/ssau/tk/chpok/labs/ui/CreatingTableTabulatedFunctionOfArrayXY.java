package ru.ssau.tk.chpok.labs.ui;

import ru.ssau.tk.chpok.labs.functions.TabulatedFunction;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreatingTableTabulatedFunctionOfArrayXY  extends JDialog {
    private final List<String> strings = new ArrayList<String>();
    private final AbstractTableModel tableModel = new AbstractTableXY(strings);
    private final JTable tableXY = new JTable(tableModel);
    private final JButton buttonCreateFunction = new JButton("Создать");
    private final JButton buttonCreateXY = new JButton("Создать point");
    private final JLabel textField = new JLabel("Введите количество точек: ");
    private final JTextField textFieldCount = new JTextField();

    public CreatingTableTabulatedFunctionOfArrayXY() {
        super();
        //размеры окна, и Layout
        getContentPane().setLayout(new GridLayout());
        setPreferredSize(new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT));
        setMaximumSize(new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT));
        setMinimumSize(new Dimension(MainWindow.WIDTH, MainWindow.HEIGHT));

        //чтобы пользователь мог выбрать только 1 строку
        tableXY.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        compose();

        //окно по середине выйдет
        setLocationRelativeTo(null);
        // парсер ввода числа точек
        buttonCreateXY.addActionListener(args -> {
            try {
                int temp = -1;
                temp = Integer.parseInt(textFieldCount.getText());
                if (temp < 0) {
                    throw new ExceptionPanel("Введите положительное число!");
                }
                addTableLine(temp);
            } catch (NumberFormatException exception) {
                new AppException(new ExceptionPanel(exception));
            } catch (NullPointerException exception) {
                new AppException(new ExceptionPanel(exception));
            } catch (ExceptionPanel exceptionPanel) {
                new AppException(exceptionPanel);
            }
        });
        //закрывается окно с таблицей
        buttonCreateFunction.addActionListener(args -> {
            getTabulatedFunctionFactory();
            setVisible(false);
        });
        setVisible(true);
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(tableXY);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(textField)
                                .addComponent(textFieldCount)))
                .addComponent(buttonCreateXY)
                .addComponent(buttonCreateFunction)
                .addComponent(tableScrollPane)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(textField)
                        .addComponent(textFieldCount))
                .addComponent(buttonCreateXY)
                .addComponent(tableScrollPane)
                .addComponent(buttonCreateFunction)
        );
    }

    private void addTableLine(int count) {
        strings.clear();
        for (int i = 0; i < count; i++) {
            strings.add(String.valueOf(0));
            tableModel.fireTableDataChanged();
        }
    }

    private TabulatedFunction getTabulatedFunctionFactory() {
        double[] yValues = new double[strings.size()];
        double[] xValues = new double[strings.size()];

        for (int i = 0; i < strings.size(); i++) {
            yValues[i] = Double.parseDouble(strings.get(i));
            xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
        }
        return new LinkedListTabulatedFunctionFactory().create(xValues, yValues);
    }
}