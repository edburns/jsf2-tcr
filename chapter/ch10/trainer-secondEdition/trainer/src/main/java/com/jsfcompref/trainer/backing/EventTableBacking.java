package com.jsfcompref.trainer.backing;

import com.jsfcompref.trainer.entity.accessor.EntityAccessorException;
import com.jsfcompref.trainer.entity.accessor.EventRegistry;
import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import com.jsfcompref.trainer.entity.Event;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;

@ManagedBean
public class EventTableBacking extends AbstractBacking {

    private UIData events;
    private List<Long> subscribedEventIds;

    public List<Long> getSubscribedEventIds() {
        if (null == subscribedEventIds) {
            subscribedEventIds = getCurrentUser().getSubscribedEventIds();
        }
        return subscribedEventIds;
    }

    public UIData getEvents() {
        return events;
    }

    public void setEvents(UIData events) {
        this.events = events;
    }

    public boolean isSubscribedToEvent() {
        boolean result = false;
        Event currentEvent = (Event) getEvents().getRowData();
        result = getSubscribedEventIds().contains(currentEvent.getId());

        return result;
    }


    public void setSubscribedToEvent(boolean subscribedToEvent) {
        Event currentEvent = (Event) getEvents().getRowData();
        Long id = currentEvent.getId();
        boolean isCurrentlySubscribed = getSubscribedEventIds().contains(id);
        boolean doPersist = false;
        if (true == subscribedToEvent) {
            if (!isCurrentlySubscribed) {
                getSubscribedEventIds().add(id);
                doPersist = true;
            }
        } else if (isCurrentlySubscribed) {

            getSubscribedEventIds().remove(id);
            doPersist = true;
        }
        if (doPersist) {
            try {
                UserRegistry.getCurrentInstance().updateUser(getCurrentUser());
            } catch (EntityAccessorException ex) {
                Logger.getLogger(EventTableBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isDeleteEvent() {
        boolean result = false;

        return result;
    }


    public void setDeleteEvent(boolean deleteEvent) {
        if (deleteEvent) {
            Event currentEvent = (Event) getEvents().getRowData();
            Long id = currentEvent.getId();
            EventRegistry.getCurrentInstance().removeEventFromRegistryAndFromUsers(id);
        }
    }

}
