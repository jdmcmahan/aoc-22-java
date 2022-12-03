package com.aoc.jdmcmahan.caloriecounting;

import com.aoc.jdmcmahan.caloriecounting.model.Elf;
import com.aoc.jdmcmahan.caloriecounting.model.Snack;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalorieListParserTest {

    @Test
    void testParse_withSingleBlock() {
        String input = "1000";

        CalorieListParser parser = new CalorieListParser();
        List<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(1, parsedElves.size());

        Elf elf1 = parsedElves.get(0);
        assertNotNull(elf1);
        assertEquals(1, elf1.id());
        assertThat(elf1.snacks())
                .extracting(Snack::calories)
                .containsExactly(1000);
    }

    @Test
    void testParse_withMultipleBlocks() {
        String input = """
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000""";

        CalorieListParser parser = new CalorieListParser();
        List<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(5, parsedElves.size());

        Elf elf1 = parsedElves.get(0);
        assertNotNull(elf1);
        assertEquals(1, elf1.id());
        assertThat(elf1.snacks())
                .extracting(Snack::calories)
                .containsExactly(1000, 2000, 3000);

        Elf elf2 = parsedElves.get(1);
        assertNotNull(elf2);
        assertEquals(2, elf2.id());
        assertThat(elf2.snacks())
                .extracting(Snack::calories)
                .containsExactly(4000);

        Elf elf3 = parsedElves.get(2);
        assertNotNull(elf3);
        assertEquals(3, elf3.id());
        assertThat(elf3.snacks())
                .extracting(Snack::calories)
                .containsExactly(5000, 6000);

        Elf elf4 = parsedElves.get(3);
        assertNotNull(elf4);
        assertEquals(4, elf4.id());
        assertThat(elf4.snacks())
                .extracting(Snack::calories)
                .containsExactly(7000, 8000, 9000);

        Elf elf5 = parsedElves.get(4);
        assertNotNull(elf5);
        assertEquals(5, elf5.id());
        assertThat(elf5.snacks())
                .extracting(Snack::calories)
                .containsExactly(10000);
    }

    @Test
    void testParse_withExtraEmptyLines() {
        String input = """
                1000
                2000
                3000

                4000



                5000
                6000


                7000
                8000
                9000

                10000""";

        CalorieListParser parser = new CalorieListParser();
        List<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(5, parsedElves.size());

        Elf elf1 = parsedElves.get(0);
        assertNotNull(elf1);
        assertEquals(1, elf1.id());
        assertThat(elf1.snacks())
                .extracting(Snack::calories)
                .containsExactly(1000, 2000, 3000);

        Elf elf2 = parsedElves.get(1);
        assertNotNull(elf2);
        assertEquals(2, elf2.id());
        assertThat(elf2.snacks())
                .extracting(Snack::calories)
                .containsExactly(4000);

        Elf elf3 = parsedElves.get(2);
        assertNotNull(elf3);
        assertEquals(3, elf3.id());
        assertThat(elf3.snacks())
                .extracting(Snack::calories)
                .containsExactly(5000, 6000);

        Elf elf4 = parsedElves.get(3);
        assertNotNull(elf4);
        assertEquals(4, elf4.id());
        assertThat(elf4.snacks())
                .extracting(Snack::calories)
                .containsExactly(7000, 8000, 9000);

        Elf elf5 = parsedElves.get(4);
        assertNotNull(elf5);
        assertEquals(5, elf5.id());
        assertThat(elf5.snacks())
                .extracting(Snack::calories)
                .containsExactly(10000);
    }

    @Test
    void testParse_withLeadingEmptyLines() {
        String input = """
                
                
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000""";

        CalorieListParser parser = new CalorieListParser();
        List<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(5, parsedElves.size());

        Elf elf1 = parsedElves.get(0);
        assertNotNull(elf1);
        assertEquals(1, elf1.id());
        assertThat(elf1.snacks())
                .extracting(Snack::calories)
                .containsExactly(1000, 2000, 3000);

        Elf elf2 = parsedElves.get(1);
        assertNotNull(elf2);
        assertEquals(2, elf2.id());
        assertThat(elf2.snacks())
                .extracting(Snack::calories)
                .containsExactly(4000);

        Elf elf3 = parsedElves.get(2);
        assertNotNull(elf3);
        assertEquals(3, elf3.id());
        assertThat(elf3.snacks())
                .extracting(Snack::calories)
                .containsExactly(5000, 6000);

        Elf elf4 = parsedElves.get(3);
        assertNotNull(elf4);
        assertEquals(4, elf4.id());
        assertThat(elf4.snacks())
                .extracting(Snack::calories)
                .containsExactly(7000, 8000, 9000);

        Elf elf5 = parsedElves.get(4);
        assertNotNull(elf5);
        assertEquals(5, elf5.id());
        assertThat(elf5.snacks())
                .extracting(Snack::calories)
                .containsExactly(10000);
    }

    @Test
    void testParse_withTrailingEmptyLines() {
        String input = """
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000

                10000

                """;

        CalorieListParser parser = new CalorieListParser();
        List<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()))
                .toList();

        assertEquals(5, parsedElves.size());

        Elf elf1 = parsedElves.get(0);
        assertNotNull(elf1);
        assertEquals(1, elf1.id());
        assertThat(elf1.snacks())
                .extracting(Snack::calories)
                .containsExactly(1000, 2000, 3000);

        Elf elf2 = parsedElves.get(1);
        assertNotNull(elf2);
        assertEquals(2, elf2.id());
        assertThat(elf2.snacks())
                .extracting(Snack::calories)
                .containsExactly(4000);

        Elf elf3 = parsedElves.get(2);
        assertNotNull(elf3);
        assertEquals(3, elf3.id());
        assertThat(elf3.snacks())
                .extracting(Snack::calories)
                .containsExactly(5000, 6000);

        Elf elf4 = parsedElves.get(3);
        assertNotNull(elf4);
        assertEquals(4, elf4.id());
        assertThat(elf4.snacks())
                .extracting(Snack::calories)
                .containsExactly(7000, 8000, 9000);

        Elf elf5 = parsedElves.get(4);
        assertNotNull(elf5);
        assertEquals(5, elf5.id());
        assertThat(elf5.snacks())
                .extracting(Snack::calories)
                .containsExactly(10000);
    }

    @Test
    void testParse_withEmptyInput() {
        String input = "";

        CalorieListParser parser = new CalorieListParser();
        Stream<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertTrue(parsedElves.findAny().isEmpty());
    }

    @Test
    void testParse_withWhitespaceInput() {
        String input = """
                
                
                """;

        CalorieListParser parser = new CalorieListParser();
        Stream<Elf> parsedElves = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertTrue(parsedElves.findAny().isEmpty());
    }

    @Test
    void testParseThrowsException_withInvalidInput() {
        String input = """
                1000
                asdf
                """;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CalorieListParser parser = new CalorieListParser();

            @SuppressWarnings("unused")
            List<Elf> ignored = parser.parse(new ByteArrayInputStream(input.getBytes()))
                    .toList();
        });

        assertEquals("Invalid input on line 2: asdf", exception.getMessage());
    }
}
