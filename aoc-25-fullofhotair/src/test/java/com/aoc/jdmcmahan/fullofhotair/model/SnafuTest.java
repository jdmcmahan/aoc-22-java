package com.aoc.jdmcmahan.fullofhotair.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnafuTest {

    @Test
    void testIntValue() {
        assertEquals(1747, Snafu.valueOf("1=-0-2").intValue());
        assertEquals(906, Snafu.valueOf("12111").intValue());
        assertEquals(198, Snafu.valueOf("2=0=").intValue());
        assertEquals(11, Snafu.valueOf("21").intValue());
        assertEquals(201, Snafu.valueOf("2=01").intValue());
        assertEquals(31, Snafu.valueOf("111").intValue());
        assertEquals(1257, Snafu.valueOf("20012").intValue());
        assertEquals(32, Snafu.valueOf("112").intValue());
        assertEquals(353, Snafu.valueOf("1=-1=").intValue());
        assertEquals(107, Snafu.valueOf("1-12").intValue());
        assertEquals(7, Snafu.valueOf("12").intValue());
        assertEquals(3, Snafu.valueOf("1=").intValue());
        assertEquals(37, Snafu.valueOf("122").intValue());
    }

    @Test
    void testLongValue() {
        assertEquals(1747L, Snafu.valueOf("1=-0-2").longValue());
        assertEquals(906L, Snafu.valueOf("12111").longValue());
        assertEquals(198L, Snafu.valueOf("2=0=").longValue());
        assertEquals(11L, Snafu.valueOf("21").longValue());
        assertEquals(201L, Snafu.valueOf("2=01").longValue());
        assertEquals(31L, Snafu.valueOf("111").longValue());
        assertEquals(1257L, Snafu.valueOf("20012").longValue());
        assertEquals(32L, Snafu.valueOf("112").longValue());
        assertEquals(353L, Snafu.valueOf("1=-1=").longValue());
        assertEquals(107L, Snafu.valueOf("1-12").longValue());
        assertEquals(7L, Snafu.valueOf("12").longValue());
        assertEquals(3L, Snafu.valueOf("1=").longValue());
        assertEquals(37L, Snafu.valueOf("122").longValue());
    }

    @Test
    void testValueOf_withDecimalInput() {
        assertEquals("1", Snafu.valueOf(1).toString());
        assertEquals("2", Snafu.valueOf(2).toString());
        assertEquals("1=", Snafu.valueOf(3).toString());
        assertEquals("1-", Snafu.valueOf(4).toString());
        assertEquals("10", Snafu.valueOf(5).toString());
        assertEquals("11", Snafu.valueOf(6).toString());
        assertEquals("12", Snafu.valueOf(7).toString());
        assertEquals("2=", Snafu.valueOf(8).toString());
        assertEquals("2-", Snafu.valueOf(9).toString());
        assertEquals("20", Snafu.valueOf(10).toString());
        assertEquals("1=0", Snafu.valueOf(15).toString());
        assertEquals("1-0", Snafu.valueOf(20).toString());
        assertEquals("1=11-2", Snafu.valueOf(2022).toString());
        assertEquals("1-0---0", Snafu.valueOf(12345).toString());
        assertEquals("1121-1110-1=0", Snafu.valueOf(314159265).toString());
    }
}