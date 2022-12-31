package com.aoc.jdmcmahan.beaconexclusionzone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    @Test
    void testRowCoverage() {
        Beacon beacon = new Beacon(Position.valueOf(2, 10));
        Sensor sensor = new Sensor(Position.valueOf(8, 7), beacon);

        assertEquals(1, sensor.rowCoverage(-2).size());
        assertEquals(3, sensor.rowCoverage(-1).size());
        assertEquals(5, sensor.rowCoverage(0).size());
        assertEquals(7, sensor.rowCoverage(1).size());
        assertEquals(9, sensor.rowCoverage(2).size());
        assertEquals(11, sensor.rowCoverage(3).size());
        assertEquals(13, sensor.rowCoverage(4).size());
        assertEquals(15, sensor.rowCoverage(5).size());
        assertEquals(17, sensor.rowCoverage(6).size());
        assertEquals(19, sensor.rowCoverage(7).size());
        assertEquals(17, sensor.rowCoverage(8).size());
        assertEquals(15, sensor.rowCoverage(9).size());
        assertEquals(13, sensor.rowCoverage(10).size());
        assertEquals(11, sensor.rowCoverage(11).size());
        assertEquals(9, sensor.rowCoverage(12).size());
        assertEquals(7, sensor.rowCoverage(13).size());
        assertEquals(5, sensor.rowCoverage(14).size());
        assertEquals(3, sensor.rowCoverage(15).size());
        assertEquals(1, sensor.rowCoverage(16).size());
    }
}