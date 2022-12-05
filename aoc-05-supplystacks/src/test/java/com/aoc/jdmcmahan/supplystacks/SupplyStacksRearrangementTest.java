package com.aoc.jdmcmahan.supplystacks;

import com.aoc.jdmcmahan.supplystacks.command.CrateMover9001CommandExecutor;
import com.aoc.jdmcmahan.supplystacks.model.Crate;
import com.aoc.jdmcmahan.supplystacks.model.CrateStack;
import com.aoc.jdmcmahan.supplystacks.model.Ship;
import com.aoc.jdmcmahan.supplystacks.command.CrateMover9000CommandExecutor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplyStacksRearrangementTest {

    @Test
    void testCrateMover9000Rearrangement_withExampleInput() throws IOException {
        try (InputStream input = SupplyStacksRearrangementTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SupplyStackParser parser = new SupplyStackParser(new CrateMover9000CommandExecutor());

            Ship ship = parser.parse(input);
            List<String> topTags = ship.stacks().stream()
                    .map(CrateStack::top)
                    .map(Crate::tag)
                    .toList();

            assertEquals(3, topTags.size());
            assertThat(topTags)
                    .containsExactly("C", "M", "Z");
        }
    }

    @Test
    void testCrateMover9000Rearrangement_withChallengeInput() throws IOException {
        try (InputStream input = SupplyStacksRearrangementTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SupplyStackParser parser = new SupplyStackParser(new CrateMover9000CommandExecutor());

            Ship ship = parser.parse(input);
            List<String> topTags = ship.stacks().stream()
                    .map(CrateStack::top)
                    .map(Crate::tag)
                    .toList();

            assertEquals(9, topTags.size());
            assertThat(topTags)
                    .containsExactly("Z", "S", "Q", "V", "C", "C", "J", "L", "L");
        }
    }

    @Test
    void testCrateMover9001Rearrangement_withExampleInput() throws IOException {
        try (InputStream input = SupplyStacksRearrangementTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SupplyStackParser parser = new SupplyStackParser(new CrateMover9001CommandExecutor());

            Ship ship = parser.parse(input);
            List<String> topTags = ship.stacks().stream()
                    .map(CrateStack::top)
                    .map(Crate::tag)
                    .toList();

            assertEquals(3, topTags.size());
            assertThat(topTags)
                    .containsExactly("M", "C", "D");
        }
    }

    @Test
    void testCrateMover9001Rearrangement_withChallengeInput() throws IOException {
        try (InputStream input = SupplyStacksRearrangementTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SupplyStackParser parser = new SupplyStackParser(new CrateMover9001CommandExecutor());

            Ship ship = parser.parse(input);
            List<String> topTags = ship.stacks().stream()
                    .map(CrateStack::top)
                    .map(Crate::tag)
                    .toList();

            assertEquals(9, topTags.size());
            assertThat(topTags)
                    .containsExactly("Q", "Z", "F", "J", "R", "W", "H", "G", "S");
        }
    }
}
