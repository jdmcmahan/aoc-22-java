package com.aoc.jdmcmahan.distresssignal.model;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Builder
public class ListPacket implements Packet {

    @Singular
    private final List<Packet> contents;

    @Override
    public int compareTo(@NotNull Packet o) {
        if (this == o) {
            return 0;
        }

        ListPacket other = (o instanceof ListPacket)
                ? (ListPacket) o
                : ListPacket.builder().content(o).build();

        Iterator<Packet> leftIterator = this.contents.iterator();
        Iterator<Packet> rightIterator = other.contents.iterator();

        while (leftIterator.hasNext() && rightIterator.hasNext()) {
            Packet left = leftIterator.next();
            Packet right = rightIterator.next();

            int value = left.compareTo(right);
            if (value != 0) {
                return value;
            }
        }

        if (!leftIterator.hasNext() && !rightIterator.hasNext()) {
            return 0;
        }

        if (!leftIterator.hasNext()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return contents.stream()
                .map(Object::toString)
                .collect(Collectors.joining(",", "[", "]"));
    }
}
