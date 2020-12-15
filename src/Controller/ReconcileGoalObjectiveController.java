/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Goalmodel;
import Model.Objectivemodel;
import Model.Usermodel;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class ReconcileGoalObjectiveController implements Initializable
{

    @FXML
    private ImageView logoDisplay;
    @FXML
    private TextField goalObjectiveNameField;
    @FXML
    private DatePicker deadlineField;
    @FXML
    private ColorPicker colorField;
    @FXML
    private CheckBox ongoingCheckBox;
    @FXML
    private CheckBox accomplishedCheckBox;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label feedbackLabel;
    private EntityManager manager;
    private MainController mainController;
    private Usermodel activeUser;
    private boolean isGoal;
    private int selectedId;

    public ImageView getLogoDisplay()
    {
        return logoDisplay;
    }

    public void setLogoDisplay(ImageView logoDisplay)
    {
        this.logoDisplay = logoDisplay;
    }

    public TextField getGoalObjectiveNameField()
    {
        return goalObjectiveNameField;
    }

    public void setGoalObjectiveNameField(TextField goalObjectiveNameField)
    {
        this.goalObjectiveNameField = goalObjectiveNameField;
    }

    public DatePicker getDeadlineField()
    {
        return deadlineField;
    }

    public void setDeadlineField(DatePicker deadlineField)
    {
        this.deadlineField = deadlineField;
    }

    public ColorPicker getColorField()
    {
        return colorField;
    }

    public void setColorField(ColorPicker colorField)
    {
        this.colorField = colorField;
    }

    public CheckBox getOngoingCheckBox()
    {
        return ongoingCheckBox;
    }

    public void setOngoingCheckBox(CheckBox ongoingCheckBox)
    {
        this.ongoingCheckBox = ongoingCheckBox;
    }

    public CheckBox getAccomplishedCheckBox()
    {
        return accomplishedCheckBox;
    }

    public void setAccomplishedCheckBox(CheckBox accomplishedCheckBox)
    {
        this.accomplishedCheckBox = accomplishedCheckBox;
    }

    public TextArea getDescriptionField()
    {
        return descriptionField;
    }

    public void setDescriptionField(TextArea descriptionField)
    {
        this.descriptionField = descriptionField;
    }

    public Button getCancelButton()
    {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton)
    {
        this.cancelButton = cancelButton;
    }

    public Button getUpdateButton()
    {
        return updateButton;
    }

    public void setUpdateButton(Button updateButton)
    {
        this.updateButton = updateButton;
    }

    public Button getDeleteButton()
    {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton)
    {
        this.deleteButton = deleteButton;
    }

    public Label getFeedbackLabel()
    {
        return feedbackLabel;
    }

    public void setFeedbackLabel(Label feedbackLabel)
    {
        this.feedbackLabel = feedbackLabel;
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

    public boolean isIsGoal()
    {
        return isGoal;
    }

    public void setIsGoal(boolean isGoal)
    {
        this.isGoal = isGoal;
    }

    public int getSelectedId()
    {
        return selectedId;
    }

    public void setSelectedId(int selectedId)
    {
        this.selectedId = selectedId;
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
    private void cancelGoalObjective(ActionEvent event)
    {
        //Close window
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
    }

    @FXML
    private void updateGoalObjective(ActionEvent event)
    {
        try
        {
            //Check whether a goal is being updated or an objective
            if(isIsGoal())
            {
                //If goal, find by id
                Goalmodel existingGoal = getManager().find(Goalmodel.class, getSelectedId());
                
                //If not null, update its properties and persist them
                if(existingGoal != null)
                {
                    //Begin transaction
                    getManager().getTransaction().begin();
                    
                    //Update fields
                    existingGoal.setName(getGoalObjectiveNameField().getText());
                    existingGoal.setDescription(getDescriptionField().getText());
                    existingGoal.setDeadline(Date.from(getDeadlineField().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    existingGoal.setRedchannel(getColorField().getValue().getRed());
                    existingGoal.setGreenchannel(getColorField().getValue().getGreen());
                    existingGoal.setBluechannel(getColorField().getValue().getBlue());
                    existingGoal.setAccomplished(getAccomplishedCheckBox().isSelected());
                    if(!getAccomplishedCheckBox().isSelected())
                    {
                        existingGoal.setOngoing(getOngoingCheckBox().isSelected());
                    }
                    else
                    {
                        existingGoal.setOngoing(false);
                    }
                    
                    //End transaction
                    getManager().getTransaction().commit();
                    
                    //Update views
                    getMainController().updateGoalViews();

                    //Close window
                    ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
                }
            }
            else
            {
                //If objective, find by id
                Objectivemodel existingObjective = getManager().find(Objectivemodel.class, getSelectedId());
                
                //If not null, update its properties and persist them
                if(existingObjective != null)
                {
                    //Begin transaction
                    getManager().getTransaction().begin();
                    
                    //Update fields
                    existingObjective.setName(getGoalObjectiveNameField().getText());
                    existingObjective.setDescription(getDescriptionField().getText());
                    existingObjective.setDeadline(Date.from(getDeadlineField().getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    existingObjective.setRedchannel(getColorField().getValue().getRed());
                    existingObjective.setGreenchannel(getColorField().getValue().getGreen());
                    existingObjective.setBluechannel(getColorField().getValue().getBlue());
                    existingObjective.setAccomplished(getAccomplishedCheckBox().isSelected());
                    existingObjective.setOngoing(getOngoingCheckBox().isSelected());
                    
                    //End transaction
                    getManager().getTransaction().commit();
                    
                    //Update views
                    getMainController().updateGoalViews();

                    //Close window
                    ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteGoalObjective(ActionEvent event)
    {
        //Hand off info to DeleteGoalObjectiveAlertController and allow it to handle
        try
        {
            //Load delete goal & objective alert
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DeleteGoalObjectiveAlertView.fxml"));
            Parent deleteGoalObjectiveView = loader.load();
            
            //Instantiate scene, give it the parent we instantiated
            Scene scene = new Scene(deleteGoalObjectiveView);
            
            //Get controller & pass it this controller
            DeleteGoalObjectiveAlertController controller = loader.getController();
            controller.setReconcileGoalObjectiveController(this);
            
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
    
    public void delete()
    {
        try
        {
            //Check if goal
            if(isIsGoal())
            {
                //If goal, find by id
                Goalmodel existingGoal = getManager().find(Goalmodel.class, getSelectedId());
                Collection<Objectivemodel> existingObjectives = existingGoal.getObjectivemodelCollection();
                
                //If goal with selected id is not null, begin database action
                if(existingGoal != null)
                {
                    //Begin transaction
                    getManager().getTransaction().begin();
                    
                    //Remove existing objectives
                    for(Objectivemodel o : existingObjectives)
                    {
                        getManager().remove(o);
                    }
                    
                    //Remove existing goal
                    getManager().remove(existingGoal);
                    
                    //End transaction
                    getManager().getTransaction().commit();
                    
                    //Update views
                    getMainController().updateGoalViews();

                    //Close window
                    ((Stage)getDeleteButton().getParent().getScene().getWindow()).close();
                }
            }
            else
            {
                //If objective, find by id
                Objectivemodel existingObjective = getManager().find(Objectivemodel.class, getSelectedId());
                
                //If objective with selected id is not null, begin database action
                if(existingObjective != null)
                {
                    //Begin transaction
                    getManager().getTransaction().begin();
                    
                    //Remove existing objective
                    getManager().remove(existingObjective);
                    
                    //End transaction
                    getManager().getTransaction().commit();
                    
                    //Update views
                    getMainController().updateGoalViews();

                    //Close window
                    ((Stage)getDeleteButton().getParent().getScene().getWindow()).close();
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
