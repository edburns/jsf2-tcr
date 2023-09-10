package com.jsfcompref;

import java.util.HashMap;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.SystemEventListener;

public class ApplicationListener implements SystemEventListener {

    public boolean isListenerForSource(Object app) {
        boolean result = false;

        result = (app instanceof Application);

        return result;
    }

    public void processEvent(SystemEvent app) throws AbortProcessingException {
        Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        if (app instanceof PostConstructApplicationEvent) {
            appMap.put("zips", createZipDatabase());
        } else if (app instanceof PreDestroyApplicationEvent) {
            Map<String, Map<String, Object>> zips =
                    (Map<String, Map<String, Object>>) appMap.remove("zips");
            zips.clear();
        }
    }

    public Map<String, Map<String, Object>> createZipDatabase() {
        Map<String, Map<String, Object>> result = new HashMap<String, Map<String, Object>>(1);

        Map<String, Object> innerMap = new HashMap<String, Object>(2);
        innerMap.put("city", "Dallas");
        innerMap.put("state", "Texas");

        String dallasZips[] = {
            "75201", "75202", "75203", "75204", "75205", "75206", "75207", "75208", "75209", "75210", "75211", "75212", "75214", "75215", "75216", "75217", "75218", "75219", "75220", "75221", "75222", "75223", "75224", "75225", "75226", "75227", "75228", "75229", "75230", "75231", "75232", "75233", "75234", "75235", "75236", "75237", "75238", "75240", "75241", "75242", "75243", "75244", "75245", "75246", "75247", "75248", "75249", "75250", "75251", "75252", "75253", "75254", "75258", "75260", "75261", "75262", "75263", "75264", "75265", "75266", "75267", "75270", "75275", "75277", "75283", "75284", "75285", "75286", "75287", "75301", "75303", "75310", "75312", "75313", "75315", "75320", "75323", "75326", "75334", "75336", "75339", "75340", "75342", "75343", "75344", "75354", "75355", "75356", "75357", "75358", "75359", "75360", "75367", "75368", "75370", "75371", "75372", "75373", "75374", "75376", "75378", "75379", "75380", "75381", "75382", "75387", "75389", "75390", "75391", "75392", "75393", "75394", "75395", "75397", "75398"
        };
        for (String zip : dallasZips) {
            result.put(zip, innerMap);
        }

        return result;
    }


}
