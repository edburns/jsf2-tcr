package com.jsfcompref.trainer.entity;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "vtEvents")
@NamedQueries({@NamedQuery(name = "event.getAll", query = "select e from Event as e")})
@ManagedBean
@RequestScoped
public class Event  extends AbstractEntity implements Serializable
{
    @Column(nullable = false, unique = true)
    String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "completion_date", nullable = false)
    Date completionDate;

    String skill;

    String type;

    public Event() {
    }


  public Event(Event src)
  {
    this.setName(src.getName());
    this.setType(src.getType());
    this.setCompletionDate(src.getCompletionDate());
    this.setSkill(src.getSkill());
  }


  public Event(String name, Date completionDate, String skill, String type)
  {
    this.setName(name);
    this.setCompletionDate(completionDate);
    this.setSkill(skill);
    this.setType(type);
  }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.completionDate != other.completionDate && (this.completionDate == null || !this.completionDate.equals(other.completionDate))) {
            return false;
        }
        if ((this.skill == null) ? (other.skill != null) : !this.skill.equals(other.skill)) {
            return false;
        }
        if ((this.type == null) ? (other.type != null) : !this.type.equals(other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.completionDate != null ? this.completionDate.hashCode() : 0);
        hash = 89 * hash + (this.skill != null ? this.skill.hashCode() : 0);
        hash = 89 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return getName() + " " + getCompletionDate();
    }





  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Date getCompletionDate()
  {
    return completionDate;
  }

  public void setCompletionDate(Date completionDate)
  {
    this.completionDate = completionDate;
  }

  public String getSkill()
  {
    return skill;
  }

  public void setSkill(String skill)
  {
    this.skill = skill;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

}
