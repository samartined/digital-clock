package com.relojdigital.back;

import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class ClockHandler implements Runnable {

    private SimpleDateFormat timeFormat;
    private JLabel timeLabel;
    private AlarmHandler alarmHandler;

    public ClockHandler(JLabel timeLabel, AlarmHandler alarmHandler) {
        this.timeLabel = timeLabel;
        this.timeFormat = new SimpleDateFormat("hh:mm:ss a");
        this.alarmHandler = alarmHandler;
    }

    @Override
    public void run() {
        while (true) {
            updateTime();
            checkAlarm();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateTime() {
        String time = timeFormat.format(new java.util.Date().getTime());
        timeLabel.setText(time);
    }

    private void checkAlarm() {
        // Verificamos si la alarma está activada
        if (alarmHandler != null) {
            alarmHandler.checkAlarm();
        }
    }
}
