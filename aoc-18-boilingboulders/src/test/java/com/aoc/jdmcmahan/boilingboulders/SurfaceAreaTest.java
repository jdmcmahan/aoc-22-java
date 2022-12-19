package com.aoc.jdmcmahan.boilingboulders;

import com.aoc.jdmcmahan.boilingboulders.model.LavaDroplet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurfaceAreaTest {

    @Test
    void testSurfaceArea_withExampleInput() throws IOException {
        try (InputStream input = SurfaceAreaTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            LavaDropletParser parser = new LavaDropletParser();
            LavaDroplet droplet = parser.parse(input);

            assertEquals(64, droplet.surfaceArea(true));
        }
    }

    @Test
    void testSurfaceArea_withChallengeInput() throws IOException {
        try (InputStream input = SurfaceAreaTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            LavaDropletParser parser = new LavaDropletParser();
            LavaDroplet droplet = parser.parse(input);

            assertEquals(4282, droplet.surfaceArea(true));
        }
    }

    @Test
    void testExposedSurfaceArea_withExampleInput() throws IOException {
        try (InputStream input = SurfaceAreaTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            LavaDropletParser parser = new LavaDropletParser();
            LavaDroplet droplet = parser.parse(input);

            assertEquals(58, droplet.surfaceArea(false));
        }
    }

    @Test
    void testExposedSurfaceArea_withChallengeInput() throws IOException {
        try (InputStream input = SurfaceAreaTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            LavaDropletParser parser = new LavaDropletParser();
            LavaDroplet droplet = parser.parse(input);

            assertEquals(2452, droplet.surfaceArea(false));
        }
    }
}
