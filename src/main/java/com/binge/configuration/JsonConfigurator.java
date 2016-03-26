package com.binge.configuration;

import com.binge.util.JsonReader;
import com.binge.util.JsonWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class JsonConfigurator<T extends Configuration> extends FileConfigurator<T> {
    public static final int DEFAULT_VERSION = 1;

    protected JsonConfigurator(File file) {
        super(file);
    }

    protected T getConfiguration(BufferedReader in) throws IOException {
        Map<String, Object> properties = null;
        if (in != null) {
            JsonReader reader = new JsonReader(in);
            properties = reader.parseProperties();
        }
        return getConfiguration(properties);
    }

    protected void setConfiguration(PrintWriter out, T configuration) throws IOException {
        out.println("#!{ type = \"" + configuration.getType() + "\", version = " + getVersion() + " }");
        out.println();
        Map<String, Object> properties = getProperties(configuration);
        for (String key : properties.keySet()) {
            out.println(key + " = " + JsonWriter.write(properties.get(key), 2, 0));
        }
    }

    protected int getVersion() {
        return DEFAULT_VERSION;
    }

    protected String getExtension() {
        return "conf";
    }

    protected abstract T getConfiguration(Map<String, Object> properties) throws IOException;

    protected abstract Map<String, Object> getProperties(T configuration) throws IOException;

    protected static abstract class ObjectMapper<T> {

        public abstract Map<String, Object> serialize(T value);

        public abstract T deserialize(Map<String, Object> map);

        public static <T> Map<String, Object> serialize(T value, ObjectMapper<T> mapper) {
            if (value == null) {
                return new LinkedHashMap<String, Object>();
            }
            return mapper.serialize(value);
        }

        public static <T> List serialize(T[] values, ObjectMapper<T> mapper) {
            List list = new ArrayList();
            if (values != null) {
                for (T value : values) {
                    Map<String, Object> map = mapper.serialize(value);
                    if (map != null) {
                        list.add(map);
                    }
                }
            }
            return list;
        }

        public static <T> List serialize(List<T> values, ObjectMapper<T> mapper) {
            List list = new ArrayList();
            if (values != null) {
                for (T value : values) {
                    Map<String, Object> map = mapper.serialize(value);
                    if (map != null) {
                        list.add(map);
                    }
                }
            }
            return list;
        }

        public static <T> T getObject(Map<String, Object> properties, String name, ObjectMapper<T> mapper) {
            Object value = properties.get(name);
            if (value != null && value instanceof Map) {
                return mapper.deserialize((Map) value);
            }
            return null;
        }

        public static <T> List<T> getList(Map<String, Object> properties, String name, ObjectMapper<T> mapper) {
            List<T> values = new ArrayList<T>();
            Object value = properties.get(name);
            if (value != null && value instanceof List) {
                List<Map> maps = (List<Map>) value;
                for (Map map : maps) {
                    values.add(mapper.deserialize(map));
                }
            }
            return values;
        }

        public static String getString(Map<String, Object> properties, String name) {
            return getString(properties, name, null);
        }

        public static String getString(Map<String, Object> properties, String name, String defaultValue) {
            Object value = properties.get(name);
            if (value == null) {
                return defaultValue;
            }
            if (value instanceof String) {
                return (String) value;
            } else if (value instanceof Number) {
                return value.toString();
            } else {
                return value.toString();
            }
        }

        public static boolean getBoolean(Map<String, Object> properties, String name) {
            return getBoolean(properties, name, false);
        }

        public static boolean getBoolean(Map<String, Object> properties, String name, boolean defaultValue) {
            Object value = properties.get(name);
            if (value == null) {
                return defaultValue;
            } else if (value instanceof Boolean) {
                return ((Boolean) value).booleanValue();
            } else if (value instanceof String) {
                return Boolean.parseBoolean((String) value);
            } else {
                return defaultValue;
            }
        }

        public static int getInt(Map<String, Object> properties, String name) {
            return getInt(properties, name, 0);
        }

        public static int getInt(Map<String, Object> properties, String name, int defaultValue) {
            Object value = properties.get(name);
            if (value == null) {
                return defaultValue;
            } else if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            } else {
                return defaultValue;
            }
        }

        public static long getLong(Map<String, Object> properties, String name, long defaultValue) {
            Object value = properties.get(name);
            if (value == null) {
                return defaultValue;
            } else if (value instanceof Number) {
                return ((Number) value).longValue();
            } else if (value instanceof String) {
                return Long.parseLong((String) value);
            } else {
                return defaultValue;
            }
        }

        public static Date getDate(Map<String, Object> properties, String name) {
            Object value = properties.get(name);
            if (value == null) {
                return null;
            } else if (value instanceof Date) {
                return (Date) value;
            } else if (value instanceof String) {
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return format.parse((String) value);
                } catch (ParseException e) {
                    return null;
                }
            } else {
                return null;
            }
        }

        public static int[] getArray(Map<String, Object> properties, String name, int[] defaultValues) {
            Object value = properties.get(name);
            if (value == null) {
                return defaultValues;
            } else if (value instanceof String) {
                return new int[]{Integer.parseInt((String) value)};
            } else if (value instanceof List) {
                List<String> list = (List) value;
                int[] values = new int[list.size()];
                for (int i = 0; i < values.length; i++) {
                    Object obj = list.get(i);
                    if (obj instanceof String) {
                        values[i] = Integer.parseInt((String) obj);
                    } else if (obj instanceof Number) {
                        values[i] = ((Number) obj).intValue();
                    }
                }
                return values;
            } else {
                return defaultValues;
            }
        }

        public static String[] getArray(Map<String, Object> properties, String name, String[] defaultValues) {
            Object value = properties.get(name);
            if (value == null) {
                return defaultValues;
            } else if (value instanceof String) {
                return new String[]{(String) value};
            } else if (value instanceof List) {
                return (String[]) ((List) value).toArray(new String[0]);
            } else {
                return defaultValues;
            }
        }

        public static <T> T[] getArray(Map<String, Object> properties, String name, ObjectMapper<T> mapper, T[] defaultValues) {
            List<T> results = getList(properties, name, mapper);
            return results.toArray(defaultValues);
        }

        public static Map<String, String> getProperties(Map<String, Object> properties, String name) {
            Map<String, String> result = new LinkedHashMap<String, String>();
            Object value = properties.get(name);
            if (value != null && value instanceof Map) {
                for (String key : ((Map<String, Object>) value).keySet()) {
                    result.put(key, getString((Map<String, Object>) value, key));
                }
            }
            return result;
        }
    }
}