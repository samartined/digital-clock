package com.relojdigital.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.relojdigital.back.ClockHandler;

public class MyFrame extends JFrame {

    private JLabel timeLabel;

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Reloj Digital");

        // Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular el ancho de la ventana para que ocupe el 60% de la pantalla
        int windowWidth = (int) (screenSize.width * 0.6);

        this.setSize(windowWidth, windowWidth); // Tamaño de la ventana
        this.setResizable(false); // No se puede redimensionar
        this.setLocationRelativeTo(null); // Centrar la ventana

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);

        this.add(timeLabel);
        this.setVisible(true);

        // Crear una instancia del manejador del reloj y ejecutarlo en un hilo separado
        ClockHandler clockHandler = new ClockHandler(timeLabel);
        new Thread(clockHandler).start();
    }
}
