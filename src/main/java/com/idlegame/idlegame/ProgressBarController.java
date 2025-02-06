package com.idlegame.idlegame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

public class ProgressBarController {
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label countLabel;

    private int count = 0;
    private int countTimerTimeSeconds = 5;
    private int counterProgressStates = 10;
    @FXML
    protected void onButtonClick() {
        startProgress(countTimerTimeSeconds);
    }


    public void startProgress(double durationSeconds) {
        progressBar.setProgress(0);
        KeyFrame[] keyFrameList = new KeyFrame[counterProgressStates+1];
        for(int i = 0; i< counterProgressStates+1; i++){
            double finalI = i;
            KeyFrame frame = new KeyFrame(Duration.seconds(i*(durationSeconds/counterProgressStates)), e -> progressBar.setProgress(finalI /counterProgressStates));
            keyFrameList[i] = frame;
        }
        Timeline timeline = new Timeline(
                keyFrameList
        );
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.setOnFinished(endEvent -> endEventTrigger());
        timeline.play();
    }

    public void endEventTrigger(){
        count++;
        countLabel.setText("Count: "+count);
        startProgress(countTimerTimeSeconds);
    }

}