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
    , @NamedQuery(name = "Eventmodel.findByStarttime", query = "SELECT e FROM Eventmodel e WHERE e.starttime = :starttime")
    , @NamedQuery(name = "Eventmodel.findByDuration", query = "SELECT e FROM Eventmodel e WHERE e.duration = :duration")
    , @NamedQuery(name = "Eventmodel.findByName", query = "SELECT e FROM Eventmodel e WHERE e.name = :name")
    , @NamedQuery(name = "Eventmodel.findByDescription", query = "SELECT e FROM Eventmodel e WHERE e.description = :description")
    , @NamedQuery(name = "Eventmodel.findByColor", query = "SELECT e FROM Eventmodel e WHERE e.color = :color")
})
public class Eventmodel implements Serializable
{

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "REDCHANNEL")
    private Double redchannel;
    @Column(name = "GREENCHANNEL")
    private Boolean greenchannel;
    @Column(name = "BLUECHANNEL")
    private Double bluechannel;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EVENTID")
    private Integer eventid;
    @Basic(optional = false)
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Date startdate;
    @Basic(optional = false)
    @Column(name = "STARTTIME")
    @Temporal(TemporalType.TIME)
    private Date starttime;
    @Basic(optional = false)
    @Column(name = "DURATION")
    private int duration;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "COLOR")
    private BigInteger color;
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

    public Eventmodel(Integer eventid, Date startdate, Date starttime, int duration)
    {
        this.eventid = eventid;
        this.startdate = startdate;
        this.starttime = starttime;
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

    public Date getStarttime()
    {
        return starttime;
    }

    public void setStarttime(Date starttime)
    {
        this.starttime = starttime;
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

    public BigInteger getColor()
    {
        return color;
    }

    public void setColor(BigInteger color)
    {
        this.color = color;
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

    public Double getRedchannel()
    {
        return redchannel;
    }

    public void setRedchannel(Double redchannel)
    {
        this.redchannel = redchannel;
    }

    public Boolean getGreenchannel()
    {
        return greenchannel;
    }

    public void setGreenchannel(Boolean greenchannel)
    {
        this.greenchannel = greenchannel;
    }

    public Double getBluechannel()
    {
        return bluechannel;
    }

    public void setBluechannel(Double bluechannel)
    {
        this.bluechannel = bluechannel;
    }
    
}
