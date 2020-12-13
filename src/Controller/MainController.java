/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GoalObjectiveDisplayable;
import Model.Goalmodel;
import Model.Objectivemodel;
import Model.Usermodel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
    private ListView<String> goalDisplay;
    @FXML
    private ListView<String> objectiveDisplay;
    @FXML
    private Button logoutButton;
    @FXML
    private Parent goalView;
    @FXML
    private Parent dailyHealthView;
    @FXML
    private Parent calendarView;
    @FXML
    private GoalController goalViewController;
    @FXML
    private DailyHealthController dailyHealthViewController;
    @FXML
    private CalendarController calendarViewController;
    private EntityManager manager;
    private Usermodel activeUser;
    private List<Goalmodel> ongoingGoals;
    private List<Goalmodel> pastGoals;
    private ObservableList<TreeItem<GoalObjectiveDisplayable>> ongoingGoalsObservable;
    private ObservableList<String> ongoingGoalNameObservable;
    private ObservableList<TreeItem<GoalObjectiveDisplayable>> pastGoalsObservable;
    private ObservableList<String> ongoingObjectiveNameObservable;

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

    public ListView<String> getGoalDisplay()
    {
        return goalDisplay;
    }

    public void setGoalDisplay(ListView<String> goalDisplay)
    {
        this.goalDisplay = goalDisplay;
    }

    public ListView<String> getObjectiveDisplay()
    {
        return objectiveDisplay;
    }

    public void setObjectiveDisplay(ListView<String> objectiveDisplay)
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

    public Parent getGoalView()
    {
        return goalView;
    }

    public void setGoalView(Parent goalView)
    {
        this.goalView = goalView;
    }

    public Parent getDailyHealthView()
    {
        return dailyHealthView;
    }

    public void setDailyHealthView(Parent dailyHealthView)
    {
        this.dailyHealthView = dailyHealthView;
    }

    public Parent getCalendarView() 
    {
        return calendarView;
    }

    public void setCalendarView(Parent calendarView) 
    {
        this.calendarView = calendarView;
    }

    public GoalController getGoalViewController()
    {
        return goalViewController;
    }

    public void setGoalViewController(GoalController goalController)
    {
        this.goalViewController = goalController;
    }

    public DailyHealthController getDailyHealthViewController()
    {
        return dailyHealthViewController;
    }

    public void setDailyHealthViewController(DailyHealthController dailyHealthViewController)
    {
        this.dailyHealthViewController = dailyHealthViewController;
    }

    public CalendarController getCalendarViewController()
    {
        return calendarViewController;
    }

    public void setCalendarViewController(CalendarController calendarViewController)
    {
        this.calendarViewController = calendarViewController;
    }

    public Usermodel getActiveUser()
    {
        return this.activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }

    public List<Goalmodel> getOngoingGoals()
    {
        return ongoingGoals;
    }

    public void setOngoingGoals(List<Goalmodel> ongoingGoals)
    {
        this.ongoingGoals = ongoingGoals;
    }

    public List<Goalmodel> getPastGoals()
    {
        return pastGoals;
    }

    public void setPastGoals(List<Goalmodel> pastGoals)
    {
        this.pastGoals = pastGoals;
    }

    public ObservableList<String> getOngoingGoalNameObservable()
    {
        return ongoingGoalNameObservable;
    }

    public void setOngoingGoalNameObservable(ObservableList<String> ongoingGoalNameObservable)
    {
        this.ongoingGoalNameObservable = ongoingGoalNameObservable;
    }

    public ObservableList<String> getOngoingObjectiveNameObservable()
    {
        return ongoingObjectiveNameObservable;
    }

    public void setOngoingObjectiveNameObservable(ObservableList<String> ongoingObjectiveNameObservable)
    {
        this.ongoingObjectiveNameObservable = ongoingObjectiveNameObservable;
    }

    public ObservableList<TreeItem<GoalObjectiveDisplayable>> getOngoingGoalsObservable()
    {
        return ongoingGoalsObservable;
    }

    public void setOngoingGoalsObservable(ObservableList<TreeItem<GoalObjectiveDisplayable>> ongoingGoalsObservable)
    {
        this.ongoingGoalsObservable = ongoingGoalsObservable;
    }

    public ObservableList<TreeItem<GoalObjectiveDisplayable>> getPastGoalsObservable()
    {
        return pastGoalsObservable;
    }

    public void setPastGoalsObservable(ObservableList<TreeItem<GoalObjectiveDisplayable>> pastGoalsObservable)
    {
        this.pastGoalsObservable = pastGoalsObservable;
    }

    public EntityManager getManager()
    {
        return manager;
    }

    public void setManager(EntityManager manager)
    {
        this.manager = manager;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Init EntityManager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
    }

    @FXML
    private void toggleCalendar(ActionEvent event)
    {
        getDailyHealthView().setVisible(false);
        getGoalView().setVisible(false);
        getCalendarView().setVisible(true);
    }

    @FXML
    private void toggleGoals(ActionEvent event)
    {
        getCalendarView().setVisible(false);
        getDailyHealthView().setVisible(false);
        getGoalView().setVisible(true);
    }

    @FXML
    private void toggleHealth(ActionEvent event)
    {
        getCalendarView().setVisible(false);
        getGoalView().setVisible(false);
        getDailyHealthView().setVisible(true);
    }

    @FXML
    private void logout(ActionEvent event)
    {
        try
        {
            //Load FXML detail view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/LoginView.fxml"));
            Parent loginView = loader.load();

            //Instantiate scene, give it the parent we instantiated, also get current scene from event source
            Scene loginScene = new Scene(loginView);
            Scene currentScene = ((Node)event.getSource()).getScene();

            //Instantiate new stage, give it the scene we instantiated, set visible
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(loginScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void updateGoalViews()
    {
        //Query for all ongoing goals
        Query query = getManager().createNamedQuery("Goalmodel.findByCalendarIdAndOngoing");
        query.setParameter("ongoing", true);
        query.setParameter("calendarid", getActiveUser().getCalendarid());
        setOngoingGoals(query.getResultList());
        
        //Reset ongoing goals observable lists
        setOngoingGoalNameObservable(FXCollections.observableArrayList());
        setOngoingObjectiveNameObservable(FXCollections.observableArrayList());
        setOngoingGoalsObservable(FXCollections.observableArrayList());
        
        //For all goals in result list, add goals to observable list
        for(Goalmodel g : getOngoingGoals())
        {
            //Read goal names and add to sidebar observable list
            getOngoingGoalNameObservable().add(g.getName());
            
            //Create goal item
            TreeItem<GoalObjectiveDisplayable> goalItem = new TreeItem(g);
            
            //Read the goal's objectives and set objectives as children to goal item's node
            ObservableList<TreeItem<GoalObjectiveDisplayable>> objectivesObservable = FXCollections.observableArrayList();
            for(Objectivemodel o : g.getObjectivemodelCollection())
            {
                objectivesObservable.add(new TreeItem<GoalObjectiveDisplayable>(o));
                if((!o.getAccomplished())&&(o.getOngoing()))
                {
                    getOngoingObjectiveNameObservable().add(o.getName());
                }
            }
            goalItem.getChildren().setAll(objectivesObservable);
            getOngoingGoalsObservable().add(goalItem);
        }
        
        //Query for all past goals
        Query query2 = getManager().createNamedQuery("Goalmodel.findByCalendarIdAndOngoing");
        query.setParameter("ongoing", false);
        query.setParameter("calendarid", getActiveUser().getCalendarid());
        setPastGoals(query.getResultList());
        
        //Reset past goals observable
        setPastGoalsObservable(FXCollections.observableArrayList());
        
        //For all goals in result list, add goals to observable list
        for(Goalmodel g : getPastGoals())
        {
            //Create goal item
            TreeItem<GoalObjectiveDisplayable> goalItem = new TreeItem(g);
            
            //Read the goal's objectives and set objectives as children to goal item's node
            ObservableList<TreeItem<GoalObjectiveDisplayable>> objectivesObservable = FXCollections.observableArrayList();
            for(Objectivemodel o : g.getObjectivemodelCollection())
            {
                objectivesObservable.add(new TreeItem<GoalObjectiveDisplayable>(o));
            }
            goalItem.getChildren().setAll(objectivesObservable);
            getOngoingGoalsObservable().add(goalItem);
        }
        
        //Update display
        getGoalDisplay().setItems(getOngoingGoalNameObservable());
        getObjectiveDisplay().setItems(getOngoingObjectiveNameObservable());
        Goalmodel ongoingRoot = new Goalmodel();
        ongoingRoot.setName("Ongoing Goals");
        getGoalViewController().getOngoingGoalTable().setRoot(new TreeItem<>(ongoingRoot));
        getGoalViewController().getOngoingGoalTable().getRoot().setExpanded(true);
        getGoalViewController().getOngoingGoalTable().getRoot().getChildren().setAll(getOngoingGoalsObservable());
        Goalmodel pastRoot = new Goalmodel();
        pastRoot.setName("Past Goals");
        getGoalViewController().getPastGoalTable().setRoot(new TreeItem<>(pastRoot));
        getGoalViewController().getPastGoalTable().getRoot().setExpanded(true);
        getGoalViewController().getPastGoalTable().getRoot().getChildren().setAll(getPastGoalsObservable());
        getGoalDisplay().refresh();
        getObjectiveDisplay().refresh();
        getGoalViewController().getOngoingGoalTable().refresh();
        getGoalViewController().getPastGoalTable().refresh();
    }
}