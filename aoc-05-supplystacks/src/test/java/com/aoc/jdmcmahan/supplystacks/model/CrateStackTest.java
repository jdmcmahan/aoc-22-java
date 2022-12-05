package com.aoc.jdmcmahan.supplystacks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CrateStackTest {

    CrateStack stack;

    @BeforeEach
    void initializeStack() {
        stack = CrateStack.builder()
                .id("1")
                .stack(new Crate("A"))
                .stack(new Crate("B"))
                .stack(new Crate("C"))
                .build();
    }

    @Test
    void testId() {
        assertEquals("1", stack.id());
    }

    @Test
    void testCrates() {
        assertThat(stack.crates())
                .extracting(Crate::tag)
                .containsExactly("C", "B", "A");
    }

    @Test
    void testTop() {
        assertEquals("C", stack.top().tag());
    }

    @Test
    void testStack() {
        stack.stack(new Crate("D"));

        assertEquals(4, stack.crates().size());
        assertEquals("D", stack.top().tag());
    }

    @Test
    void testStack_withMultipleCrates() {
        stack.stack(Arrays.asList(new Crate("D"), new Crate("E")));

        assertEquals(5, stack.crates().size());
        assertEquals("D", stack.top().tag());

        assertThat(stack.crates())
                .extracting(Crate::tag)
                .containsExactly("D", "E", "C", "B", "A");
    }

    @Test
    void testUnstack() {
        List<Crate> crates = stack.unstack(2);

        assertEquals(2, crates.size());
        assertThat(crates)
                .extracting(Crate::tag)
                .containsExactly("C", "B");

        assertEquals(1, stack.crates().size());
        assertThat(stack.crates())
                .extracting(Crate::tag)
                .containsExactly("A");
    }

    @Test
    void testUnstackThrowsException_whenNotEnoughCrates() {
        assertThrows(IllegalArgumentException.class, () -> stack.unstack(4));
    }
}
