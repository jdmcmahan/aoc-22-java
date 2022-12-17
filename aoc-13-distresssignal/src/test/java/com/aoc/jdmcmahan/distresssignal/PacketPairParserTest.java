package com.aoc.jdmcmahan.distresssignal;

import com.aoc.jdmcmahan.distresssignal.model.PacketPair;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PacketPairParserTest {

    @Test
    void testParse() throws IOException {
        String input = """
                [1,1,3,1,1]
                [1,1,5,1,1]

                [[1],[2,3,4]]
                [[1],4]

                [9]
                [[8,7,6]]

                [[4,4],4,4]
                [[4,4],4,4,4]

                [7,7,7,7]
                [7,7,7]

                []
                [3]

                [[[]]]
                [[]]

                [1,[2,[3,[4,[5,6,7]]]],8,9]
                [1,[2,[3,[4,[5,6,0]]]],8,9]
                """;

        PacketPairParser parser = new PacketPairParser();
        List<PacketPair> pairs = parser.parse(new ByteArrayInputStream(input.getBytes()));

        assertEquals(8, pairs.size());
    }
}