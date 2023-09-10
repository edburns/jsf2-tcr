package com.jsfcompref.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.Flash;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
public class UserBean {
    
    protected String firstName;
    protected String lastName;
    protected Date dob;
    protected String sex;
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
    
    public void validateEmail(FacesContext context, UIComponent toValidate,
            Object value) throws ValidatorException {
        String emailStr = (String) value;
        if (-1 == emailStr.indexOf("@")) {
            FacesMessage message = new FacesMessage("Invalid email address");
            throw new ValidatorException(message);
        }
    }

    public void pullValuesFromFlash() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setFirstName((String) flash.get("firstName"));
        setLastName((String) flash.get("lastName"));
        setServiceLevel((String) flash.get("sex"));
        setDob((Date) flash.get("dob"));
        setEmail((String) flash.get("email"));
        setServiceLevel((String) flash.get("serviceLevel"));
    }

    public String addConfirmedUser() {

	boolean added = true; // actual application may fail to add user
	FacesMessage doneMessage = null;
	String outcome = null;
	if (added) {
	    doneMessage = new FacesMessage("Successfully added new user");
	    outcome = "done?faces-redirect=true";
	} else {
	    doneMessage = new FacesMessage("Failed to add new user");
	    outcome = "register?faces-redirect=true";
	}
        FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        return outcome;
    }
}

