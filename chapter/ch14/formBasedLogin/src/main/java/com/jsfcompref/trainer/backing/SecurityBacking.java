
package com.jsfcompref.trainer.backing;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class SecurityBacking {

    /** Creates a new instance of SecurityBacking */
    public SecurityBacking() {
    }

    public String logout() {
        String result = "/login?faces-redirect=true";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.
                getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(SecurityBacking.class.getName()).log(Level.SEVERE, null, ex);
            result = "/loginError?faces-redirect=true";
        }

        return result;
    }

}
