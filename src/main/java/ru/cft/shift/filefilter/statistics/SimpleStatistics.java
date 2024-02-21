package ru.cft.shift.filefilter.statistics;

public class SimpleStatistics implements Statistics {
    private long wroteIntegers = 0;
    private long wroteFloats = 0;
    private long wroteStrings = 0;

    @Override
    public void registerInteger(String integerString) {
        ++wroteIntegers;
    }

    @Override
    public void registerFloat(String floatString) {
        ++wroteFloats;
    }

    @Override
    public void registerString(String string) {
        ++wroteStrings;
    }

    @Override
    public String getStatisticsAsString() {
        return String.format("Totally wrote:\nIntegers: %d\nFloats: %d\nStrings: %d\n", wroteIntegers, wroteFloats, wroteStrings);
    }
}
