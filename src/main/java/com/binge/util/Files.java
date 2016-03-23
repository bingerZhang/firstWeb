package com.binge.util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zlb
 * Date: 16-3-7
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class Files {
    public static byte[] toBytes(File file) throws IOException {
        if (!file.exists()) {
            return new byte[0];
        }
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return ByteStreams.toBytes(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static String toString(File file, Charset charset) throws IOException {
        return new String(toBytes(file), charset);
    }

    public static String readFirstLine(File file, Charset charset) throws IOException {
        List<String> lines = Files.readLines(file, charset);
        if (lines.size() == 0) {
            return null;
        }
        return lines.get(0);
    }

    public static List<String> readLines(File file, Charset charset) throws IOException {
        if (!file.exists()) {
            return Collections.emptyList();
        }
        Reader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(file), charset);
            return CharStreams.readLines(in);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void readLines(File file, Charset charset, LineProcessor processor) throws IOException {
        if (!file.exists()) {
            return;
        }
        Reader in = null;
        try {
            in = new InputStreamReader(new FileInputStream(file), charset);
            CharStreams.readLines(in, processor);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void writeLines(File file, Charset charset, Iterable<String> lines) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(getOutputStream(file, false), charset));
            for (String line : lines) {
                out.write(line);
                out.newLine();
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void appendLines(File file, Charset charset, Iterable<String> lines) throws IOException {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(getOutputStream(file, true), charset));
            for (String line : lines) {
                out.write(line);
                out.newLine();
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void writeFile(File file, Charset charset, String text) throws IOException {
        writeFile(file, text.getBytes(charset));
    }

    public static void writeFile(File file, byte[] data) throws IOException {
        OutputStream out = null;
        try {
            out = getOutputStream(file, false);
            out.write(data);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void writeFile(File file, InputStream in) throws IOException {
        OutputStream out = null;
        try {
            out = getOutputStream(file, false);
            ByteStreams.copy(in, out);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void writeLines(File file, Charset charset, Iterable<String> lines, File tempPath) throws IOException {
        String name = file.getName();
        int index = name.indexOf('.');
        String prefix = index == -1 ? name : name.substring(0, index);
        String surfix = index == -1 ? null : name.substring(index + 1);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        File tempFile = File.createTempFile(prefix, surfix, tempPath);
        writeLines(tempFile, charset, lines);
        moveFile(tempFile, file);
    }

    public static void writeFile(File file, Charset charset, String text, File tempPath) throws IOException {
        writeFile(file, text.getBytes(charset), tempPath);
    }

    public static void writeFile(File file, byte[] data, File tempPath) throws IOException {
        writeFile(file, new ByteArrayInputStream(data), tempPath);
    }

    public static void writeFile(File file, InputStream in, File tempPath) throws IOException {
        String name = file.getName();
        int index = name.indexOf('.');
        String prefix = index == -1 ? name : name.substring(0, index);
        String surfix = index == -1 ? null : name.substring(index + 1);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        File tempFile = File.createTempFile(prefix, surfix, tempPath);
        writeFile(tempFile, in);
        moveFile(tempFile, file);
    }

    public static boolean equals(File file1, File file2) throws IOException {
        if (file1 == file2 || file1.equals(file2)) {
            return true;
        }

        /*
        * Some operating systems may return zero as the length for files
        * denoting system-dependent entities such as devices or pipes, in
        * which case we must fall back on comparing the bytes directly.
        */
        long len1 = file1.length();
        long len2 = file2.length();
        if (len1 != 0 && len2 != 0 && len1 != len2) {
            return false;
        }
        InputStream in1 = null;
        InputStream in2 = null;
        try {
            in1 = new FileInputStream(file1);
            in2 = new FileInputStream(file2);
            return ByteStreams.equals(in1, in2);
        } finally {
            if (in1 != null) {
                in1.close();
            }
            if (in2 != null) {
                in2.close();
            }
        }
    }

    private static OutputStream getOutputStream(File file, boolean append) throws IOException {
        createParentPath(file);
        return new FileOutputStream(file, append);
    }

    public static void createParentPath(File file) throws IOException {
        File parent = file.getCanonicalFile().getParentFile();
        if (parent == null) {
            /*
            * The given directory is a filesystem root. All zero of its ancestors
            * exist. This doesn't mean that the root itself exists -- consider x:\ on
            * a Windows machine without such a drive -- or even that the caller can
            * create it, but this method makes no such guarantees even for non-root
            * files.
            */
            return;
        }
        parent.mkdirs();
        if (!parent.isDirectory()) {
            throw new IOException("Unable to create parent directories of " + file);
        }
    }

    public static void touchFile(File file) throws IOException {
        if (!file.createNewFile()
                && !file.setLastModified(System.currentTimeMillis())) {
            throw new IOException("Unable to touch file: " + file);
        }
    }

    public static long getFileSize(File file) {
        if (!file.exists()) {
            return 0;
        }
        if (file.isFile()) {
            return file.length();
        }
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            long count = 0;
            for (File f : fileList) {
                count += getFileSize(f);
            }
            return count;
        } else {
            return 0;
        }
    }

    public static void moveFile(File srcFile, File dstFile) throws IOException {
        if (!srcFile.exists()) {
            throw new IOException("Cannot rename nonexistent file " + srcFile);
        }
        if (srcFile.equals(dstFile)) {
            throw new IOException("Rename of " + srcFile + " to " + dstFile + " is a no-op.");
        }
        if (dstFile.exists() && !(srcFile.equals(dstFile.getCanonicalFile()) || dstFile.delete())) {
            throw new IOException("Failed to delete " + dstFile + " while trying to rename " + srcFile);
        }
        File parentPath = dstFile.getParentFile();
        if (parentPath != null && !parentPath.exists() && !parentPath.mkdirs()) {
            throw new IOException("Failed to create directory " + parentPath + " while trying to rename " + srcFile);
        }
        if (!srcFile.renameTo(dstFile)) {
            copyFile(srcFile, dstFile);
            if (!srcFile.delete()) {
                throw new IOException("Failed to delete " + srcFile + " while trying to rename it.");
            }
        }
    }

    public static List<File> listFiles(File path) {
        return listFiles(path, null);
    }

    public static List<File> listFiles(File path, FileFilter filter) {
        List<File> files = new ArrayList<File>();
        listFiles(path, filter, files);
        return files;
    }

    private static void listFiles(File path, FileFilter filter, List<File> files) {
        if (path.isDirectory()) {
            for (File file : path.listFiles()) {
                listFiles(file, filter, files);
            }
        } else if (filter == null || filter.accept(path)) {
            files.add(path);
        }
    }

    public static void copyDirectory(File srcFile, File destFile) throws IOException {
        copyDirectory(srcFile, destFile, null);
    }


    public static void copyDirectory(File srcFile, File destFile, FileFilter filter) throws IOException {
        if (!srcFile.isDirectory()) {
            return;
        }
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
        for (File file : srcFile.listFiles()) {
            File copiedFile = new File(destFile, file.getName());
            if (file.isDirectory()) {
                copyDirectory(file, copiedFile, filter);
            } else if (filter == null || filter.accept(file)) {
                copyFile(file, copiedFile);
            }
        }
    }

    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            return;
        }
        if (destFile.exists() && destFile.isDirectory()) {
            destFile = new File(destFile, srcFile.getName());
        }
        // ensure that parent dir of dest file exists!
        File parent = destFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[8 * 1024];
            int count;
            while ((count = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, count);
            }
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void cleanFiles(File file, long expiresTime) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    cleanFiles(f, expiresTime);
                }
            }
        } else if (file.isFile()) {
            long modifiedTime = file.lastModified();
            if (expiresTime <= 0 || (modifiedTime > 0 && modifiedTime < expiresTime)) {
                file.delete();
            }
        }
    }

    public static void cleanFiles(File directory) {
        cleanFiles(directory, 0);
    }

    public static void deleteFile(File file) {
        cleanFiles(file);
        if (file.isDirectory()) {
            file.delete();
        }
    }

    public static boolean existsFile(File file) {
        return file.exists();
    }
}
