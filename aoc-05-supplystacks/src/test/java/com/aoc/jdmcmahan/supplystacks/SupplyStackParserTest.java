package com.aoc.jdmcmahan.supplystacks;

import com.aoc.jdmcmahan.supplystacks.model.Crate;
import com.aoc.jdmcmahan.supplystacks.model.CrateStack;
import com.aoc.jdmcmahan.supplystacks.model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplyStackParserTest {

    @Test
    void testParse() {
        String input = """
                    [D]   \s
                [N] [C]   \s
                [Z] [M] [P]
                 1   2   3\s
                """;

        SupplyStackParser parser = new SupplyStackParser();
        Ship ship = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertEquals(3, ship.stacks().size());
        assertThat(ship.stacks())
                .extracting(CrateStack::id)
                .containsExactlyInAnyOrder("1", "2", "3");

        assertThat(ship.stack("1").map(CrateStack::crates).orElseGet(Assertions::fail))
                .extracting(Crate::tag)
                .containsExactly("N", "Z");

        assertThat(ship.stack("2").map(CrateStack::crates).orElseGet(Assertions::fail))
                .extracting(Crate::tag)
                .containsExactly("D", "C", "M");

        assertThat(ship.stack("3").map(CrateStack::crates).orElseGet(Assertions::fail))
                .extracting(Crate::tag)
                .containsExactly("P");
    }

    @Test
    void testParse_withEmptyInitialStack() {
        String input = """
                [P] [D]   \s
                [N] [C]   \s
                [Z] [M]   \s
                 1   2   3\s
                """;

        SupplyStackParser parser = new SupplyStackParser();
        Ship ship = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertEquals(3, ship.stacks().size());
        assertThat(ship.stacks())
                .extracting(CrateStack::id)
                .containsExactlyInAnyOrder("1", "2", "3");

        assertThat(ship.stack("1").map(CrateStack::crates).orElseGet(Assertions::fail))
                .extracting(Crate::tag)
                .containsExactly("P", "N", "Z");

        assertThat(ship.stack("2").map(CrateStack::crates).orElseGet(Assertions::fail))
                .extracting(Crate::tag)
                .containsExactly("D", "C", "M");

        assertEquals(0, ship.stack("3").map(CrateStack::crates).map(Collection::size).orElseGet(Assertions::fail));
    }
}
