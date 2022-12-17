package com.aoc.jdmcmahan.distresssignal;

import com.aoc.jdmcmahan.distresssignal.model.IntegerPacket;
import com.aoc.jdmcmahan.distresssignal.model.ListPacket;
import com.aoc.jdmcmahan.distresssignal.model.Packet;
import com.aoc.jdmcmahan.distresssignal.model.PacketPair;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortedPacketsTest {

    @Test
    void testSortedPackets_withExampleInput() throws IOException {
        try (InputStream input = SortedPacketsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PacketPairParser parser = new PacketPairParser();
            List<PacketPair> packetPairs = parser.parse(input);

            List<PacketPair> sortedPairs = packetPairs.stream()
                    .filter(PacketPair::isSorted)
                    .toList();

            assertThat(sortedPairs)
                    .extracting(PacketPair::id)
                    .containsExactly(1, 2, 4, 6);

            assertEquals(13, sortedPairs.stream()
                    .mapToInt(PacketPair::id)
                    .sum());
        }
    }

    @Test
    void testSortedPackets_withChallengeInput() throws IOException {
        try (InputStream input = SortedPacketsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PacketPairParser parser = new PacketPairParser();
            List<PacketPair> packetPairs = parser.parse(input);

            int sum = packetPairs.stream()
                    .filter(PacketPair::isSorted)
                    .mapToInt(PacketPair::id)
                    .sum();

            assertEquals(6369, sum);
        }
    }

    @Test
    void testDividerPackets_withExampleInput() throws IOException {
        try (InputStream input = SortedPacketsTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            PacketPairParser parser = new PacketPairParser();
            List<PacketPair> packetPairs = parser.parse(input);

            PacketSet.PacketSetBuilder setBuilder = PacketSet.builder();
            packetPairs.stream()
                    .flatMap(pair -> Stream.of(pair.left(), pair.right()))
                    .forEach(setBuilder::packet);

            Packet divider1 = ListPacket.builder()
                    .content(ListPacket.builder()
                            .content(IntegerPacket.builder()
                                    .value(2)
                                    .build())
                            .build())
                    .build();

            Packet divider2 = ListPacket.builder()
                    .content(ListPacket.builder()
                            .content(IntegerPacket.builder()
                                    .value(6)
                                    .build())
                            .build())
                    .build();

            PacketSet packetSet = setBuilder.packet(divider1)
                    .packet(divider2)
                    .build();

            int index1 = packetSet.indexOf(divider1) + 1;
            int index2 = packetSet.indexOf(divider2) + 1;

            assertEquals(10, index1);
            assertEquals(14, index2);
            assertEquals(140, index1 * index2);
        }
    }

    @Test
    void testDividerPackets_withChallengeInput() throws IOException {
        try (InputStream input = SortedPacketsTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            PacketPairParser parser = new PacketPairParser();
            List<PacketPair> packetPairs = parser.parse(input);

            PacketSet.PacketSetBuilder setBuilder = PacketSet.builder();
            packetPairs.stream()
                    .flatMap(pair -> Stream.of(pair.left(), pair.right()))
                    .forEach(setBuilder::packet);

            Packet divider1 = ListPacket.builder()
                    .content(ListPacket.builder()
                            .content(IntegerPacket.builder()
                                    .value(2)
                                    .build())
                            .build())
                    .build();

            Packet divider2 = ListPacket.builder()
                    .content(ListPacket.builder()
                            .content(IntegerPacket.builder()
                                    .value(6)
                                    .build())
                            .build())
                    .build();

            PacketSet packetSet = setBuilder.packet(divider1)
                    .packet(divider2)
                    .build();

            int index1 = packetSet.indexOf(divider1) + 1;
            int index2 = packetSet.indexOf(divider2) + 1;

            assertEquals(25800, index1 * index2);
        }
    }
}
