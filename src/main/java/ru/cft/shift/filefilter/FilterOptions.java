package ru.cft.shift.filefilter;

import ru.cft.shift.filefilter.statistics.Statistics;

import java.util.List;

public record FilterOptions(
        String outputDirectory,
        String filePrefix,
        boolean append,
        Statistics statistics,
        List<String> arguments
) {
}
