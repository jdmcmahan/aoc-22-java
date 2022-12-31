package com.aoc.jdmcmahan.beaconexclusionzone.model;

public record Sensor(Position position, Beacon closestBeacon) {

    public int range() {
        return position.manhattanDistance(closestBeacon.position());
    }

    public Segment rowCoverage(int row) {
        int rowOffset = Math.abs(row - position.getY());
        if (rowOffset > this.range()) {
            return Segment.EMPTY;
        }

        int start = position.getX() - this.range() + rowOffset;
        int end = position.getX() + this.range() - rowOffset;
        return new Segment(start, end);
    }
}
