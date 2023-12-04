package com.relojdigital.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

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
        JPanel buttonPanel = new JPanel();
        JButton startChronometerButton = ButtonsStyler.customizeButton("Iniciar Cronómetro", new Color(50, 205, 50), 16,
                210, 40);

        buttonPanel.add(startChronometerButton);
        startChronometerButton.addActionListener(e -> startChronometer());

        // Colores y estilo del panel de botones
        buttonPanel.setBackground(new Color(20, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Uso GridBagLayout para más control sobre el diseño
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre botones

        // Agregar el panel de botones al sur del panel de contenido
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel de contenido al frame
        add(panel);

        // Hacer visible la ventana después de iniciar el reloj
        this.setVisible(true);

        // Iniciar el reloj digital
        startClock();
    }

    private void startClock() {
        // Crear una instancia del manejador del reloj y ejecutarlo en un hilo separado
        ClockHandler clockHandler = new ClockHandler(timeLabel);
        new Thread(clockHandler).start();
    }

    private void startChronometer() {
        // Crear una instancia de ChronometerFrame al iniciar el cronómetro
        ChronometerFrame chronometerFrame = new ChronometerFrame(this);
        chronometerFrame.setVisible(true);
    }
}