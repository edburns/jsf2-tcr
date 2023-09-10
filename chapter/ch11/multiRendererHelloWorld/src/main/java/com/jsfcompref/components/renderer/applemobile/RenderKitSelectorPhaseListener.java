package com.jsfcompref.components.renderer.applemobile;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class RenderKitSelectorPhaseListener implements PhaseListener {

  public PhaseId getPhaseId() {
    return PhaseId.RESTORE_VIEW;
  }

  private void setRenderKitFromUserAgent(FacesContext context, String userAgent) {
    // To implement this kind of feature in a robust way requires a lot
    // of research and will result in lots of heuristics.  This version
    // is very simple and just looks for the strings iPod or iPhone
    // to determine if the User-Agent is coming from an iPhone or iPod
    // touch.
    if (userAgent.contains("iPod") || userAgent.contains("iPhone")) {
      context.getViewRoot().setRenderKitId(AppleMobileRenderKit.RENDER_KIT_ID);
    }
  }

  public void afterPhase(PhaseEvent event) {
    setRenderKitFromUserAgent(event.getFacesContext(), event.getFacesContext().
            getExternalContext().getRequestHeaderMap().get("User-Agent"));
  }

  public void beforePhase(PhaseEvent event) {
  }
}
