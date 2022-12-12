package com.aoc.jdmcmahan.ropebridge.model;

public record Position(int x, int y) {

    public static final Position ORIGIN = new Position(0, 0);

    public int distanceTo(Position other) {
        // Chebyshev distance formula!
        return Math.max(Math.abs(other.x() - this.x()), Math.abs(other.y() - this.y()));
    }

    public int directionVertical(Position other) {
        return (int) Math.signum(other.y() - this.y());
    }

    public int directionHorizontal(Position other) {
        return (int) Math.signum(other.x() - this.x());
    }

    public Position horizontal(int vector) {
        return new Position(this.x() + vector, this.y());
    }

    public Position vertical(int vector) {
        return new Position(this.x(), this.y() + vector);
    }

    public Position diagonal(int horizontalVector, int verticalVector) {
        return new Position(this.x() + horizontalVector, this.y() + verticalVector);
    }

    public Position left() {
        return this.left(1);
    }

    public Position left(int distance) {
        return this.horizontal(-1 * distance);
    }

    public Position right() {
        return this.right(1);
    }

    public Position right(int distance) {
        return horizontal(distance);
    }

    public Position up() {
        return this.up(1);
    }

    public Position up(int distance) {
        return vertical(distance);
    }

    public Position down() {
        return this.down(1);
    }

    public Position down(int distance) {
        return vertical(-1 * distance);
    }
}
