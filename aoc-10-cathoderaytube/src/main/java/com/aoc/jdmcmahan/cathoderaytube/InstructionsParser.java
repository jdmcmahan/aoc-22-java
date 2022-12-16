package com.aoc.jdmcmahan.cathoderaytube;

import com.aoc.jdmcmahan.cathoderaytube.model.Cpu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class InstructionsParser {

    public void parse(InputStream input, Cpu cpu) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.equals("noop")) {
                        cpu.noop();
                    } else if (line.startsWith("addx")) {
                        String[] tokens = line.split(" ");
                        if (tokens.length < 2) {
                            throw new IllegalArgumentException("Invalid addx command: " + line);
                        }

                        int value = Integer.parseInt(tokens[1]);
                        cpu.addx(value);
                    }
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }
}
