package com.aoc.jdmcmahan.rockpaperscissors.model;

public record Round(Shape player1Shape, Shape player2Shape) {

    public Outcome player1Outcome() {
        return player1Shape.shoot(player2Shape);
    }

    public Outcome player2Outcome() {
        return player2Shape.shoot(player1Shape);
    }

    public int player1Score() {
        return calculateScore(player1Shape, this.player1Outcome());
    }

    public int player2Score() {
        return calculateScore(player2Shape, this.player2Outcome());
    }

    private static int calculateScore(Shape shape, Outcome outcome) {
        return shape.getScore() + outcome.getScore();
    }
}
