// ProgressBarObserver.java
package main.Implementation;

import main.framework.ProgressObserver;
import javafx.scene.control.ProgressBar;

public class ProgressBarObserver implements ProgressObserver {
    private ProgressBar progressBar;

    public ProgressBarObserver(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void updateProgress(double progress) {
        // Ensure UI updates are run on the JavaFX Application Thread
        javafx.application.Platform.runLater(() -> progressBar.setProgress(progress));
    }
}
