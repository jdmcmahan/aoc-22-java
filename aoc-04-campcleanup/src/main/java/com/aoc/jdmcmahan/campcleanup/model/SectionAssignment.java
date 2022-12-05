package com.aoc.jdmcmahan.campcleanup.model;

public record SectionAssignment(SectionRange range) {

    public SectionAssignment(int start, int end) {
        this(new SectionRange(start, end));
    }
}
