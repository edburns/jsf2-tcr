package com.jsfcompref.trainer.backing;

import com.jsfcompref.trainer.entity.accessor.UserRegistry;
import com.jsfcompref.trainer.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
public class LoginBacking extends AbstractBacking {

    private UIInput loginOutcomeChoiceList;

    private User nonAuthenticatedUser;
    
    public boolean useridIsValid(String toTest) {
        boolean result = false;

        UserRegistry registry = UserRegistry.getCurrentInstance();

        result = null != (nonAuthenticatedUser = registry.getUserByUserid(toTest));

        return result;
    }

    public boolean passwordIsValid(String toTest) {
        boolean result = false;

        if (null != nonAuthenticatedUser) {
            String userPassword = nonAuthenticatedUser.getPassword();
            if (null != userPassword && userPassword.equals(toTest)) {
                // Put the current user in the session
                setCurrentUser(nonAuthenticatedUser);
                result = true;
            }
        }
        nonAuthenticatedUser = null;

        return result;
    }

    public String getSuccessOutcome() {
        String choice = (String) getLoginOutcomeChoiceList().getValue();

        return "/user/" + choice + "?faces-redirect=true";
    }

    public void forwardToMainIfLoggedIn(ComponentSystemEvent cse) {
        if (isUserLoggedIn()) {
            getFacesContext().getApplication().getNavigationHandler().
                    handleNavigation(getFacesContext(), null,
                    "/user/allEvents?faces-redirect=true");
        }
    }

    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent cse) {
        String viewId = getFacesContext().getViewRoot().getViewId();
        if (!isUserLoggedIn() && !viewId.startsWith("/login") &&
            !viewId.startsWith("/register")) {
            getFacesContext().getApplication().getNavigationHandler().
                    handleNavigation(getFacesContext(), null,
                    "/login?faces-redirect=true");
        }
    }

    public String performLogout() {
        String result = "/login?faces-redirect=true";
        setCurrentUser(null);
        getFacesContext().getExternalContext().invalidateSession();

        return result;
    }

    public UIInput getLoginOutcomeChoiceList() {
        return loginOutcomeChoiceList;
    }

    public void setLoginOutcomeChoiceList(UIInput loginOutcomeChoiceList) {
        this.loginOutcomeChoiceList = loginOutcomeChoiceList;
    }

}
