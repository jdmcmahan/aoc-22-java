package com.aoc.jdmcmahan.monkeyinthemiddle;

import com.aoc.jdmcmahan.monkeyinthemiddle.model.Item;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.Monkey;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.MonkeyRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AntlrMonkeyNotesParserTest {

    @Test
    void testMonkeyRegistryPopulated_withSingleMonkey() throws IOException {
        String notes = """
                Monkey 0:
                  Starting items: 79, 98
                  Operation: new = old * 19
                  Test: divisible by 23
                    If true: throw to monkey 2
                    If false: throw to monkey 3
                """;

        try (ByteArrayInputStream input = new ByteArrayInputStream(notes.getBytes(StandardCharsets.UTF_8))) {
            AntlrMonkeyNotesParser parser = new AntlrMonkeyNotesParser();
            MonkeyRegistry registry = parser.parse(input);

            assertEquals(1, registry.getMonkeys().size());

            Monkey monkey = registry.getMonkey("0")
                    .orElseGet(Assertions::fail);

            assertEquals("0", monkey.getId());
            assertEquals(23, monkey.getDivisor());

            assertThat(monkey.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(79L, 98L);
        }
    }
}
