package com.as.niti;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Clock {

    public static void startThreadedClock(Label currentTimeThreadLabel) {
        Thread clockThread = new ClockThread(currentTimeThreadLabel);
        clockThread.setDaemon(true);
        clockThread.start();
    }

    public static void startClockTask(Label currentTimeTaskLabel) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while(!isCancelled()) {
                    updateMessage(new Date().toString());
                    Thread.sleep(1000);
                }
                return null;
            }
        };

        currentTimeTaskLabel.textProperty().bind(task.messageProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    static class ClockThread extends Thread {

        private final Label label;
        private volatile boolean running = true;
        private static final String DATE_FORMAT_NOW = "HH:mm:ss";

        public ClockThread(Label label) {
            this.label = label;

        }

        void terminate() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                Calendar kalendar = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                Platform.runLater(() -> label.setText(sdf.format(kalendar.getTime())));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println(getName() + ": interrupted, will check if need to be terminated");
                }
            }
        }
    }
}
