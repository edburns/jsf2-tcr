/*
 * SkuConverter.java
 *
 * Created on September 18, 2006, 3:07 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.jsfcompref.convert;

import com.jsfcompref.model.SKU;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="skuConverter")
public class SkuConverter implements Converter {
    
    /** Creates a new instance of SkuConverter */
    public SkuConverter() {
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = "";
        try {
            result = ((SKU) value).toString();
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage("Cannot convert value " + value + " to String"));
        }
        return result;
    }

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        SKU result;

        try {
            result = new SKU(value.toCharArray());
        } catch (Exception e) {
            throw new ConverterException(new FacesMessage("Cannot convert value " + value + " to SKU"));
        }
        return result;
    }
    
}
