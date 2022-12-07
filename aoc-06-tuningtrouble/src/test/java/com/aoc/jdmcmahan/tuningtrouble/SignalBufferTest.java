package com.aoc.jdmcmahan.tuningtrouble;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignalBufferTest {

    @Test
    void testPacketStart_withExampleInput() throws IOException {
        SignalBuffer buffer1 = bufferSignal("example1.txt");
        assertEquals(7, buffer1.getPacketStart());

        SignalBuffer buffer2 = bufferSignal("example2.txt");
        assertEquals(5, buffer2.getPacketStart());

        SignalBuffer buffer3 = bufferSignal("example3.txt");
        assertEquals(6, buffer3.getPacketStart());

        SignalBuffer buffer4 = bufferSignal("example4.txt");
        assertEquals(10, buffer4.getPacketStart());

        SignalBuffer buffer5 = bufferSignal("example5.txt");
        assertEquals(11, buffer5.getPacketStart());
    }

    @Test
    void testPacketStart_withChallengeInput() throws IOException {
        SignalBuffer buffer = bufferSignal("challenge.txt");
        assertEquals(1658, buffer.getPacketStart());
    }

    @Test
    void testMessageStart_withExampleInput() throws IOException {
        SignalBuffer buffer1 = bufferSignal("example1.txt");
        assertEquals(19, buffer1.getMessageStart());

        SignalBuffer buffer2 = bufferSignal("example2.txt");
        assertEquals(23, buffer2.getMessageStart());

        SignalBuffer buffer3 = bufferSignal("example3.txt");
        assertEquals(23, buffer3.getMessageStart());

        SignalBuffer buffer4 = bufferSignal("example4.txt");
        assertEquals(29, buffer4.getMessageStart());

        SignalBuffer buffer5 = bufferSignal("example5.txt");
        assertEquals(26, buffer5.getMessageStart());
    }

    @Test
    void testMessageStart_withChallengeInput() throws IOException {
        SignalBuffer buffer = bufferSignal("challenge.txt");
        assertEquals(2260, buffer.getMessageStart());
    }

    /**
     * This would be much cleaner by simply loading the whole resource as a string, but:
     *   1. the challenge *techknickally* states the signal is received one character at a time
     *   2. the actual challenge is kinda mid anyway so why not
     * <p>
     * We could also short-circuit once the markers are found, but this tests the full buffer to ensure the markers
     * don't change for some reason.
     */
    private static SignalBuffer bufferSignal(String resourceName) throws IOException {
        try (InputStream input = SignalBufferTest.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (input == null) {
                throw new IllegalArgumentException("Cannot find resource with name " + resourceName);
            }

            SignalBuffer buffer = new SignalBuffer(4, 14);
            for (int c; (c = input.read()) != -1; ) {
                buffer.buffer((char) c);
            }

            return buffer;
        }
    }
}
