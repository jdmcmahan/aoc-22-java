package com.aoc.jdmcmahan.rockpaperscissors.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Outcome {
    WIN(6),
    LOSS(0),
    DRAW(3);

    private final int score;
}
