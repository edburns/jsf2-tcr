
package com.jsfcompref.trainer.entity;

import com.jsfcompref.trainer.entity.accessor.EventRegistry;
import com.jsfcompref.trainer.entity.accessor.EntityAccessorException;
import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "vtUsers")
@NamedQueries({
    @NamedQuery(name = "user.getAll", query = "select u from User as u"),
    @NamedQuery(name = "user.getTrainers", query = "select u from User as u where u.trainer = TRUE"),
    @NamedQuery(name = "user.getUsersForTrainerId", query = "select u from User as u where u.personalTrainerId = :theId")

})
@ManagedBean
@RequestScoped
public class User extends AbstractEntity implements Serializable {


    protected String firstName;
    protected String lastName;
    @Temporal(TemporalType.DATE)
    protected Date dob;
    protected String sex;
    protected String email;
    private String serviceLevel = "medium";
    @Column(name="userid", nullable=false)
    private String userid;
    private String password;
    private boolean trainer;
    private List<Long> subscribedEventIds;
    private Long personalTrainerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TrainingSession> sessions;

    private boolean sessionsInitialized = false;

    public User() {
        this.init();
    }


    public User(String firstName, String lastName,
            String sex, Date dob, String email, String serviceLevel,
            String userid, String password, boolean isTrainer) {
        this.init();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setSex(sex);
        this.setDob(dob);
        this.setEmail(email);
        this.setServiceLevel(serviceLevel);
        this.setUserid(userid);
        this.setPassword(password);
        this.setTrainer(isTrainer);
    }
    
    private void init() {
        subscribedEventIds = new ArrayList<Long>();
    }

    private void populateTrainingSessions() {
        if (null == sessions) {
            sessions = new ArrayList<TrainingSession>();
        }
        EventRegistry eventReg = EventRegistry.getCurrentInstance();
        List<Long> eventIds = getSubscribedEventIds();
        for (Long eventId : eventIds) {
            sessions.add(new TrainingSession(eventId, this, new java.util.Date(1227817411), "a workout desc", true, "something for now", "something"));
            sessions.add(new TrainingSession(eventId, this, new java.util.Date(1229459011), "a workout desc", true, "something for now", "something"));

        }
        try {
            UserRegistry userReg = UserRegistry.getCurrentInstance();
            // necessary? userReg.addTrainingSessions(sessions);
            userReg.updateUser(this);
        } catch (EntityAccessorException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public List<TrainingSession> getTrainingSessions() {
        return sessions;
    }

    public List<Long> getSubscribedEventIds() {
        return subscribedEventIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if (this.dob != other.dob && (this.dob == null || !this.dob.equals(other.dob))) {
            return false;
        }
        if ((this.sex == null) ? (other.sex != null) : !this.sex.equals(other.sex)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.serviceLevel == null) ? (other.serviceLevel != null) : !this.serviceLevel.equals(other.serviceLevel)) {
            return false;
        }
        if ((this.userid == null) ? (other.userid != null) : !this.userid.equals(other.userid)) {
            return false;
        }
        if ((this.password == null) ? (other.password != null) : !this.password.equals(other.password)) {
            return false;
        }
        if (this.trainer != other.trainer) {
            return false;
        }
        if (this.subscribedEventIds != other.subscribedEventIds && (this.subscribedEventIds == null || !this.subscribedEventIds.equals(other.subscribedEventIds))) {
            return false;
        }
        if (this.getPersonalTrainerId() != other.getPersonalTrainerId() && (this.getPersonalTrainerId() == null || !this.personalTrainerId.equals(other.personalTrainerId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 73 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 73 * hash + (this.dob != null ? this.dob.hashCode() : 0);
        hash = 73 * hash + (this.sex != null ? this.sex.hashCode() : 0);
        hash = 73 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 73 * hash + (this.serviceLevel != null ? this.serviceLevel.hashCode() : 0);
        hash = 73 * hash + (this.userid != null ? this.userid.hashCode() : 0);
        hash = 73 * hash + (this.password != null ? this.password.hashCode() : 0);
        hash = 73 * hash + (this.trainer ? 1 : 0);
        hash = 73 * hash + (this.subscribedEventIds != null ? this.subscribedEventIds.hashCode() : 0);
        hash = 73 * hash + (this.getPersonalTrainerId() != null ? this.getPersonalTrainerId().hashCode() : 0);
        return hash;
    }

    

    public DataModel<Event> getMyEvents() {
        DataModel<Event> events = null; //
        List<Event> myEvents = new ArrayList<Event>();
        EventRegistry eventRegistry = EventRegistry.getCurrentInstance();

        for (Long eventId : getSubscribedEventIds()) {
            Event event = eventRegistry.getEventForId(eventId);
            myEvents.add(event);
        }
        events = new ListDataModel<Event>(myEvents);

        return events;
    }

    public DataModel<TrainingSession> getTrainingSessionsForEvent(Event e) {
        // lazily initialize training sessions
        if (!sessionsInitialized) {
            populateTrainingSessions();
            sessionsInitialized = true;
            try {
                UserRegistry.getCurrentInstance().updateUser(this);
            } catch (EntityAccessorException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        DataModel<TrainingSession> sessionsForEvent = null;
        List<TrainingSession> sessionList = UserRegistry.getCurrentInstance().getTrainingSessionsForUserAndEvent(this, e);

        sessionsForEvent = new ListDataModel<TrainingSession>(sessionList);

        return sessionsForEvent;
    }



    @Override
    public String toString() {
        return getFirstName() + " " + getLastName();
    }

    @UseridUniquenessConstraint
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the isTrainer
     */
    public boolean isTrainer() {
        return trainer;
    }

    /**
     * @param isTrainer the isTrainer to set
     */
    public void setTrainer(boolean isTrainer) {
        this.trainer = isTrainer;
    }

    public Long getPersonalTrainerId() {
        return personalTrainerId;
    }

    public void setPersonalTrainerId(Long personalTrainerId) {
        this.personalTrainerId = personalTrainerId;
    }

    public User getPersonalTrainer() {

        // By default, you are your own trainer
        User result = this;
        Long trainerId = getPersonalTrainerId();
        if (null != trainerId) {
            result = UserRegistry.getCurrentInstance().getUserById(trainerId);
        }
        return result;
    }

}
