/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Dailyhealthmodel;
import Model.Usermodel;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 * FXML Controller class
 *
 * @author ebalc
 */
public class DailyHealthController implements Initializable
{
    @FXML
    private TableView<Dailyhealthmodel> macronutrientTable;
    @FXML
    private TableColumn<Dailyhealthmodel, Date> dateColumn;
    @FXML
    private TableColumn<Dailyhealthmodel, Integer> carbohydratesColumn;
    @FXML 
    private TableColumn<Dailyhealthmodel, Integer> fatsColumn;
    @FXML
    private TableColumn<Dailyhealthmodel, Integer> proteinsColumn;
    @FXML
    private TableView<Dailyhealthmodel> moodTable;
    @FXML
    private TableColumn<Dailyhealthmodel, Date> moodDateColumn;
    @FXML
    private TableColumn<Dailyhealthmodel, String> moodColumn;
    @FXML
    private TableView journalTable;
    @FXML
    private TableColumn<Dailyhealthmodel, Date> journalDateColumn;
    @FXML
    private TableColumn<Dailyhealthmodel, String> journalColumn;
    private MainController mainController;
    private Usermodel activeUser;
    private EntityManager manager;

    public TableView<Dailyhealthmodel> getMacronutrientTable()
    {
        return macronutrientTable;
    }

    public void setMacronutrientTable(TableView<Dailyhealthmodel> macronutrientTable)
    {
        this.macronutrientTable = macronutrientTable;
    }

    public TableColumn<Dailyhealthmodel, Date> getDateColumn()
    {
        return dateColumn;
    }

    public void setDateColumn(TableColumn<Dailyhealthmodel, Date> dateColumn)
    {
        this.dateColumn = dateColumn;
    }

    public TableColumn<Dailyhealthmodel, Integer> getCarbohydratesColumn()
    {
        return carbohydratesColumn;
    }

    public void setCarbohydratesColumn(TableColumn<Dailyhealthmodel, Integer> carbohydratesColumn)
    {
        this.carbohydratesColumn = carbohydratesColumn;
    }

    public TableColumn<Dailyhealthmodel, Integer> getFatsColumn()
    {
        return fatsColumn;
    }

    public void setFatsColumn(TableColumn<Dailyhealthmodel, Integer> fatsColumn)
    {
        this.fatsColumn = fatsColumn;
    }

    public TableColumn<Dailyhealthmodel, Integer> getProteinsColumn()
    {
        return proteinsColumn;
    }

    public void setProteinsColumn(TableColumn<Dailyhealthmodel, Integer> proteinsColumn)
    {
        this.proteinsColumn = proteinsColumn;
    }

    public TableView<Dailyhealthmodel> getMoodTable()
    {
        return moodTable;
    }

    public void setMoodTable(TableView<Dailyhealthmodel> moodTable)
    {
        this.moodTable = moodTable;
    }

    public TableColumn<Dailyhealthmodel, Date> getMoodDateColumn()
    {
        return moodDateColumn;
    }

    public void setMoodDateColumn(TableColumn<Dailyhealthmodel, Date> moodDateColumn)
    {
        this.moodDateColumn = moodDateColumn;
    }

    public TableColumn<Dailyhealthmodel, String> getMoodColumn()
    {
        return moodColumn;
    }

    public void setMoodColumn(TableColumn<Dailyhealthmodel, String> moodColumn)
    {
        this.moodColumn = moodColumn;
    }

    public TableView getJournalTable()
    {
        return journalTable;
    }

    public void setJournalTable(TableView journalTable)
    {
        this.journalTable = journalTable;
    }

    public TableColumn<Dailyhealthmodel, Date> getJournalDateColumn()
    {
        return journalDateColumn;
    }

    public void setJournalDateColumn(TableColumn<Dailyhealthmodel, Date> journalDateColumn)
    {
        this.journalDateColumn = journalDateColumn;
    }

    public TableColumn<Dailyhealthmodel, String> getJournalColumn()
    {
        return journalColumn;
    }

    public void setJournalColumn(TableColumn<Dailyhealthmodel, String> journalColumn)
    {
        this.journalColumn = journalColumn;
    }

    public Usermodel getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }

    public MainController getMainController()
    {
        return mainController;
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
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
    private void toCreateEntry(ActionEvent event)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateDailyHealthView.fxml"));
            Parent createDailyHealthView = loader.load();
            
            Scene scene = new Scene(createDailyHealthView);
            
            CreateDailyHealthController controller = loader.getController();
            controller.setDailyHealthController(this);
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
        //Init entity manager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
        
        //Init property value factories
        getDateColumn().setCellValueFactory(new PropertyValueFactory<>("datesubmitted"));
        getCarbohydratesColumn().setCellValueFactory(new PropertyValueFactory<>("carbohydrates"));
        getFatsColumn().setCellValueFactory(new PropertyValueFactory<>("fat"));
        getProteinsColumn().setCellValueFactory(new PropertyValueFactory<>("protein"));
        getMoodDateColumn().setCellValueFactory(new PropertyValueFactory<>("datesubmitted"));
        getMoodColumn().setCellValueFactory(new PropertyValueFactory<>("mood"));
        getJournalDateColumn().setCellValueFactory(new PropertyValueFactory<>("datesubmitted"));
        getJournalColumn().setCellValueFactory(new PropertyValueFactory<>("journalentry"));
    }
    
    public void updateView()
    {
        //Query for daily health entries by calendar id
        Query query = getManager().createNamedQuery("Dailyhealthmodel.findByCalendarid");
        query.setParameter("calendarid", getActiveUser().getCalendarid());
        query.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List<Dailyhealthmodel> entries = query.getResultList();
        
        //Init observable lists
        ObservableList<Dailyhealthmodel> macronutrientEntriesObservable = FXCollections.observableArrayList();
        ObservableList<Dailyhealthmodel> moodEntriesObservable = FXCollections.observableArrayList();
        ObservableList<Dailyhealthmodel> journalEntriesObservable = FXCollections.observableArrayList();
        for(Dailyhealthmodel entry : entries)
        {
            if((entry.getCarbohydrates() != 0) || (entry.getFat() != 0) || (entry.getProtein() != 0))
            {
                macronutrientEntriesObservable.add(entry);
            }
            
            if(!entry.getMood().equals(""))
            {
                moodEntriesObservable.add(entry);
            }
            
            if(!entry.getJournalentry().equals(""))
            {
                journalEntriesObservable.add(entry);
            }
        }
        
        //Add observable list to table columns
        getMacronutrientTable().setItems(macronutrientEntriesObservable);
        getMoodTable().setItems(moodEntriesObservable);
        getJournalTable().setItems(journalEntriesObservable);
    }
}