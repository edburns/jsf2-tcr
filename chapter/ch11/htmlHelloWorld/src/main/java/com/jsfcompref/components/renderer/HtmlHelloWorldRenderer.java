package com.jsfcompref.components.renderer;

import java.io.IOException;
import java.util.Map;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

@FacesRenderer(componentFamily="javax.faces.Input", rendererType="HtmlHelloWorld")
public class HtmlHelloWorldRenderer extends Renderer{

  @Override
  public void decode(FacesContext context, UIComponent component) {
    Map requestMap = context.getExternalContext().getRequestParameterMap();
    String clientId = component.getClientId(context);
    char sep = UINamingContainer.getSeparatorChar(context);
    String submitted_hello_msg =
        ((String) requestMap.get(clientId + sep + "inputfield"));
    ((EditableValueHolder)component).setSubmittedValue(submitted_hello_msg);

  }

  @Override
  public void encodeBegin(FacesContext context, UIComponent component) throws IOException {

  }

  @Override
  public void encodeChildren(FacesContext context, UIComponent component) throws IOException {

  }

  @Override
  public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
    String clientId = component.getClientId(context);
    char sep = UINamingContainer.getSeparatorChar(context);
    ResponseWriter writer = context.getResponseWriter();
    writer.startElement("div", component);
    writer.writeAttribute("style", "padding: 8px; margin: 10px 0; border: 1px solid #CCC; background-color: #f9f9f9;", "style");
    encodeInputField(context, component, clientId + sep + "inputfield");
    encodeSubmitButton(context, component, clientId + sep + "submit");
    encodeOutputField(context, component);
    writer.endElement("div");
  }

  private void encodeInputField(FacesContext context,
      UIComponent component,
      String clientId)
      throws IOException {
    // Render a standard HTML input field
    EditableValueHolder editable = (EditableValueHolder) component;
    ResponseWriter writer = context.getResponseWriter();
    writer.startElement("input", component);
    writer.writeAttribute("type", "text", "type");
    writer.writeAttribute("name", clientId, "clientId");
    Object value = editable.getValue();
    if (value != null) {
      writer.writeAttribute("value", value.toString(), "value");
    }
    writer.writeAttribute("size", "6", "size");
    writer.endElement("input");
  }

  private void encodeSubmitButton(FacesContext context, UIComponent component,
      String clientId)
      throws IOException {
    // render a submit button
    ResponseWriter writer = context.getResponseWriter();
    writer.startElement("input", component);
    writer.writeAttribute("type", "Submit", "type");
    writer.writeAttribute("name", clientId, "clientId");
    writer.writeAttribute("value", "Click Me!", "value");
    writer.endElement("input");
  }

  private void encodeOutputField(FacesContext context, UIComponent component)
      throws IOException {
    ResponseWriter writer = context.getResponseWriter();
    String hellomsg = (String) component.getAttributes().get("value");
    if (null != hellomsg) {
      writer.startElement("p", component);
      writer.writeText("You entered: " + hellomsg, null);
      writer.endElement("p");
    }
  }
}
