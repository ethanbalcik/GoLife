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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class DailyHealthController implements Initializable
{
    private Usermodel activeUser;

    public Usermodel getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }
    
    @FXML
    private Button createEntry;
    
    private MainController mainController;
    
    public Button getCreateEntry() {
        return createEntry;
    }

    public void setCreateEntry(Button createEntry) {
        this.createEntry = createEntry;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    
    
    
    
    @FXML
    private void toCreateEntry(ActionEvent event) {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateDailyHealthView.fxml"));
            Parent createDailyHealthView = loader.load();
            
            Scene scene = new Scene(createDailyHealthView);
            
            CreateDailyHealthController controller = loader.getController();
            controller.setMainController(getMainController());
            controller.setActiveUser(getActiveUser());
            
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();  
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         assert createEntry != null : "fx:id=\"createEntry\" was not injected: check your FXML file 'DailyHealthView.fxml'.";
    }
}
