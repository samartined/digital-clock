package com.relojdigital.back;

import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class ClockHandler implements Runnable {

    private SimpleDateFormat timeFormat;
    private JLabel timeLabel;

    public ClockHandler(JLabel timeLabel) {
        this.timeLabel = timeLabel;
        this.timeFormat = new SimpleDateFormat("hh:mm:ss a");
    }

    @Override
    public void run() {
        while (true) {
            updateTime();
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
}
