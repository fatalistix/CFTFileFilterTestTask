package ru.cft.shift.filefilter;

import org.apache.commons.cli.*;

import java.util.Optional;

public class FilterOptions {
    private final Options options = new Options();
    public FilterOptions() {
        {
            Option outputOption = new Option("o", true, "set result path");
            outputOption.setRequired(false);
            options.addOption(outputOption);
        }

        {
            Option resultFilesPrefixesOption = new Option("p", false, "set result files prefixes");
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

    public CommandLine parseOptions(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args, true);
    }
}
