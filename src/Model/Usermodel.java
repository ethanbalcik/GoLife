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
import javax.persistence.Id;
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
@Table(name = "USERMODEL")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "Usermodel.findAll", query = "SELECT u FROM Usermodel u ORDER BY u.userid DESC")
    , @NamedQuery(name = "Usermodel.findByUserid", query = "SELECT u FROM Usermodel u WHERE u.userid = :userid")
    , @NamedQuery(name = "Usermodel.findByFirstname", query = "SELECT u FROM Usermodel u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "Usermodel.findByLastname", query = "SELECT u FROM Usermodel u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "Usermodel.findByDateofbirth", query = "SELECT u FROM Usermodel u WHERE u.dateofbirth = :dateofbirth")
    , @NamedQuery(name = "Usermodel.findByEmail", query = "SELECT u FROM Usermodel u WHERE u.email = :email")
    , @NamedQuery(name = "Usermodel.findByUsername", query = "SELECT u FROM Usermodel u WHERE u.username = :username")
    , @NamedQuery(name = "Usermodel.findByPassword", query = "SELECT u FROM Usermodel u WHERE u.password = :password")
    , @NamedQuery(name = "Usermodel.findByUsernamePassword", query = "SELECT u FROM Usermodel u WHERE u.username = :username AND u.password = :password")
    , @NamedQuery(name = "Usermodel.findByAllMinusUserid", query = "SELECT u FROM Usermodel u WHERE u.firstname = :firstname AND u.lastname = :lastname AND u.dateofbirth = :dateofbirth AND u.email = :email AND u.username = :username AND u.password = :password")
})
public class Usermodel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USERID")
    private Integer userid;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "DATEOFBIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    public Usermodel()
    {
    }

    public Usermodel(Integer userid)
    {
        this.userid = userid;
    }

    public Integer getUserid()
    {
        return userid;
    }

    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public Date getDateofbirth()
    {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth)
    {
        this.dateofbirth = dateofbirth;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usermodel))
        {
            return false;
        }
        Usermodel other = (Usermodel) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "Model.Usermodel[ userid=" + userid + ", username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", dateofbirth=" + dateofbirth.toString() + " ]";
    }
    
}
