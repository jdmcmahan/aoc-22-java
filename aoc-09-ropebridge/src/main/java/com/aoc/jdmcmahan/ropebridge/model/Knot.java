package com.aoc.jdmcmahan.ropebridge.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Knot {

    private final List<Position> path = new LinkedList<>();

    public Knot(Position start) {
        this.setPosition(start);
    }

    public Position getPosition() {
        return path.get(path.size() - 1);
    }

    protected final void setPosition(Position position) {
        this.path.add(position);
    }

    public List<Position> getPath() {
        return Collections.unmodifiableList(path);
    }
}
