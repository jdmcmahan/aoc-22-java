package com.aoc.jdmcmahan.regolithreservoir.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AbstractCave {

    protected final int width;
    protected final int depth;

    protected final Set<Position> rocks = new HashSet<>();

    protected final Set<Position> sand = new HashSet<>();

    public AbstractCave(Collection<Position> rocks) {
        int width = 0;
        int depth = 0;

        for (Position rock : rocks) {
            width = Math.max(width, rock.getX());
            depth = Math.max(depth, rock.getY());

            this.rocks.add(rock);
        }

        this.width = width;
        this.depth = depth;
    }

    public Collection<Position> getRocks() {
        return Collections.unmodifiableCollection(rocks);
    }

    public Collection<Position> getSand() {
        return Collections.unmodifiableCollection(sand);
    }

    public Position pourSand(Position from) {
        if (!isAirAt(from)) {
            return from;
        }

        Position below = from.down();
        Position[] options = {below, below.left(), below.right()};

        for (Position option : options) {
            if (isAirAt(option)) {
                return pourSand(option);
            }
        }

        sand.add(from);
        return from;
    }

    protected boolean isAirAt(Position position) {
        return !rocks.contains(position) && !sand.contains(position);
    }
}
