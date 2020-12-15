/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Calendarmodel;
import Model.Eventmodel;
import Model.Goalmodel;
import Model.Objectivemodel;
import Model.Usermodel;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class CreateEventController implements Initializable
{

    @FXML
    private ImageView logoDisplay;
    @FXML
    private TextField eventNameField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private ComboBox<LocalTime> startTimeField;
    @FXML
    private ComboBox<Integer> durationField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label feedbackLabel;
    private CalendarController calendarController;
    private Usermodel activeUser;
    private EntityManager manager;
    private boolean affiliated;
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

    public TextField getEventNameField()
    {
        return eventNameField;
    }

    public void setEventNameField(TextField eventNameField)
    {
        this.eventNameField = eventNameField;
    }

    public DatePicker getStartDateField()
    {
        return startDateField;
    }

    public void setStartDateField(DatePicker startDateField)
    {
        this.startDateField = startDateField;
    }

    public ComboBox<LocalTime> getStartTimeField()
    {
        return startTimeField;
    }

    public void setStartTimeField(ComboBox<LocalTime> startTimeField)
    {
        this.startTimeField = startTimeField;
    }

    public ComboBox<Integer> getDurationField()
    {
        return durationField;
    }

    public void setDurationField(ComboBox<Integer> durationField)
    {
        this.durationField = durationField;
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

    public CalendarController getCalendarController()
    {
        return calendarController;
    }

    public void setCalendarController(CalendarController calendarController)
    {
        this.calendarController = calendarController;
    }

    public Usermodel getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }

    public EntityManager getManager()
    {
        return manager;
    }

    public void setManager(EntityManager manager)
    {
        this.manager = manager;
    }

    public boolean isAffiliated()
    {
        return affiliated;
    }

    public void setAffiliated(boolean affiliated)
    {
        this.affiliated = affiliated;
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
        
        //Setup combobox fields
        loadComboboxes();
    }    

    @FXML
    private void cancelEvent(ActionEvent event)
    {
        //Close window
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
    }

    @FXML
    private void submitEvent(ActionEvent event)
    {
        LocalDate localDate = getStartDateField().getValue();
        Instant instant = localDate.atTime(getStartTimeField().getValue()).toInstant(OffsetDateTime.now().getOffset());
        Date date = Date.from(instant);

        Eventmodel e;
        
        if(!isAffiliated())
        {
            e = createEvent(getEventNameField().getText(), date, getDurationField().getValue(), getDescriptionField().getText());
        }
        else
        {
            e = createEvent(getEventNameField().getText(), date, getDurationField().getValue(), getDescriptionField().getText(), isIsGoal());
        }
        
        //Check if transaction complete by querying for goal
        Query query = getManager().createNamedQuery("Eventmodel.findByEventid");
        query.setParameter("eventid", e.getEventid());
        int resultSize = query.getResultList().size();
        if(resultSize == 1)
        {
            //Update view
            getCalendarController().generateMonth(false, false);
            
            //Close window
            ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
        }
        else
        {
            //Notify user
            getFeedbackLabel().setText("Goal was not saved");
        }
    }
    
    public Eventmodel createEvent(String name, Date startDate, int duration, String description)
    {
        try
        {
            //Query for active user's calendar
            Query query = getManager().createNamedQuery("Calendarmodel.findByCalendarid");
            query.setParameter("calendarid", getActiveUser().getCalendarid().getCalendarid());
            Calendarmodel calendar = (Calendarmodel)query.getSingleResult();
            
            //Instantiate goal and set attributes using input params
            Eventmodel event = new Eventmodel();
            event.setName(name);
            event.setDescription(description);
            event.setStartdate(startDate);
            event.setDuration(duration);
            
            //Generate additional attributes
            event.setCalendarid(calendar);
            
            //Begin transaction
            getManager().getTransaction().begin();

            //Persist goal
            getManager().persist(event);

            //End transaction
            getManager().getTransaction().commit();
            
            return event;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public Eventmodel createEvent(String name, Date startDate, int duration, String description, boolean goal)
    {
        try
        {
            //Query for active user's calendar
            Query query = getManager().createNamedQuery("Calendarmodel.findByCalendarid");
            query.setParameter("calendarid", getActiveUser().getCalendarid().getCalendarid());
            Calendarmodel calendar = (Calendarmodel)query.getSingleResult();
            
            //Instantiate goal and set attributes using input params
            Eventmodel event = new Eventmodel();
            event.setName(name);
            event.setDescription(description);
            event.setStartdate(startDate);
            event.setDuration(duration);
            
            //Generate additional attributes
            event.setCalendarid(calendar);
            
            //if is goal, query for goal affiliation, otherwise, query for objective affiliation
            Query query2;
            if(goal)
            {
                query2 = getManager().createNamedQuery("Goalmodel.findByGoalid");
                query2.setParameter("goalid", getSelectedId());
                Goalmodel g = (Goalmodel)query2.getSingleResult();
                event.setGoalid(g);
            }
            else
            {
                query2 = getManager().createNamedQuery("Objectivemodel.findByObjectiveid");
                query2.setParameter("objectiveid", getSelectedId());
                Objectivemodel o = (Objectivemodel)query2.getSingleResult();
                event.setObjectiveid(o);
            }
            
            //Begin transaction
            getManager().getTransaction().begin();

            //Persist goal
            getManager().persist(event);

            //End transaction
            getManager().getTransaction().commit();
            
            return event;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private void loadComboboxes()
    {
        //Instantiate observable lists
        ObservableList<LocalTime> times = FXCollections.observableArrayList();
        ObservableList<Integer> durations = FXCollections.observableArrayList();
        
        //Get t=0
        LocalTime minimum = LocalTime.MIN;
        
        //l00p
        for(int i = 0; i < 24; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                //Calculate a total of 96 local times (15 minute intervals) and add to observable list
                LocalTime time = minimum.plusHours(i);
                time = time.plusMinutes(j * 15);
                times.add(time);
                
                //Calculate a total of 96 durations (15 minute intervals) and add to observable list
                int minutes = (j * 15) + (i * 60);
                durations.add(minutes);
            }
        }
        
        //Set items
        getStartTimeField().setItems(times);
        getDurationField().setItems(durations);
    }
}
