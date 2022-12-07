package com.aoc.jdmcmahan.tuningtrouble;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Getter
public class SignalBuffer {

    private final int packetMarkerWindowSize;
    private final int messageMarkerWindowSize;

    private String signal = "";
    private int packetStart = -1;
    private int messageStart = -1;

    public void buffer(char c) {
        this.signal += c;

        if (packetStart < 0 && hasMarkerAtCurrentPosition(packetMarkerWindowSize)) {
            packetStart = signal.length();
        }

        if (messageStart < 0 && hasMarkerAtCurrentPosition(messageMarkerWindowSize)) {
            messageStart = signal.length();
        }
    }

    protected boolean hasMarkerAtCurrentPosition(int windowSize) {
        int length = signal.length();
        if (windowSize > length) {
            return false;
        }

        Set<Integer> unique = new HashSet<>();
        return IntStream.range(length - windowSize, length)
                .map(signal::charAt)
                .allMatch(unique::add);
    }
}
