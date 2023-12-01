package com.relojdigital.back;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ChronometerHandler implements Runnable {

    private volatile boolean running, paused;
    private long elapsedTime;
    private JLabel timeLabel;

    public ChronometerHandler(JLabel timeLabel) {
        this.timeLabel = timeLabel;
        this.running = true;
        this.paused = false;
        this.elapsedTime = 0;
    }

    @Override
    public void run() {
        while (running) {

            if (!paused) {
                elapsedTime++;
                SwingUtilities.invokeLater(() -> updateTimeLabel());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void pause() {
        paused = true;
    }

    private void resume() {
        paused = false;
    }

    private void stop() {
        running = false;
    }

    private void updateTimeLabel() {
        long hours = elapsedTime / 3600;
        long minutes = (elapsedTime % 3600) / 60;
        long seconds = elapsedTime % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(time);
    }
}
