package com.jsfcompref;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Model {

    protected boolean booleanValue = false;

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    protected String successOutcome = "/success.xhtml";

    public String getSuccessOutcome() {
        return successOutcome;
    }

    public void setSuccessOutcome(String successOutcome) {
        this.successOutcome = successOutcome;
    }
    protected String failureOutcome = "/failure.xhtml";

    public String getFailureOutcome() {
        return failureOutcome;
    }

    public void setFailureOutcome(String failureOutcome) {
        this.failureOutcome = failureOutcome;
    }


 }

