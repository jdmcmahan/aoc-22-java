package com.aoc.jdmcmahan.fullofhotair;

import com.aoc.jdmcmahan.fullofhotair.model.Snafu;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.stream.Stream;

public class SnafuParser {

    public Stream<Snafu> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(Snafu::valueOf);
    }
}
