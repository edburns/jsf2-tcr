
package com.jsfcompref.trainer.backing;

import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import com.jsfcompref.trainer.entity.TrainingSession;
import com.jsfcompref.trainer.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean
public class ViewTraineesBacking extends AbstractBacking {

    public DataModel<User> getTraineesForCurrentUser() {
        DataModel<User> users = new ListDataModel<User>(UserRegistry.getCurrentInstance().getTraineesForTrainer(getCurrentUser()));
        return users;
    }

    public UIData getTrainingSessions() {
        return trainingSessions;
    }

    public void setTrainingSessions(UIData trainingSessions) {
        this.trainingSessions = trainingSessions;
    }

    public List<TrainingSession> getTrainingSessionsWithChangedTrainerNotes() {
        if (null == trainingSessionsWithChangedTrainerNotes) {
            trainingSessionsWithChangedTrainerNotes = new ArrayList<TrainingSession>();
        }
        return trainingSessionsWithChangedTrainerNotes;
    }

    public void setTrainingSessionsWithChangedTrainerNotes(List<TrainingSession> trainingSessionsWithChangedTrainerNotes) {
        this.trainingSessionsWithChangedTrainerNotes = trainingSessionsWithChangedTrainerNotes;
    }

    private enum Constants {
        EventKey,
        TrainingSessionKey
    }

    private UIData trainingSessions;

    private List<TrainingSession> trainingSessionsWithChangedTrainerNotes;

    private Long userId;

    private User selectedUser;

    public void loadUser(ComponentSystemEvent cse) {
        // if the event has not yet been set
        if (null == getSelectedUser()) {
            Long id = getUserId();

            if (null == id) {
                getFacesContext().addMessage(null,
                        new FacesMessage("The user you requested is invalid"));
                getFacesContext().getExternalContext().getFlash().setKeepMessages(true);
                getFacesContext().getApplication().getNavigationHandler().
                        handleNavigation(getFacesContext(), null, "/user/allEvents?faces-redirect=true");
            } else {
                User user = UserRegistry.getCurrentInstance().getUserById(id);
                if (null == user) {
                    getFacesContext().addMessage(null,
                            new FacesMessage("The user you requested does not exist"));
                    getFacesContext().getExternalContext().getFlash().setKeepMessages(true);
                    getFacesContext().getApplication().getNavigationHandler().
                            handleNavigation(getFacesContext(), null, "/user/allEvents?faces-redirect=true");
                } else {
                    setSelectedUser(user);
                }
            }
        }

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;

        if (null != selectedUser) {
            getFlash().put("selectedUser", selectedUser);
        }
    }

}
