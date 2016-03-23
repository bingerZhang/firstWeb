package com.binge.configuration;

import com.surfront.exception.DataBackendException;
import com.surfront.manage.configure.Configuration;

public interface Configurator<T extends Configuration> {
    public T getConfiguration() throws DataBackendException;

    public void setConfiguration(T configuration) throws DataBackendException;
}