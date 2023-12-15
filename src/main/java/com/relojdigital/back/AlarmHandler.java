package com.relojdigital.back;

import java.io.File;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AlarmHandler {
    private Calendar alarmTime;

    public AlarmHandler() {
        this.alarmTime = Calendar.getInstance();
        this.alarmTime.set(Calendar.SECOND, 0);
        startAlarmChecking();
    }

    public void setAlarmTime(int hour, int minute) {
        this.alarmTime.set(Calendar.HOUR_OF_DAY, hour);
        this.alarmTime.set(Calendar.MINUTE, minute);
    }

    private void startAlarmChecking() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                checkAlarm();
            }
        }, 0, 1000); // Verificar cada segundo
    }

    public void checkAlarm() {
        Calendar currentTime = Calendar.getInstance();
        System.out.println("Checking Alarm - Current Time: " + currentTime.getTime());
        System.out.println("Checking Alarm - Alarm Time: " + this.alarmTime.getTime());

        if (currentTime.get(Calendar.HOUR_OF_DAY) == this.alarmTime.get(Calendar.HOUR_OF_DAY) &&
                currentTime.get(Calendar.MINUTE) == this.alarmTime.get(Calendar.MINUTE)) {
            playSystemAlarmSound();
        }
    }

    private void playSystemAlarmSound() {
        File systemSound = SoundSystem.getSystemSound();

        if (systemSound != null) {
            playAlarmSound(systemSound);
        } else {
            System.out.println("No system sound available. Playing default alarm sound.");
            // Si no hay un sonido de sistema disponible, reproduce un sonido de alarma
            // predeterminado
            // Puedes agregar aquí tu lógica para reproducir un sonido de alarma
            // predeterminado
        }
    }

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
