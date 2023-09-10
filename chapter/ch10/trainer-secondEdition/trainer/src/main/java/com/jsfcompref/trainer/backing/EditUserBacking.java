package com.jsfcompref.trainer.backing;

import com.jsfcompref.trainer.entity.accessor.EntityAccessorException;
import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import com.jsfcompref.trainer.entity.User;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class EditUserBacking extends AbstractBacking {

    public Collection<User> getAvailableTrainers() {
        Collection<User> result = null;
        UserRegistry userRegistry = UserRegistry.getCurrentInstance();
        result = userRegistry.getTrainerList();

        return result;
    }

    public void updateUser() {
        User newUser = (User) getSessionMap().get("currentUser");
        try {
            UserRegistry.getCurrentInstance().updateUser(newUser);
        } catch (EntityAccessorException ex) {
            getFacesContext().addMessage(null,
                    new FacesMessage("Error when adding user" +
               ((null != newUser) ? " " + newUser.toString() : "") + "."));

        }

    }





}
