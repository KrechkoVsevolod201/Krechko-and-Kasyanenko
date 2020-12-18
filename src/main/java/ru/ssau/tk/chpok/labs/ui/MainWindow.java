package ru.ssau.tk.chpok.labs.ui;

import ru.ssau.tk.chpok.labs.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.chpok.labs.ui.listeners.TestActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JFrame {
    public static final int HEIGHT = 340;
    public static final int WIDTH = 640;
    public static TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();



    public MainWindow() {
        super("Главное окно");
        getContentPane().setLayout(new FlowLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        List<ColorOption> colorOptions = new ArrayList<>();
        colorOptions.add(new ColorOption("Black", Color.BLACK));
        colorOptions.add(new ColorOption("White", Color.WHITE));
        colorOptions.add(new ColorOption("Gray", Color.LIGHT_GRAY));
        colorOptions.add(new ColorOption("Orange", Color.ORANGE));
        colorOptions.add(new ColorOption("Tea tree", new Color(0x008080)));
        ButtonGroup buttonGroup = new ButtonGroup();
        for (var colorOption : colorOptions) {
            buttonGroup.add(colorOption);
            getContentPane().add(colorOption);
            colorOption.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        getContentPane().setBackground(colorOption.getColor());
                    }
                }
            });
        }

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

        List<ColorOption> colorOptions = new ArrayList<>();
        colorOptions.add(new ColorOption("Тёмная тема", Color.BLACK));
        colorOptions.add(new ColorOption("Светлая тема", Color.WHITE));
        ButtonGroup buttonGroup = new ButtonGroup();

        if (factory.getClass() == ArrayTabulatedFunctionFactory.class) {
            arraysItem.setSelected(true);
            linkedListItem.setSelected(false);
        } else {
            arraysItem.setSelected(false);
            linkedListItem.setSelected(true);
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
        JLabel blueLabel = new JLabel("Какая-то надпись =)");
        blueLabel.setLocation(1, -40); /* надпись синего цвета*/
        blueLabel.setSize(300, 100); // размер области надписи
        //blueLabel.setHorizontalAlignment(0);
        blueLabel.setForeground(Color.blue); // задаём цвет
        totalGUI.add(blueLabel); // добавляем текстовую метку на поверхность

        // Создаём кнопку---------------
        JButton redButton = new JButton("Выбор темы");
        redButton.setLocation(1, 200); // расположение кнопки
        redButton.setSize(200, 40); // размер кнопки
        // создаём объект-обработчик события
        ActionListener actionListener = new TestActionListener(); // создаём создаём действие
        // назначаем этот обработчик кнопке
        redButton.addActionListener(actionListener);// прикрепляем действие к кнопке (срабоет по нажатии на неё)
        totalGUI.add(redButton); // добавляем кнопку на поверхность


        totalGUI.setOpaque(true);
        return totalGUI; // возвращаем внешний вид
    }

    private static class ColorOption extends JRadioButton {
        private Color color;

        public ColorOption(String colorName, Color color) {
            super(colorName);
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    public static void main(String[] args) {
        MainWindow demo1 = new MainWindow(); // внешность формы
    }
}





