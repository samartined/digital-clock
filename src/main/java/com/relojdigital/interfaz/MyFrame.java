package com.relojdigital.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.relojdigital.back.ClockHandler;

public class MyFrame extends JFrame {

    private JLabel timeLabel;

    public MyFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reloj Digital");

        // Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Calcular el ancho de la ventana para que ocupe el 60% de la pantalla
        int windowWidth = (int) (screenSize.width * 0.6);

        setSize(windowWidth, windowWidth); // Tamaño de la ventana
        setResizable(true); // No se puede redimensionar
        setLocationRelativeTo(null); // Centrar la ventana

        // Panel para contenido con fondo gradiente
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(30, 30, 30), getWidth(), getHeight(),
                        new Color(10, 10, 10));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());

        // Agregar el timeLabel al panel
        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 100));
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        // Ubica la etiqueta en el centro del panel
        panel.add(timeLabel, BorderLayout.CENTER);

        // Hacer visible la ventana después de iniciar el reloj
        setVisible(true);

        // Panel para botones del cronómetro
        JPanel chronometerButtonPanel = new JPanel();
        chronometerButtonPanel.setLayout(new BorderLayout());

        // Etiqueta para indicar que son los botones del cronómetro
        JLabel chronometerLabel = new JLabel("Cronómetro");
        chronometerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        chronometerLabel.setForeground(Color.WHITE);
        chronometerLabel.setHorizontalAlignment(JLabel.CENTER);
        chronometerButtonPanel.add(chronometerLabel, BorderLayout.NORTH);

        // Panel para botones
        chronometerButtonPanel = new ChronometerPanel();
        add(chronometerButtonPanel, BorderLayout.SOUTH);

        // Agregar la etiqueta al frame
        add(panel);

        // Hacer visible la ventana después de iniciar el reloj
        this.setVisible(true);

        // Iniciar el reloj digital
        startClock();

    }

    public void startClock() {
        // Crear una instancia del manejador del reloj y ejecutarlo en un hilo separado
        ClockHandler clockHandler = new ClockHandler(timeLabel);
        new Thread(clockHandler).start();
    }
}