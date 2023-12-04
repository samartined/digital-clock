package com.relojdigital.interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.relojdigital.back.ChronometerHandler;

public class ChronometerFrame extends JFrame {

    private JLabel timeLabel;
    private ChronometerHandler chronometerHandler;

    public ChronometerFrame(MyFrame parentFrame) {
        setTitle("Cronómetro");
        setSize(parentFrame.getWidth(), parentFrame.getHeight());
        setLocationRelativeTo(parentFrame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana al salir

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

        // Panel para botones del cronómetro
        JPanel buttonPanel = new JPanel();

        // Colores y estilo del panel de botones
        buttonPanel.setBackground(new Color(20, 20, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Botón para pausar/reanudar el cronómetro
        JButton pauseChronometerButton = ButtonsStyler.customizeButton("Pausar / Reanudar", new Color(50, 205, 50), 16,
                210, 40);
        buttonPanel.add(pauseChronometerButton);
        pauseChronometerButton.addActionListener(e -> {
            if (chronometerHandler.isPaused()) {
                chronometerHandler.resume();
            } else {
                chronometerHandler.pause();
            }
        });

        // Botón para detener el cronómetro
        JButton stopChronometerButton = ButtonsStyler.customizeButton("Detener", new Color(50, 205, 50), 16,
                210, 40);
        buttonPanel.add(stopChronometerButton);
        stopChronometerButton.addActionListener(e -> stopChronometer(parentFrame));

        // Agregar el panel de botones al sur del panel de contenido
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el panel de contenido al frame
        add(panel);

        // Iniciar el cronómetro
        startChronometer();
    }

    private void startChronometer() {
        chronometerHandler = new ChronometerHandler(timeLabel);
        new Thread(chronometerHandler).start();
    }

    private void stopChronometer(MyFrame parentFrame) {
        // Detener el cronómetro y cerrar la ventana del cronómetro
        chronometerHandler.stop();
        dispose();

        // Mostrar nuevamente el reloj en la ventana principal
        parentFrame.setVisible(true);
    }
}
