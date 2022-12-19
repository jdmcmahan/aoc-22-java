package com.aoc.jdmcmahan.boilingboulders.model;

import java.util.stream.Stream;

public record Cube(int x, int y, int z) {

    public Cube left() {
        return new Cube(x - 1, y, z);
    }

    public Cube right() {
        return new Cube(x + 1, y, z);
    }

    public Cube fore() {
        return new Cube(x, y + 1, z);
    }

    public Cube back() {
        return new Cube(x, y - 1, z);
    }

    public Cube up() {
        return new Cube(x, y, z + 1);
    }

    public Cube down() {
        return new Cube(x, y, z - 1);
    }

    public Stream<Cube> surrounding() {
        return Stream.of(this.left(), this.right(), this.fore(), this.back(), this.up(), this.down());
    }
}
