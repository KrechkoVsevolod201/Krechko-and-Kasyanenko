package ru.ssau.tk.chpok.labs.ui;

import javax.swing.*;

public class AppException {
    public AppException(ExceptionPanel exception){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
                exception.getMessage(),
                "Ошибка :'(",
                JOptionPane.WARNING_MESSAGE
        );
    }
}