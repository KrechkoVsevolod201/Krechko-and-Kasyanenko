package ru.ssau.tk.chpok.labs.ui;

import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainWindow extends JFrame {
    public static final int HEIGHT = 340;
    public static final int WIDTH = 640;
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    // Текстовые поля
    public final JButton arrayButton = new JButton("Массив");
    public final JButton functionButton = new JButton("Функция");
    public final JButton rickButton = new JButton("Rick Roll");
    private final JButton calculatorButton = new JButton("Калькулятор");

    public MainWindow() throws URISyntaxException {
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
        addButtonListeners();
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
        if (getBackground() == (Color.WHITE)) {
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

    public JPanel createContentPane() throws URISyntaxException {

        // We create a bottom JPanel to place everything on.
        // сначала создаётся "панель", на которой и размещаюся
        // остальные компоненты
        JPanel totalGUI = new JPanel(); // создаём "поверхность"
        totalGUI.setLayout(null);

        // Создадим ярлык (надпись) синего цвета
        JLabel blueLabel = new JLabel("Добро пожаловать, добро пожаловать в программу 17");
        blueLabel.setLocation(100, -40); /* надпись синего цвета*/
        blueLabel.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 16));
        blueLabel.setSize(640, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        blueLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(blueLabel); // добавляем текстовую метку на поверхность

        // Создаём кнопку---------------
        arrayButton.setLocation(300, 100); // расположение кнопки
        arrayButton.setSize(100, 40); // размер кнопки
        arrayButton.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события


        arrayButton.setActionCommand("Open");
        totalGUI.add(arrayButton); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        functionButton.setLocation(200, 100); // расположение кнопки
        functionButton.setSize(100, 40); // размер кнопки
        functionButton.setBackground(new Color(0xC175EF));
        // создаём объект-обработчик события


        functionButton.setActionCommand("Open");
        totalGUI.add(functionButton); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        rickButton.setLocation(200, 150); // расположение кнопки
        rickButton.setSize(200, 40); // размер кнопки
        rickButton.setBackground(new Color(0xFF0000));

        final URI uri = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstleyVEVO");
        class OpenUrlAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                open(uri);
            }
        }
        rickButton.setActionCommand("Open");
        rickButton.addActionListener(new OpenUrlAction());
        totalGUI.add(rickButton); // добавляем кнопку на поверхность

        // Создаём кнопку---------------
        calculatorButton.setLocation(200, 200); // расположение кнопки
        calculatorButton.setSize(200, 40); // размер кнопки
        calculatorButton.setBackground(new Color(0x28A888));

        class Calculator implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == calculatorButton) {
                    WindowTabulatedFunctionOperationService windowTabulatedFunctionOperationService = new WindowTabulatedFunctionOperationService();
                    windowTabulatedFunctionOperationService.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    windowTabulatedFunctionOperationService.setVisible(true);
                }
            }
        }
        calculatorButton.setActionCommand("Open");
        calculatorButton.addActionListener(new Calculator());
        totalGUI.add(calculatorButton); // добавляем кнопку на поверхность

        totalGUI.setOpaque(true);
        return totalGUI; // возвращаем внешний вид
    }

    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
    }

    private void addButtonListeners() {
        arrayButton.addActionListener(e -> {
            new CreatingArrayTable(function -> {
                String[] buttonsNames = {"Да", "Нет"};
                int resultSave = JOptionPane.showOptionDialog(new Frame(), "Вы хотите сохранить функцию?",
                        "Сохранить..", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, buttonsNames, buttonsNames[1]);

            });
        });

        functionButton.addActionListener(e -> {
            new CreatingFunctionTable(function -> {
                String[] buttonsNames = {"Да", "Нет"};
                int resultSave = JOptionPane.showOptionDialog(new Frame(), "Вы хотите сохранить функцию?",
                        "Сохранить..", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, buttonsNames, buttonsNames[1]);
            });
        });

    }



    public static void main(String[] args) throws URISyntaxException {
        MainWindow demo1 = new MainWindow(); // внешность формы

    }
}





