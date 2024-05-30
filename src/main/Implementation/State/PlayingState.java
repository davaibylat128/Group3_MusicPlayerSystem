// PlayingState.java
package main.Implementation.State;

import main.framework.MusicPlayerState;

import javax.sound.sampled.Clip;

public class PlayingState implements MusicPlayerState {
    private final Clip clip;

    public PlayingState(Clip clip) {
        this.clip = clip;
    }

    public String getStateName(){
        return "Playing";
    }

    @Override
    public void play() {
        // Already playing, do nothing
    }

    @Override
    public void pause() {
        clip.stop();
        clip.flush();
        clip.setMicrosecondPosition(0);
        clip.setFramePosition(0);
        clip.stop();
    }

    @Override
    public void stop() {
        clip.stop();
        clip.flush();
        clip.setMicrosecondPosition(0);
        clip.setFramePosition(0);
    }


}
