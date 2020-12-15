/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usermodel;
import Model.Dailyhealthmodel;
import java.awt.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Brett Randby
 */
public class CreateDailyHealthController implements Initializable {
    
    

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField carbField;

    @FXML
    private TextField fatField;

    @FXML
    private TextField proteinField;

    @FXML
    private TextField moodField;

    @FXML
    private TextArea journalField;

    @FXML
    private Button cancelEntry;

    @FXML
    private Button submitEntry;
    
    private MainController mainController;
    private Usermodel activeUser;
    private Dailyhealthmodel healthModel;

    public Button getCancelEntry() {
        return cancelEntry;
    }

    public void setCancelEntry(Button cancelEntry) {
        this.cancelEntry = cancelEntry;
    }

    public Button getSubmitEntry() {
        return submitEntry;
    }

    public void setSubmitEntry(Button submitEntry) {
        this.submitEntry = submitEntry;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Usermodel getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser) {
        this.activeUser = activeUser;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public void setResources(ResourceBundle resources) {
        this.resources = resources;
    }

    public URL getLocation() {
        return location;
    }

    public void setLocation(URL location) {
        this.location = location;
    }

    public TextField getCarbField() {
        return carbField;
    }

    public void setCarbField(TextField carbField) {
        this.carbField = carbField;
    }

    public TextField getFatField() {
        return fatField;
    }

    public void setFatField(TextField fatField) {
        this.fatField = fatField;
    }

    public TextField getProteinField() {
        return proteinField;
    }

    public void setProteinField(TextField proteinField) {
        this.proteinField = proteinField;
    }

    public TextField getMoodField() {
        return moodField;
    }

    public void setMoodField(TextField moodField) {
        this.moodField = moodField;
    }

    public TextArea getJournalField() {
        return journalField;
    }

    public void setJournalField(TextArea journalField) {
        this.journalField = journalField;
    }
    
    public void cancelDailyHealth(ActionEvent event){
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
    }
    
    public void submitDailyHealth(ActionEvent event) {
        String carbs = carbField.getText();
        String fats = fatField.getText();
        String proteins = proteinField.getText();
        String mood = moodField.getText();
        String journal = journalField.getText();
        date = java.time.LocalDateTime;
        
        
        int carb = Integer.valueOf(carbs);
        int fat = Integer.valueOf(fats);
        int protein = Integer.valueOf(proteins);
        
        
        healthModel.setCarbohydrates(carb);
        healthModel.setFat(fat);
        healthModel.setProtein(protein);
        healthModel.setMood(mood);
        healthModel.setJournalentry(journal);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
