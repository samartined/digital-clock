package com.relojdigital.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame {

    SimpleDateFormat timeFormat;
    JLabel timeLabel;
    JLabel dayLabel;
    String time;

    public MyFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Reloj Digital");

        // Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular el ancho de la ventana para que ocupe el 60% de la pantalla
        int windowWidth = (int) (screenSize.width * 0.6);

        this.setSize(windowWidth, 200); // Tamaño de la ventana
        this.setResizable(false); // No se puede redimensionar
        timeFormat = new SimpleDateFormat("hh:mm:ss a");

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        timeLabel.setForeground(new Color(0x00FF00));
        timeLabel.setBackground(Color.black);
        timeLabel.setOpaque(true);

        this.add(timeLabel);
        this.setVisible(true);

        setTime();
    }

    public void setTime() {
        while (true) {
            time = timeFormat.format(new java.util.Date().getTime());
            timeLabel.setText(time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
