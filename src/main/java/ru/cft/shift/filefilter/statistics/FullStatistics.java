package ru.cft.shift.filefilter.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FullStatistics implements Statistics {

    private long wroteIntegers = 0;
    private BigDecimal minIntegerValue = new BigDecimal(0);
    private BigDecimal maxIntegerValue = new BigDecimal(0);
    private BigDecimal sumOfIntegerValues = new BigDecimal(0);

    private long wroteFloats = 0;
    private BigDecimal minFloatValue = new BigDecimal(0);
    private BigDecimal maxFloatValue = new BigDecimal(0);
    private BigDecimal sumOfFloatValues = new BigDecimal(0);

    private long wroteStrings = 0;
    private long shortestStringLength = Long.MAX_VALUE;
    private long longestStringLength = -1;

    //TODO: Possibly decompose to one class "NumberStatistics"
    @Override
    public void registerInteger(String integerString) {
        BigDecimal newValue = new BigDecimal(integerString);
        if (wroteIntegers == 0) {
            minIntegerValue = newValue;
            maxIntegerValue = newValue;
            sumOfIntegerValues = newValue;
        } else {
            minIntegerValue = minIntegerValue.min(newValue);
            maxIntegerValue = maxIntegerValue.max(newValue);
            sumOfIntegerValues = sumOfIntegerValues.add(newValue);
        }
        ++wroteIntegers;
    }

    //TODO: Possibly decompose to one class "NumberStatistics"
    @Override
    public void registerFloat(String floatString) {
        BigDecimal newValue = new BigDecimal(floatString);
        if (wroteFloats == 0) {
            minFloatValue = newValue;
            maxFloatValue = newValue;
            sumOfFloatValues = newValue;
        } else {
            minFloatValue = minFloatValue.min(newValue);
            maxFloatValue = maxFloatValue.max(newValue);
            sumOfFloatValues = sumOfFloatValues.add(newValue);
        }
        ++wroteFloats;
    }

    @Override
    public void registerString(String string) {
        ++wroteStrings;
        shortestStringLength = Math.min(string.length(), shortestStringLength);
        longestStringLength = Math.max(string.length(), longestStringLength);
    }

    @Override
    public String getStatisticsAsString() {
        String integersStats = String.format("""
                        Integers:
                        Totally wrote:          %d
                        Maximum value:          %s
                        Minimum value:          %s
                        Sum of all integers:    %s
                        average value:          %s
                        
                        """,
                wroteIntegers,
                wroteIntegers == 0 ? "not available" : maxIntegerValue,
                wroteIntegers == 0 ? "not available" : minIntegerValue,
                wroteIntegers == 0 ? "not available" : sumOfIntegerValues,
                wroteIntegers == 0 ? "not available" : sumOfIntegerValues.divide(
                        new BigDecimal(wroteIntegers), RoundingMode.UNNECESSARY
                ));
        String floatStats = String.format("""
                        Floats:
                        Totally wrote:          %d
                        Maximum value:          %s
                        Minimum value:          %s
                        Sum of all floats:      %s
                        average value:          %s
                        
                        """,
                wroteFloats,
                wroteFloats == 0 ? "not available" : maxFloatValue,
                wroteFloats == 0 ? "not available" : minFloatValue,
                wroteFloats == 0 ? "not available" : sumOfFloatValues,
                wroteFloats == 0 ? "not available" : sumOfFloatValues.divide(
                        new BigDecimal(wroteFloats), RoundingMode.UNNECESSARY
                ));
        String stringStats = String.format("""
                        Strings:
                        Totally wrote:          %d
                        Longest string length:  %s
                        Shortest string length: %s
                        """,
                wroteStrings,
                wroteStrings == 0 ? "not available" : longestStringLength,
                wroteStrings == 0 ? "not available" : shortestStringLength);
        return integersStats + floatStats + stringStats;
    }
}
