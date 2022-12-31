package com.aoc.jdmcmahan.beaconexclusionzone;

import com.aoc.jdmcmahan.beaconexclusionzone.model.Position;
import com.aoc.jdmcmahan.beaconexclusionzone.model.Segment;
import com.aoc.jdmcmahan.beaconexclusionzone.model.Segments;
import com.aoc.jdmcmahan.beaconexclusionzone.model.SensorArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BeaconExclusionTest {

    @Test
    void testBeaconExclusion_withExampleInput() throws IOException {
        try (InputStream input = BeaconExclusionTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SensorParser parser = new SensorParser();
            SensorArray sensors = parser.parse(input);

            long coverage = sensors.rowCoverage(10).stream()
                    .mapToInt(Segment::size)
                    .sum();

            assertEquals(26, coverage - 1); // beacons aren't included
        }
    }

    @Test
    void testBeaconExclusion_withChallengeInput() throws IOException {
        try (InputStream input = BeaconExclusionTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SensorParser parser = new SensorParser();
            SensorArray sensors = parser.parse(input);

            long coverage = sensors.rowCoverage(2000000).stream()
                    .mapToInt(Segment::size)
                    .sum();

            assertEquals(4985193, coverage - 1); // beacons aren't included
        }
    }

    @Test
    void testDistressSignal_withExampleInput() throws IOException {
        try (InputStream input = BeaconExclusionTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            SensorParser parser = new SensorParser();
            SensorArray sensors = parser.parse(input);

            Position position = this.findDistressSignal(sensors, 20);

            assertEquals(Position.valueOf(14, 11), position);
            assertEquals(56000011L, this.calculateFrequency(position));
        }
    }

    @Test
    void testDistressSignal_withChallengeInput() throws IOException {
        try (InputStream input = BeaconExclusionTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            SensorParser parser = new SensorParser();
            SensorArray sensors = parser.parse(input);

            Position position = this.findDistressSignal(sensors, 4000000);

            assertEquals(Position.valueOf(2895970, 2601918), position);
            assertEquals(11583882601918L, this.calculateFrequency(position));
        }
    }

    protected Position findDistressSignal(SensorArray sensors, int limit) {
        for (int y = 0; y <= limit; y++) {
            Segments row = sensors.rowCoverage(y);

            for (int x = 0; x <= limit; x++) {
                Position candidate = Position.valueOf(x, y);

                Segment segment = row.segmentContaining(x);
                if (segment != null) {
                    x = segment.end();
                    continue;
                }

                return candidate;
            }
        }

        return null;
    }

    protected long calculateFrequency(Position position) {
        return (position.getX() * 4000000L) + position.getY();
    }
}