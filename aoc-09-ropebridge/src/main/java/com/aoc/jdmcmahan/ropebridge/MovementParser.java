package com.aoc.jdmcmahan.ropebridge;

import com.aoc.jdmcmahan.ropebridge.model.Rope;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class MovementParser {

    public Rope parse(InputStream input, int knotCount) throws IOException {
        Rope rope = new Rope(knotCount);

        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(" ");
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid input: " + line);
                    }

                    String direction = tokens[0];
                    int distance = Integer.parseInt(tokens[1]);

                    switch (direction) {
                        case "U" -> rope.moveHeadUp(distance);
                        case "D" -> rope.moveHeadDown(distance);
                        case "L" -> rope.moveHeadLeft(distance);
                        case "R" -> rope.moveHeadRight(distance);
                        default -> throw new IllegalArgumentException("Unknown direction: " + direction);
                    }
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }

        return rope;
    }
}
