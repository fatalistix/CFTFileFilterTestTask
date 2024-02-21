package ru.cft.shift.filefilter;

import ru.cft.shift.filefilter.sorter.Sorter;
import ru.cft.shift.filefilter.writer.DifferentFilesWriter;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        FilterOptionsParser optionsParser = new FilterOptionsParser();
        try {
            FilterOptions options = optionsParser.parseOptions(args);
            if (!Files.isDirectory(Paths.get(options.outputDirectory()))) {
                throw new FileNotFoundException("Directory " + options.outputDirectory() + " does not exists");
            }

            DifferentFilesWriter differentFilesWriter = new DifferentFilesWriter(
                    options.outputDirectory(),
                    options.filePrefix(),
                    options.append()
            );

            Sorter sorter = new Sorter(options.statistics(), differentFilesWriter);
            sorter.sort(options.arguments());

            System.out.print(options.statistics().getStatisticsAsString());

        } catch (Exception e) {
            //TODO: possibly replace with logger
            System.out.println(e.getMessage());
        }
    }
}