package com.jsfcompref.components.renderer.applemobile;

import com.jsfcompref.components.renderer.wrapper.BaseRendererWrapper;
import java.io.IOException;
import java.io.StringWriter;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;

@FacesRenderer(componentFamily="javax.faces.Output", 
               rendererType="javax.faces.Body",
               renderKitId=AppleMobileRenderKit.RENDER_KIT_ID)
@ResourceDependency(name = AppleMobileRenderKit.RESOURCE_LIBRARY_NAME + ".css",
                    library = AppleMobileRenderKit.RESOURCE_LIBRARY_NAME)
public class AppleMobileBodyRenderer extends BaseRendererWrapper {

  @Override
  public String getFamily() {
    return "javax.faces.Output";
  }

  @Override
  public String getRendererType() {
    return "javax.faces.Body";
  }

  @Override
  public void encodeBegin(FacesContext context, UIComponent component) 
    throws IOException {
    ResponseWriter writer = context.getResponseWriter();
    StringWriter buf = new StringWriter();

    // Allow the base class writer to actually write out the body element
    try {
      ResponseWriter clonedWriter = writer.cloneWithWriter(buf);
      context.setResponseWriter(clonedWriter);
      super.encodeBegin(context, component);


      // write out a background attribute with an apple mobile specific image
      String requestPath = AppleMobileRenderKit.
              getRequestPathForResource(context, "bg.gif");
      // check if the body element already has a style attribute.
      if (buf.toString().contains("style=")) {
        // exercise for the reader
      } else {
        // It does not have one, we can simply write it.
        clonedWriter.writeAttribute("style", "background: url(" + requestPath +
                                    ") repeat top center;", "style");
      }
      clonedWriter.close();
    } finally {
      context.setResponseWriter(writer);
    }

    if (null != buf) {
      writer.write(buf.toString());
    }

  }

}
