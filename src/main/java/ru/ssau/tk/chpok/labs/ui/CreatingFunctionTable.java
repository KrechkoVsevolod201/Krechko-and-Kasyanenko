package ru.ssau.tk.chpok.labs.ui;

import ru.ssau.tk.chpok.labs.functions.*;
import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.io.FunctionsIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CreatingFunctionTable extends JDialog {

    private final JLabel labelCount = new JLabel("Количество точек: ");
    private final JTextField textFieldCount = new JTextField(2);

    private final JLabel labelInterval = new JLabel("Интервал: ");
    private final JLabel labelBracket1 = new JLabel("От");
    private final JLabel labelBracket2 = new JLabel("До");
    private final JTextField textFieldTo = new JTextField();
    private final JTextField textFieldFrom = new JTextField();

    private final JButton buttonCreateFunction = new JButton("Создать функцию");
    public TabulatedFunction function;

    Map<String, MathFunction> functionMap = new HashMap<>();
    JComboBox<String> comboBoxFunctions = showComboBox();

    public CreatingFunctionTable(Consumer<? super TabulatedFunction> callback) {
        super();
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(100, 100, 640, 200);
        setBackground(Color.DARK_GRAY);
        buttonCreateFunction.setBackground(new Color(0x39A0C2) );
        textFieldCount.setBackground(new Color(0xB0DAEA) );
        textFieldFrom.setBackground(new Color(0xB0DAEA) );
        textFieldTo.setBackground(new Color(0xB0DAEA) );
        getContentPane().add(labelCount);
        getContentPane().add(textFieldCount);

        getContentPane().add(labelInterval);
        getContentPane().add(labelBracket1);
        getContentPane().add(textFieldFrom);
        getContentPane().add(labelBracket2);
        getContentPane().add(textFieldTo);

        getContentPane().add(buttonCreateFunction);
        getContentPane().add(comboBoxFunctions);

        compose();
        addButtonListeners();
        setVisible(true);

        callback.accept(function);
        dispose();
    }

    private void addButtonListeners() {
        buttonCreateFunction.addActionListener(
                e -> {
                    int count = Integer.parseInt(textFieldCount.getText());
                    double from = Double.parseDouble(textFieldFrom.getText());
                    double to = Double.parseDouble(textFieldTo.getText());
                    String str = comboBoxFunctions.getItemAt(comboBoxFunctions.getSelectedIndex());
                    if(count <= 1){
                        buttonCreateFunction.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Включение в интерфейс иконки
                                JOptionPane.showMessageDialog(CreatingFunctionTable.this,
                                        "Колличество точек должно быть больше 1",
                                        "ЭТЭНШН ПЛИЗ", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
                    }
                    if (str.equals("Константная функция")) {
                        String result = JOptionPane.showInputDialog("Введите значение константы");
                        double constant = Double.parseDouble(result);
                        function = new ArrayTabulatedFunctionFactory().create(new ConstantFunction(constant), from, to, count);
                    } else {
                        MathFunction mathFunction = functionMap.get(str);
                        function = new ArrayTabulatedFunctionFactory().create(mathFunction, from, to, count);
                    }
                    dispose();
                    System.out.println(function.toString());
                    try (BufferedWriter outArray = new BufferedWriter(new FileWriter("SavedFunction/function.txt"));) {
                        FunctionsIO.writeTabulatedFunction(outArray, function);

                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }
        );
    }

    private void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(labelCount)
                        .addComponent(textFieldCount))
                .addComponent(labelInterval)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(labelBracket1)
                        .addComponent(textFieldFrom)
                        .addComponent(labelBracket2)
                        .addComponent(textFieldTo))
                .addComponent(comboBoxFunctions)
                .addComponent(buttonCreateFunction)
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelCount)
                        .addComponent(textFieldCount))
                .addComponent(labelInterval)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(labelBracket1)
                        .addComponent(textFieldFrom)
                        .addComponent(labelBracket2)
                        .addComponent(textFieldTo))
                .addComponent(comboBoxFunctions)
                .addComponent(buttonCreateFunction));
    }

    private JComboBox<String> showComboBox() {
        functionMap.put("Единичная функция", new UnitFunction());
        functionMap.put("Квадратичная функция", new SqrFunction());
        functionMap.put("Константная функция", new ConstantFunction(15));
        functionMap.put("Нулевая функция", new ZeroFunction());
        functionMap.put("Синусоидальная функция", new SinFunction());
        functionMap.put("Тождественная функция", new IdentityFunction());



        DefaultComboBoxModel<String> functions = new DefaultComboBoxModel<>();

        functions.addElement("Единичная функция");
        functions.addElement("Квадратичная функция");
        functions.addElement("Константная функция");
        functions.addElement("Нулевая функция");
        functions.addElement("Синусоидальная функция");
        functions.addElement("Тождественная функция");

        return new JComboBox<>(functions);
    }
}