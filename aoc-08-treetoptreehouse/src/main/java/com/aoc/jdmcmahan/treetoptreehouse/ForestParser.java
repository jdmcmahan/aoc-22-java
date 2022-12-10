package com.aoc.jdmcmahan.treetoptreehouse;

import com.aoc.jdmcmahan.treetoptreehouse.model.Forest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class ForestParser {

    public Forest parse(InputStream input) throws IOException {
        Forest.ForestBuilder builder = Forest.builder();

        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] heights = line.chars()
                        .map(c -> Integer.parseInt(String.valueOf((char) c)))
                        .toArray();

                builder = builder.row(heights);
            }
        }

        return builder.build();
    }
}
