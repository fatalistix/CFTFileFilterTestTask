package ru.cft.shift.filefilter.statistics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FullStatistics implements Statistics {

    private final NumberStatistics integerStatistics = new NumberStatistics();
    private final NumberStatistics floatStatistics = new NumberStatistics();
    private long wroteStrings = 0;
    private long shortestStringLength = Long.MAX_VALUE;
    private long longestStringLength = -1;

    private static class NumberStatistics {
        private long totallyWrote = 0;
        private BigDecimal minValue = new BigDecimal(0);
        private BigDecimal maxValue = new BigDecimal(0);
        private BigDecimal sumOfValues = new BigDecimal(0);

        public void register(String number) {
            BigDecimal newValue = new BigDecimal(number);
            if (totallyWrote == 0) {
                minValue = newValue;
                maxValue = newValue;
                sumOfValues = newValue;
            } else {
                minValue = minValue.min(newValue);
                maxValue = maxValue.max(newValue);
                sumOfValues = sumOfValues.add(newValue);
            }
            ++totallyWrote;
        }

        public String getStatisticsAsString() {
            return String.format("""
                        Totally wrote:          %d
                        Maximum value:          %s
                        Minimum value:          %s
                        Sum of all values:      %s
                        average value:          %s
                        """,
                    totallyWrote,
                    totallyWrote == 0 ? "not available" : maxValue,
                    totallyWrote == 0 ? "not available" : minValue,
                    totallyWrote == 0 ? "not available" : sumOfValues,
                    totallyWrote == 0 ? "not available" : sumOfValues.divide(
                            new BigDecimal(totallyWrote), 20, RoundingMode.HALF_UP
                    ));
        }
    }

    //TODO: Possibly decompose to one class "NumberStatistics"
    @Override
    public void registerInteger(String integerString) {
        integerStatistics.register(integerString);
    }

    //TODO: Possibly decompose to one class "NumberStatistics"
    @Override
    public void registerFloat(String floatString) {
        floatStatistics.register(floatString);
    }

    @Override
    public void registerString(String string) {
        ++wroteStrings;
        shortestStringLength = Math.min(string.length(), shortestStringLength);
        longestStringLength = Math.max(string.length(), longestStringLength);
    }

    @Override
    public String getStatisticsAsString() {

        String stringStats = String.format("""
                        Strings:
                        Totally wrote:          %d
                        Longest string length:  %s
                        Shortest string length: %s
                        """,
                wroteStrings,
                wroteStrings == 0 ? "not available" : longestStringLength,
                wroteStrings == 0 ? "not available" : shortestStringLength);
        return "Integers:\n" +
                integerStatistics.getStatisticsAsString() +
                "\nFloats:\n" +
                floatStatistics.getStatisticsAsString() +
                "\n" +
                stringStats;
    }
}
