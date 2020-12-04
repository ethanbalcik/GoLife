/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usermodel;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
public class RegisterController implements Initializable
{

    @FXML
    private ImageView logoView;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button submitButton;
    @FXML
    private Label feedbackLabel;
    private EntityManager manager;

    public ImageView getLogoView()
    {
        return logoView;
    }

    public void setLogoView(ImageView logoView)
    {
        this.logoView = logoView;
    }

    public TextField getFirstNameField()
    {
        return firstNameField;
    }

    public void setFirstNameField(TextField firstNameField)
    {
        this.firstNameField = firstNameField;
    }

    public TextField getLastNameField()
    {
        return lastNameField;
    }

    public void setLastNameField(TextField lastNameField)
    {
        this.lastNameField = lastNameField;
    }

    public DatePicker getDateOfBirthField()
    {
        return dateOfBirthField;
    }

    public void setDateOfBirthField(DatePicker dateOfBirthField)
    {
        this.dateOfBirthField = dateOfBirthField;
    }

    public TextField getEmailField()
    {
        return emailField;
    }

    public void setEmailField(TextField emailField)
    {
        this.emailField = emailField;
    }

    public TextField getUsernameField()
    {
        return usernameField;
    }

    public void setUsernameField(TextField usernameField)
    {
        this.usernameField = usernameField;
    }

    public PasswordField getPasswordField()
    {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField)
    {
        this.passwordField = passwordField;
    }

    public Button getLoginButton()
    {
        return loginButton;
    }

    public void setLoginButton(Button loginButton)
    {
        this.loginButton = loginButton;
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
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //Set logo image
        getLogoView().setImage(new Image("/Assets/20_IST311_GoLife_Logo_v1.png"));
        
        //Init EntityManager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
    }    

    @FXML
    private void toLogin(ActionEvent event)
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

    @FXML
    private void submitRegister(ActionEvent event)
    {
        //Transform date of birth field information to useful information for storage
        LocalDate localDate = getDateOfBirthField().getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date dateofbirth = Date.from(instant);
        
        //Create user given the input information
        createUser(getFirstNameField().getText(), getLastNameField().getText(), dateofbirth, getEmailField().getText(), getUsernameField().getText(), getPasswordField().getText());
    }
    
    private void createUser(String firstname, String lastname, Date dateofbirth, String email, String username, String password)
    {
        try
        {
            //Search for next id in sequence
            Query query = getManager().createNamedQuery("Usermodel.findAll");
            List<Usermodel> users = query.getResultList();
            int userId = 1;
            
            //Change value if there are existing users
            if(users.size() != 0)
            {
                //Usermodel.findAll query is written to sort in descending order
                //Increment largest userid by 1
                userId = users.get(0).getUserid() + 1;
            }
            
            //Instantiate usermodel and set parameters
            Usermodel user = new Usermodel();
            user.setUserid(userId);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setDateofbirth(dateofbirth);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            
            //Check if user exists & successfully created
            if(!searchUser(username))
            {
                //Begin transaction
                getManager().getTransaction().begin();

                //Persist user and end transaction
                getManager().persist(user);
                getManager().getTransaction().commit();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private boolean searchUser(String username)
    {
        //Instantiate flag value
        boolean userFound = false;
        
        //Create and execute query to find user by all input fields
        Query query = getManager().createNamedQuery("Usermodel.findByUsername");
        query.setParameter("username", username);
        List<Usermodel> users = query.getResultList();
        
        //Check result list size, if users found with same information, user should not be created
        switch(users.size())
        {
            case 0:
                //TODO: remove setText once register function implemented
                getFeedbackLabel().setText("User created successfully!");
                break;
                
            default:
                //Notify user if, for some reason, more than one user exists with their input username
                getFeedbackLabel().setText("Username already exists");
                
                //Update flag value
                userFound = true;
                break;
        }
        
        //Return flag value
        return userFound;
    }
}
