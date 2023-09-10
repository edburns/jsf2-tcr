/*
 * User.java
 *
 * Created on September 18, 2006, 4:33 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jsfcompref.model;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author edburns
 */
public class User {
    
    /** Creates a new instance of User */
    public User() {
    }

    /**
     * Holds value of property preferences.
     */
    private Map preferences;

    /**
     * Getter for property preferences.
     * @return Value of property preferences.
     */
    public Map getPreferences() {
        if (null == this.preferences) {
            this.preferences = new HashMap();
        }
        return this.preferences;
    }
    
    public void setPreferences(Map preferences) {
        this.preferences = preferences;
    }

    /**
     * Holds value of property currentWeight.
     */
    private float currentWeight;

    /**
     * Getter for property currentWeight.
     * @return Value of property currentWeight.
     */
    public float getCurrentWeight() {
        return this.currentWeight;
    }

    /**
     * Setter for property currentWeight.
     * @param currentWeight New value of property currentWeight.
     */
    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    /**
     * Holds value of property creditCard.
     */
    private String creditCard;

    /**
     * Getter for property creditCard.
     * @return Value of property creditCard.
     */
    public String getCreditCard() {
        return this.creditCard;
    }

    /**
     * Setter for property creditCard.
     * @param creditCard New value of property creditCard.
     */
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * Holds value of property zipcode.
     */
    private Integer zipcode;

    /**
     * Getter for property zipcode.
     * @return Value of property zipcode.
     */
    public Integer getZipcode() {
        return this.zipcode;
    }

    /**
     * Setter for property zipcode.
     * @param zipcode New value of property zipcode.
     */
    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }
    
    public void validateAge(FacesContext context, UIComponent component, Object value) {
        if (18 > ((Integer) value).intValue()) {
            throw new ValidatorException(new FacesMessage("Must be at least 18."));
        }
    }

    /**
     * Holds value of property age.
     */
    private int age;

    /**
     * Getter for property age.
     * @return Value of property age.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Setter for property age.
     * @param age New value of property age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Holds value of property status.
     */
    private Map status;

    /**
     * Getter for property status.
     * @return Value of property status.
     */
    public Map getStatus() {
        return this.status;
    }

    /**
     * Setter for property status.
     * @param status New value of property status.
     */
    public void setStatus(Map status) {
        this.status = status;
    }
    
    public String checkForFatalError() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        
        // Test method, queue some errors
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "warning", "warning"));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "info", "info"));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                "fatal", "fatal"));
        
        FacesMessage.Severity severity = context.getMaximumSeverity();
        String result = "checkForFatalError2";
        
        if (null != severity) {
            if (severity == FacesMessage.SEVERITY_FATAL) {
                result = "fatalError";
            }
        }
        return result;
    }
}
