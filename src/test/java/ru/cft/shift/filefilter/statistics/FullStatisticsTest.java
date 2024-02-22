package ru.cft.shift.filefilter.statistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FullStatisticsTest {

    @Test
    public void givenIntegers_whenRegisterInteger_thenCorrectStringResult() {
        Statistics statistics = new FullStatistics();
        List<String> integers = List.of(
                "2",
                "4",
                "4"
        );

        integers.forEach(statistics::registerInteger);
        System.out.println(statistics.getStatisticsAsString());

        Assertions.assertEquals("""
                Integers:
                Totally wrote:          3
                Maximum value:          4
                Minimum value:          2
                Sum of all values:      10
                average value:          3.33333333333333333333
                
                Floats:
                Totally wrote:          0
                Maximum value:          not available
                Minimum value:          not available
                Sum of all values:      not available
                average value:          not available
                               
                Strings:
                Totally wrote:          0
                Longest string length:  not available
                Shortest string length: not available
                """, statistics.getStatisticsAsString());
    }
}
