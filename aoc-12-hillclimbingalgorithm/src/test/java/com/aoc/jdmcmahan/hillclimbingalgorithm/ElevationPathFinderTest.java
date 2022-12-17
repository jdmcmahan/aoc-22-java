package com.aoc.jdmcmahan.hillclimbingalgorithm;

import com.aoc.jdmcmahan.hillclimbingalgorithm.model.ElevationMap;
import com.aoc.jdmcmahan.hillclimbingalgorithm.model.Hike;
import com.aoc.jdmcmahan.hillclimbingalgorithm.model.Position;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class ElevationPathFinderTest {

    @Test
    void testFindPath_withExampleInput() throws IOException {
        try (InputStream input = ElevationPathFinderTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ElevationParser parser = new ElevationParser();
            Hike hike = parser.parse(input);

            ElevationPathFinder pathFinder = new ElevationPathFinder();
            List<Position> path = pathFinder.mapPath(hike, 1);

            assertEquals(31, path.size());
        }
    }

    @Test
    void testFindPath_withChallengeInput() throws IOException {
        try (InputStream input = ElevationPathFinderTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ElevationParser parser = new ElevationParser();
            Hike hike = parser.parse(input);

            ElevationPathFinder pathFinder = new ElevationPathFinder();
            List<Position> path = pathFinder.mapPath(hike, 1);

            assertEquals(534, path.size());
        }
    }

    @Test
    void testFindIdealTrail_withExampleInput() throws IOException {
        try (InputStream input = ElevationPathFinderTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            ElevationParser parser = new ElevationParser();
            Hike hike = parser.parse(input);

            List<Position> path = idealPath(hike.map(), hike.end());

            assertEquals(29, path.size());
        }
    }

    @Test
    void testFindIdealTrail_withChallengeInput() throws IOException {
        try (InputStream input = ElevationPathFinderTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            ElevationParser parser = new ElevationParser();
            Hike hike = parser.parse(input);

            List<Position> path = idealPath(hike.map(), hike.end());

            assertEquals(525, path.size());
        }
    }

    private static List<Position> idealPath(ElevationMap map, Position end) {
        ElevationPathFinder pathFinder = new ElevationPathFinder();

        return map.positions()
                .filter(current -> map.elevationAt(current) == 'a')
                .map(current -> new Hike(map, current, end))
                .map(hike -> pathFinder.mapPath(hike, 1))
                .filter(Objects::nonNull)
                .min(Comparator.comparing(Collection::size))
                .orElse(null);
    }
}
