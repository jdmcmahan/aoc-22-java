package com.aoc.jdmcmahan.beaconexclusionzone.model;

import java.util.*;
import java.util.stream.Stream;

public class Segments {

    private final TreeSet<Segment> segments = new TreeSet<>(Comparator.comparing(Segment::start));

    public void add(Segment segment) {
        for (Iterator<Segment> iterator = segments.iterator(); iterator.hasNext();) {
            Segment existing = iterator.next();

            if (existing.overlaps(segment)) {
                iterator.remove();
                segment = existing.union(segment);
            }
        }

        segments.add(segment);
    }

    public Segment segmentContaining(int index) {
        Segment floor = segments.floor(new Segment(index, index));
        if (floor != null && floor.contains(index)) {
            return floor;
        }

        return null;
    }

    public Stream<Segment> stream() {
        return segments.stream();
    }
}
