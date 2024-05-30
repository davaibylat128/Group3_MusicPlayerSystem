package main.Implementation;

import main.Implementation.Command.*;
import main.Implementation.State.MusicPlayerFactoryImpl;
import main.framework.*;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.Label;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;

public class MusicPlayerMediatorImpl implements MusicPlayerMediator {
    private Clip clip;
    private MusicPlayer musicPlayer;
    private MusicPlayerCommand playCommand;
    private MusicPlayerCommand pauseCommand;
    private MusicPlayerCommand resumeCommand;
    private MusicPlayerCommand stopCommand;
    private MusicPlayerCommand seekForwardCommand;
    private MusicPlayerCommand seekBackwardCommand;
    private MusicPlayerCommand volumeUpCommand;
    private MusicPlayerCommand volumeDownCommand;
    private ProgressBar progressBar;
    private Label stateLabel;
    private Handler chain;

    public MusicPlayerMediatorImpl(ProgressBar progressBar, Label stateLabel) {
        this.progressBar = progressBar;
        this.stateLabel = stateLabel;
    }

     private void buildChain() {
        Handler playHandler = new PlayHandler(musicPlayer);
        Handler pauseHandler = new PauseHandler(musicPlayer);
        Handler resumeHandler = new ResumeHandler(musicPlayer);
        Handler stopHandler = new StopHandler(musicPlayer);
        // Add more handlers as needed

        playHandler.setNext(pauseHandler);
        pauseHandler.setNext(resumeHandler);
        resumeHandler.setNext(stopHandler);
        // Chain more handlers

        this.chain = playHandler; // First handler in the chain
    }

    

    @Override
    public void play() {
        if (playCommand != null) musicPlayer.play();
        chain.handleRequest("play");
    }

    @Override
    public void pause() {
        if (pauseCommand != null) musicPlayer.pause();
        chain.handleRequest("pause");
    }

    @Override
    public void resume() {
        if (resumeCommand != null) resumeCommand.execute();
        chain.handleRequest("resume");
    }

    @Override
    public void stop() {
        if (stopCommand != null) musicPlayer.stop();
        chain.handleRequest("stop");
 
    }

    @Override
    public void seekForward() {
        if (seekForwardCommand != null) seekForwardCommand.execute();
        chain.handleRequest("seekForward");

    }

    @Override
    public void seekBackward() {
        if (seekBackwardCommand != null) seekBackwardCommand.execute();
        chain.handleRequest("seekBackward");

    }

    @Override
    public void volumeUp() {
        if (volumeUpCommand != null) volumeUpCommand.execute();
        chain.handleRequest("volumeUp");

    }

    @Override
    public void volumeDown() {
        if (volumeDownCommand != null) volumeDownCommand.execute();
        chain.handleRequest("volumeDown");

    }

    @Override
    public void loadAudio(File file) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            playCommand = new PlayCommand(clip);
            pauseCommand = new PauseCommand(clip);
            resumeCommand = new ResumeCommand(clip);
            stopCommand = new StopCommand(clip);
            seekForwardCommand = new SeekForwardCommand(clip);
            seekBackwardCommand = new SeekBackwardCommand(clip);
            volumeUpCommand = new VolumeUpCommand(clip);
            volumeDownCommand = new VolumeDownCommand(clip);

            MusicPlayerFactory factory = new MusicPlayerFactoryImpl(clip);
            musicPlayer = new MusicPlayer(factory.createClip());
            musicPlayer.attachStateObserver(newState -> updateState());

            ProgressSubjectImpl progressSubject = new ProgressSubjectImpl(clip);
            progressSubject.attach(progress -> progressBar.setProgress(progress));
            progressSubject.attach(progress -> updateState()); 

            progressSubject.startUpdating();

            buildChain();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateState() {
        if (musicPlayer != null) {
            stateLabel.setText("State: " + musicPlayer.getCurrentState().getClass().getSimpleName());
        }
    }
}
