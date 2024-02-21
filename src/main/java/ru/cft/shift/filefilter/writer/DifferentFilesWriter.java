package ru.cft.shift.filefilter.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DifferentFilesWriter implements AutoCloseable {

    private final static String INTEGERS_FILENAME = "integers.txt";
    private final static String FLOATS_FILENAME = "floats.txt";
    private final static String STRINGS_FILENAME = "strings.txt";

    private final Path integersPath;
    private final Path floatsPath;
    private final Path stringsPath;

    private BufferedWriter integerWriter = null;
    private BufferedWriter floatWriter = null;
    private BufferedWriter stringWriter = null;

    private final boolean append;

    public DifferentFilesWriter(String outputDirectory, String prefix, boolean append) {
        integersPath = Paths.get(outputDirectory, prefix + INTEGERS_FILENAME);
        floatsPath = Paths.get(outputDirectory, prefix + FLOATS_FILENAME);
        stringsPath = Paths.get(outputDirectory, prefix + STRINGS_FILENAME);
        this.append = append;
    }

    //TODO: IOException
    public void writeInteger(String string) throws IOException {
        integerWriter = createIfNull(integerWriter, integersPath, append);
        writeString(integerWriter, string);
    }

    //TODO: IOException
    public void writeFloat(String string) throws IOException {
        floatWriter = createIfNull(floatWriter, floatsPath, append);
        writeString(floatWriter, string);
    }

    //TODO: IOException
    public void writeString(String string) throws IOException {
        stringWriter = createIfNull(stringWriter, stringsPath, append);
        writeString(stringWriter, string);
    }

    private static BufferedWriter createIfNull(BufferedWriter writer, Path outputDirectory, boolean append) throws IOException {
        if (writer == null) {
            if (append) {
                writer = Files.newBufferedWriter(outputDirectory,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND,
                        StandardOpenOption.WRITE
                );
            } else {
                writer = Files.newBufferedWriter(outputDirectory);
            }
        }
        return writer;
    }

    //TODO: think about flush
    private static void writeString(BufferedWriter writer, String string) throws IOException {
        writer.write(string);
        writer.newLine();
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        IOException resultException = null;
        try {
            closeIfNotNull(integerWriter);
        } catch (IOException e) {
            resultException = e;
        }
        try {
            closeIfNotNull(floatWriter);
        } catch (IOException e) {
            if (resultException == null) {
                resultException = e;
            }
        }
        try {
            closeIfNotNull(stringWriter);
        } catch (IOException e) {
            if (resultException == null) {
                resultException = e;
            }
        }
        if (resultException != null) {
            throw resultException;
        }
    }

    private static void closeIfNotNull(BufferedWriter writer) throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
