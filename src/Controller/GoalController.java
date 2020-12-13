/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GoalObjectiveDisplayable;
import Model.Goalmodel;
import Model.Usermodel;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class GoalController implements Initializable
{
    @FXML
    private Button createEventButton;
    @FXML
    private Button createObjectiveButton;
    @FXML
    private Button reconcileGoalObjectiveButton;
    @FXML
    private TreeTableView<GoalObjectiveDisplayable> pastGoalTable;
    @FXML
    private TreeTableColumn<GoalObjectiveDisplayable, String> pastGoalNameColumn;
    @FXML
    private TreeTableColumn<GoalObjectiveDisplayable, Boolean> pastGoalAccomplishedColumn;
    @FXML
    private TreeTableView<GoalObjectiveDisplayable> ongoingGoalTable;
    @FXML
    private TreeTableColumn<GoalObjectiveDisplayable, String> ongoingGoalNameColumn;
    @FXML
    private TreeTableColumn<GoalObjectiveDisplayable, String> ongoingGoalDescriptionColumn;
    @FXML
    private TreeTableColumn<GoalObjectiveDisplayable, Date> ongoingGoalDeadlineColumn;
    private MainController mainController;
    private Usermodel activeUser;

    public Button getCreateEventButton()
    {
        return createEventButton;
    }

    public void setCreateEventButton(Button createEventButton)
    {
        this.createEventButton = createEventButton;
    }

    public Button getCreateObjectiveButton()
    {
        return createObjectiveButton;
    }

    public void setCreateObjectiveButton(Button createObjectiveButton)
    {
        this.createObjectiveButton = createObjectiveButton;
    }

    public Button getReconcileGoalObjectiveButton()
    {
        return reconcileGoalObjectiveButton;
    }

    public void setReconcileGoalObjectiveButton(Button reconcileGoalObjectiveButton)
    {
        this.reconcileGoalObjectiveButton = reconcileGoalObjectiveButton;
    }

    public TreeTableView getPastGoalTable()
    {
        return pastGoalTable;
    }

    public void setPastGoalTable(TreeTableView pastGoalTable)
    {
        this.pastGoalTable = pastGoalTable;
    }

    public TreeTableColumn getPastGoalNameColumn()
    {
        return pastGoalNameColumn;
    }

    public void setPastGoalNameColumn(TreeTableColumn pastGoalNameColumn)
    {
        this.pastGoalNameColumn = pastGoalNameColumn;
    }

    public TreeTableColumn getPastGoalAccomplishedColumn()
    {
        return pastGoalAccomplishedColumn;
    }

    public void setPastGoalAccomplishedColumn(TreeTableColumn pastGoalAccomplishedColumn)
    {
        this.pastGoalAccomplishedColumn = pastGoalAccomplishedColumn;
    }

    public TreeTableView getOngoingGoalTable()
    {
        return ongoingGoalTable;
    }

    public void setOngoingGoalTable(TreeTableView ongoingGoalTable)
    {
        this.ongoingGoalTable = ongoingGoalTable;
    }

    public TreeTableColumn getOngoingGoalNameColumn()
    {
        return ongoingGoalNameColumn;
    }

    public void setOngoingGoalNameColumn(TreeTableColumn ongoingGoalNameColumn)
    {
        this.ongoingGoalNameColumn = ongoingGoalNameColumn;
    }

    public TreeTableColumn getOngoingGoalDescriptionColumn()
    {
        return ongoingGoalDescriptionColumn;
    }

    public void setOngoingGoalDescriptionColumn(TreeTableColumn ongoingGoalDescriptionColumn)
    {
        this.ongoingGoalDescriptionColumn = ongoingGoalDescriptionColumn;
    }

    public TreeTableColumn getOngoingGoalDeadlineColumn()
    {
        return ongoingGoalDeadlineColumn;
    }

    public void setOngoingGoalDeadlineColumn(TreeTableColumn ongoingGoalDeadlineColumn)
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
        getPastGoalNameColumn().setCellValueFactory(new TreeItemPropertyValueFactory("name"));
        getPastGoalAccomplishedColumn().setCellValueFactory(new TreeItemPropertyValueFactory("accomplished"));
        getOngoingGoalNameColumn().setCellValueFactory(new TreeItemPropertyValueFactory("name"));
        getOngoingGoalDescriptionColumn().setCellValueFactory(new TreeItemPropertyValueFactory("description"));
        getOngoingGoalDeadlineColumn().setCellValueFactory(new TreeItemPropertyValueFactory("deadline"));
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
        try
        {
            //Load create goal & objective view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateGoalObjectiveView.fxml"));
            Parent createGoalObjectiveView = loader.load();
            
            //Instantiate scene, give it the parent we instantiated
            Scene scene = new Scene(createGoalObjectiveView);
            
            //Get controller, set active user, set objective
            CreateGoalObjectiveController controller = loader.getController();
            controller.setMainController(getMainController());
            controller.setActiveUser(getActiveUser());
            controller.setIsGoal(false);
            
            //Set flag value
            boolean continueFlag = true;
            
            //Check for parent
            Goalmodel g;
            try
            {
                g = ((Goalmodel)((TreeItem<GoalObjectiveDisplayable>)getOngoingGoalTable().getSelectionModel().getSelectedItem()).getParent().getValue());
            }
            catch(NullPointerException npe)
            {
                npe.printStackTrace();
                continueFlag = false;
            }
            
            //Check for parent of parent
            if(continueFlag = true)
            {
                Goalmodel g2;
                try
                {
                    g2 = ((Goalmodel)((TreeItem<GoalObjectiveDisplayable>)getOngoingGoalTable().getSelectionModel().getSelectedItem()).getParent().getParent().getValue());
                }
                catch(NullPointerException npe)
                {
                    //Set selected id equal to the selected goal id
                    controller.setSelectedId(((TreeItem<GoalObjectiveDisplayable>)getOngoingGoalTable().getSelectionModel().getSelectedItem()).getValue().getId());

                    //Instantiate new stage, give it the scene we instantiated, set visible
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                    
                    continueFlag = false;
                }
            }
            
            if(continueFlag = true)
            {
                //If parent of parent exists, it is an objective, so get its parent's goal
                controller.setSelectedId(((TreeItem<GoalObjectiveDisplayable>)getOngoingGoalTable().getSelectionModel().getSelectedItem()).getParent().getValue().getId());

                //Instantiate new stage, give it the scene we instantiated, set visible
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
        catch(LoadException e)
        {
            e.printStackTrace();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    
    @FXML
    private void toCreateEvent(ActionEvent event)
    {
    
    }
    
    @FXML
    private void reconcileGoalObjective(ActionEvent event)
    {
        
    }
    
    public void bindOngoingGoalTableWithButtons()
    {
        getCreateObjectiveButton().disableProperty().bind(Bindings.isEmpty(getOngoingGoalTable().getSelectionModel().getSelectedItems()));
        getReconcileGoalObjectiveButton().disableProperty().bind(Bindings.isEmpty(getOngoingGoalTable().getSelectionModel().getSelectedItems()));
        getCreateEventButton().disableProperty().bind(Bindings.isEmpty(getOngoingGoalTable().getSelectionModel().getSelectedItems()));
    }
}
