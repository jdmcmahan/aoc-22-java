package com.aoc.jdmcmahan.hillclimbingalgorithm.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ElevationMap {

    private final Map<Position, Integer> elevationGrid = new HashMap<>();

    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    protected ElevationMap(Map<Position, Integer> elevationGrid) {
        // unnecessary fanciness for printing
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Map.Entry<Position, Integer> entry : elevationGrid.entrySet()) {
            Position position = entry.getKey();
            int elevation = entry.getValue();

            minX = Math.min(minX, position.getX());
            maxX = Math.max(maxX, position.getX());
            minY = Math.min(minY, position.getY());
            maxY = Math.max(maxY, position.getY());

            this.elevationGrid.put(position, elevation);
        }

        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public Stream<Position> positions() {
        return elevationGrid.keySet().stream();
    }

    public int elevationAt(int x, int y) {
        return this.elevationAt(Position.valueOf(x, y));
    }

    public int elevationAt(Position position) {
        return elevationGrid.get(position);
    }

    public boolean contains(Position position) {
        return elevationGrid.containsKey(position);
    }

    public void print(OutputStream out) throws IOException {
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                int c = elevationGrid.getOrDefault(Position.valueOf(x, y), (int) ' ');
                out.write((char) c);
            }

            out.write('\n');
        }
    }

    public static ElevationMapBuilder builder() {
        return new ElevationMapBuilder();
    }

    public static class ElevationMapBuilder {

        private final Map<Position, Integer> elevationGrid = new HashMap<>();

        public ElevationMapBuilder square(Position position, int elevation) {
            this.elevationGrid.put(position, elevation);
            return this;
        }

        public ElevationMap build() {
            return new ElevationMap(elevationGrid);
        }
    }
}
