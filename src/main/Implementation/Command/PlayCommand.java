// PlayCommand.java
package main.Implementation.Command;

import javax.sound.sampled.Clip;

import main.framework.MusicPlayerCommand;

public class PlayCommand implements MusicPlayerCommand {
    private Clip clip;

    public PlayCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null) {
            clip.start();
        }
    }
}
