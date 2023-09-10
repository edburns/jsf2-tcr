package com.jsfcompref;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean {

    @NotEmpty(message="You must supply a first name")
    protected String firstName;
    @NotEmpty(message="You must supply a last name")
    protected String lastName;
    @NotEmpty(message="You must supply a birthday")
    protected Date dob;
    protected String sex;
    @NotEmpty(message="You must supply an email address")
    @Email
    protected String email;
    protected String serviceLevel = "medium";

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
    
    public String addConfirmedUser() {
        // This method would call a database or other service and add the 
        // confirmed user information.
        // For now, we just place an informative message in request scope
        FacesMessage doneMessage = 
                new FacesMessage("Successfully added new user");
        FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        return "done";
    }
}

