/*
 * SkuValidator.java
 *
 * Created on September 18, 2006, 3:09 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jsfcompref.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="skuValidator")
public class SkuValidator implements Validator {
    
    /** Creates a new instance of SkuValidator */
    public SkuValidator() {
    }

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String errorMessage = "",
               valueAsString;
        boolean validationFailed = false;
        // simple code, just check for 12 character length where each character
        // is a digit
        if (null != value && 12 == (valueAsString = value.toString()).length()) {
            for (char cur : valueAsString.toCharArray()) {
                if (validationFailed = !Character.isDigit(cur)) {
                    errorMessage = "Each character in SKU must be a digit.";
                    break;
                }
            }
        } else {
            validationFailed = true;
            errorMessage = "Value " + value + " is not 12 characters long.  A SKU must be 12 characters long.";
        }
        if (validationFailed) {
            throw new ValidatorException(new FacesMessage(errorMessage));
        }
        
    }
    
}
