package main.Implementation.Command;

import main.framework.MusicPlayerCommand;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class VolumeUpCommand implements MusicPlayerCommand {
    private Clip clip;

    public VolumeUpCommand(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void execute() {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float newVolume = Math.min(volumeControl.getValue() + 2.0f, volumeControl.getMaximum());
            volumeControl.setValue(newVolume);
        }
    }
}
