package net.ethansk13s.symphocraft.core;

import java.util.HashMap;

public class InputThread extends Thread {
    private boolean isRunning;
    private HashMap<String, Integer> queue;

    public InputThread() {
        this.isRunning = false;
        this.queue = new HashMap<>();
    }

    // Similar problem to image server in miriondb
    public void run() {
        while(this.isRunning) {
            try {
                if (!this.queue.isEmpty()) {
                    for (HashMap.Entry<String, Integer> entry : this.queue.entrySet()) {
                        entry.setValue(entry.getValue() - 1);
                        if (entry.getValue() <= 10) {
                            this.queue.remove(entry.getKey());
                        }
                    }
                }

                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }

    public void startThread() {
        this.isRunning = true;
        this.start();
    }

    public void stopThread() {
        this.isRunning = false;
        interrupt();
    }

    public HashMap<String, Integer> getQueue() {
        return this.queue;
    }
}
