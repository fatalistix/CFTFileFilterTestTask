package ru.cft.shift.filefilter.statistics;

public class NoStatistics implements Statistics {
    @Override
    public void registerInteger(String integerString) {
    }

    @Override
    public void registerFloat(String floatString) {
    }

    @Override
    public void registerString(String string) {
    }

    @Override
    public String getStatisticsAsString() {
        return "";
    }
}
