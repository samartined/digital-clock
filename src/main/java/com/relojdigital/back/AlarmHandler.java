package com.relojdigital.back;

import java.io.File;
import java.util.Calendar;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class AlarmHandler {
    private Calendar alarmTime;

    public AlarmHandler() {
        this.alarmTime = Calendar.getInstance();
        this.alarmTime.set(Calendar.SECOND, 0);
    }

    public void setAlarmTime(int hour, int minute) {
        this.alarmTime.set(Calendar.HOUR_OF_DAY, hour);
        this.alarmTime.set(Calendar.MINUTE, minute);
    }

    public void checkAlarm() {
        Calendar currentTime = Calendar.getInstance();
        if (currentTime.get(Calendar.HOUR_OF_DAY) == this.alarmTime.get(Calendar.HOUR_OF_DAY) &&
                currentTime.get(Calendar.MINUTE) == this.alarmTime.get(Calendar.MINUTE)) {
        }
    }

    private void triggerAlarm() {
        JOptionPane.showMessageDialog(null, alarmTime);
        selectAndPlayAlarmSound();
    }

    // Damos al usuario la posibilidad de seleccionar un sonido de alarma
    private void selectAndPlayAlarmSound() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona el sonido de la alarma");

        // Para filtrar por archivos de sonido
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".wav");
            }

            @Override
            public String getDescription() {
                return "Archivos de sonido (*.wav)";
            }
        });

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            playAlarmSound(selectedFile);
        }
    }

    // Reproducimos el sonido de alarma seleccionado
    private void playAlarmSound(File soundFile) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
