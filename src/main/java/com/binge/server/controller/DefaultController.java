package com.binge.server.controller;

import com.binge.configuration.Configuration;
import com.binge.configuration.DefaultConfigurationManager;
import com.binge.exception.DataBackendException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

/**
 * Created by user on 2015/6/5.
 */
public class DefaultController implements ApplicationContextAware {

    protected ApplicationContext applicationContext;
    protected static final String INPUT = "redirect:/error";

    @Autowired
    protected HttpServletRequest request;
    protected static final String ERRORS = "fieldErrors";

    protected <T extends Configuration> T getConfiguration(Class<T> clazz) {
        try {
            return DefaultConfigurationManager.getInstance().getConfiguration(clazz);
        } catch (DataBackendException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void setConfiguration(Configuration configuration) {
        try {
            DefaultConfigurationManager.getInstance().setConfiguration(configuration);
        } catch (DataBackendException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected void addFieldError(String field, String message) {
        LinkedHashMap<String, String> fieldErrors;
        if (request.getAttribute(ERRORS) != null) {
            fieldErrors = (LinkedHashMap<String, String>) request.getAttribute(ERRORS);
        } else {
            fieldErrors = new LinkedHashMap<String, String>();
        }
        fieldErrors.put(field, message);
        request.setAttribute(ERRORS, fieldErrors);
    }

    protected boolean hasErrors() {
        return request.getAttribute(ERRORS) != null;
    }

}
