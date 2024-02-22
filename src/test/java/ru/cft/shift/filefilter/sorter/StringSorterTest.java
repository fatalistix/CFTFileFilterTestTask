package ru.cft.shift.filefilter.sorter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

public class StringSorterTest {

    @Test
    public void givenRandomLong_whenDetermineType_thenReturnIntegerType() {
        Random random = new Random(100);
        StringSorter stringSorter = new StringSorter();

        for (int i = 0; i < 100_000; ++i) {
            long number = random.nextLong();

            {
                StringType type = stringSorter.determineType(Long.toString(number));
                Assertions.assertEquals(type, StringType.INTEGER);
            }

            if (number > 0) {
                {
                    StringType type = stringSorter.determineType("-" + number);
                    Assertions.assertEquals(type, StringType.INTEGER);
                }

                {
                    StringType type = stringSorter.determineType("+" + number);
                    Assertions.assertEquals(type, StringType.INTEGER);
                }
            }
        }
    }

    @Test
    public void givenLongInteger_whenDetermineType_thenReturnIntegerType() {
        List<String> longIntegers = List.of(
                "+12324427864876867823658687236587236587236572635876782365786273865782637856872365872365876325",
                "-389578768967789896578965984702632845023984902380958230985690823906823908609348690839048609348906834",
                "58340986098346098340968934860348683486254306390459068904857349708634590869034586989034586908390458608",
                "00000000000098346098340968934860348683486254306390459068904857349708634590869034586989034586908390458608"
        );
        StringSorter stringSorter = new StringSorter();

        longIntegers.forEach((longInteger) -> {
            StringType type = stringSorter.determineType(longInteger);
            Assertions.assertEquals(type, StringType.INTEGER);
        });
    }

    @Test
    public void givenBlankString_whenDetermineType_thenReturnStringType() {
        StringSorter stringSorter = new StringSorter();
        StringType type = stringSorter.determineType("");
        Assertions.assertEquals(type, StringType.STRING);
    }

    @Test
    public void givenSign_whenDetermineType_thenReturnStringType() {
        StringSorter stringSorter = new StringSorter();

        {
            StringType type = stringSorter.determineType("+");
            Assertions.assertEquals(type, StringType.STRING);
        }

        {
            StringType type = stringSorter.determineType("-");
            Assertions.assertEquals(type, StringType.STRING);
        }
    }

    @Test
    public void givenSmallSeparatedByDotFloatWithoutSign_whenDetermineType_thenReturnFloatType() {
        StringSorter stringSorter = new StringSorter();

        List<String> smallFloats = List.of(
                "1.1",
                "123.2",
                "2.123989",
                "3123145.123455515",
                "0.5555555555",
                "100000.00000",
                "300.0",
                "0000.0"
        );

        smallFloats.forEach((sf) -> {
            StringType type = stringSorter.determineType(sf);
            Assertions.assertEquals(type, StringType.FLOAT);
        });
    }

    @Test
    public void givenLongSeparatedByDotFloatWithoutSign_whenDetermineType_thenReturnFloatType() {
        StringSorter stringSorter = new StringSorter();

        List<String> longFloats = List.of(
                "1.62103985610239847283749875105087105987348612389658961892074192837489127359812386490823175890126908346719082374",
                "46813267586692138675987642319784618237647823164897126876785623786478326784681792365826587657892365872687123647823164879.4",
                "5612387569128736581923765812376589172367865198658921376598761239857651251231908756.012956102398573129087512093856312097865",
                "00000000000000025198236581236589716235897192385698712356.35123987658912365782365897126389756100000000000000"
        );

        longFloats.forEach((lf) -> {
            StringType type = stringSorter.determineType(lf);
            Assertions.assertEquals(type, StringType.FLOAT);
        });
    }

    @Test
    public void givenSignedFloatSeparatedByDot_whenDetermineType_thenReturnFloatType() {
        StringSorter stringSorter = new StringSorter();

        List<String> signedFloats = List.of(
                "+123453275235987597.213",
                "-12134.24242424",
                "+1.1",
                "-0.0"
        );

        signedFloats.forEach((sf) -> {
            StringType type = stringSorter.determineType(sf);
            Assertions.assertEquals(type, StringType.FLOAT);
        });
    }

    @Test
    public void givenFloatThatStartsOrEndWithDot_whenDetermineType_thenReturnFloatType() {
        StringSorter stringSorter = new StringSorter();

        List<String> dotsFloats = List.of(
                ".12345",
                ".1",
                "1.",
                "12345."
        );

        dotsFloats.forEach((sf) -> {
            StringType type = stringSorter.determineType(sf);
            Assertions.assertEquals(type, StringType.FLOAT);
        });
    }

    @Test
    public void givenSignedFloatThatStartsOrEndWithDot_whenDetermineType_thenReturnFloatType() {
        StringSorter stringSorter = new StringSorter();

        List<String> signedDotsFloats = List.of(
                "+.12345",
                "-.12345",
                "+.1",
                "-.1",
                "+1.",
                "-1.",
                "+12345.",
                "-12345."
        );

        signedDotsFloats.forEach((sdf) -> {
            StringType type = stringSorter.determineType(sdf);
            Assertions.assertEquals(type, StringType.FLOAT);
        });
    }

    @Test
    public void givenFloatWithE_whenDetermineType_thenReturnFloatType() {
        StringSorter stringSorter = new StringSorter();

        List<String> eFloats = List.of(
                "1e2",
                "1.e2",
                "+1.e2",
                "-1.e2",
                "1.123e10",
                "+1.123e10",
                "-1.123e10",
                ".123e10",
                "+.123e10",
                "-.123e10",
                "1E2",
                "1.E2",
                "+1.E2",
                "-1.E2",
                "1.123E10",
                "+1.123E10",
                "-1.123E10",
                ".123E10",
                "+.123E10",
                "-.123E10",
                "1.e+2",
                "1.e-2",
                "+.123e+10",
                "-.123E-10"
        );

        eFloats.forEach((ef) -> {
            StringType type = stringSorter.determineType(ef);
            Assertions.assertEquals(type, StringType.FLOAT);
        });
    }
}
