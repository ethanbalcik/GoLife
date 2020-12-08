/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ebalc
 */
@Entity
@Table(name = "CALENDARMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Calendarmodel.findAll", query = "SELECT c FROM Calendarmodel c")
    , @NamedQuery(name = "Calendarmodel.findByCalendarid", query = "SELECT c FROM Calendarmodel c WHERE c.calendarid = :calendarid")
    , @NamedQuery(name = "Calendarmodel.findByCurrentdate", query = "SELECT c FROM Calendarmodel c WHERE c.currentdate = :currentdate")
    , @NamedQuery(name = "Calendarmodel.findByDisplaydate", query = "SELECT c FROM Calendarmodel c WHERE c.displaydate = :displaydate")
    , @NamedQuery(name = "Calendarmodel.findByTimescope", query = "SELECT c FROM Calendarmodel c WHERE c.timescope = :timescope")
})
public class Calendarmodel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CALENDARID")
    private Integer calendarid;
    @Basic(optional = false)
    @Column(name = "CURRENTDATE")
    @Temporal(TemporalType.DATE)
    private Date currentdate;
    @Basic(optional = false)
    @Column(name = "DISPLAYDATE")
    @Temporal(TemporalType.DATE)
    private Date displaydate;
    @Column(name = "TIMESCOPE")
    private Integer timescope;
    @OneToMany(mappedBy = "calendarid")
    private Collection<Eventmodel> eventmodelCollection;
    @OneToMany(mappedBy = "calendarid")
    private Collection<Goalmodel> goalmodelCollection;
    @OneToMany(mappedBy = "calendarid")
    private Collection<Dailyhealthmodel> dailyhealthmodelCollection;
    @OneToMany(mappedBy = "calendarid")
    private Collection<Usermodel> usermodelCollection;

    public Calendarmodel()
    {
    }

    public Calendarmodel(Integer calendarid)
    {
        this.calendarid = calendarid;
    }

    public Calendarmodel(Integer calendarid, Date currentdate, Date displaydate)
    {
        this.calendarid = calendarid;
        this.currentdate = currentdate;
        this.displaydate = displaydate;
    }

    public Integer getCalendarid()
    {
        return calendarid;
    }

    public void setCalendarid(Integer calendarid)
    {
        this.calendarid = calendarid;
    }

    public Date getCurrentdate()
    {
        return currentdate;
    }

    public void setCurrentdate(Date currentdate)
    {
        this.currentdate = currentdate;
    }

    public Date getDisplaydate()
    {
        return displaydate;
    }

    public void setDisplaydate(Date displaydate)
    {
        this.displaydate = displaydate;
    }

    public Integer getTimescope()
    {
        return timescope;
    }

    public void setTimescope(Integer timescope)
    {
        this.timescope = timescope;
    }

    @XmlTransient
    public Collection<Eventmodel> getEventmodelCollection()
    {
        return eventmodelCollection;
    }

    public void setEventmodelCollection(Collection<Eventmodel> eventmodelCollection)
    {
        this.eventmodelCollection = eventmodelCollection;
    }

    @XmlTransient
    public Collection<Goalmodel> getGoalmodelCollection()
    {
        return goalmodelCollection;
    }

    public void setGoalmodelCollection(Collection<Goalmodel> goalmodelCollection)
    {
        this.goalmodelCollection = goalmodelCollection;
    }

    @XmlTransient
    public Collection<Dailyhealthmodel> getDailyhealthmodelCollection()
    {
        return dailyhealthmodelCollection;
    }

    public void setDailyhealthmodelCollection(Collection<Dailyhealthmodel> dailyhealthmodelCollection)
    {
        this.dailyhealthmodelCollection = dailyhealthmodelCollection;
    }

    @XmlTransient
    public Collection<Usermodel> getUsermodelCollection()
    {
        return usermodelCollection;
    }

    public void setUsermodelCollection(Collection<Usermodel> usermodelCollection)
    {
        this.usermodelCollection = usermodelCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (calendarid != null ? calendarid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendarmodel))
        {
            return false;
        }
        Calendarmodel other = (Calendarmodel) object;
        if ((this.calendarid == null && other.calendarid != null) || (this.calendarid != null && !this.calendarid.equals(other.calendarid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Calendarmodel[ calendarid=" + calendarid + " ]";
    }
    
}
