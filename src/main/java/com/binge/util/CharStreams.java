package com.binge.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-7
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
public class CharStreams {
    private static final int BUFFER_SIZE = 8 * 1024;

    public static void copy(String data, Writer out) throws IOException {
        try {
            out.write(data);
        } finally {
            out.close();
        }
    }

    public static long copy(Readable in, Appendable out) throws IOException {
        CharBuffer buf = CharBuffer.allocate(BUFFER_SIZE);
        long total = 0;
        while (true) {
            int read = in.read(buf);
            if (read == -1) {
                break;
            }
            buf.flip();
            out.append(buf, 0, read);
            total += read;
        }
        return total;
    }

    public static String toString(Readable in) throws IOException {
        StringBuilder sb = new StringBuilder();
        copy(in, sb);
        return sb.toString();
    }

    public static List<String> readLines(Reader in) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(in);
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    public static void readLines(Reader in, LineProcessor processor) throws IOException {
        BufferedReader br = new BufferedReader(in);
        String line;
        while ((line = br.readLine()) != null) {
            processor.processLine(line);
        }
    }
}
