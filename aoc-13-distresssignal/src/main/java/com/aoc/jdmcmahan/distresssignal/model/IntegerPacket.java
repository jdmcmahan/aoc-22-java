package com.aoc.jdmcmahan.distresssignal.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Builder
public class IntegerPacket implements Packet {

    private final int value;

    @Override
    public int compareTo(@NotNull Packet o) {
        if (o instanceof IntegerPacket) {
            return Integer.compare(this.value, ((IntegerPacket) o).value);
        }

        return -o.compareTo(this);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
