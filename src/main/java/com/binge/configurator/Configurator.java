package com.binge.configurator;

import com.binge.configuration.Configuration;
import com.binge.exception.DataBackendException;

public interface Configurator<T extends Configuration> {
    public T getConfiguration() throws DataBackendException;

    public void setConfiguration(T configuration) throws DataBackendException, DataBackendException;
}