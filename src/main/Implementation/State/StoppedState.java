// StoppedState.java
package main.Implementation.State;

import main.framework.MusicPlayerState;

import javax.sound.sampled.Clip;

public class StoppedState implements MusicPlayerState {
    private final Clip clip;

    public StoppedState(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void play() {
        clip.start();
    }

    @Override
    public void pause() {
        // Already stopped, cannot pause
    }

    @Override
    public void stop() {
        // Already stopped, do nothing
    }
    public String getStateName(){
        return "Stopped";
    }

    
}
