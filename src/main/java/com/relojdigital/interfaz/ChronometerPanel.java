// ChronometerPanel.java

package com.relojdigital.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.relojdigital.back.ChronometerHandler;

public class ChronometerPanel extends JPanel {

    private JLabel timeLabel;
    private JButton startButton, pauseButton, stopButton;
    private ChronometerHandler chronometerHandler;

    public ChronometerPanel() {
        setLayout(new BorderLayout());

        // Etiqueta para indicar que son los botones del cronómetro
        JLabel chronometerLabel = new JLabel("Cronómetro");
        chronometerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        chronometerLabel.setForeground(Color.WHITE);
        chronometerLabel.setHorizontalAlignment(JLabel.CENTER);
        add(chronometerLabel, BorderLayout.NORTH);

        // Panel para botones
        JPanel buttonPanel = new JPanel();
        startButton = new JButton("Iniciar");
        pauseButton = new JButton("Pausar");
        stopButton = new JButton("Detener");

        // Establecer colores para los botones
        startButton.setBackground(new Color(50, 205, 50)); // Verde claro
        startButton.setForeground(Color.BLACK); // Texto en negro

        pauseButton.setBackground(new Color(255, 165, 0)); // Naranja
        pauseButton.setForeground(Color.BLACK); // Texto en negro

        stopButton.setBackground(new Color(220, 20, 60)); // Rojo
        stopButton.setForeground(Color.BLACK); // Texto en negro

        // Agregar ActionListener a los botones
        startButton.addActionListener(e -> startChronometer());
        pauseButton.addActionListener(e -> pauseChronometer());
        stopButton.addActionListener(e -> stopChronometer());

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);

        // Agregar el panel de botones al centro del panel de cronómetro
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void startChronometer() {
        chronometerHandler = new ChronometerHandler(timeLabel);
        new Thread(chronometerHandler).start();
    }

    private void pauseChronometer() {
        if (chronometerHandler != null) {
            chronometerHandler.pause();
        }
    }

    private void stopChronometer() {
        if (chronometerHandler != null) {
            chronometerHandler.stop();
        }
    }
}
