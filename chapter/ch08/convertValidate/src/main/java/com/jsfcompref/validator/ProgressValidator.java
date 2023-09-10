/*
 * SkuValidator.java
 *
 * Created on September 18, 2006, 3:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jsfcompref.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="progressValidator")
public class ProgressValidator implements Validator {
    
    /** Creates a new instance of SkuValidator */
    public ProgressValidator() {
    }

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    }
    
}
