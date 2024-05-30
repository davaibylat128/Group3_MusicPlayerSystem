package main.Implementation.Command;
import main.framework.MusicPlayerCommand;

import javax.sound.sampled.Clip;

public class ResumeCommand implements MusicPlayerCommand{
     private Clip clip;

    public ResumeCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null && clip.isOpen() && !clip.isRunning()) {
            clip.setMicrosecondPosition(clip.getMicrosecondPosition()); // Set the position to current position (resume)
            clip.start();
        }
    }
}
