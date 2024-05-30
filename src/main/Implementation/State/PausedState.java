// PausedState.java
package main.Implementation.State;

import main.framework.MusicPlayerState;

import javax.sound.sampled.Clip;

public class PausedState implements MusicPlayerState {
    private final Clip clip;
    private long pausedPosition; // Store the position where the audio was paused

    public PausedState(Clip clip) {
        this.clip = clip;
        // Store the position only if the clip is open and active
        if (clip != null && clip.isOpen() && clip.isActive()) {
            this.pausedPosition = clip.getMicrosecondPosition();
        }
    }

    @Override
    public void play() {
        // Check if the pausedPosition is valid before starting playback
        if (pausedPosition >= 0 && pausedPosition < clip.getMicrosecondLength()) {
            clip.setMicrosecondPosition(pausedPosition); // Set the position to where it was paused
            clip.start();
        }
    }

    @Override
    public void pause() {
        pausedPosition = clip.getMicrosecondPosition();
        clip.stop();
    }

    public String getStateName(){
        return "Paused";
        
    }

    @Override
    public void stop() {
        clip.stop();
        clip.flush();
        clip.setMicrosecondPosition(0);
        clip.setFramePosition(0);
    }

}
