package com.aoc.jdmcmahan.campcleanup.model;

import java.util.Optional;

public record SectionAssignmentPair(SectionAssignment assignment1, SectionAssignment assignment2) {

    public boolean isFullyOverlapping() {
        return assignment1.range().encloses(assignment2.range())
                || assignment2.range().encloses(assignment1.range());
    }

    public Optional<SectionRange> overlap() {
        return assignment1.range().overlap(assignment2.range());
    }
}
