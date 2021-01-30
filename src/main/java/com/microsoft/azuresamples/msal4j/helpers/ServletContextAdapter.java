package com.microsoft.azuresamples.msal4j.helpers;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implementation of IContextAdapter for AuthHelper for use with Java HttpServletRequests/Responses
 * MUST BE INSTANTIATED ONCE PER REQUEST IN WEB APPS / WEB APIs before passing to AuthHelper
 */

public class ServletContextAdapter implements IContextAdapter, HttpSessionActivationListener {
    private static Logger logger = Logger.getLogger(ServletContextAdapter.class.getName());
    private HttpSession session = null;
    private IdentityContextData context = null;
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    public ServletContextAdapter(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.session = request.getSession();
        this.response = response;
    }

    // load from session on session activation
    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        this.session = se.getSession();
        loadContext();
    }

    // save to session on session passivation
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        this.session = se.getSession();
        saveContext();
    }

    public void saveContext() {
        if (this.context == null)
            this.context = new IdentityContextData();

        if (this.context.hasChanged())
            this.session.setAttribute(Config.SESSION_PARAM, context);
    }

    public void loadContext() {
        this.context = (IdentityContextData) session.getAttribute(Config.SESSION_PARAM);
        if (this.context == null) {
            this.context = new IdentityContextData();
        }
    }

    @Override
    public IdentityContextData getContext(){
        loadContext();
        return this.context;
    }

    @Override
    public void setContext(IdentityContextData context){
        this.context = context;
        saveContext();
    }

    @Override
    public void redirectUser(String location) {
        logger.log(Level.INFO, "Redirecting user to {0}", location);
        try {
            this.response.sendRedirect(location);
        } catch (IOException ex) {
            // fill this later
        }
    }

    @Override
    public String getParameter(String parameterName) {
        return this.request.getParameter(parameterName);
    }

}
