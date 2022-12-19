package com.aoc.jdmcmahan.boilingboulders.model;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Bounds(int minX, int maxX, int minY, int maxY, int minZ, int maxZ) {

    public boolean contains(Cube cube) {
        return cube.x() >= minX && cube.x() <= maxX
                && cube.y() >= minY && cube.y() <= maxY
                && cube.z() >= minZ && cube.z() <= maxZ;
    }

    public Stream<Cube> cubes() {
        return IntStream.rangeClosed(minX, maxX)
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(minY, maxY)
                        .boxed()
                        .flatMap(y -> IntStream.rangeClosed(minZ, maxZ)
                                .mapToObj(z -> new Cube(x, y, z))));
    }
}
