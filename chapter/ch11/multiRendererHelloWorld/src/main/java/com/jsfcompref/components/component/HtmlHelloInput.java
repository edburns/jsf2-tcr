
package com.jsfcompref.components.component;

import java.io.IOException;
import java.util.Map;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

@FacesComponent(value="HtmlHelloInput")
public class HtmlHelloInput extends UIInput {

    public HtmlHelloInput() {
        setRendererType(null);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        String clientId = getClientId(context);
        char sep = UINamingContainer.getSeparatorChar(context);
        encodeInputField(context, clientId + sep + "inputfield");
        encodeSubmitButton(context, clientId + sep + "submit");
        encodeOutputField(context);
    }

    private void encodeInputField(FacesContext context, String clientId)
            throws IOException {
        // Render a standard HTML input field
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", this);
        writer.writeAttribute("type", "text", null);
        writer.writeAttribute("name", clientId, "clientId");
        Object value = getValue();
        if (value != null) {
            writer.writeAttribute("value", value.toString(), "value");
        }
        writer.writeAttribute("size", "6", null);
        writer.endElement("input");
    }

    private void encodeSubmitButton(FacesContext context, String clientId)
            throws IOException {
        // render a submit button
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", this);
        writer.writeAttribute("type", "Submit", null);
        writer.writeAttribute("name", clientId, "clientId");
        writer.writeAttribute("value", "Click Me!", null);
        writer.endElement("input");
    }

    private void encodeOutputField(FacesContext context)
            throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String hellomsg = (String) getAttributes().get("value");
        writer.startElement("p", this);
        writer.writeText("You entered: " + hellomsg, null);
        writer.endElement("p");
    }

    @Override
    public void decode(FacesContext context) {
        Map requestMap = context.getExternalContext().getRequestParameterMap();
        String clientId = getClientId(context);
        char sep = UINamingContainer.getSeparatorChar(context);
        String submitted_hello_msg =
                ((String) requestMap.get(clientId + sep + "inputfield"));
        setSubmittedValue(submitted_hello_msg);
    }



}
