package com.aoc.jdmcmahan.monkeymath;

import com.aoc.jdmcmahan.monkeymath.model.Monkey;
import com.aoc.jdmcmahan.monkeymath.model.MonkeyRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonkeyYellTest {

    @Test
    void testYell_withExampleInput() throws IOException {
        try (InputStream input = MonkeyYellTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            MonkeyMathParser parser = new MonkeyMathParser();
            MonkeyRegistry registry = parser.parse(input);

            Monkey root = registry.getMonkey("root")
                    .orElseGet(Assertions::fail);

            assertEquals(152, root.yell());
        }
    }

    @Test
    void testYell_withChallengeInput() throws IOException {
        try (InputStream input = MonkeyYellTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            MonkeyMathParser parser = new MonkeyMathParser();
            MonkeyRegistry registry = parser.parse(input);

            Monkey root = registry.getMonkey("root")
                    .orElseGet(Assertions::fail);

            assertEquals(85616733059734L, root.yell());
        }
    }

    @Test
    void testUnisolatedYell_withExampleInput() throws IOException {
        try (InputStream input = MonkeyYellTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            MonkeyMathParser parser = new MonkeyMathParser();
            MonkeyRegistry registry = parser.parse(input, MonkeyMathParser.ROOT_AS_EQUALITY_PARSER, MonkeyMathParser.HUMAN_AS_UNISOLATED_PARSER);

            Monkey root = registry.getMonkey("root")
                    .orElseGet(Assertions::fail);

            assertEquals(150, root.yell());

            Monkey human = registry.getMonkey("humn")
                    .orElseGet(Assertions::fail);

            assertEquals(301, human.yell());
        }
    }

    @Test
    void testUnisolatedYell_withChallengeInput() throws IOException {
        try (InputStream input = MonkeyYellTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            MonkeyMathParser parser = new MonkeyMathParser();
            MonkeyRegistry registry = parser.parse(input, MonkeyMathParser.ROOT_AS_EQUALITY_PARSER, MonkeyMathParser.HUMAN_AS_UNISOLATED_PARSER);

            Monkey human = registry.getMonkey("humn")
                    .orElseGet(Assertions::fail);

            assertEquals(3560324848168L, human.yell());
        }
    }
}
