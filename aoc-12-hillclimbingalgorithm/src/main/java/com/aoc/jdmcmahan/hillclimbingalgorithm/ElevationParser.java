package com.aoc.jdmcmahan.hillclimbingalgorithm;

import com.aoc.jdmcmahan.hillclimbingalgorithm.model.ElevationMap;
import com.aoc.jdmcmahan.hillclimbingalgorithm.model.Hike;
import com.aoc.jdmcmahan.hillclimbingalgorithm.model.Position;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ElevationParser {

    public Hike parse(InputStream input) throws IOException {
        ElevationMap.ElevationMapBuilder mapBuilder = ElevationMap.builder();

        Position start = null;
        Position end = null;

        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            int y = 0;
            String line;

            while ((line = reader.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    char c = line.charAt(x);
                    Position current = Position.valueOf(x, y);

                    if (c == 'S') {
                        start = current;
                        c = 'a';
                    } else if (c == 'E') {
                        end = current;
                        c = 'z';
                    }

                    mapBuilder = mapBuilder.square(current, c);
                }

                y++;
            }
        }

        ElevationMap map = mapBuilder.build();
        return new Hike(map, start, end);
    }
}
