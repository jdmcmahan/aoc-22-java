package com.aoc.jdmcmahan.ropebridge.model;

import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Getter
public class Rope {

    private final List<Knot> knots;

    public Rope(int knotCount) {
        this.knots = IntStream.range(0, knotCount)
                .mapToObj(i -> new Knot(Position.ORIGIN))
                .toList();
    }

    public Knot getHead() {
        return knots.get(0);
    }

    public Knot getTail() {
        return knots.get(knots.size() - 1);
    }

    public void moveHeadUp(int distance) {
        this.moveHead(Position::up, distance);
    }

    public void moveHeadDown(int distance) {
        this.moveHead(Position::down, distance);
    }

    public void moveHeadLeft(int distance) {
        this.moveHead(Position::left, distance);
    }

    public void moveHeadRight(int distance) {
        this.moveHead(Position::right, distance);
    }

    protected void moveHead(Function<Position, Position> movement, int distance) {
        Knot head = this.getHead();

        // do em one at a time (thanks kwalker314!)
        for (int i = 0; i < distance; i++) {
            Position currentPosition = head.getPosition();
            head.setPosition(movement.apply(currentPosition));

            this.propagate();
        }
    }

    protected void propagate() {
        Position previousPosition = this.getHead().getPosition();
        for (int i = 1; i < knots.size(); i++) {
            Knot currentKnot = knots.get(i);
            Position currentPosition = currentKnot.getPosition();

            while (currentPosition.distanceTo(previousPosition) > 1) {
                Position targetPosition;

                if (currentPosition.x() == previousPosition.x()) {
                    targetPosition = currentPosition.vertical(currentPosition.directionVertical(previousPosition));
                } else if (currentPosition.y() == previousPosition.y()) {
                    targetPosition = currentPosition.horizontal(currentPosition.directionHorizontal(previousPosition));
                } else {
                    targetPosition = currentPosition.diagonal(currentPosition.directionHorizontal(previousPosition), currentPosition.directionVertical(previousPosition));
                }

                currentKnot.setPosition(targetPosition);
                currentPosition = targetPosition;
            }

            previousPosition = currentPosition;
        }
    }
}
