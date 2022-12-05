package com.aoc.jdmcmahan.campcleanup.model;

public record SectionRange(int start, int end) {

    public SectionRange(int start, int end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }
}
