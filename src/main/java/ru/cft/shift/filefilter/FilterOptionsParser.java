package ru.cft.shift.filefilter;

import org.apache.commons.cli.*;
import ru.cft.shift.filefilter.statistics.FullStatistics;
import ru.cft.shift.filefilter.statistics.NoStatistics;
import ru.cft.shift.filefilter.statistics.SimpleStatistics;
import ru.cft.shift.filefilter.statistics.Statistics;

public class FilterOptionsParser {

    private final Options options = new Options();

    public FilterOptionsParser() {
        {
            Option outputOption = new Option("o", true, "set result path");
            outputOption.setRequired(false);
            options.addOption(outputOption);
        }

        {
            Option resultFilesPrefixesOption = new Option("p", true, "set result files prefixes");
            resultFilesPrefixesOption.setRequired(false);
            options.addOption(resultFilesPrefixesOption);
        }

        {
            Option afterOption = new Option("a", false, "add to existing files");
            afterOption.setRequired(false);
            options.addOption(afterOption);
        }

        {
            Option shortStatisticsOption = new Option("s", false, "show short statistics");
            shortStatisticsOption.setRequired(false);
            options.addOption(shortStatisticsOption);
        }

        {
            Option fullStatisticsOption = new Option("f", false, "show full statistics");
            fullStatisticsOption.setRequired(false);
            options.addOption(fullStatisticsOption);
        }
    }

    public FilterOptions parseOptions(String[] args) throws ParseOptionsException {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(options, args, false);
        } catch (ParseException e) {
            throw new ParseOptionsException(e);
        }

        String outputDirectoryPath;
        if (commandLine.hasOption('o')) {
            outputDirectoryPath = commandLine.getOptionValue('o');
        } else {
            outputDirectoryPath = ".";
        }

        String filePrefix;
        if (commandLine.hasOption('p')) {
            filePrefix = commandLine.getOptionValue('p');
        } else {
            filePrefix = "";
        }

        boolean append;
        append = commandLine.hasOption('a');

        boolean simpleStats = commandLine.hasOption('s');
        boolean fullStats = commandLine.hasOption('f');
        if (simpleStats && fullStats) {
            throw new ParseOptionsException("illegal options: cannot use -s and -f options at the same time");
        }

        Statistics statistics;
        if (simpleStats) {
            statistics = new SimpleStatistics();
        } else if (fullStats) {
            statistics = new FullStatistics();
        } else {
            statistics = new NoStatistics();
        }

        return new FilterOptions(outputDirectoryPath, filePrefix, append, statistics, commandLine.getArgList());
    }
}
