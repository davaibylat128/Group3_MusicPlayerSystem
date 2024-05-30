package main.Implementation.Command;

import main.framework.MusicPlayerCommand;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class VolumeDownCommand implements MusicPlayerCommand {
    private Clip clip;

    public VolumeDownCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float newVolume = Math.max(volumeControl.getValue() - 2.0f, volumeControl.getMinimum());
            volumeControl.setValue(newVolume);
        }
    }
}
