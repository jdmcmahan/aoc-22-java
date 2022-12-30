package com.aoc.jdmcmahan.regolithreservoir.model;

import lombok.Builder;
import lombok.Singular;

import java.util.Collection;

public class BottomlessCave extends AbstractCave {

    @Builder
    protected BottomlessCave(@Singular Collection<Position> rocks) {
        super(rocks);
    }

    @Override
    public Position pourSand(Position from) {
        if (from.getY() >= this.depth) {
            return Position.VOID;
        }

        return super.pourSand(from);
    }
}
