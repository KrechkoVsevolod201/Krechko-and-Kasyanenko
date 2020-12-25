package ru.ssau.tk.chpok.labs.ui;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import ru.ssau.tk.chpok.labs.ui.MainWindow;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TableModelTest extends JFrame
{

    // Модель данных таблицы
    private DefaultTableModel tableModel;
    private JTable table1;
    private Object[] columnsHeader = new String[] {"X", "Y"};
    public JTextField smallField;
    //private final ImageIcon icon = new ImageIcon("Errror.jpg");

    public TableModelTest() {
        super("Пример использования TableModel");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.BLACK);
        // Создание стандартной модели
        tableModel = new DefaultTableModel();
        // Определение столбцов
        tableModel.setColumnIdentifiers(columnsHeader);

        // Создание таблицы на основании модели данных
        table1 = new JTable(tableModel);

        // Создание кнопки добавления строки таблицы
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int pointNumber;
                String textNumber;
                textNumber = smallField.getText();
                pointNumber = Integer.parseInt(textNumber);
                if(pointNumber < 1){
                    add.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            // Включение в интерфейс иконки
                            JOptionPane.showMessageDialog(TableModelTest.this,
                                    "Ну наверное число то побольше 1 надо писать, чтобы работало",
                                    "ЭТЭНШН ПЛИЗ", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                }
                for (int i = 0; i < pointNumber; i++) {
                    // Номер выделенной строки
                    int idx = table1.getSelectedRow();
                    // Вставка новой строки после выделенной
                    tableModel.insertRow(idx + 1, new String[]{
                            String.valueOf(table1.getRowCount()),
                            ""});
                }
            }
        });
        // Создание кнопки удаления строки таблицы
        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Удаление выделенной строки
                tableModel.removeRow(idx);
            }
        });


        //Текстовое поле----------------
        smallField = new JTextField(25);
        smallField.setToolTipText("Введите количество точек у функции");
        smallField.setFont(new Font("Dialog", Font.PLAIN, 20));
        smallField.setText("Напишите сколько вам нужно точек");
        /*smallField.setLocation(200, 50); // расположение кнопки
        smallField.setSize(200, 40); // размер кнопки
*/
        smallField.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {

                smallField.setText(null);

            }

            @Override
            public void focusLost(FocusEvent e) {
                String bugText;
                bugText = smallField.getText();

                smallField.setText(bugText);

            }

        });

        JPanel buttons = new JPanel();
        JPanel text = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        text.add(smallField);
        getContentPane().add(buttons, "North");
        getContentPane().add(text, "South");
        // Вывод окна на экран
        setSize(640, 440);
        setVisible(true);


        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);
        //getContentPane().add(smallField);

    }

}