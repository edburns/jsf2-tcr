/*
 * ProgrammaticConverterAdditionPhaseListener.java
 *
 * Created on September 18, 2006, 3:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jsfcompref.convert;

import javax.faces.component.UINamingContainer;
import javax.faces.component.UIViewRoot;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 *
 * @author edburns
 */
public class ProgrammaticConverterAdditionPhaseListener implements PhaseListener {
    
    /** Creates a new instance of ProgrammaticConverterAdditionPhaseListener */
    public ProgrammaticConverterAdditionPhaseListener() {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        Converter intConverter = null;
        Converter floatConverter = null;
        ValueHolder component1 = null;
        ValueHolder component2 = null;
        UIViewRoot root = context.getViewRoot();
        
        component1 = (ValueHolder) root.findComponent("form" + UINamingContainer.getSeparatorChar(context) +
                "intComponent");
        component2 = (ValueHolder) root.findComponent("form" + UINamingContainer.getSeparatorChar(context) +
                "floatComponent");

        if (null != component1 && null == component1.getConverter()) {
            intConverter = context.getApplication().createConverter("javax.faces.Integer");
            component1.setConverter(intConverter);
            context.getExternalContext().getRequestMap().put("intConverterAdded", 
                    "Added int converter");
        }
        
        if (null != component1 && null == component2.getConverter()) {
            floatConverter = context.getApplication().createConverter(Float.class);
            component2.setConverter(floatConverter);
            context.getExternalContext().getRequestMap().put("floatConverterAdded", 
                    "Added float converter");
        }
        
    }

    public void afterPhase(PhaseEvent event) {
    }
    
}
