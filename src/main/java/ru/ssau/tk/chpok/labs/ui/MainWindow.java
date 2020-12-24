package ru.ssau.tk.chpok.labs.ui;

import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.ui.listeners.TestActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainWindow extends JFrame {
    public static final int HEIGHT = 340;
    public static final int WIDTH = 640;
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    // Текстовые поля
    public JTextField smallField, bigField;
    public String pointText; // создаём объект текущей даты
    public MainWindow() {
        super("Главное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setVisible(true);

        setContentPane(createContentPane()); // передаем как параметр в коструктор
        setVisible(true); //  форма будет видимой

        setJMenuBar(getCreatingWindowSettings());
        // Подключаем меню к интерфейсу приложения
        setLocationRelativeTo(null);
        setVisible(true);


    }


    //кнопки настройки - сменить тип фабрики - массивы - связный список
    private JMenuBar getCreatingWindowSettings() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu settings = new JMenu("Настройки");
        JMenu theme = new JMenu("Тема");
        JMenu changeFactory = new JMenu("Сменить тип фабрики");
        JMenuItem arraysItem = new JRadioButtonMenuItem("Массивы");
        JMenuItem linkedListItem = new JRadioButtonMenuItem("Связный список");
        JMenuItem whiteMode = new JRadioButtonMenuItem("Светлая тема");
        JMenuItem darkMode = new JRadioButtonMenuItem("Тёмная тема");
        JMenuItem pinkMode = new JRadioButtonMenuItem("Розовая тема");
        JMenuItem yellowMode = new JRadioButtonMenuItem("Желтая тема");
        JMenuItem greenMode = new JRadioButtonMenuItem("Зеленая тема");

        if (factory.getClass() == ArrayTabulatedFunctionFactory.class) {
            arraysItem.setSelected(true);
            linkedListItem.setSelected(false);
        } else {
            arraysItem.setSelected(false);
            linkedListItem.setSelected(true);
        }
        if (getBackground() == Color.WHITE) {
            whiteMode.setSelected(true);
            darkMode.setSelected(false);
            pinkMode.setSelected(false);
            yellowMode.setSelected(false);
        }
        arraysItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new ArrayTabulatedFunctionFactory();
                }
                linkedListItem.setSelected(false);
            }
        });
        linkedListItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    factory = new LinkedListTabulatedFunctionFactory();
                }
                arraysItem.setSelected(false);
            }
        });
        whiteMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getContentPane().setBackground(Color.WHITE);
                }
                darkMode.setSelected(false);
                pinkMode.setSelected(false);
                yellowMode.setSelected(false);
                greenMode.setSelected(false);
            }
        });
        darkMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getContentPane().setBackground(Color.BLACK);
                }
                whiteMode.setSelected(false);
                pinkMode.setSelected(false);
                yellowMode.setSelected(false);
                greenMode.setSelected(false);
            }
        });
        pinkMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getContentPane().setBackground(Color.PINK);
                }
                darkMode.setSelected(false);
                whiteMode.setSelected(false);
                yellowMode.setSelected(false);
                greenMode.setSelected(false);
            }
        });
        yellowMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getContentPane().setBackground(Color.YELLOW);
                }
                darkMode.setSelected(false);
                whiteMode.setSelected(false);
                pinkMode.setSelected(false);
                greenMode.setSelected(false);
            }
        });
        greenMode.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    getContentPane().setBackground(Color.GREEN);
                }
                darkMode.setSelected(false);
                whiteMode.setSelected(false);
                pinkMode.setSelected(false);
                yellowMode.setSelected(false);
            }
        });
        theme.add(whiteMode);
        theme.add(darkMode);
        theme.add(pinkMode);
        theme.add(yellowMode);
        theme.add(greenMode);
        changeFactory.add(arraysItem);
        changeFactory.add(linkedListItem);
        settings.add(changeFactory);
        settings.add(theme);
        jMenuBar.add(settings);
        return jMenuBar;
    }

    public JPanel createContentPane() {

        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel totalGUI = new JPanel(); // создаём "поверхность"
        totalGUI.setLayout(null);

        // Создадим ярлык (надпись) синего цвета
        JLabel blueLabel = new JLabel("Здравствуйте, нажмите кнопку Создать, " +
                "\n" + "чтобы создать новую таблицу");
        blueLabel.setLocation(1, -40); /* надпись синего цвета*/
        blueLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 16));
        blueLabel.setSize(640, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        blueLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(blueLabel); // добавляем текстовую метку на поверхность

        // Создаём кнопку---------------
        JButton redButton = new JButton("Создать таблицу");
        redButton.setLocation(200, 100); // расположение кнопки
        redButton.setSize(200, 40); // размер кнопки
        // создаём объект-обработчик события

        redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TableModelTest();
            }
        });

        redButton.setActionCommand("Open");
        totalGUI.add(redButton); // добавляем кнопку на поверхность
/*
        //Текстовое поле----------------
        smallField = new JTextField(25);
        smallField.setToolTipText("Введите количество точек у функции");
        smallField.setFont(new Font("Dialog", Font.PLAIN, 20));
        smallField.setLocation(200, 50); // расположение кнопки
        smallField.setSize(200, 40); // размер кнопки
        totalGUI.add(smallField);
*/
        totalGUI.setOpaque(true);
        return totalGUI; // возвращаем внешний вид
    }

    public static void main(String[] args) {
        MainWindow demo1 = new MainWindow(); // внешность формы

    }
}





