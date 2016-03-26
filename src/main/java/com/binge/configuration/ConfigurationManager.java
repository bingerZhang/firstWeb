package com.binge.configuration;


import com.binge.exception.DataBackendException;

public interface ConfigurationManager {
    public <T extends Configuration> T getConfiguration(Class<T> clazz) throws DataBackendException;

    public <T extends Configuration> void setConfiguration(T configuration) throws DataBackendException;
}