package com.binge.util;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-7
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public class JsonWriter {
    public static String write(Object value) {
        if (value == null || value.equals(null)) {
            return "null";
        } else if (value instanceof Number) {
            return write((Number) value);
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Date) {
            return quote(formatDateTime((Date) value));
        } else if (value instanceof List) {
            return write((List) value);
        } else if (value instanceof Collection) {
            return write(toList((Collection) value));
        } else if (value instanceof Map) {
            return write((Map<String, Object>) value);
        } else if (value.getClass().isArray()) {
            return write(toList(value));
        } else {
            return quote(value.toString());
        }
    }

    public static String write(Object value, int indentFactor, int indent) {
        if (value == null || value.equals(null)) {
            return "null";
        } else if (value instanceof Number) {
            return write((Number) value);
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof Date) {
            return quote(formatDateTime((Date) value));
        } else if (value instanceof List) {
            return write((List) value, indentFactor, indent);
        } else if (value instanceof Collection) {
            return write(toList((Collection) value), indentFactor, indent);
        } else if (value instanceof Map) {
            return write((Map) value, indentFactor, indent);
        } else if (value.getClass().isArray()) {
            return write(toList(value), indentFactor, indent);
        } else {
            return quote(value.toString());
        }
    }

    private static String formatDateTime(Date value) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(value);
    }

    private static String write(Number number) {
        String s = number.toString();
        if (s.indexOf('.') > 0 && s.indexOf('e') < 0 && s.indexOf('E') < 0) {
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }
            if (s.endsWith(".")) {
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }

    private static String write(List array) {
        int len = array.size();
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i = 0; i < len; i += 1) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(write(array.get(i)));
        }
        sb.append("]");
        return sb.toString();
    }

    private static String write(Map<String, Object> map) {
        Iterator<String> keys = map.keySet().iterator();
        StringBuffer sb = new StringBuffer("{");
        while (keys.hasNext()) {
            if (sb.length() > 1) {
                sb.append(',');
            }
            String key = keys.next();
            sb.append(quote(key));
            sb.append(" = ");
            sb.append(write(map.get(key)));
        }
        sb.append('}');
        return sb.toString();
    }

    private static String write(List array, int indentFactor, int indent) {
        if (isPrimitive(array)) {
            return write(array);
        }
        int len = array.size();
        if (len == 0) {
            return "[]";
        }
        int i;
        StringBuffer sb = new StringBuffer("[");
        if (len == 1) {
            sb.append(write(array.get(0), indentFactor, indent));
        } else {
            int newindent = indent + indentFactor;
            sb.append('\n');
            for (i = 0; i < len; i += 1) {
                if (i > 0) {
                    sb.append(",\n");
                }
                for (int j = 0; j < newindent; j += 1) {
                    sb.append(' ');
                }
                sb.append(write(array.get(i), indentFactor, newindent));
            }
            sb.append('\n');
            for (i = 0; i < indent; i += 1) {
                sb.append(' ');
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private static String write(Map map, int indentFactor, int indent) {
        int j;
        int n = map.size();
        if (n == 0) {
            return "{}";
        }
        Iterator keys = map.keySet().iterator();
        StringBuffer sb = new StringBuffer("{");
        int newindent = indent + indentFactor;
        Object o;
        if (n == 1) {
            o = keys.next();
            sb.append(quote(o.toString()));
            sb.append(" = ");
            sb.append(write(map.get(o), indentFactor, indent));
        } else {
            while (keys.hasNext()) {
                o = keys.next();
                if (sb.length() > 1) {
                    sb.append(",\n");
                } else {
                    sb.append('\n');
                }
                for (j = 0; j < newindent; j += 1) {
                    sb.append(' ');
                }
                sb.append(quote(o.toString()));
                sb.append(" = ");
                sb.append(write(map.get(o), indentFactor, newindent));
            }
            if (sb.length() > 1) {
                sb.append('\n');
                for (j = 0; j < indent; j += 1) {
                    sb.append(' ');
                }
            }
        }
        sb.append('}');
        return sb.toString();
    }

    private static List toList(Collection values) {
        List list = new ArrayList();
        if (values != null) {
            for (Object value : values) {
                list.add(value);
            }
        }
        return list;
    }

    private static List toList(Object array) {
        List list = new ArrayList();
        if (array != null) {
            int length = Array.getLength(array);
            for (int i = 0; i < length; i += 1) {
                list.add(Array.get(array, i));
            }
        }
        return list;
    }

    public static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }

        char b;
        char c = 0;
        int i;
        int len = string.length();
        StringBuffer sb = new StringBuffer(len + 4);
        String t;

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            b = c;
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    if (b == '<') {
                        sb.append('\\');
                    }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ' || (c >= '\u0080' && c < '\u00a0') ||
                            (c >= '\u2000' && c < '\u2100')) {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }

    private static boolean isPrimitive(Collection collection) {
        if (collection.size() > 0) {
            for (Object obj : collection) {
                if (obj instanceof Map || obj instanceof Collection || obj.getClass().isArray()) {
                    return false;
                }
            }
        }
        return true;
    }
}
