package com.jsfcompref.components.renderer.applemobile;

import com.jsfcompref.components.renderer.wrapper.BaseRenderKitWrapper;
import javax.faces.context.FacesContext;

public class AppleMobileRenderKit extends BaseRenderKitWrapper {

  public static final String RENDER_KIT_ID = "HTML_APPLEMOBILE";
  public static final String RESOURCE_LIBRARY_NAME = "applemobile";
  
  static String getRequestPathForResource(FacesContext context,
					  String resourceName) {
    String result = null;
    String expressionString = "#{resource['" + RESOURCE_LIBRARY_NAME + ":" +
      resourceName + "']}";
    result = context.getApplication().evaluateExpressionGet(context,
				    expressionString, String.class);
    return result;
  }
}
