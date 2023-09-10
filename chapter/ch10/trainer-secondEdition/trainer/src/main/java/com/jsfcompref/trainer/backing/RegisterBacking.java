package com.jsfcompref.trainer.backing;

import com.jsfcompref.trainer.entity.accessor.EntityAccessorException;
import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import com.jsfcompref.trainer.entity.User;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

@ManagedBean
public class RegisterBacking extends AbstractBacking {
    
    private Object password1;

    public void validatePassword1(FacesContext context, UIComponent component,
            Object password1) throws ValidatorException {
        this.password1 = password1;
    }

    public void validatePassword2(FacesContext context, UIComponent component,
            Object password2) throws ValidatorException {
        if (!(password1.equals(password2))) {
            throw new ValidatorException(new FacesMessage("Passwords must match."));
        }
    }

    /**
     * <p>If this method is called, we know that the user is valid and
     * safe to persist</p>
     * @return
     */
    public String registerUser() {
        String result = null;
        User newUser = (User) getRequestMap().get("user");
        // set the password into the user, because we know the validator was
        // successful if we reached here.
        newUser.setPassword((String) getRequestMap().get("password1"));
        try {
            UserRegistry.getCurrentInstance().addUser(newUser);
            // Put the current user in the session
            setCurrentUser(newUser);
            // redirect to the main page
            result = "/user/allEvents?faces-redirect=true";
        } catch (EntityAccessorException ex) {
            getFacesContext().addMessage(null,
                    new FacesMessage("Error when adding user" +
                    ((null != newUser) ? " " + newUser.toString() : "") + "."));

        }

        return result;

    }

}
