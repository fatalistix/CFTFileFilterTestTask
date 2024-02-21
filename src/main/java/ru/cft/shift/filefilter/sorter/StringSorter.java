package ru.cft.shift.filefilter.sorter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSorter {
    private static final String INTEGER_PATTERN = "[+-]?\\d+";
    private static final String FLOAT_PATTERN = "[+-]?\\d*(\\.\\d*)?([Ee][+-]?\\d+)?";
    private final Pattern integerPattern = Pattern.compile(INTEGER_PATTERN);
    private final Pattern floatPattern = Pattern.compile(FLOAT_PATTERN);

    public StringType determineType(String string) {
        Matcher integerMatcher = integerPattern.matcher(string);
        if (integerMatcher.matches()) {
            return StringType.INTEGER;
        }

        Matcher floatMatcher = floatPattern.matcher(string);
        if (floatMatcher.matches()) {
            return StringType.FLOAT;
        }

        return StringType.STRING;
    }
}
