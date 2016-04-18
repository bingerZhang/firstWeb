package com.binge.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zlb on 2016/4/18.
 */
public class MapConfiguration implements Configuration {
    public static final String TYPE = "map";
    private Map<String,Object> cityMap;
    private Map<String,Object> typeMap;
    private Map<String,Object> brandtypeMap;

    public Map<String, Object> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, Object> cityMap) {
        this.cityMap = cityMap;
    }

    public Map<String, Object> getTypeMap() {
        return typeMap;
    }

    public void setTypeMap(Map<String, Object> typeMap) {
        this.typeMap = typeMap;
    }

    public Map<String, Object> getBrandtypeMap() {
        return brandtypeMap;
    }

    public void setBrandtypeMap(Map<String, Object> brandtypeMap) {
        this.brandtypeMap = brandtypeMap;
    }

    public void add(Map<String,Object> map,String key, Object value)
    {
        if(map==null){
            map = new HashMap<String,Object>();
        }
        map.put(key,value);
    }

    public void del(Map<String,Object> map,String key)
    {
        if(map.containsKey(key)){
            map.remove(key);
        }
    }

    public Object get(Map<String,Object> map,String key){
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
