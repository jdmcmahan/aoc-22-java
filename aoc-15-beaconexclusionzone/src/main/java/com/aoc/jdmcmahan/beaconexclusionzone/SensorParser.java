package com.aoc.jdmcmahan.beaconexclusionzone;

import com.aoc.jdmcmahan.beaconexclusionzone.model.Beacon;
import com.aoc.jdmcmahan.beaconexclusionzone.model.Position;
import com.aoc.jdmcmahan.beaconexclusionzone.model.Sensor;
import com.aoc.jdmcmahan.beaconexclusionzone.model.SensorArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensorParser {

    private static final Pattern SENSOR_PATTERN = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

    public SensorArray parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                SensorArray.SensorArrayBuilder builder = SensorArray.builder();

                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = SENSOR_PATTERN.matcher(line);
                    if (!matcher.find()) {
                        throw new IllegalArgumentException("Invalid format: " + line);
                    }

                    Position sensorPosition = Position.valueOf(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
                    Position beaconPosition = Position.valueOf(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

                    Beacon beacon = new Beacon(beaconPosition);
                    Sensor sensor = new Sensor(sensorPosition, beacon);

                    builder = builder.sensor(sensor);
                }

                return builder.build();
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }
}
