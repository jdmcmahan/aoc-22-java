package com.aoc.jdmcmahan.regolithreservoir.model;

import lombok.Builder;
import lombok.Singular;

import java.util.Collection;

public class FlooredCave extends AbstractCave {

    private final int floorOffset;

    @Builder
    protected FlooredCave(@Singular Collection<Position> rocks, int floorOffset) {
        super(rocks);

        this.floorOffset = floorOffset;
    }

    @Override
    public Position pourSand(Position from) {
        if (from.getY() == this.floorY() - 1) {
            sand.add(from);
            return from;
        }

        return super.pourSand(from);
    }

    public int floorY() {
        return this.depth + floorOffset;
    }
}
