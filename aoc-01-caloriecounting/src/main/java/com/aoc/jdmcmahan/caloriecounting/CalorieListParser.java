package com.aoc.jdmcmahan.caloriecounting;

import com.aoc.jdmcmahan.caloriecounting.model.Elf;
import com.aoc.jdmcmahan.caloriecounting.model.Snack;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CalorieListParser {

    public Stream<Elf> parse(InputStream input) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new ElfIterator(input), Spliterator.ORDERED | Spliterator.NONNULL), false);
    }

    protected static class ElfIterator implements Iterator<Elf> {

        private final LineNumberReader reader;

        private String nextLine;
        private int count = 0;

        public ElfIterator(InputStream input) {
            this.reader = new LineNumberReader(new InputStreamReader(input));
        }

        @Override
        public boolean hasNext() {
            if (nextLine != null && !nextLine.isBlank()) {
                return true;
            } else {
                try {
                    nextLine = reader.readLine();
                    return nextLine != null && hasNext();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }

        @Override
        public Elf next() {
            if (nextLine == null && !hasNext()) {
                throw new NoSuchElementException();
            }

            Elf.ElfBuilder builder = Elf.builder()
                    .id(++count);

            do {
                try {
                    int calories = Integer.parseInt(nextLine);
                    builder.snack(new Snack(calories));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid input on line " + reader.getLineNumber() + ": " + nextLine, e);
                }

                try {
                    nextLine = reader.readLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            } while (nextLine != null && !nextLine.isBlank());

            return builder.build();
        }
    }
}
