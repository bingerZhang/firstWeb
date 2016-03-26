package com.binge.module;

import java.awt.*;

public class MenuItem {

    private String id;
    private String module;
    private String path;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuItem(String name, String url) {
        this.id = name.replaceAll("\\.", "\\_");
        String[] parts = name.split("\\.");
        this.module = parts.length > 1 ? parts[1] : "";
        this.path = parts.length > 3 ? parts[3] : "";
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.id != null && obj instanceof MenuItem && this.id.equals(((MenuItem) obj).id);
    }
}
