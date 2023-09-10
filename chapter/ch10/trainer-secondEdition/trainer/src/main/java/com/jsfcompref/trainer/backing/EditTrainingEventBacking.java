package com.jsfcompref.trainer.backing;

import com.jsfcompref.trainer.entity.accessor.EntityAccessorException;
import com.jsfcompref.trainer.entity.accessor.EventRegistry;
import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import com.jsfcompref.trainer.entity.Event;
import com.jsfcompref.trainer.entity.TrainingSession;
import com.jsfcompref.trainer.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
public class EditTrainingEventBacking extends AbstractBacking {

    private Long selectedEventId;

    private Event selectedEvent;

    private UIData trainingSessionData;

    public void loadTrainingEvent(ComponentSystemEvent cse) {
        // if the event has not yet been set
        if (null == getSelectedEvent()) {
            Long eventId = getSelectedEventId();
            if (null == eventId) {
                // Try to get it from the flash
                eventId = (Long) getFlash().get("selectedEventId");
            }

            if (null == eventId) {
                getFacesContext().addMessage(null,
                        new FacesMessage("The training event you requested is invalid"));
                getFlash().setKeepMessages(true);
                getFacesContext().getApplication().getNavigationHandler().
                        handleNavigation(getFacesContext(), null, "/user/allEvents?faces-redirect=true");
            } else {
                Event event = EventRegistry.getCurrentInstance().
                        getEventForId(eventId);
                if (null == event) {
                    getFacesContext().addMessage(null,
                            new FacesMessage("The training event you requested does not exist"));
                    getFlash().setKeepMessages(true);
                    getFacesContext().getApplication().getNavigationHandler().
                            handleNavigation(getFacesContext(), null, "/user/allEvents?faces-redirect=true");
                } else {
                    getFlash().put("selectedEvent", event);
                    setSelectedEvent(event);
                }
            }
        }

    }

    public void addTrainingSessionToCurrentEventForCurrentUser() {
        TrainingSession newSession = (TrainingSession)
                getFacesContext().getExternalContext().getRequestMap().get("trainingSession");
        User currentUser = getCurrentUser();

        newSession.setEventId(getSelectedEventId());
        newSession.setUser(currentUser);

        currentUser.getTrainingSessions().add(newSession);
        try {
            UserRegistry.getCurrentInstance().updateUser(currentUser);
        } catch (EntityAccessorException ex) {
            Logger.getLogger(EditTrainingEventBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void forwardToMainIfNotTrainer(ComponentSystemEvent cse) {
        User user;
        if (null != (user = getCurrentUser()) && !user.isTrainer()) {
            getFacesContext().getApplication().getNavigationHandler().
                    handleNavigation(getFacesContext(), null,
                    "/user/allEvents?faces-redirect=true");

        }
    }

    public String addNewTrainingEvent() {
        String result = null;
        ExternalContext extContext = getFacesContext().getExternalContext();

        EventRegistry eventRegistry = EventRegistry.getCurrentInstance();
        Event newEvent = (Event) extContext.getRequestMap().get("event");
        try {
            eventRegistry.addEvent(newEvent);
            result = "/user/allEvents?faces-redirect=true";
        } catch (EntityAccessorException ex) {
            Logger.getLogger(EditTrainingEventBacking.class.getName()).log(Level.SEVERE, null, ex);
        }


        return result;


    }

    public String updateExistingTrainingEvent() {
        String result = null;

        EventRegistry eventRegistry = EventRegistry.getCurrentInstance();
        Event newEvent = getSelectedEvent();
        try {
            eventRegistry.updateEvent(newEvent);
            result = "/user/allEvents?faces-redirect=true";
        } catch (EntityAccessorException ex) {
            Logger.getLogger(EditTrainingEventBacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        getFlash().clear();

        return result;
    }


    public void copySelectedEventToFlash(ActionEvent e) {
        getFacesContext().getExternalContext().getFlash().put("selectedEvent", getSelectedEvent());
    }

    public Long getSelectedEventId() {
        return selectedEventId;
    }

    public void setSelectedEventId(Long selectedEventId) {
        this.selectedEventId = selectedEventId;
        setSelectedEvent(EventRegistry.getCurrentInstance().getEventForId(selectedEventId));
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {

        this.selectedEvent = selectedEvent;
    }

    public boolean isDeleteSession() {
        boolean result = false;

        return result;
    }


    public void setDeleteSession(boolean deleteSession) {
        if (deleteSession) {
            UserRegistry userRegistry = UserRegistry.getCurrentInstance();
            User currentUser = getCurrentUser();

            TrainingSession toRemove = (TrainingSession)trainingSessionData.getRowData();

            userRegistry.removeTrainingSessionForUserAndEvent(currentUser, selectedEvent, toRemove);
        }
    }

    public UIData getTrainingSessionData() {
        return trainingSessionData;
    }

    public void setTrainingSessionData(UIData trainingSessionData) {
        this.trainingSessionData = trainingSessionData;
    }



}
