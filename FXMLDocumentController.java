/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avc9wbstopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Chavakula
 */
public class FXMLDocumentController implements Initializable {
    
    
    private Integer tickTimeInSeconds = 1;
    private Integer angleDeltaPerSecond = 6;
    private Integer secondsElapsed = 0;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private Integer minutesElapsed = 0;
    
    @FXML
    ImageView handImageView;
    
    @FXML
    ImageView clockImageView;
    
    @FXML
    Label digitalClock;
     
    @FXML
     private void update() {
        secondsElapsed += tickTimeInSeconds;
        Integer rotation = secondsElapsed * angleDeltaPerSecond;
        handImageView.setRotate(rotation);
        
        if(secondsElapsed > 59){
            minutesElapsed += 1;
            secondsElapsed = 0;
            digitalClock.setText(String.format("%02d", minutesElapsed) + ":" + String.format("%02d", secondsElapsed));
        }
        else{
            digitalClock.setText(String.format("%02d", minutesElapsed) + ":" + String.format("%02d", secondsElapsed));
        }  
    }
     
    
     public void start(){
        timeline.play();
    }
     
    public void stop(){
        timeline.stop();
    }
    
    public void reset(){
         stop();
        secondsElapsed = 0;
        handImageView.setRotate(0);
        minutesElapsed = 0;
        digitalClock.setText("00:00");
    }
     
    @FXML
    public void handleStart(ActionEvent event){
        start();
    }
    
    @FXML
    public void handleStop(ActionEvent event){
        stop();
    }
    
    @FXML
    public void handleReset(ActionEvent event){
        reset();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        digitalClock.setText("00:00");
    }    
    
}