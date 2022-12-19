package com.aoc.jdmcmahan.boilingboulders;

import com.aoc.jdmcmahan.boilingboulders.model.LavaDroplet;
import com.aoc.jdmcmahan.boilingboulders.model.Cube;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class LavaDropletParser {

    public LavaDroplet parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                LavaDroplet.LavaDropletBuilder builder = LavaDroplet.builder();

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens.length != 3) {
                        throw new IllegalArgumentException("Invalid coordinates: " + line);
                    }

                    Cube cube = new Cube(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    builder.cube(cube);
                }

                return builder.build();
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }
}
