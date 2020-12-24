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


    public TableModelTest() throws FileNotFoundException {
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
                // Номер выделенной строки
                int idx = table1.getSelectedRow();
                // Вставка новой строки после выделенной
                tableModel.insertRow(idx + 1, new String[] {
                        String.valueOf(table1.getRowCount()),
                        "???" });
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

        int pointNumber = 0;
        FileInputStream fileIn = new FileInputStream("C://Users/GachiBoy/Documents/GitHub/Krechko-and-Kasyanenko/output/pointMemory");
        try {
            pointNumber = fileIn.read();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        table1 = new JTable(tableModel);
        /*for(int i = 0; i < pointNumber; i++){
            tableModel.insertRow(i + 1, new String[] {
                    String.valueOf(table1.getRowCount()),
                    "???"});
        }*/



        // Формирование интерфейса
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        getContentPane().add(contents);

        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        getContentPane().add(buttons, "South");
        // Вывод окна на экран
        setSize(400, 300);
        setVisible(true);
    }


    public static void main(String[] args) throws FileNotFoundException {
        new TableModelTest();
    }
}