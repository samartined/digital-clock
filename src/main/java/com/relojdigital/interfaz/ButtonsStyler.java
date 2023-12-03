package com.relojdigital.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonsStyler {
    public static JButton customizeButton(String text, Color backgroundColor, int fontSize, int buttonWidth,
            int buttonHeight) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor);
        button.setForeground(Color.BLACK); // Texto en negro
        button.setFont(new Font("Arial", Font.BOLD, fontSize));

        // Establecer el tamaño preferido del botón
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        return button;
    }
}
