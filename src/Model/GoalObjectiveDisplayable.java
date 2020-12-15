/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author ebalc
 */
public interface GoalObjectiveDisplayable
{
    //ID property
    public int getId();
    
    //Name property
    public String getName();
    
    //Description property
    public String getDescription();
    
    //Deadline property
    public Date getDeadline();
    
    //Ongoing property
    public Boolean getOngoing();
    
    //Accomplished property
    public Boolean getAccomplished();
    
    //Color channels
    public Double getRedchannel();
    public Double getGreenchannel();
    public Double getBluechannel();
    
    //Objectives property
    public Collection<Objectivemodel> getObjectivemodelCollection();
}
