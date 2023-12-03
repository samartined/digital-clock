package com.relojdigital.back;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ChronometerHandler implements Runnable {

    private volatile boolean running, paused;
    private long elapsedTime;
    private JLabel timeLabel;

    public ChronometerHandler(JLabel timeLabel) {
        if (timeLabel == null) {
            throw new IllegalArgumentException("timeLabel cannot be null");
        }
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

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public void stop() {
        running = false;
    }

    public boolean isPaused() {
        return paused;
    }

    private void updateTimeLabel() {
        // Verificamos si timeLabel es null antes de intentar actualizarlo
        if (timeLabel != null) {
            long hours = elapsedTime / 3600;
            long minutes = (elapsedTime % 3600) / 60;
            long seconds = elapsedTime % 60;

            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            SwingUtilities.invokeLater(() -> timeLabel.setText(time));
        }
    }
}