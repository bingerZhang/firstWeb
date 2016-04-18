package com.binge.configurator;

import com.binge.configuration.Configuration;
import com.binge.exception.DataBackendException;
import com.binge.util.Application;
import com.binge.util.Files;

import java.io.*;

public abstract class FileConfigurator<T extends Configuration> implements Configurator<T> {
    protected final File file;
    protected long lastModified = -1;

    public FileConfigurator(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public boolean isChanged() {
        if (lastModified == -1) {
            //Not yet loaded
            return false;
        }
        return lastModified != file.lastModified();
    }

    public T getConfiguration() throws DataBackendException {
        BufferedReader in = null;
        try {
            if (file.exists()) {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            }
            if (in == null) {
                InputStream stream = getDefaultStream();
                if (stream != null) {
                    in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                }
            }
            T configuration = getConfiguration(in);
            lastModified = file.lastModified();
            return configuration;
        } catch (IOException e) {
            throw new DataBackendException(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public void setConfiguration(T configuration) throws DataBackendException {
        PrintWriter out = null;
        try {
            File tempFile = getTempFile(configuration.getType(), getExtension());
            out = new PrintWriter(tempFile, "UTF-8");
            setConfiguration(out, configuration);
            out.close();
            out = null;
            Files.moveFile(tempFile, file);
            lastModified = file.lastModified();
        } catch (IOException e) {
            throw new DataBackendException(e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected abstract int getVersion();

    protected abstract String getExtension();

    protected abstract T getConfiguration(BufferedReader in) throws IOException;

    protected abstract void setConfiguration(PrintWriter out, T configuration) throws IOException;

    protected InputStream getDefaultStream() {
        return null;
    }

    protected File getTempFile(String type, String extension) throws IOException {
        File tempPath = new File(Application.TEMP_PATH, "configuration");
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        return File.createTempFile(type + "_", "." + extension, tempPath);
    }
}
