package ru.cft.shift.filefilter.statistics;

public interface Statistics {

    void registerInteger(String integerString);

    void registerFloat(String floatString);

    void registerString(String string);

    String getStatisticsAsString();
}
