// SeekForwardCommand.java
package main.Implementation.Command;

import javax.sound.sampled.Clip;

import main.framework.MusicPlayerCommand;

public class SeekForwardCommand implements MusicPlayerCommand {
    private Clip clip;

    public SeekForwardCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null) {
            long newPosition = clip.getMicrosecondPosition() + 5000000; // Seek 5 seconds forward
            long clipLength = clip.getMicrosecondLength();
            if (newPosition < clipLength) {
                clip.setMicrosecondPosition(newPosition);
            }
        }
    }
}
