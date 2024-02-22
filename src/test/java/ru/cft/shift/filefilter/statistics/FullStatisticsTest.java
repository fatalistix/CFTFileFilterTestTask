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
                Sum of all integers:    10
                average value:          3.33333333333333333333
                
                """, statistics.getStatisticsAsString());
    }
}
