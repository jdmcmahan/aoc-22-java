package com.aoc.jdmcmahan.grovepositioningsystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;

public class SequenceParser {

    public SequenceMixer parse(InputStream input, long decryptionKey) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                List<Integer> values = new LinkedList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    values.add(Integer.parseInt(line));
                }

                return new SequenceMixer(values, decryptionKey);
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }
}
