package com.aoc.jdmcmahan.monkeyinthemiddle;

import com.aoc.jdmcmahan.monkeyinthemiddle.model.Item;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.Monkey;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.MonkeyRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KeepAwayTest {

    @Test
    void testPlayRounds_withExampleInput() throws IOException {
        try (InputStream input = KeepAwayTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            AntlrMonkeyNotesParser parser = new AntlrMonkeyNotesParser();
            MonkeyRegistry registry = parser.parse(input);

            Monkey monkey0 = registry.getMonkey("0")
                    .orElseGet(Assertions::fail);

            Monkey monkey1 = registry.getMonkey("1")
                    .orElseGet(Assertions::fail);

            Monkey monkey2 = registry.getMonkey("2")
                    .orElseGet(Assertions::fail);

            Monkey monkey3 = registry.getMonkey("3")
                    .orElseGet(Assertions::fail);

            KeepAway game = new KeepAway(registry, worry -> worry / 3);

            // Round 1
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(20L, 23L, 27L, 26L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(2080L, 25L, 167L, 207L, 401L, 1046L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 2
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(695L, 10L, 71L, 135L, 350L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(43L, 49L, 58L, 55L, 362L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 3
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(16L, 18L, 21L, 20L, 122L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(1468L, 22L, 150L, 286L, 739L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 4
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(491L, 9L, 52L, 97L, 248L, 34L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(39L, 45L, 43L, 258L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 5
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(15L, 17L, 16L, 88L, 1037L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(20L, 110L, 205L, 524L, 72L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 6
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(8L, 70L, 176L, 26L, 34L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(481L, 32L, 36L, 186L, 2190L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 7
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(162L, 12L, 14L, 64L, 732L, 17L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(148L, 372L, 55L, 72L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 8
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(51L, 126L, 20L, 26L, 136L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(343L, 26L, 30L, 1546L, 36L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 9
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(116L, 10L, 12L, 517L, 14L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(108L, 267L, 43L, 55L, 288L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            // Round 10
            game.playRound();

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(91L, 16L, 20L, 98L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(481L, 245L, 22L, 26L, 1092L, 30L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            while (game.getCurrentRound() <= 15) {
                game.playRound();
            }

            // Round 15

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(83L, 44L, 8L, 184L, 9L, 20L, 26L, 102L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(110L, 36L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            while (game.getCurrentRound() <= 20) {
                game.playRound();
            }

            // Round 20

            assertThat(monkey0.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(10L, 12L, 14L, 26L, 34L);

            assertThat(monkey1.getItems())
                    .extracting(Item::getWorryLevel)
                    .containsExactly(245L, 93L, 53L, 199L, 115L);

            assertTrue(monkey2.getItems().isEmpty());
            assertTrue(monkey3.getItems().isEmpty());

            assertEquals(101L, monkey0.getMonkeyBusiness());
            assertEquals(95L, monkey1.getMonkeyBusiness());
            assertEquals(7L, monkey2.getMonkeyBusiness());
            assertEquals(105L, monkey3.getMonkeyBusiness());

            long topMonkeyBusiness = registry.getMonkeys().stream()
                    .map(Monkey::getMonkeyBusiness)
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .reduce(1L, (b1, b2) -> b1 * b2);

            assertEquals(10605L, topMonkeyBusiness);
        }
    }

    @Test
    void testPlayRounds_withChallengeInput() throws IOException {
        try (InputStream input = KeepAwayTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            AntlrMonkeyNotesParser parser = new AntlrMonkeyNotesParser();
            MonkeyRegistry registry = parser.parse(input);

            KeepAway game = new KeepAway(registry, worry -> worry / 3);

            while (game.getCurrentRound() <= 20) {
                game.playRound();
            }

            long topMonkeyBusiness = registry.getMonkeys().stream()
                    .map(Monkey::getMonkeyBusiness)
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .reduce(1L, (b1, b2) -> b1 * b2);

            assertEquals(117624L, topMonkeyBusiness);
        }
    }

    @Test
    void testPlayMoarRounds_withExampleInput() throws IOException {
        try (InputStream input = KeepAwayTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            AntlrMonkeyNotesParser parser = new AntlrMonkeyNotesParser();
            MonkeyRegistry registry = parser.parse(input);

            Monkey monkey0 = registry.getMonkey("0")
                    .orElseGet(Assertions::fail);

            Monkey monkey1 = registry.getMonkey("1")
                    .orElseGet(Assertions::fail);

            Monkey monkey2 = registry.getMonkey("2")
                    .orElseGet(Assertions::fail);

            Monkey monkey3 = registry.getMonkey("3")
                    .orElseGet(Assertions::fail);

            // "find another way to keep your worry levels manageable"
            // This uses modular arithmetic to avoid numeric overflow at the cost of correct items (and flexibility with the test logic)
            long divisorProduct = registry.getMonkeys().stream()
                    .mapToLong(Monkey::getDivisor)
                    .reduce(1, (d1, d2) -> d1 * d2);

            KeepAway game = new KeepAway(registry, worry -> worry % divisorProduct);

            game.playRound();

            assertEquals(2L, monkey0.getMonkeyBusiness());
            assertEquals(4L, monkey1.getMonkeyBusiness());
            assertEquals(3L, monkey2.getMonkeyBusiness());
            assertEquals(6L, monkey3.getMonkeyBusiness());

            while (game.getCurrentRound() <= 20) {
                game.playRound();
            }

            assertEquals(99L, monkey0.getMonkeyBusiness());
            assertEquals(97L, monkey1.getMonkeyBusiness());
            assertEquals(8L, monkey2.getMonkeyBusiness());
            assertEquals(103L, monkey3.getMonkeyBusiness());

            while (game.getCurrentRound() <= 1000) {
                game.playRound();
            }

            assertEquals(5204L, monkey0.getMonkeyBusiness());
            assertEquals(4792L, monkey1.getMonkeyBusiness());
            assertEquals(199L, monkey2.getMonkeyBusiness());
            assertEquals(5192L, monkey3.getMonkeyBusiness());

            while (game.getCurrentRound() <= 9000) {
                game.playRound();
            }

            assertEquals(46945L, monkey0.getMonkeyBusiness());
            assertEquals(43051L, monkey1.getMonkeyBusiness());
            assertEquals(1746L, monkey2.getMonkeyBusiness());
            assertEquals(46807L, monkey3.getMonkeyBusiness());

            while (game.getCurrentRound() <= 10000) {
                game.playRound();
            }

            assertEquals(52166L, monkey0.getMonkeyBusiness());
            assertEquals(47830L, monkey1.getMonkeyBusiness());
            assertEquals(1938L, monkey2.getMonkeyBusiness());
            assertEquals(52013L, monkey3.getMonkeyBusiness());

            long topMonkeyBusiness = registry.getMonkeys().stream()
                    .map(Monkey::getMonkeyBusiness)
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .reduce(1L, (b1, b2) -> b1 * b2);

            assertEquals(2713310158L, topMonkeyBusiness);
        }
    }

    @Test
    void testPlayMoarRounds_withChallengeInput() throws IOException {
        try (InputStream input = KeepAwayTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            AntlrMonkeyNotesParser parser = new AntlrMonkeyNotesParser();
            MonkeyRegistry registry = parser.parse(input);

            long divisorProduct = registry.getMonkeys().stream()
                    .mapToLong(Monkey::getDivisor)
                    .reduce(1, (d1, d2) -> d1 * d2);

            KeepAway game = new KeepAway(registry, worry -> worry % divisorProduct);

            while (game.getCurrentRound() <= 10000) {
                game.playRound();
            }

            long topMonkeyBusiness = registry.getMonkeys().stream()
                    .map(Monkey::getMonkeyBusiness)
                    .sorted(Comparator.reverseOrder())
                    .limit(2)
                    .reduce(1L, (b1, b2) -> b1 * b2);

            assertEquals(16792940265L, topMonkeyBusiness);
        }
    }
}
