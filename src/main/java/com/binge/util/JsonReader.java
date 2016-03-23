package com.binge.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-7
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
public class JsonReader {
    private int index;
    private Reader reader;
    private char lastChar;
    private boolean useLastChar;

    public JsonReader(Reader reader) {
        this.reader = reader.markSupported() ? reader : new BufferedReader(reader);
        this.useLastChar = false;
        this.index = 0;
    }

    public JsonReader(String s) {
        this(new StringReader(s));
    }

    /**
     * Back up one character. This provides a sort of lookahead capability,
     * so that you can test for a digit or letter before attempting to parse
     * the next number or identifier.
     *
     * @throws java.io.IOException thrown if you try and step back twice.
     */
    public void back() throws IOException {
        if (useLastChar || index <= 0) {
            throw new IOException("Stepping back two steps is not supported");
        }
        index -= 1;
        useLastChar = true;
    }

    /**
     * Get the hex value of a character (base16).
     *
     * @param c A character between '0' and '9' or between 'A' and 'F' or
     *          between 'a' and 'f'.
     * @return An int between 0 and 15, or -1 if c was not a hex digit.
     */
    public static int dehexchar(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - ('A' - 10);
        }
        if (c >= 'a' && c <= 'f') {
            return c - ('a' - 10);
        }
        return -1;
    }

    /**
     * Determine if the source string still contains characters that next()
     * can consume.
     *
     * @return true if not yet at the end of the source.
     * @throws IOException thrown if underlying IOException is thrown.
     */
    public boolean more() throws IOException {
        char nextChar = next();
        if (nextChar == 0) {
            return false;
        }
        back();
        return true;
    }

    /**
     * Get the next character in the source string.
     *
     * @return The next character, or 0 if past the end of the source string.
     * @throws IOException if underlying IOException is thrown.
     */
    public char next() throws IOException {
        if (this.useLastChar) {
            this.useLastChar = false;
            if (this.lastChar != 0) {
                this.index += 1;
            }
            return this.lastChar;
        }
        int c = this.reader.read();
        if (c <= 0) { // End of stream
            this.lastChar = 0;
            return 0;
        }
        this.index += 1;
        this.lastChar = (char) c;
        return this.lastChar;
    }

    /**
     * Consume the next character, and check that it matches a specified
     * character.
     *
     * @param c The character to match.
     * @return The character.
     * @throws IOException if the character does not match.
     */
    public char next(char c) throws IOException {
        char n = next();
        if (n != c) {
            throw syntaxError("Expected '" + c + "' and instead saw '" +
                    n + "'");
        }
        return n;
    }

    /**
     * Get the next n characters.
     *
     * @param n The number of characters to take.
     * @return A string of n characters.
     * @throws IOException Substring bounds error if there are not
     *                     n characters remaining in the source string.
     */
    public String next(int n) throws IOException {
        if (n == 0) {
            return "";
        }

        char[] buffer = new char[n];
        int pos = 0;

        if (this.useLastChar) {
            this.useLastChar = false;
            buffer[0] = this.lastChar;
            pos = 1;
        }

        int len;
        while ((pos < n) && ((len = reader.read(buffer, pos, n - pos)) != -1)) {
            pos += len;
        }
        this.index += pos;

        if (pos < n) {
            throw syntaxError("Substring bounds error");
        }

        this.lastChar = buffer[n - 1];
        return new String(buffer);
    }

    /**
     * Get the next char in the string, skipping whitespace.
     *
     * @return A character, or 0 if there are no more characters.
     * @throws IOException if the syntax of the JSON stream is not correct.
     */
    public char nextClean() throws IOException {
        for (; ; ) {
            char c = next();
            if (c == '#') {
                do {
                    c = next();
                } while (c != '\n' && c != '\r' && c != 0);
            } else if (c == 0 || c > ' ') {
                return c;
            }
        }
    }

    /**
     * Return the characters up to the next close quote character.
     * Backslash processing is done. The formal JSON format does not
     * allow strings in single quotes, but an implementation is allowed to
     * accept them.
     *
     * @param quote The quoting character, either
     *              <code>"</code>&nbsp;<small>(double quote)</small> or
     *              <code>'</code>&nbsp;<small>(single quote)</small>.
     * @return A String.
     * @throws IOException Unterminated string.
     */
    public String nextString(char quote) throws IOException {
        char c;
        StringBuilder sb = new StringBuilder();
        for (; ; ) {
            c = next();
            switch (c) {
                case 0:
                case '\n':
                case '\r':
                    throw syntaxError("Unterminated string");
                case '\\':
                    c = next();
                    switch (c) {
                        case 'b':
                            sb.append('\b');
                            break;
                        case 't':
                            sb.append('\t');
                            break;
                        case 'n':
                            sb.append('\n');
                            break;
                        case 'f':
                            sb.append('\f');
                            break;
                        case 'r':
                            sb.append('\r');
                            break;
                        case 'u':
                            sb.append((char) Integer.parseInt(next(4), 16));
                            break;
                        case 'x':
                            sb.append((char) Integer.parseInt(next(2), 16));
                            break;
                        default:
                            sb.append(c);
                    }
                    break;
                default:
                    if (c == quote) {
                        return sb.toString();
                    }
                    sb.append(c);
            }
        }
    }

    /**
     * Get the next value. The value can be a Boolean, Double, Integer,
     * JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
     *
     * @return An object.
     * @throws IOException If syntax error.
     */
    public Object nextValue() throws IOException {
        char c = nextClean();
        String s;

        switch (c) {
            case '"':
            case '\'':
                return nextString(c);
            case '{':
                back();
                return parseObject();
            case '[':
            case '(':
                back();
                return parseArray();
        }

        /*
        * Handle unquoted text. This could be the values true, false, or
        * null, or it can be a number. An implementation (such as this one)
        * is allowed to also accept non-standard forms.
        *
        * Accumulate characters until we reach the end of the text or a
        * formatting character.
        */

        StringBuilder sb = new StringBuilder();
        while (c >= ' ' && ",:]}/\\\"[{;=#".indexOf(c) < 0) {
            sb.append(c);
            c = next();
        }
        back();

        s = sb.toString().trim();
        if (s.equals("")) {
            throw syntaxError("Missing value");
        }
        return stringToValue(s);
    }

    public Map<String, Object> parseProperties() throws IOException {
        Map<String, Object> properties = new LinkedHashMap<String, Object>();
        char c;
        String key;
        for (; ; ) {
            c = nextClean();
            switch (c) {
                case 0:
                    return properties;
                case ';':
                case ',':
                    continue;
                default:
                    back();
                    key = nextValue().toString();
            }
            /*
            * The key is followed by ':'. We will also tolerate '=' or '=>'.
            */
            c = nextClean();
            if (c == '=') {
                if (next() != '>') {
                    back();
                }
            } else if (c != ':') {
                throw syntaxError("Expected a ':' after a key");
            }
            properties.put(key, nextValue());
        }
    }

    public Map<String, Object> parseObject() throws IOException {
        char c;
        String key;

        Map<String, Object> object = new LinkedHashMap<String, Object>();

        if (nextClean() != '{') {
            throw syntaxError("A JSONObject text must begin with '{'");
        }
        for (; ; ) {
            c = nextClean();
            switch (c) {
                case 0:
                    throw syntaxError("A JSONObject text must end with '}'");
                case '}':
                    return object;
                default:
                    back();
                    key = nextValue().toString();
            }

            /*
            * The key is followed by ':'. We will also tolerate '=' or '=>'.
            */

            c = nextClean();
            if (c == '=') {
                if (next() != '>') {
                    back();
                }
            } else if (c != ':') {
                throw syntaxError("Expected a ':' after a key");
            }
            object.put(key, nextValue());

            /*
            * Pairs are separated by ','. We will also tolerate ';'.
            */
            switch (nextClean()) {
                case ';':
                case ',':
                    if (nextClean() == '}') {
                        return object;
                    }
                    back();
                    break;
                case '}':
                    return object;
                default:
                    throw syntaxError("Expected a ',' or '}'");
            }
        }
    }

    public List<Object> parseArray() throws IOException {
        List<Object> list = new ArrayList<Object>();

        char c = nextClean();
        char q;
        if (c == '[') {
            q = ']';
        } else if (c == '(') {
            q = ')';
        } else {
            throw syntaxError("A JSONArray text must start with '['");
        }
        if (nextClean() == ']') {
            return list;
        }
        back();
        for (; ; ) {
            if (nextClean() == ',') {
                back();
                list.add(null);
            } else {
                back();
                list.add(nextValue());
            }
            c = nextClean();
            switch (c) {
                case ';':
                case ',':
                    if (nextClean() == ']') {
                        return list;
                    }
                    back();
                    break;
                case '{':
                    back();
                    break;
                case ']':
                case ')':
                    if (q != c) {
                        throw syntaxError("Expected a '" + q + "'");
                    }
                    return list;
                default:
                    throw syntaxError("Expected a ',' or ']'");
            }
        }
    }

    private Object stringToValue(String s) {
        if (s.equals("")) {
            return s;
        } else if (s.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        } else if (s.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        } else if (s.equalsIgnoreCase("null")) {
            return null;
        } else if (s.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return format.parse(s);
            } catch (ParseException e) {
                return null;
            }
        }

        /*
        * If it might be a number, try converting it. We support the 0- and 0x-
        * conventions. If a number cannot be produced, then the value will just
        * be a string. Note that the 0-, 0x-, plus, and implied string
        * conventions are non-standard. A JSON parser is free to accept
        * non-JSON forms as long as it accepts all correct JSON forms.
        */

        char b = s.charAt(0);
        if ((b >= '0' && b <= '9') || b == '.' || b == '-' || b == '+') {
            if (b == '0') {
                if (s.length() > 2 &&
                        (s.charAt(1) == 'x' || s.charAt(1) == 'X')) {
                    try {
                        return Integer.parseInt(s.substring(2),
                                16);
                    } catch (Exception e) {
                        /* Ignore the error */
                    }
                } else {
                    try {
                        return Integer.parseInt(s, 8);
                    } catch (Exception e) {
                        /* Ignore the error */
                    }
                }
            }
            try {
                return new Integer(s);
            } catch (Exception e) {
                try {
                    return new Long(s);
                } catch (Exception f) {
                    try {
                        return new Double(s);
                    } catch (Exception g) {
                        /* Ignore the error */
                    }
                }
            }
        }
        return s;
    }

    /**
     * Make a IOException to signal a syntax error.
     *
     * @param message The error message.
     * @return A IOException object, suitable for throwing
     */
    public IOException syntaxError(String message) {
        return new IOException(message + " at character " + index);
    }

    public static Object fromJson(String json) throws IOException {
        JsonReader reader = new JsonReader(json);
        return reader.nextValue();
    }
}
