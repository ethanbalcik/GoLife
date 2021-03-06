/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
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
@Table(name = "DAILYHEALTHMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Dailyhealthmodel.findAll", query = "SELECT d FROM Dailyhealthmodel d")
    , @NamedQuery(name = "Dailyhealthmodel.findByDailyhealthid", query = "SELECT d FROM Dailyhealthmodel d WHERE d.dailyhealthid = :dailyhealthid")
    , @NamedQuery(name = "Dailyhealthmodel.findByCarbohydrates", query = "SELECT d FROM Dailyhealthmodel d WHERE d.carbohydrates = :carbohydrates")
    , @NamedQuery(name = "Dailyhealthmodel.findByFat", query = "SELECT d FROM Dailyhealthmodel d WHERE d.fat = :fat")
    , @NamedQuery(name = "Dailyhealthmodel.findByProtein", query = "SELECT d FROM Dailyhealthmodel d WHERE d.protein = :protein")
    , @NamedQuery(name = "Dailyhealthmodel.findByMood", query = "SELECT d FROM Dailyhealthmodel d WHERE d.mood = :mood")
    , @NamedQuery(name = "Dailyhealthmodel.findByJournalentry", query = "SELECT d FROM Dailyhealthmodel d WHERE d.journalentry = :journalentry")
    , @NamedQuery(name = "Dailyhealthmodel.findByCalendarid", query = "SELECT d FROM Dailyhealthmodel d WHERE d.calendarid = :calendarid")
})
public class Dailyhealthmodel implements Serializable
{

    @Column(name = "DATESUBMITTED")
    @Temporal(TemporalType.DATE)
    private Date datesubmitted;
    @Column(name = "PROTEIN")
    private Integer protein;
    @Column(name = "MOOD")
    private String mood;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "DAILYHEALTHID")
    private Integer dailyhealthid;
    @Column(name = "CARBOHYDRATES")
    private Integer carbohydrates;
    @Column(name = "FAT")
    private Integer fat;
    @Column(name = "JOURNALENTRY")
    private String journalentry;
    @JoinColumn(name = "CALENDARID", referencedColumnName = "CALENDARID")
    @ManyToOne
    private Calendarmodel calendarid;

    public Dailyhealthmodel()
    {
    }

    public Dailyhealthmodel(Integer dailyhealthid)
    {
        this.dailyhealthid = dailyhealthid;
    }

    public Integer getDailyhealthid()
    {
        return dailyhealthid;
    }

    public void setDailyhealthid(Integer dailyhealthid)
    {
        this.dailyhealthid = dailyhealthid;
    }

    public Integer getCarbohydrates()
    {
        return carbohydrates;
    }

    public void setCarbohydrates(Integer carbohydrates)
    {
        this.carbohydrates = carbohydrates;
    }

    public Integer getFat()
    {
        return fat;
    }

    public void setFat(Integer fat)
    {
        this.fat = fat;
    }

    public String getJournalentry()
    {
        return journalentry;
    }

    public void setJournalentry(String journalentry)
    {
        this.journalentry = journalentry;
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
        hash += (dailyhealthid != null ? dailyhealthid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dailyhealthmodel))
        {
            return false;
        }
        Dailyhealthmodel other = (Dailyhealthmodel) object;
        if ((this.dailyhealthid == null && other.dailyhealthid != null) || (this.dailyhealthid != null && !this.dailyhealthid.equals(other.dailyhealthid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Dailyhealthmodel[ dailyhealthid=" + dailyhealthid + " ]";
    }

    public Integer getProtein()
    {
        return protein;
    }

    public void setProtein(Integer protein)
    {
        this.protein = protein;
    }

    public String getMood()
    {
        return mood;
    }

    public void setMood(String mood)
    {
        this.mood = mood;
    }

    public Date getDatesubmitted()
    {
        return datesubmitted;
    }

    public void setDatesubmitted(Date datesubmitted)
    {
        this.datesubmitted = datesubmitted;
    }
    
}
