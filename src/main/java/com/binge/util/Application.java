package com.binge.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-7
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class Application {
    public static final String ROOT_PATH;
    public static final String CONF_PATH;
    public static final String DATA_PATH;
    public static final String TEMP_PATH;
    public static final String KS_PATH;
    private static final Date START_TIME;

    static {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            ROOT_PATH = System.getProperty("application.home", "d:/opt/testweb");
        } else {
            ROOT_PATH = System.getProperty("application.home", "/opt/testweb");
        }
        CONF_PATH = ROOT_PATH + "/conf";
        KS_PATH = ROOT_PATH + "/keystore";
        DATA_PATH = ROOT_PATH + "/data";
        TEMP_PATH = DATA_PATH + "/tmp";

        try {
            loadProperties("application.properties");

            String language = getProperty("user.language", "zh");
            String country = getProperty("user.country", "CN");
            Locale.setDefault(new Locale(language, country));
            String timezone = getProperty("user.timezone", "PRC");
            TimeZone.setDefault(TimeZone.getTimeZone(timezone));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = getProperty("application.name", "admin");
        String host = getProperty("application.host", "127.0.0.1");
        int port = getProperty("application." + name + ".port", 6001);
        START_TIME = new Date();
    }


    public static Date getStartTime() {
        return START_TIME;
    }


    public static String getProperty(String name) {
        return System.getProperty(name);
    }

    public static String getProperty(String name, String defaultValue) {
        return System.getProperty(name, defaultValue);
    }

    public static String[] getProperty(String name, String[] defaultValue) {
        String value = System.getProperty(name);
        if (value == null) {
            return defaultValue;
        }
        return StringUtils.split(value, ",;");
    }

    public static int getProperty(String name, int defaultValue) {
        String value = System.getProperty(name);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public static long getProperty(String name, long defaultValue) {
        String value = System.getProperty(name);
        if (value == null) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    public static boolean getProperty(String name, boolean defaultValue) {
        String value = System.getProperty(name);
        if (value == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }

    public synchronized static void setProperty(String name, String value) {
        if (value == null) {
            System.clearProperty(name);
        } else {
            System.setProperty(name, value);
        }
    }

    public synchronized static void setProperties(Map<String, String> properties) {
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                System.clearProperty(name);
            } else {
                System.setProperty(name, value);
            }
        }
    }

    public synchronized static void setLocale(String language, String country) {
        setProperty("user.language", language);
        setProperty("user.country", country);
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);
    }

    public synchronized static void setTimeZone(String timezone) {
        setProperty("user.timezone", timezone);
        TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    }

    public static File createTempFile(String type, String prefix, String suffix) throws IOException {
        File tempPath = new File(TEMP_PATH, type);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        return new File(tempPath, prefix + UUID.randomUUID() + suffix);
    }

    public static File createTempFile(String type, String fileName) throws IOException {
        File tempPath = new File(TEMP_PATH, type);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        return new File(tempPath, fileName);
    }


    private static void loadProperties(String name) throws IOException {
        URL url = Application.class.getClassLoader().getResource(name);
        if (url != null) {
            setProperties(PropertiesUtils.getProperties(url));
        }
        File applicationFile = new File(CONF_PATH, name);
        if (applicationFile.exists()) {
            setProperties(PropertiesUtils.getProperties(applicationFile));
        }
    }

//    public synchronized static void saveProperties() throws IOException {
//        File file = new File(Application.CONF_PATH, "application.properties");
//        PrintWriter out = null;
//        try {
//            URL url = Application.getTemplateResource("application.properties");
//            out = new PrintWriter(file, "UTF-8");
//            for (String line : Resources.readLines(url, Charsets.UTF_8)) {
//                String name = getName(line);
//                if (name == null) {
//                    out.println(line);
//                    continue;
//                }
//                String value = Application.getProperty(name);
//                if (value == null) {
//                    out.println(line);
//                } else {
//                    out.println(name + "=" + value);
//                }
//            }
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//            } catch (Exception e) {
//            }
//        }
//    }


    public static void main(String[] args) {

    }
}
