package main.Implementation;

import javax.sound.sampled.Clip;

import main.framework.ProgressObserver;
import main.framework.ProgressSubject;

import java.util.ArrayList;
import java.util.List;

public class ProgressSubjectImpl implements ProgressSubject {
    private final List<ProgressObserver> observers = new ArrayList<>();
    private final Clip clip;

    public ProgressSubjectImpl(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void attach(ProgressObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(ProgressObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(double progress) {
        for (ProgressObserver observer : observers) {
            observer.updateProgress(progress);
        }
    }

    public void startUpdating() {
        Thread progressThread = new Thread(() -> {
            while (clip.isOpen() && clip.isActive()) {
                double progress = (double) clip.getMicrosecondPosition() / clip.getMicrosecondLength();
                notifyObservers(progress);
                try {
                    Thread.sleep(100); // Update progress every 100 ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        progressThread.setDaemon(true);
        progressThread.start();
    }
}
