/*
 * ProgrammaticConverterAdditionPhaseListener.java
 *
 * Created on September 18, 2006, 3:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jsfcompref.validator;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.faces.validator.MethodExpressionValidator;
import javax.faces.validator.Validator;

/**
 *
 * @author edburns
 */
public class ProgrammaticValidatorAdditionPhaseListener implements PhaseListener {
    
    /** Creates a new instance of ProgrammaticConverterAdditionPhaseListener */
    public ProgrammaticValidatorAdditionPhaseListener() {
    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

    public void beforePhase(PhaseEvent event) {
        FacesContext context = event.getFacesContext();
        Validator progressValidator = null;
        MethodExpression pointerToValidatorMethod = null;
        EditableValueHolder component = null;
        UIViewRoot root = context.getViewRoot();
        
        component = (EditableValueHolder) root.findComponent("form" + 
                UINamingContainer.getSeparatorChar(context) +
                "userComponent");

        if (null == component) {
            return;
        }
        
        Validator [] validators = component.getValidators();
        boolean found = false;
        for (int i = 0; i < validators.length && !found; i++) {
            found = (validators[i] instanceof ProgressValidator);
        }
        if (found) {
            return;
        }
        
        progressValidator = context.getApplication().createValidator("progressValidator");
        
        component.addValidator(progressValidator);
        context.getExternalContext().getRequestMap().put("progressValidatorAdded", 
                    "Added ProgressValidator");
        
        Class [] params = {FacesContext.class, UIComponent.class, Object.class };
        ELContext elContext = context.getELContext();
        pointerToValidatorMethod = context.getApplication().
                getExpressionFactory().createMethodExpression(elContext, "#{user.validateAge}", null,
                params);
        component.addValidator(new MethodExpressionValidator(pointerToValidatorMethod));
        context.getExternalContext().getRequestMap().put("methodExpressionValidatorAdded",
                    "MethodExpression Validator Set");

    }

    public void afterPhase(PhaseEvent event) {
    }
    
}
