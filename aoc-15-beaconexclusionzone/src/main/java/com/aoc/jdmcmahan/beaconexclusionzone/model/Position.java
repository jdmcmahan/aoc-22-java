package com.aoc.jdmcmahan.beaconexclusionzone.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Position {

    protected static final Table<Integer, Integer, Position> INTERN = HashBasedTable.create();

    private final int x;
    private final int y;

    public int manhattanDistance(Position other) {
        return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() - other.getY());
    }

    public static Position valueOf(int x, int y) {
        Position position = INTERN.get(x, y);
        if (position == null) {
            position = new Position(x, y);
            INTERN.put(x, y, position);
        }

        return position;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
