package ru.ssau.tk.chpok.labs.ui.listeners;

import ru.ssau.tk.chpok.labs.ui.MainWindow;
import ru.ssau.tk.chpok.labs.ui.TableModelTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TestActionListener extends JFrame implements ActionListener { // наследуемся от стандартного класса  ActionListener
    public void actionPerformed(ActionEvent e) {
        new TableModelTest();
    }
}