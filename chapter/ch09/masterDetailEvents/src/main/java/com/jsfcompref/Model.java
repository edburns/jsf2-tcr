package com.jsfcompref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class Model {

    private Map<String, List<UserBean>> data;

    public Model() {
        data = new HashMap<String, List<UserBean>>();
        // Populate the model with data in the constructor.  Naturally
        // in a real application, the model would be populated some
        // other way
        List<UserBean> users = new ArrayList<UserBean>();

        // users with "Medium" service level
        users.add(makeUser("Bob", "Biceps", "Medium", "M"));
        users.add(makeUser("Frank", "Forearms", "Medium", "M"));
        users.add(makeUser("Sherry", "Shins", "Medium", "F"));
        users.add(makeUser("Alice", "Abs", "Medium", "F"));
        data.put(users.get(0).getServiceLevel(), users);

        users = new ArrayList<UserBean>();

        // users with "Basic" service level
        users.add(makeUser("Pete", "Pectorals", "Basic", "M"));
        users.add(makeUser("Neil", "Neck", "Basic", "M"));
        users.add(makeUser("Ellen", "Elbows", "Basic", "F"));
        users.add(makeUser("Tina", "Tummy", "Basic", "F"));
        data.put(users.get(0).getServiceLevel(), users);

        users = new ArrayList<UserBean>();

        // users with "Premium" service level
        users.add(makeUser("Bernd", "Beine", "Premium", "M"));
        users.add(makeUser("Rolf", "RŸckenschmerz", "Premium", "M"));
        users.add(makeUser("Bettina", "Bauch", "Premium", "F"));
        users.add(makeUser("Frauke", " Fu§knšchel", "Premium", "F"));
        data.put(users.get(0).getServiceLevel(), users);


    }

    public Set<String> getKeys() {
        return data.keySet();
    }

    private UserBean makeUser(String firstName, String lastName,
            String serviceLevel, String sex) {
        UserBean result = new UserBean();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        result.setServiceLevel(serviceLevel);
        result.setSex(sex);
        return result;
    }
    
    public Map<String,List<UserBean>> getData() {
        return data;
    }

    protected String serviceLevel = "Basic";

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public void menuValueChanged(ValueChangeEvent vce) {
        FacesContext context = FacesContext.getCurrentInstance();
        Flash flash = context.getExternalContext().getFlash();
        String
                oldValue = vce.getOldValue().toString(),
                newValue = vce.getNewValue().toString();

        flash.put("valueChangeMessage", "Value changed from " + oldValue +
                " to " + newValue);
        this.setServiceLevel(newValue);
    }

    public void checkDRPL(ComponentSystemEvent cse) throws AbortProcessingException {
        ValueHolder lastName = (ValueHolder) cse.getComponent();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String,Object> requestMap = context.getExternalContext().getRequestMap();

        // check the government "Denied or Restricted Person's List" for this last name
        String restrictedName = "Abs";
        if (restrictedName.equals(lastName.getValue())) {
            String message = 
                    "Warning, record lookup for restricted person " +
                    lastName.getValue().toString();
            requestMap.put("drplMessage", message);
        }
    }

}
