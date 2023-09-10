package com.jsfcompref.components.renderer.wrapper;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.render.Renderer;

public abstract class BaseRendererWrapper extends Renderer {
  
  public abstract String getFamily();

  public abstract String getRendererType();

  private BaseRenderKitWrapper baseRenderKit;

  public BaseRenderKitWrapper getBaseRenderKit() {
    return baseRenderKit;
  }
  
  public void setBaseRenderKit(BaseRenderKitWrapper appleMobileRenderKit) {
    this.baseRenderKit = appleMobileRenderKit;
  }

  @Override
  public String convertClientId(FacesContext context, String clientId) {
    return getBaseRenderKit().getBaseRenderer(getFamily(),
			getRendererType()).convertClientId(context, clientId);
  }

  @Override
  public void decode(FacesContext context, UIComponent component) {
    getBaseRenderKit().getBaseRenderer(getFamily(),
                getRendererType()).decode(context, component);
  }

  @Override
  public void encodeBegin(FacesContext context, UIComponent component)
    throws IOException {
    getBaseRenderKit().getBaseRenderer(getFamily(),
                getRendererType()).encodeBegin(context, component);
  }

  @Override
  public void encodeChildren(FacesContext context, UIComponent component)
    throws IOException {
    getBaseRenderKit().getBaseRenderer(getFamily(),
                getRendererType()).encodeChildren(context, component);
  }

  @Override
  public void encodeEnd(FacesContext context, UIComponent component)
    throws IOException {
    getBaseRenderKit().getBaseRenderer(getFamily(),
                getRendererType()).encodeEnd(context, component);
  }

  @Override
  public Object getConvertedValue(FacesContext context, UIComponent component,
				  Object submittedValue) 
    throws ConverterException {
    return getBaseRenderKit().getBaseRenderer(getFamily(),
                getRendererType()).getConvertedValue(context, component,
                submittedValue);
  }

  @Override
  public boolean getRendersChildren() {
    return getBaseRenderKit().getBaseRenderer(getFamily(),
			       getRendererType()).getRendersChildren();
  }

}
