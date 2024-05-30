package main.gui;

import main.framework.MusicPlayerMediator;
import main.Implementation.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;

public class App extends Application {
    private MusicPlayerMediator mediator;
    private ProgressBar progressBar;
    private Label stateLabel;

    @Override
    public void start(Stage primaryStage) {
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(280); // Set a fixed width for the progress bar
        stateLabel = new Label("State: Stopped");
        stateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        mediator = new MusicPlayerMediatorImpl(progressBar, stateLabel);

        Button playButton = createButton("Play");
        Button pauseButton = createButton("Pause");
        Button resumeButton = new Button("Resume");
        Button stopButton = createButton("Stop");
        Button seekForwardButton = createButton("Seek Forward");
        Button seekBackwardButton = createButton("Seek Backward");
        Button addButton = createButton("Add Audio");
        Button volumeUpButton = createButton("Volume Up");
        Button volumeDownButton = createButton("Volume Down");

        playButton.setOnAction(e -> mediator.play());
        pauseButton.setOnAction(e -> mediator.pause());
        resumeButton.setOnAction(e -> mediator.resume());
        stopButton.setOnAction(e -> mediator.stop());
        seekForwardButton.setOnAction(e -> mediator.seekForward());
        seekBackwardButton.setOnAction(e -> mediator.seekBackward());
        addButton.setOnAction(e -> addAudio());
        volumeUpButton.setOnAction(e -> mediator.volumeUp());
        volumeDownButton.setOnAction(e -> mediator.volumeDown());

        VBox root = new VBox(10);
        root.getChildren().addAll(playButton, pauseButton, resumeButton, stopButton, seekForwardButton, seekBackwardButton, addButton, volumeUpButton, volumeDownButton, progressBar, stateLabel);
        root.setPrefSize(500, 600);
        root.setStyle("-fx-padding: 20px; -fx-background-color: #f0f0f0;"); // Add padding and background color

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Music Player");
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(120); // Set a fixed width for buttons
        button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setTooltip(new Tooltip("Click to " + text));
        return button;
    }

    private void addAudio() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Audio File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.ogg")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            mediator.loadAudio(selectedFile);
        }
    }
    


    public static void main(String[] args) {
        launch(args);
    }
}

