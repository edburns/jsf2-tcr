package com.jsfcompref;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="zipAutoFill")
@RequestScoped
public class ZipAutoFillBackingBean {

    private HtmlInputText inputName, inputZip, inputCity, inputState;

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
        Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        Map<String, Map<String, Object>> zips =
                (Map<String, Map<String, Object>>) appMap.get("zips");
        Map<String, Object> dataForZip = zips.get(zip);
        if (null != dataForZip) {
            city.append((String) dataForZip.get("city"));
            state.append((String) dataForZip.get("state"));
        } else {
            city.append("Unknown City");
            state.append("Unknown State, maybe Texas?");
        }

    }
    
}

