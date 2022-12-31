package com.aoc.jdmcmahan.beaconexclusionzone.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Singular;

import java.util.Collection;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SensorArray {

    @Singular
    private final Collection<Sensor> sensors;

    public Segments rowCoverage(int row) {
        Segments segments = new Segments();
        sensors.stream()
                .map(sensor -> sensor.rowCoverage(row))
                .filter(segment -> !segment.isEmpty())
                .forEach(segments::add);

        return segments;
    }
}
