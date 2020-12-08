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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "GOALMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Goalmodel.findAll", query = "SELECT g FROM Goalmodel g")
    , @NamedQuery(name = "Goalmodel.findByGoalid", query = "SELECT g FROM Goalmodel g WHERE g.goalid = :goalid")
    , @NamedQuery(name = "Goalmodel.findByName", query = "SELECT g FROM Goalmodel g WHERE g.name = :name")
    , @NamedQuery(name = "Goalmodel.findByDescription", query = "SELECT g FROM Goalmodel g WHERE g.description = :description")
    , @NamedQuery(name = "Goalmodel.findByDeadline", query = "SELECT g FROM Goalmodel g WHERE g.deadline = :deadline")
    , @NamedQuery(name = "Goalmodel.findByColor", query = "SELECT g FROM Goalmodel g WHERE g.color = :color")
})
public class Goalmodel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GOALID")
    private Integer goalid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Basic(optional = false)
    @Column(name = "COLOR")
    private long color;
    @OneToMany(mappedBy = "goalid")
    private Collection<Objectivemodel> objectivemodelCollection;
    @OneToMany(mappedBy = "goalid")
    private Collection<Eventmodel> eventmodelCollection;
    @JoinColumn(name = "CALENDARID", referencedColumnName = "CALENDARID")
    @ManyToOne
    private Calendarmodel calendarid;

    public Goalmodel()
    {
    }

    public Goalmodel(Integer goalid)
    {
        this.goalid = goalid;
    }

    public Goalmodel(Integer goalid, Date deadline, long color)
    {
        this.goalid = goalid;
        this.deadline = deadline;
        this.color = color;
    }

    public Integer getGoalid()
    {
        return goalid;
    }

    public void setGoalid(Integer goalid)
    {
        this.goalid = goalid;
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

    public Date getDeadline()
    {
        return deadline;
    }

    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public long getColor()
    {
        return color;
    }

    public void setColor(long color)
    {
        this.color = color;
    }

    @XmlTransient
    public Collection<Objectivemodel> getObjectivemodelCollection()
    {
        return objectivemodelCollection;
    }

    public void setObjectivemodelCollection(Collection<Objectivemodel> objectivemodelCollection)
    {
        this.objectivemodelCollection = objectivemodelCollection;
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

    public Calendarmodel getCalendarid()
    {
        return calendarid;
    }

    public void setCalendarid(Calendarmodel calendarid)
    {
        this.calendarid = calendarid;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (goalid != null ? goalid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Goalmodel))
        {
            return false;
        }
        Goalmodel other = (Goalmodel) object;
        if ((this.goalid == null && other.goalid != null) || (this.goalid != null && !this.goalid.equals(other.goalid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Goalmodel[ goalid=" + goalid + " ]";
    }
    
}
