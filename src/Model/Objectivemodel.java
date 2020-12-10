/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "OBJECTIVEMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Objectivemodel.findAll", query = "SELECT o FROM Objectivemodel o")
    , @NamedQuery(name = "Objectivemodel.findByObjectiveid", query = "SELECT o FROM Objectivemodel o WHERE o.objectiveid = :objectiveid")
    , @NamedQuery(name = "Objectivemodel.findByName", query = "SELECT o FROM Objectivemodel o WHERE o.name = :name")
    , @NamedQuery(name = "Objectivemodel.findByDescription", query = "SELECT o FROM Objectivemodel o WHERE o.description = :description")
    , @NamedQuery(name = "Objectivemodel.findByDeadline", query = "SELECT o FROM Objectivemodel o WHERE o.deadline = :deadline")
    , @NamedQuery(name = "Objectivemodel.findByColor", query = "SELECT o FROM Objectivemodel o WHERE o.color = :color")
    , @NamedQuery(name = "Objectivemodel.findByOngoing", query = "SELECT o FROM Objectivemodel o WHERE o.ongoing = :ongoing")
    , @NamedQuery(name = "Objectivemodel.findByAccomplished", query = "SELECT o FROM Objectivemodel o WHERE o.accomplished = :accomplished")
})
public class Objectivemodel implements Serializable
{

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "REDCHANNEL")
    private Double redchannel;
    @Column(name = "GREENCHANNEL")
    private Double greenchannel;
    @Column(name = "BLUECHANNEL")
    private Double bluechannel;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "OBJECTIVEID")
    private Integer objectiveid;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "DEADLINE")
    @Temporal(TemporalType.DATE)
    private Date deadline;
    @Column(name = "COLOR")
    private BigInteger color;
    @Column(name = "ONGOING")
    private Boolean ongoing;
    @Column(name = "ACCOMPLISHED")
    private Boolean accomplished;
    @JoinColumn(name = "GOALID", referencedColumnName = "GOALID")
    @ManyToOne
    private Goalmodel goalid;
    @OneToMany(mappedBy = "objectiveid")
    private Collection<Eventmodel> eventmodelCollection;

    public Objectivemodel()
    {
    }

    public Objectivemodel(Integer objectiveid)
    {
        this.objectiveid = objectiveid;
    }

    public Objectivemodel(Integer objectiveid, Date deadline)
    {
        this.objectiveid = objectiveid;
        this.deadline = deadline;
    }

    public Integer getObjectiveid()
    {
        return objectiveid;
    }

    public void setObjectiveid(Integer objectiveid)
    {
        this.objectiveid = objectiveid;
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

    public BigInteger getColor()
    {
        return color;
    }

    public void setColor(BigInteger color)
    {
        this.color = color;
    }

    public Boolean getOngoing()
    {
        return ongoing;
    }

    public void setOngoing(Boolean ongoing)
    {
        this.ongoing = ongoing;
    }

    public Boolean getAccomplished()
    {
        return accomplished;
    }

    public void setAccomplished(Boolean accomplished)
    {
        this.accomplished = accomplished;
    }

    public Goalmodel getGoalid()
    {
        return goalid;
    }

    public void setGoalid(Goalmodel goalid)
    {
        this.goalid = goalid;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (objectiveid != null ? objectiveid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objectivemodel))
        {
            return false;
        }
        Objectivemodel other = (Objectivemodel) object;
        if ((this.objectiveid == null && other.objectiveid != null) || (this.objectiveid != null && !this.objectiveid.equals(other.objectiveid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Objectivemodel[ objectiveid=" + objectiveid + " ]";
    }

    public Double getRedchannel()
    {
        return redchannel;
    }

    public void setRedchannel(Double redchannel)
    {
        this.redchannel = redchannel;
    }

    public Double getGreenchannel()
    {
        return greenchannel;
    }

    public void setGreenchannel(Double greenchannel)
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
