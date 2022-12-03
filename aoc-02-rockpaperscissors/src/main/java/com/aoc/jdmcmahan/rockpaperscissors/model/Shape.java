package com.aoc.jdmcmahan.rockpaperscissors.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

@RequiredArgsConstructor
@Getter
public enum Shape {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private static final BidiMap<Shape, Shape> HIERARCHY = new DualHashBidiMap<>();

    static {
        HIERARCHY.put(ROCK, SCISSORS);
        HIERARCHY.put(PAPER, ROCK);
        HIERARCHY.put(SCISSORS, PAPER);
    }

    private final int score;

    public Shape winningShape() {
        return HIERARCHY.get(this);
    }

    public Shape losingShape() {
        return HIERARCHY.getKey(this);
    }

    public Outcome shoot(Shape opponentShape) {
        if (this == opponentShape) {
            return Outcome.DRAW;
        }

        return this.winningShape() == opponentShape
                ? Outcome.WIN
                : Outcome.LOSS;
    }
}
