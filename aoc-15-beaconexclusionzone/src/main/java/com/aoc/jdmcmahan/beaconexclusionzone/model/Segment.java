package com.aoc.jdmcmahan.beaconexclusionzone.model;

public record Segment(int start, int end) {

    public static final Segment EMPTY = new Segment(0, -1);

    public int size() {
        return end - start + 1;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean contains(int value) {
        return start <= value && value <= end;
    }

    public boolean overlaps(Segment other) {
        return (this.start() <= other.end() && this.end() >= other.end())
                || (other.start() <= this.end() && other.end() >= this.end())
                || (this.start() >= other.start() && this.end() <= other.end())
                || (other.start() >= this.start() && other.end() <= this.end());
    }

    public Segment union(Segment other) {
        if (!this.overlaps(other)) {
            throw new IllegalArgumentException("Segments do not overlap!");
        }

        return new Segment(Math.min(this.start(), other.start()), Math.max(this.end(), other.end()));
    }
}
