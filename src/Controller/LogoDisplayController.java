/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class LogoDisplayController implements Initializable
{
    @FXML
    private ImageView logoDisplay;
    private Timer animationTimer;

    public ImageView getLogoDisplay()
    {
        return logoDisplay;
    }

    public void setLogoDisplay(ImageView logoDisplay)
    {
        this.logoDisplay = logoDisplay;
    }

    public Timer getAnimationTimer()
    {
        return animationTimer;
    }

    public void setAnimationTimer(Timer animationTimer)
    {
        this.animationTimer = animationTimer;
    }
    
    /**
     * Switches view from LogoDisplayView to LoginView
     */
    public void initLoginView()
    {
        try
        {
            //Load FXML detail view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginView.fxml"));
            Parent loginView = loader.load();

            //Instantiate scene, give it the parent we instantiated, also get current scene from event source
            Scene loginScene = new Scene(loginView);
            Scene currentScene = ((Node)getLogoDisplay()).getScene();

            //Instantiate new stage, give it the scene we instantiated, set visible
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(loginScene);
            stage.show();
            
            //End timer
            getAnimationTimer().cancel();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Set logo animation image
        getLogoDisplay().setImage(new Image("/Assets/20_IST311_GoLife_Logo_v1_Animation.gif"));
        
        //Instantiate timer and schedule class
        setAnimationTimer(new Timer());
        getAnimationTimer().schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Platform.runLater(() -> initLoginView());
            }
        }, 3700);
    }
}
