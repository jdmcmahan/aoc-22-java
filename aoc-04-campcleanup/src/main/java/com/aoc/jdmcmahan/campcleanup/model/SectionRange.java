package com.aoc.jdmcmahan.campcleanup.model;

import java.util.Optional;

public record SectionRange(int start, int end) {

    public SectionRange(int start, int end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }

    public boolean encloses(SectionRange other) {
        return this.start <= other.start && this.end >= other.end;
    }

    public Optional<SectionRange> overlap(SectionRange other) {
        int start1 = this.start();
        int end1 = this.end();

        int start2 = other.start();
        int end2 = other.end();

        if (start1 > end2 || end1 < start2) {
            return Optional.empty();
        }

        return Optional.of(new SectionRange(Math.max(start1, start2), Math.min(end1, end2)));
    }
}
