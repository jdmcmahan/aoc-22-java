package com.aoc.jdmcmahan.boilingboulders.model;

import lombok.Builder;
import lombok.Singular;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LavaDroplet {

    private final Set<Cube> cubes = new HashSet<>();
    private final Bounds bounds;

    private Set<Cube> air;
    private Set<Cube> exposedAir;

    @Builder
    public LavaDroplet(@Singular Collection<Cube> cubes) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        int minZ = Integer.MAX_VALUE, maxZ = Integer.MIN_VALUE;

        for (Cube cube : cubes) {
            this.cubes.add(cube);

            minX = Math.min(cube.x(), minX);
            maxX = Math.max(cube.x(), maxX);

            minY = Math.min(cube.y(), minY);
            maxY = Math.max(cube.y(), maxY);

            minZ = Math.min(cube.z(), minZ);
            maxZ = Math.max(cube.z(), maxZ);
        }

        this.bounds = new Bounds(minX, maxX, minY, maxY, minZ, maxZ);
    }

    public long surfaceArea(boolean includeInternal) {
        return cubes.stream()
                .mapToLong(cube -> cube.surrounding()
                        .filter(other -> {
                            if (!bounds.contains(other)) {
                                return true;
                            }

                            return includeInternal
                                    ? this.air().contains(other)
                                    : this.exposedAir().contains(other);
                        })
                        .count())
                .sum();
    }

    protected Set<Cube> air() {
        if (this.air == null) {
            this.air = bounds.cubes()
                    .filter(Predicate.not(cubes::contains))
                    .collect(Collectors.toSet());
        }

        return this.air;
    }

    protected Set<Cube> exposedAir() {
        if (this.exposedAir == null) {
            this.exposedAir = findExposedAir();
        }

        return this.exposedAir;
    }

    protected Set<Cube> findExposedAir() {
        return this.findExposedAir(this.air());
    }

    protected Set<Cube> findExposedAir(Set<Cube> remaining) {
        remaining = new HashSet<>(remaining);
        if (remaining.isEmpty()) {
            return remaining;
        }

        Cube start = remaining.iterator().next();
        remaining.remove(start);

        Set<Cube> block = new HashSet<>();

        LinkedList<Cube> queue = new LinkedList<>();
        queue.add(start);

        boolean exposed = false;
        while (!queue.isEmpty()) {
            Cube current = queue.removeFirst();
            block.add(current);

            for (Iterator<Cube> surrounding = current.surrounding().iterator(); surrounding.hasNext(); ) {
                Cube next = surrounding.next();

                if (!bounds.contains(next)) {
                    exposed = true;
                } else if (remaining.remove(next)) {
                    queue.addLast(next);
                }
            }
        }

        Set<Cube> merged = this.findExposedAir(remaining);
        if (exposed) {
            merged.addAll(block);
        }

        return merged;
    }
}
