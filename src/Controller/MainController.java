/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usermodel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class MainController implements Initializable
{

    @FXML
    private ImageView logoDisplay;
    @FXML
    private Button calendarButton;
    @FXML
    private Button goalsButton;
    @FXML
    private Button healthButton;
    @FXML
    private ListView<?> goalDisplay;
    @FXML
    private ListView<?> objectiveDisplay;
    @FXML
    private Button logoutButton;
    @FXML
    private Pane nestedDisplay;
    private Usermodel activeUser;

    public ImageView getLogoDisplay()
    {
        return logoDisplay;
    }

    public void setLogoDisplay(ImageView logoDisplay)
    {
        this.logoDisplay = logoDisplay;
    }

    public Button getCalendarButton()
    {
        return calendarButton;
    }

    public void setCalendarButton(Button calendarButton)
    {
        this.calendarButton = calendarButton;
    }

    public Button getGoalsButton()
    {
        return goalsButton;
    }

    public void setGoalsButton(Button goalsButton)
    {
        this.goalsButton = goalsButton;
    }

    public Button getHealthButton()
    {
        return healthButton;
    }

    public void setHealthButton(Button healthButton)
    {
        this.healthButton = healthButton;
    }

    public ListView<?> getGoalDisplay()
    {
        return goalDisplay;
    }

    public void setGoalDisplay(ListView<?> goalDisplay)
    {
        this.goalDisplay = goalDisplay;
    }

    public ListView<?> getObjectiveDisplay()
    {
        return objectiveDisplay;
    }

    public void setObjectiveDisplay(ListView<?> objectiveDisplay)
    {
        this.objectiveDisplay = objectiveDisplay;
    }

    public Button getLogoutButton()
    {
        return logoutButton;
    }

    public void setLogoutButton(Button logoutButton)
    {
        this.logoutButton = logoutButton;
    }

    public Pane getNestedDisplay()
    {
        return nestedDisplay;
    }

    public void setNestedDisplay(Pane nestedDisplay)
    {
        this.nestedDisplay = nestedDisplay;
    }

    public Usermodel getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        getLogoDisplay().setImage(new Image("/Assets/20_IST311_GoLife_Logo_v1.png"));
    }    

    @FXML
    private void toggleCalendar(ActionEvent event)
    {
        //TODO: set nestedDisplay to CalendarView
    }

    @FXML
    private void toggleGoals(ActionEvent event)
    {
        //TODO: set nestedDisplay to GoalView
    }

    @FXML
    private void toggleHealth(ActionEvent event)
    {
        //TODO: set nestedDisplay to HealthView
    }

    @FXML
    private void logout(ActionEvent event)
    {
        //TODO: navigate to login view
    }
}
