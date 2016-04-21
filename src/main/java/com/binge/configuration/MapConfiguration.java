package com.binge.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class MapConfiguration implements Configuration {
    public static final String TYPE = "map";
    private Map<String,String> cityMap;
    private Map<String,String> typeMap;
    private Map<String,String> brandtypeMap;

    public Map<String, String> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, String> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, String> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, String> typeMap) {
        this.typeMap = typeMap;
    }

    public Map<String, String> getBrandtypeMap() {
        return brandtypeMap;
    }

    public void setBrandtypeMap(Map<String, String> brandtypeMap) {
        this.brandtypeMap = brandtypeMap;
    }

    public void add(Map<String,String> map,String key, String value)
    {
        if(map==null){
            map = new HashMap<String,String>();
        }
        map.put(key,value);
    }

    public void del(Map<String,String> map,String key)
    {
        if(map.containsKey(key)){
            map.remove(key);
        }
    }

    public String get(Map<String,String> map,String key){
        if(map!=null)return map.get(key);
        return null;
    }

    @Override
    public String getType() {
        return TYPE;
    }
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
