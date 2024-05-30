package main.Implementation.Command;

import main.framework.MusicPlayerCommand;
import javax.sound.sampled.Clip;

public class PauseCommand implements MusicPlayerCommand {
    private Clip clip;

    public PauseCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
