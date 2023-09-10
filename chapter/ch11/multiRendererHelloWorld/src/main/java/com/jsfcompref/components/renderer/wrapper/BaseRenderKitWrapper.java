package com.jsfcompref.components.renderer.wrapper;

import java.util.HashMap;
import java.util.Map;
import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.render.RenderKit;
import javax.faces.render.RenderKitFactory;
import javax.faces.render.RenderKitWrapper;
import javax.faces.render.Renderer;

public abstract class BaseRenderKitWrapper extends RenderKitWrapper {

  private RenderKit basic;
  private Map<String, Map<String, Renderer>> myFamilies =
    new HashMap<String, Map<String, Renderer>>();

  public BaseRenderKitWrapper() {
    RenderKitFactory factory = (RenderKitFactory)
      FactoryFinder.getFactory(FactoryFinder.RENDER_KIT_FACTORY);
    basic = factory.getRenderKit(FacesContext.getCurrentInstance(),
				 "HTML_BASIC");
  }
  
  @Override
    public RenderKit getWrapped() {
    return basic;
  }
  
  @Override
    public void addRenderer(String family, String rendererType, Renderer renderer) {
    Map<String, Renderer> renderersForFamily = myFamilies.get(family);
    if (null == renderersForFamily) {
      renderersForFamily = new HashMap<String, Renderer>();
      myFamilies.put(family, renderersForFamily);
    }
    renderersForFamily.put(rendererType, renderer);
  }
  
  @Override
    public Renderer getRenderer(String family, String rendererType) {
    Renderer result = null;
    Map<String, Renderer> renderersForFamily = myFamilies.get(family);
    if (null != renderersForFamily) {
      result = renderersForFamily.get(rendererType);
      if (result instanceof BaseRendererWrapper) {
	((BaseRendererWrapper)result).setBaseRenderKit(this);
      }
    }
    if (null == result) {
      result = basic.getRenderer(family, rendererType);
    }
    return result;
  }
  
  public Renderer getBaseRenderer(String family, String rendererType) {
    Renderer result = null;
    
    result = basic.getRenderer(family, rendererType);
    
    return result;
  }
    
}

