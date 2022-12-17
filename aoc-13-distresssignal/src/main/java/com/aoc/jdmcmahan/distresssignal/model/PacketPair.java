package com.aoc.jdmcmahan.distresssignal.model;

public record PacketPair(int id, Packet left, Packet right) {

    public boolean isSorted() {
        return left.compareTo(right) <= 0;
    }
}
