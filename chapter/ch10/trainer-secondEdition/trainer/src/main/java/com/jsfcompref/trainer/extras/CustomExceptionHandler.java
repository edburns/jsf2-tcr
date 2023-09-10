/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jsfcompref.trainer.extras;

import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler parent;

    public CustomExceptionHandler(ExceptionHandler parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.parent;
    }

    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            ExceptionQueuedEvent event = i.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
            Throwable t = context.getException();
            if (t instanceof ViewExpiredException) {
                ViewExpiredException vee = (ViewExpiredException) t;
                FacesContext fc = FacesContext.getCurrentInstance();

                NavigationHandler nav =
                        fc.getApplication().getNavigationHandler();
                try {
                    // Push some useful stuff to the flash scope for
                    // use in the page
                    fc.getExternalContext().getFlash().put("expiredViewId", vee.getViewId());

                    nav.handleNavigation(fc, null, "/login?faces-redirect=true");
                    fc.renderResponse();

                } finally {
                    i.remove();
                }
            }
        }
        // At this point, the queue will not contain any ViewExpiredEvents.
        // Therefore, let the parent handle them.
        getWrapped().handle();
    }



}
