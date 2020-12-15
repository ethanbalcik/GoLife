/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ebalc
 */
@Entity
@Table(name = "EVENTMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Eventmodel.findAll", query = "SELECT e FROM Eventmodel e")
    , @NamedQuery(name = "Eventmodel.findByEventid", query = "SELECT e FROM Eventmodel e WHERE e.eventid = :eventid")
    , @NamedQuery(name = "Eventmodel.findByStartdate", query = "SELECT e FROM Eventmodel e WHERE e.startdate = :startdate")
    , @NamedQuery(name = "Eventmodel.findByDuration", query = "SELECT e FROM Eventmodel e WHERE e.duration = :duration")
    , @NamedQuery(name = "Eventmodel.findByName", query = "SELECT e FROM Eventmodel e WHERE e.name = :name")
    , @NamedQuery(name = "Eventmodel.findByDescription", query = "SELECT e FROM Eventmodel e WHERE e.description = :description")
    , @NamedQuery(name = "Eventmodel.findEventsInRangeByCalendarId", query = "Select e FROM Eventmodel e WHERE e.calendarid = :calendarid AND e.startdate >= :lower AND e.startdate <= :upper")
})
public class Eventmodel implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "EVENTID")
    private Integer eventid;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @Column(name = "DURATION")
    private int duration;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "CALENDARID", referencedColumnName = "CALENDARID")
    @ManyToOne
    private Calendarmodel calendarid;
    @JoinColumn(name = "GOALID", referencedColumnName = "GOALID")
    @ManyToOne
    private Goalmodel goalid;
    @JoinColumn(name = "OBJECTIVEID", referencedColumnName = "OBJECTIVEID")
    @ManyToOne
    private Objectivemodel objectiveid;

    public Eventmodel()
    {
    }

    public Eventmodel(Integer eventid)
    {
        this.eventid = eventid;
    }

    public Eventmodel(Integer eventid, Date startdate, int duration)
    {
        this.eventid = eventid;
        this.startdate = startdate;
        this.duration = duration;
    }

    public Integer getEventid()
    {
        return eventid;
    }

    public void setEventid(Integer eventid)
    {
        this.eventid = eventid;
    }

    public Date getStartdate()
    {
        return startdate;
    }

    public void setStartdate(Date startdate)
    {
        this.startdate = startdate;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Calendarmodel getCalendarid()
    {
        return calendarid;
    }

    public void setCalendarid(Calendarmodel calendarid)
    {
        this.calendarid = calendarid;
    }

    public Goalmodel getGoalid()
    {
        return goalid;
    }

    public void setGoalid(Goalmodel goalid)
    {
        this.goalid = goalid;
    }

    public Objectivemodel getObjectiveid()
    {
        return objectiveid;
    }

    public void setObjectiveid(Objectivemodel objectiveid)
    {
        this.objectiveid = objectiveid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (eventid != null ? eventid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eventmodel))
        {
            return false;
        }
        Eventmodel other = (Eventmodel) object;
        if ((this.eventid == null && other.eventid != null) || (this.eventid != null && !this.eventid.equals(other.eventid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Eventmodel[ eventid=" + eventid + " ]";
    }
}
