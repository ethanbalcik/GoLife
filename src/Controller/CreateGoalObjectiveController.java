/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Calendarmodel;
import Model.Goalmodel;
import Model.Objectivemodel;
import Model.Usermodel;
import javafx.scene.paint.Color;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class CreateGoalObjectiveController implements Initializable
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
    private TextArea descriptionField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
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

    public Button getSubmitButton()
    {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton)
    {
        this.submitButton = submitButton;
    }

    public Label getFeedbackLabel()
    {
        return feedbackLabel;
    }

    public void setFeedbackLabel(Label feedbackLabel)
    {
        this.feedbackLabel = feedbackLabel;
    }

    public EntityManager getManager()
    {
        return manager;
    }

    public void setManager(EntityManager manager)
    {
        this.manager = manager;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Init entity manager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
    }    

    @FXML
    private void cancelGoalObjective(ActionEvent event)
    {
        //Close window
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
    }

    @FXML
    private void submitGoalObjective(ActionEvent event)
    {
        //Set flag value
        boolean transactionComplete = false;
        
        //Transform deadline field information to useful information for storage
        LocalDate localDate = getDeadlineField().getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date deadline = Date.from(instant);
        
        //Check if goal or objective
        if(isIsGoal())
        {
            //Create goal given the input information
            Goalmodel goal = createGoal(getGoalObjectiveNameField().getText(), deadline, getColorField().getValue(), getDescriptionField().getText());
            
            //Check if transaction complete by querying for goal
            Query query = getManager().createNamedQuery("Goalmodel.findByGoalid");
            query.setParameter("goalid", goal.getGoalid());
            int resultSize = query.getResultList().size();
            if(resultSize == 1)
            {
                //Transaction was successful
                transactionComplete = true;
            }
            else
            {
                //Notify user
                getFeedbackLabel().setText("Goal was not saved");
            }
        }
        else
        {
            //Create objective givent the input information
            Objectivemodel objective = createObjective(getGoalObjectiveNameField().getText(), deadline, getColorField().getValue(), getDescriptionField().getText());
            
            //Check if transaction complete by querying for goal
            Query query = getManager().createNamedQuery("Objectivemodel.findByObjectiveid");
            query.setParameter("objectiveid", objective.getObjectiveid());
            int resultSize = query.getResultList().size();
            if(resultSize == 1)
            {
                //Transaction was successful
                transactionComplete = true;
            }
            else
            {
                //Notify user
                getFeedbackLabel().setText("Goal was not saved");
            }
        }
        
        if(transactionComplete)
        {
            //Update views
            getMainController().updateGoalViews();
            
            //Close window
            ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
        }
    }
    
    private Goalmodel createGoal(String name, Date deadline, Color color, String description)
    {
        try
        {
            //Query for active user's calendar
            Query query = getManager().createNamedQuery("Calendarmodel.findByCalendarid");
            query.setParameter("calendarid", getActiveUser().getCalendarid().getCalendarid());
            Calendarmodel calendar = (Calendarmodel)query.getSingleResult();
            
            //Instantiate goal and set attributes using input params
            Goalmodel goal = new Goalmodel();
            goal.setName(name);
            goal.setDeadline(deadline);
            goal.setRedchannel(color.getRed());
            goal.setGreenchannel(color.getGreen());
            goal.setBluechannel(color.getBlue());
            goal.setDescription(description);
            
            //Generate additional attributes
            goal.setCalendarid(calendar);
            goal.setOngoing(true);
            goal.setAccomplished(false);
            goal.setEventmodelCollection(new ArrayList());
            
            //Begin transaction
            getManager().getTransaction().begin();

            //Persist goal
            getManager().persist(goal);

            //End transaction
            getManager().getTransaction().commit();
            
            return goal;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private Objectivemodel createObjective(String name, Date deadline, Color color, String description)
    {
        try
        {
            //Query for selected goal
            Query query = getManager().createNamedQuery("Goalmodel.findByGoalid");
            query.setParameter("goalid", getSelectedId());
            Goalmodel selectedGoal = (Goalmodel)query.getSingleResult();
            
            //Instantiate goal and set attributes using input params
            Objectivemodel objective = new Objectivemodel();
            objective.setName(name);
            objective.setDeadline(deadline);
            objective.setRedchannel(color.getRed());
            objective.setGreenchannel(color.getGreen());
            objective.setBluechannel(color.getBlue());
            objective.setDescription(description);
            
            //Generate additional attributes
            objective.setGoalid(selectedGoal);
            objective.setOngoing(true);
            objective.setAccomplished(false);
            
            //Begin transaction
            getManager().getTransaction().begin();

            //Persist goal
            getManager().persist(objective);

            //End transaction
            getManager().getTransaction().commit();
            
            return objective;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
