package ru.cft.shift.filefilter.statistics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SimpleStatisticsTest {

    @Test
    public void given100Integers_whenRegisterInteger_thenCorrectStringResult() {
        Statistics statistics = new SimpleStatistics();
        Random random = new Random(100);

        for (int i = 0; i < 100; ++i) {
            statistics.registerInteger(Integer.toString(random.nextInt()));
        }

        Assertions.assertEquals("""
                Totally wrote:
                Integers: 100
                Floats:   0
                Strings:  0
                """, statistics.getStatisticsAsString());
    }
}
