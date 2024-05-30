package main.Implementation.State;

import javax.sound.sampled.Clip;

import main.framework.MusicPlayerFactory;

public class MusicPlayerFactoryImpl implements MusicPlayerFactory {
    private final Clip clip;

    public MusicPlayerFactoryImpl(Clip clip) {
        this.clip = clip;
    }

    @Override
    public Clip createClip() {
        return clip;
    }
}
