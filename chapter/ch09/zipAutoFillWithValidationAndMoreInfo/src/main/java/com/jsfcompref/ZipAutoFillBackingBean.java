package com.jsfcompref;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlInputTextarea;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="zipAutoFill")
@RequestScoped
public class ZipAutoFillBackingBean {

    private HtmlInputText inputName, inputZip, inputCity, inputState;

    private HtmlInputTextarea moreInfo;
    private HtmlSelectBooleanCheckbox moreInfoCheckbox;
    private boolean infoRendered = false;

    /**
     * @return the inputName
     */
    public HtmlInputText getInputName() {
        return inputName;
    }

    /**
     * @param inputName the inputName to set
     */
    public void setInputName(HtmlInputText inputName) {
        this.inputName = inputName;
    }

    /**
     * @return the inputZip
     */
    public HtmlInputText getInputZip() {
        return inputZip;
    }

    /**
     * @param inputZip the inputZip to set
     */
    public void setInputZip(HtmlInputText inputZip) {
        this.inputZip = inputZip;
    }

    /**
     * @return the inputCity
     */
    public HtmlInputText getInputCity() {
        return inputCity;
    }

    /**
     * @param inputCity the inputCity to set
     */
    public void setInputCity(HtmlInputText inputCity) {
        this.inputCity = inputCity;
    }

    /**
     * @return the inputState
     */
    public HtmlInputText getInputState() {
        return inputState;
    }

    /**
     * @param inputState the inputState to set
     */
    public void setInputState(HtmlInputText inputState) {
        this.inputState = inputState;
    }

    public void zipAutoFillListener(ValueChangeEvent vce) {
        String zip = vce.getNewValue().toString();

        StringBuilder city = new StringBuilder();
        StringBuilder state = new StringBuilder();
        performLookup(zip, city, state);

        inputCity.setValue(city.toString());
        inputState.setValue(state.toString());
    }

    private void performLookup(String zip, StringBuilder city,
            StringBuilder state) {
        // Production code would actually perform the lookup.
        // For now, we assume that every possible zip code is
        // in Dallas, Texas.
        city.append("Dallas");
        state.append("Texas");

    }

    public void toggleMoreInfo(ValueChangeEvent vce) {
        setInfoRendered((Boolean) vce.getNewValue());
        
    }

    /**
     * @return the moreInfo
     */
    public HtmlInputTextarea getMoreInfo() {
        return moreInfo;
    }

    /**
     * @param moreInfo the moreInfo to set
     */
    public void setMoreInfo(HtmlInputTextarea moreInfo) {
        this.moreInfo = moreInfo;
    }

    /**
     * @return the moreInfoCheckbox
     */
    public HtmlSelectBooleanCheckbox getMoreInfoCheckbox() {
        return moreInfoCheckbox;
    }

    /**
     * @param moreInfoCheckbox the moreInfoCheckbox to set
     */
    public void setMoreInfoCheckbox(HtmlSelectBooleanCheckbox moreInfoCheckbox) {
        this.moreInfoCheckbox = moreInfoCheckbox;
    }

    /**
     * @return the infoRendered
     */
    public boolean isInfoRendered() {
        return infoRendered;
    }

    /**
     * @param infoRendered the infoRendered to set
     */
    public void setInfoRendered(boolean infoRendered) {
        this.infoRendered = infoRendered;
    }
    
}

