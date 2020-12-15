/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class DeleteGoalObjectiveAlertController implements Initializable
{

    @FXML
    private Button cancelButton;
    @FXML
    private Button deleteButton;
    private ReconcileGoalObjectiveController reconcileGoalObjectiveController;

    public Button getCancelButton()
    {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton)
    {
        this.cancelButton = cancelButton;
    }

    public Button getDeleteButton()
    {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton)
    {
        this.deleteButton = deleteButton;
    }

    public ReconcileGoalObjectiveController getReconcileGoalObjectiveController()
    {
        return reconcileGoalObjectiveController;
    }

    public void setReconcileGoalObjectiveController(ReconcileGoalObjectiveController reconcileGoalObjectiveController)
    {
        this.reconcileGoalObjectiveController = reconcileGoalObjectiveController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void cancelGoalObjective(ActionEvent event)
    {
        //Close window
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
    }

    @FXML
    private void deleteGoalObjective(ActionEvent event)
    {
        
        //Close window and then run delete function
        ((Stage)((Node)event.getSource()).getParent().getScene().getWindow()).close();
        getReconcileGoalObjectiveController().delete();
    }
}
