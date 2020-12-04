/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usermodel;
import java.net.URL;
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
public class LoginController implements Initializable
{
    @FXML
    private ImageView logoView;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registerButton;
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

    public Button getRegisterButton()
    {
        return registerButton;
    }

    public void setRegisterButton(Button registerButton)
    {
        this.registerButton = registerButton;
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
        //Set top left logo image
        getLogoView().setImage(new Image("/Assets/20_IST311_GoLife_Logo_v1.png"));
        
        //Init EntityManager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
    }

    @FXML
    private void toRegister(ActionEvent event)
    {
        try
        {
            //Load FXML detail view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegisterView.fxml"));
            Parent registerView = loader.load();

            //Instantiate scene, give it the parent we instantiated, also get current scene from event source
            Scene registerScene = new Scene(registerView);
            Scene currentScene = ((Node)event.getSource()).getScene();

            //Instantiate new stage, give it the scene we instantiated, set visible
            Stage stage = (Stage) currentScene.getWindow();
            stage.setScene(registerScene);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void submitLogin(ActionEvent event)
    {
        //Get user from searchByUsernamePassword method using username and password input fields
        Usermodel user = searchByUsernamePassword(getUsernameField().getText(), getPasswordField().getText());
        
        //TODO: eventually call a login function here
        if(user != null)
        {
            System.out.println(user.toString());
        }
    }
    
    private Usermodel searchByUsernamePassword(String username, String password)
    {
        //Instantiate query and set parameters
        Query query = getManager().createNamedQuery("Usermodel.findByUsernamePassword");
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        //Execute query
        List<Usermodel> users = query.getResultList();
        
        if(users.size() < 1)
        {
            //Notify user if user not found
            getFeedbackLabel().setText("User with the given credentials does not exist");
            return null;
        }
        else if(users.size() > 1)
        {
            //Notify user if, for some reason, more than one users share the same username and password
            getFeedbackLabel().setText("More than one user with the given credentials found");
            return null;
        }
        else
        {
            //Otherwise, return the one user found, remove any error messages present
            //TODO: once login function is implemented, remove text in setText() function
            getFeedbackLabel().setText("User found!");
            return users.get(0);
        }
    }
}
