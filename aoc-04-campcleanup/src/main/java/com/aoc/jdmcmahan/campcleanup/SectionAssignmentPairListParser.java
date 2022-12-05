package com.aoc.jdmcmahan.campcleanup;

import com.aoc.jdmcmahan.campcleanup.model.SectionAssignment;
import com.aoc.jdmcmahan.campcleanup.model.SectionAssignmentPair;
import com.aoc.jdmcmahan.campcleanup.model.SectionRange;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.stream.Stream;

public class SectionAssignmentPairListParser {

    public Stream<SectionAssignmentPair> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    try {
                        String[] sections = row.split(",");
                        if (sections.length != 2) {
                            throw new IllegalArgumentException("Expected 2 sections but found " + sections.length);
                        }

                        return new SectionAssignmentPair(parseSection(sections[0]), parseSection(sections[1]));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid input at line " + reader.getLineNumber() + ": " + row);
                    }
                });
    }

    protected static SectionAssignment parseSection(String s) {
        String[] tokens = s.split("-");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid section format for string: " + s);
        }

        try {
            SectionRange range = new SectionRange(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
            return new SectionAssignment(range);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid section ID in string: " + s);
        }
    }
}
