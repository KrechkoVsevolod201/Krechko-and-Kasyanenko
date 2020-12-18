package ru.ssau.tk.chpok.labs.ui.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class TestActionListener implements ActionListener { // наследуемся от стандартного класса  ActionListener
    public void actionPerformed(ActionEvent e) {
        // выведем сообщение из нескольких строк
        // одну из них в кавычках

        // задаём текст для сообщения
        String message = "Нажал ок";
        // выполняем команду вывода сообщения
        JOptionPane.showMessageDialog(new JFrame(), message, "Message by 2",
                JOptionPane.ERROR_MESSAGE);
    }
}