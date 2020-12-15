/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Calendarmodel;
import Model.Usermodel;
import Model.Dailyhealthmodel;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Brett Randby
 */
public class CreateDailyHealthController implements Initializable 
{
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ComboBox<Integer> carbField;
    @FXML
    private ComboBox<Integer> fatField;
    @FXML
    private ComboBox<Integer> proteinField;
    @FXML
    private TextField moodField;
    @FXML
    private TextArea journalField;
    @FXML
    private Label feedbackLabel;
    @FXML
    private Button submitEntry;
    private DailyHealthController dailyHealthController;
    private Usermodel activeUser;
    private EntityManager manager;

    public Button getSubmitEntry() 
    {
        return submitEntry;
    }

    public void setSubmitEntry(Button submitEntry) 
    {
        this.submitEntry = submitEntry;
    }

    public DailyHealthController getDailyHealthController()
    {
        return dailyHealthController;
    }

    public void setDailyHealthController(DailyHealthController dailyHealthController)
    {
        this.dailyHealthController = dailyHealthController;
    }

    public Usermodel getActiveUser() 
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser) 
    {
        this.activeUser = activeUser;
    }

    public ResourceBundle getResources() 
    {
        return resources;
    }

    public void setResources(ResourceBundle resources) 
    {
        this.resources = resources;
    }

    public URL getLocation() 
    {
        return location;
    }

    public void setLocation(URL location) 
    {
        this.location = location;
    }

    public ComboBox<Integer> getCarbField()
    {
        return carbField;
    }

    public void setCarbField(ComboBox<Integer> carbField)
    {
        this.carbField = carbField;
    }

    public ComboBox<Integer> getFatField()
    {
        return fatField;
    }

    public void setFatField(ComboBox<Integer> fatField)
    {
        this.fatField = fatField;
    }

    public ComboBox<Integer> getProteinField()
    {
        return proteinField;
    }

    public void setProteinField(ComboBox<Integer> proteinField)
    {
        this.proteinField = proteinField;
    }

    public TextField getMoodField() 
    {
        return moodField;
    }

    public void setMoodField(TextField moodField) 
    {
        this.moodField = moodField;
    }

    public TextArea getJournalField() 
    {
        return journalField;
    }

    public void setJournalField(TextArea journalField)
    {
        this.journalField = journalField;
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
    
    @FXML
    private void cancelDailyHealth(ActionEvent event)
    {
        //Close window
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
    }
    
    @FXML
    private void submitDailyHealth(ActionEvent event) 
    {
        try
        {
            //Init some input properties
            int carbs = 0;
            int fats = 0;
            int proteins = 0;
            
            //Check for exceptions
            if(!getCarbField().getSelectionModel().isEmpty())
            {
                carbs = getCarbField().getValue();
            }
            
            if(!getFatField().getSelectionModel().isEmpty())
            {
                fats = getFatField().getValue();
            }
            
            if(!getProteinField().getSelectionModel().isEmpty())
            {
                proteins = getProteinField().getValue();
            }
            
            String mood = getMoodField().getText();
            String journal = getJournalField().getText();
            
            //Create DailyHealth given the input information
            Dailyhealthmodel dailyHealth = createDailyHealthEntry(carbs, fats, proteins, mood, journal);

            //Check if transaction complete by querying for goal
            Query query = getManager().createNamedQuery("Dailyhealthmodel.findByDailyhealthid");
            query.setParameter("dailyhealthid", dailyHealth.getDailyhealthid());
            int resultSize = query.getResultList().size();
            if(resultSize == 1)
            {
                //Update view
                getDailyHealthController().updateView();
                
                //Transaction was successful, close window
                ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
            }
            else
            {
                //Notify user
                getFeedbackLabel().setText("Daily health entry was unsuccessful");
            }
        }
        catch(Exception e)
        {
            //Notify user
            getFeedbackLabel().setText("Daily health entry was unsuccessful");
            e.printStackTrace();
        }
    }
    
    private Dailyhealthmodel createDailyHealthEntry(int carbs, int fats, int proteins, String mood, String journal)
    {
        try
        {
            //Get activeUser calendarId
            Calendarmodel calendar = getActiveUser().getCalendarid();

            //Instantiate daily health object
            Dailyhealthmodel dailyHealth = new Dailyhealthmodel();

            //Set its properties
            dailyHealth.setCarbohydrates(carbs);
            dailyHealth.setFat(fats);
            dailyHealth.setProtein(proteins);
            dailyHealth.setMood(mood);
            dailyHealth.setJournalentry(journal);

            //Generate additional properties
            dailyHealth.setDatesubmitted(new Date());
            dailyHealth.setCalendarid(calendar);

            //Begin transaction
            getManager().getTransaction().begin();

            //Persist goal
            getManager().persist(dailyHealth);

            //End transaction
            getManager().getTransaction().commit();

            return dailyHealth;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //Init entity manager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
        
        //Load comboboxes with values
        loadComboboxes();
    }    
    
    private void loadComboboxes()
    {
        //Generate values
        ObservableList<Integer> comboboxValues = FXCollections.observableArrayList();
        for(int i = 1; i <= 300; i++)
        {
            comboboxValues.add(i);
        }
        
        //Load fields with itmes
        getCarbField().setItems(comboboxValues);
        getFatField().setItems(comboboxValues);
        getProteinField().setItems(comboboxValues);
    }
}
