package com.binge.configuration;

import java.io.Serializable;

public interface Configuration extends Serializable, Cloneable {
    public String getType();

    public Object clone();
}