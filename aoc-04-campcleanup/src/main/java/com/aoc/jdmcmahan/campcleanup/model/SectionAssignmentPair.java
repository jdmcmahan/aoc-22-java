package com.aoc.jdmcmahan.campcleanup.model;

import java.util.Optional;

public record SectionAssignmentPair(SectionAssignment assignment1, SectionAssignment assignment2) {

    public boolean isFullyOverlapping() {
        int start1 = assignment1.range().start();
        int end1 = assignment1.range().end();

        int start2 = assignment2.range().start();
        int end2 = assignment2.range().end();

        return ((start1 >= start2 && end1 <= end2)
                || (start2 >= start1 && end2 <= end1));
    }

    public Optional<SectionRange> overlap() {
        int start1 = assignment1.range().start();
        int end1 = assignment1.range().end();

        int start2 = assignment2.range().start();
        int end2 = assignment2.range().end();

        if (start1 > end2 || end1 < start2) {
            return Optional.empty();
        }

        return Optional.of(new SectionRange(Math.max(start1, start2), Math.min(end1, end2)));
    }
}
