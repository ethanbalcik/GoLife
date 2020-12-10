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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class GoalController implements Initializable
{
    @FXML
    private TableView pastGoalTable;
    @FXML
    private TableColumn pastGoalNameColumn;
    @FXML
    private TableColumn pastGoalAccomplishedColumn;
    @FXML
    private TableView ongoingGoalTable;
    @FXML
    private TableColumn ongoingGoalNameColumn;
    @FXML
    private TableColumn ongoingGoalDescriptionColumn;
    @FXML
    private TableColumn ongoingGoalDeadlineColumn;
    private MainController mainController;
    private Usermodel activeUser;

    public TableView getPastGoalTable()
    {
        return pastGoalTable;
    }

    public void setPastGoalTable(TableView pastGoalTable)
    {
        this.pastGoalTable = pastGoalTable;
    }

    public TableColumn getPastGoalNameColumn()
    {
        return pastGoalNameColumn;
    }

    public void setPastGoalNameColumn(TableColumn pastGoalNameColumn)
    {
        this.pastGoalNameColumn = pastGoalNameColumn;
    }

    public TableColumn getPastGoalAccomplishedColumn()
    {
        return pastGoalAccomplishedColumn;
    }

    public void setPastGoalAccomplishedColumn(TableColumn pastGoalAccomplishedColumn)
    {
        this.pastGoalAccomplishedColumn = pastGoalAccomplishedColumn;
    }

    public TableView getOngoingGoalTable()
    {
        return ongoingGoalTable;
    }

    public void setOngoingGoalTable(TableView ongoingGoalTable)
    {
        this.ongoingGoalTable = ongoingGoalTable;
    }

    public TableColumn getOngoingGoalNameColumn()
    {
        return ongoingGoalNameColumn;
    }

    public void setOngoingGoalNameColumn(TableColumn ongoingGoalNameColumn)
    {
        this.ongoingGoalNameColumn = ongoingGoalNameColumn;
    }

    public TableColumn getOngoingGoalDescriptionColumn()
    {
        return ongoingGoalDescriptionColumn;
    }

    public void setOngoingGoalDescriptionColumn(TableColumn ongoingGoalDescriptionColumn)
    {
        this.ongoingGoalDescriptionColumn = ongoingGoalDescriptionColumn;
    }

    public TableColumn getOngoingGoalDeadlineColumn()
    {
        return ongoingGoalDeadlineColumn;
    }

    public void setOngoingGoalDeadlineColumn(TableColumn ongoingGoalDeadlineColumn)
    {
        this.ongoingGoalDeadlineColumn = ongoingGoalDeadlineColumn;
    }

    public MainController getMainController()
    {
        return mainController;
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
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
        //Initialize Table Columns
        getPastGoalNameColumn().setCellValueFactory(new PropertyValueFactory("name"));
        getPastGoalAccomplishedColumn().setCellValueFactory(new PropertyValueFactory("accomplished"));
        getOngoingGoalNameColumn().setCellValueFactory(new PropertyValueFactory("name"));
        getOngoingGoalDescriptionColumn().setCellValueFactory(new PropertyValueFactory("description"));
        getOngoingGoalDeadlineColumn().setCellValueFactory(new PropertyValueFactory("deadline"));
    }
    
    @FXML
    private void toCreateGoal(ActionEvent event)
    {
        try
        {
            //Load create goal & objective view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateGoalObjectiveView.fxml"));
            Parent createGoalObjectiveView = loader.load();
            
            //Instantiate scene, give it the parent we instantiated
            Scene scene = new Scene(createGoalObjectiveView);
            
            //Get controller, set active user, set goal
            CreateGoalObjectiveController controller = loader.getController();
            controller.setMainController(getMainController());
            controller.setActiveUser(getActiveUser());
            controller.setIsGoal(true);
            
            //Instantiate new stage, give it the scene we instantiated, set visible
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void toCreateObjective(ActionEvent event)
    {
    
    }
    
    @FXML
    private void toCreateEvent(ActionEvent event)
    {
    
    }
}
