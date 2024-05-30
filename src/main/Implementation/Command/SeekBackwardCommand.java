package main.Implementation.Command;

import main.framework.MusicPlayerCommand;
import javax.sound.sampled.Clip;

public class SeekBackwardCommand implements MusicPlayerCommand {
    private Clip clip;

    public SeekBackwardCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null) {
            long newPosition = clip.getMicrosecondPosition() - 5000000; // Seek 5 seconds backward
            if (newPosition >= 0) {
                clip.setMicrosecondPosition(newPosition);
            } else {
                clip.setMicrosecondPosition(0); // Seek to the beginning if attempting to seek before the start
            }
        }
    }
}