package com.binge.configuration;

import com.binge.exception.DataBackendException;

public class DefaultConfigurator<T extends Configuration> implements Configurator<T> {
    private final Configurator<T> configurator;
    private T configuration;

    public DefaultConfigurator(Configurator<T> configurator) {
        this.configurator = configurator;
    }

    public Configurator<T> getConfigurator() {
        return configurator;
    }

    public synchronized T getConfiguration() throws DataBackendException {
        return getConfiguration(false);
    }

    public synchronized T getConfiguration(boolean reload) throws DataBackendException {
        if (configuration == null || reload) {
            configuration = configurator.getConfiguration();
        }
        return (T) configuration.clone();
    }

    public synchronized void setConfiguration(T configuration) throws DataBackendException {
        configurator.setConfiguration(configuration);
        this.configuration = (T) configuration.clone();
    }
}