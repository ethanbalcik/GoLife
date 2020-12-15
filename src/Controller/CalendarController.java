/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Eventmodel;
import Model.Usermodel;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
public class CalendarController implements Initializable
{
    @FXML
    private GridPane calendarGrid;
    @FXML
    private Label monthLabel;
    private ArrayList<Label> dayLabels;
    private ArrayList<ListView> eventLists;
    private ObservableList<String> eventNamesObservable;
    private Usermodel activeUser;
    private GregorianCalendar calendar;
    private EntityManager manager;

    public GridPane getCalendarGrid()
    {
        return calendarGrid;
    }

    public void setCalendarGrid(GridPane calendarGrid)
    {
        this.calendarGrid = calendarGrid;
    }

    public Label getMonthLabel()
    {
        return monthLabel;
    }

    public void setMonthLabel(Label monthLabel)
    {
        this.monthLabel = monthLabel;
    }

    public ArrayList<Label> getDayLabels()
    {
        return dayLabels;
    }

    public void setDayLabels(ArrayList<Label> dayLabels)
    {
        this.dayLabels = dayLabels;
    }

    public ArrayList<ListView> getEventLists()
    {
        return eventLists;
    }

    public void setEventLists(ArrayList<ListView> eventLists)
    {
        this.eventLists = eventLists;
    }

    public ObservableList<String> getEventNamesObservable()
    {
        return eventNamesObservable;
    }

    public void setEventNamesObservable(ObservableList<String> eventNamesObservable)
    {
        this.eventNamesObservable = eventNamesObservable;
    }

    public Usermodel getActiveUser()
    {
        return activeUser;
    }

    public void setActiveUser(Usermodel activeUser)
    {
        this.activeUser = activeUser;
    }

    public GregorianCalendar getCalendar()
    {
        return calendar;
    }

    public void setCalendar(GregorianCalendar calendar)
    {
        this.calendar = calendar;
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
        //Init entity manager
        setManager((EntityManager) Persistence.createEntityManagerFactory("GoLifePU").createEntityManager());
        
        //Add all Labels and ListViews to ArrayLists
        setViewLists();
        
        //Set current date
        setCalendar(new GregorianCalendar());
        
        //Display current month
        generateMonth(false, false);
    }
    
    @FXML
    private void navigateBackward(ActionEvent event)
    {
        generateMonth(true, false);
    }
    
    @FXML
    private void navigateForward(ActionEvent event)
    {
        generateMonth(true, true);
    }
    
    @FXML
    private void toCreateEvent(ActionEvent event)
    {
        try
        {
            //Load create goal & objective view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateEventView.fxml"));
            Parent createGoalObjectiveView = loader.load();
            
            //Instantiate scene, give it the parent we instantiated
            Scene scene = new Scene(createGoalObjectiveView);
            
            //Get controller, set active user, set objective
            CreateEventController controller = loader.getController();
            controller.setCalendarController(this);
            controller.setActiveUser(getActiveUser());
            controller.setAffiliated(false);
            controller.setIsGoal(false);
            
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
    
    private void setViewLists()
    {
        //Init view lists
        setDayLabels(new ArrayList());
        setEventLists(new ArrayList());
        
        //Get nodes, loop through, and add to list
        ObservableList<Node> children = getCalendarGrid().getChildren();
        for(int i = 7; i < children.size() - 1; i++)
        {
            VBox v = (VBox)children.get(i);
            ObservableList<Node> vChildren = v.getChildren();
            getDayLabels().add((Label)vChildren.get(0));
            getEventLists().add((ListView)vChildren.get(1));
        }
    }
    
    public void generateMonth(boolean isMovement, boolean isForward)
    {
        GregorianCalendar previousMonthCalendar;
        GregorianCalendar thisMonthCalendar;
        
        if(isMovement)
        {
            if(isForward)
            {
                //First set previous month calendar to current month & year
                previousMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH));

                //Roll month forward
                getCalendar().roll(Calendar.MONTH, true);

                //Check if jumped to January while moving forward
                if(getCalendar().get(Calendar.MONTH) == Calendar.JANUARY)
                {
                    getCalendar().roll(Calendar.YEAR, true);
                }

                //Get new calendar to set day labels and events
                thisMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().getActualMinimum(Calendar.DAY_OF_MONTH));
            }
            else
            {
                //First roll month backward
                getCalendar().roll(Calendar.MONTH, false);

                //Set this month calendar
                thisMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().getActualMinimum(Calendar.DAY_OF_MONTH));
                
                //Check if jumped to December while moving backward
                switch(getCalendar().get(Calendar.MONTH))
                {
                    case Calendar.DECEMBER:
                        //Roll year backward
                        getCalendar().roll(Calendar.YEAR, false);
                        
                        //Roll this month calendar year backward
                        thisMonthCalendar.roll(Calendar.YEAR, false);

                        //Set previous month calendar
                        previousMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().get(Calendar.DAY_OF_MONTH));
                        previousMonthCalendar.roll(Calendar.MONTH, false);
                        previousMonthCalendar.set(Calendar.DATE, previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        break;
                        
                    case Calendar.JANUARY:
                        //Set previous month calendar and roll previous month's year back
                        previousMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().get(Calendar.DAY_OF_MONTH));
                        previousMonthCalendar.roll(Calendar.MONTH, false);
                        previousMonthCalendar.roll(Calendar.YEAR, false);
                        previousMonthCalendar.set(Calendar.DATE, previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        break;
                        
                    default:
                        //Just set previous month calendar
                        previousMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().get(Calendar.DAY_OF_MONTH));
                        previousMonthCalendar.roll(Calendar.MONTH, false);
                        previousMonthCalendar.set(Calendar.DATE, previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                        break;
                }
            }
        }
        else
        {
            //Set this month calendar to the first day of the current month
            thisMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().getActualMinimum(Calendar.DAY_OF_MONTH));
            
            //Check if previoius month is last year
            if(thisMonthCalendar.get(Calendar.MONTH) == Calendar.JANUARY)
            {
                //Set previous month calendar and roll year back
                previousMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().get(Calendar.DAY_OF_MONTH));
                previousMonthCalendar.roll(Calendar.MONTH, false);
                previousMonthCalendar.roll(Calendar.YEAR, false);
                previousMonthCalendar.set(Calendar.DATE, previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
            else
            {
                //Just set previous month calendar
                previousMonthCalendar = new GregorianCalendar(getCalendar().get(Calendar.YEAR), getCalendar().get(Calendar.MONTH), getCalendar().get(Calendar.DAY_OF_MONTH));
                previousMonthCalendar.roll(Calendar.MONTH, false);
                previousMonthCalendar.set(Calendar.DATE, previousMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        }
        
        //Send calendars to displayMonth function
        displayMonth(previousMonthCalendar, thisMonthCalendar);
    }
    
    private void displayMonth(GregorianCalendar previousMonthCalendar, GregorianCalendar thisMonthCalendar)
    {
        //Set month label
        getMonthLabel().setText(thisMonthCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + thisMonthCalendar.get(Calendar.YEAR));
        
        //Set previous month day labels
        for(int i = 0; i < thisMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1; i++)
        {
            getDayLabels().get(i).setText(String.format("%d", previousMonthCalendar.get(Calendar.DAY_OF_MONTH) + i + 2 - thisMonthCalendar.get(Calendar.DAY_OF_WEEK)));
            getEventLists().get(i).setVisible(false);
        }
        
        //Query for events in month
        Query q = getManager().createNamedQuery("Eventmodel.findEventsInRange");
        GregorianCalendar lower = new GregorianCalendar(thisMonthCalendar.get(Calendar.YEAR), thisMonthCalendar.get(Calendar.MONTH), thisMonthCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        GregorianCalendar upper = new GregorianCalendar(thisMonthCalendar.get(Calendar.YEAR), thisMonthCalendar.get(Calendar.MONTH), thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        q.setParameter("lower", lower.getTime());
        q.setParameter("upper", upper.getTime());
        q.setHint(QueryHints.REFRESH, HintValues.TRUE);
        List<Eventmodel> events = q.getResultList();
        
        //Set current month day labels
        for(int i = thisMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1; i < thisMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1 + thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
        {
            getDayLabels().get(i).setText(String.format("%d", i + 2 - thisMonthCalendar.get(Calendar.DAY_OF_WEEK)));
            GregorianCalendar eventCalendar = new GregorianCalendar(thisMonthCalendar.get(Calendar.YEAR), thisMonthCalendar.get(Calendar.MONTH), i + 2 - thisMonthCalendar.get(Calendar.DAY_OF_WEEK));
            ObservableList<String> eventsDisplayable = FXCollections.observableArrayList();
            for(Eventmodel e : events)
            {
                if(e.getStartdate().compareTo(eventCalendar.getTime()) == 0)
                {
                    eventsDisplayable.add(e.getName());
                }
            }
            getEventLists().get(i).setItems(eventsDisplayable);
            if(!getEventLists().get(i).isVisible())
            {
                getEventLists().get(i).setVisible(true);
            }
        }
        
        //Set following month day labels
        for(int i = thisMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1 + thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); i < getDayLabels().size(); i++)
        {
            getDayLabels().get(i).setText(String.format("%d", i + 1 - (thisMonthCalendar.get(Calendar.DAY_OF_WEEK) - 1 + thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))));
            getEventLists().get(i).setVisible(false);
        }
    }
}
