package ru.ssau.tk.chpok.labs.ui.listeners;

import ru.ssau.tk.chpok.labs.ui.MainWindow;
import ru.ssau.tk.chpok.labs.ui.TextFieldTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;

public class TestActionListener extends JFrame implements ActionListener { // наследуемся от стандартного класса  ActionListener
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
            if (cmd.equals("Open")) {
                new TextFieldTest();
            }

    }
}