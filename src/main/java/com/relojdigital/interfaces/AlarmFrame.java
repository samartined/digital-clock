package com.relojdigital.interfaces;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.relojdigital.back.AlarmHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AlarmFrame extends JFrame {

    private AlarmHandler alarmHandler;
    private JTextField alarmHourTextField;
    private JTextField alarmMinuteTextField;

    public AlarmFrame(AlarmHandler alarmHandler) {
        this.alarmHandler = alarmHandler;

        setTitle("Configurar Alarma");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        // Uso GridBagLayout para más control sobre el diseño
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Espacio entre componentes

        // Agregar campos de texto y etiquetas
        JLabel hourLabel = new JLabel("Hora:");
        panel.add(hourLabel, gbc);

        gbc.gridx++;
        alarmHourTextField = new JTextField(2);
        panel.add(alarmHourTextField, gbc);

        gbc.gridx++;
        JLabel minuteLabel = new JLabel("Minuto:");
        panel.add(minuteLabel, gbc);

        gbc.gridx++;
        alarmMinuteTextField = new JTextField(2);
        panel.add(alarmMinuteTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 4;
        JButton setAlarmButton = ButtonsStyler.customizeButton("Configurar Alarma", new Color(255, 165, 0), 16, 210,
                40);
        panel.add(setAlarmButton, gbc);
        setAlarmButton.addActionListener(e -> configureAlarm());

        // Agregar el panel al frame
        add(panel);

        // Hacer visible el frame
        setVisible(true);
    }

    private void configureAlarm() {
        // Configurar la alarma
        try {
            int hour = Integer.parseInt(alarmHourTextField.getText());
            int minute = Integer.parseInt(alarmMinuteTextField.getText());

            if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
                alarmHandler.setAlarmTime(hour, minute);
                JOptionPane.showMessageDialog(null, "Alarma configurada para " + hour + ":" + minute, "Alarma",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Cerrar la ventana después de configurar la alarma
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una hora y minutos válidos", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese valores numéricos para hora y minutos", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
