package ru.cft.shift.filefilter.sorter;

import ru.cft.shift.filefilter.statistics.Statistics;
import ru.cft.shift.filefilter.writer.DifferentFilesWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Sorter {
    private final StringSorter stringSorter = new StringSorter();
    private final Statistics statistics;
    private final DifferentFilesWriter differentFilesWriter;

    public Sorter(Statistics statistics, DifferentFilesWriter differentFilesWriter) {
        this.statistics = statistics;
        this.differentFilesWriter = differentFilesWriter;
    }

    public void sort(List<String> files) {
        List<Path> filesPaths = files.stream().map(Paths::get).toList();
        try (differentFilesWriter) {
            filesPaths.forEach((path) -> {
                try (Stream<String> linesStream = Files.lines(path)) {
                    linesStream.forEach((line) -> {
                        StringType stringType = stringSorter.determineType(line);
                        try {
                            switch (stringType) {
                                case INTEGER -> {
                                    statistics.registerInteger(line);
                                    differentFilesWriter.writeInteger(line);
                                }
                                case FLOAT -> {
                                    statistics.registerFloat(line);
                                    differentFilesWriter.writeFloat(line);
                                }
                                case STRING -> {
                                    statistics.registerString(line);
                                    differentFilesWriter.writeString(line);
                                }
                            }
                        } catch (IOException e) {
                            //TODO: Possibly replace with slf4j
                            System.out.println(e.getMessage());
                        }
                    });
                } catch (IOException e) {
                    //TODO: Possibly replace with slf4j
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
