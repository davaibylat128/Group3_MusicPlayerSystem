package main.Implementation.Command;

import main.framework.MusicPlayerCommand;
import javax.sound.sampled.Clip;

public class StopCommand implements MusicPlayerCommand {
    private Clip clip;

    public StopCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null) {
            clip.stop();
            clip.setMicrosecondPosition(0);
        }
    }
}
