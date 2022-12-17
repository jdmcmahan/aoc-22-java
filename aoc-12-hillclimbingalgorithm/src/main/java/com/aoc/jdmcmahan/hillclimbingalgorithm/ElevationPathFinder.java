package com.aoc.jdmcmahan.hillclimbingalgorithm;

import com.aoc.jdmcmahan.hillclimbingalgorithm.model.ElevationMap;
import com.aoc.jdmcmahan.hillclimbingalgorithm.model.Hike;
import com.aoc.jdmcmahan.hillclimbingalgorithm.model.Position;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ElevationPathFinder {

    public List<Position> mapPath(Hike hike, int stepSize) {
        ElevationMap map = hike.map();
        Position start = hike.start();
        Position end = hike.end();

        Map<Position, PathNode> visited = new HashMap<>();

        LinkedList<PathNode> queue = new LinkedList<>();
        queue.addLast(new PathNode(start));

        while (!queue.isEmpty()) {
            PathNode current = queue.removeFirst();
            Position currentPosition = current.getPosition();

            visited.putIfAbsent(currentPosition, current);

            if (currentPosition.equals(end)) {
                break;
            }

            int currentElevation = map.elevationAt(currentPosition);
            int currentDistance = current.getDistance();

            Stream.of(currentPosition.up(), currentPosition.down(), currentPosition.left(), currentPosition.right())
                    .filter(map::contains)
                    .filter(nextPosition -> !visited.containsKey(nextPosition))
                    .filter(nextPosition -> map.elevationAt(nextPosition) <= currentElevation + stepSize)
                    .forEach(nextPosition -> {
                        PathNode next = visited.computeIfAbsent(nextPosition, PathNode::new);
                        next.setPrevious(current);
                        next.setDistance(currentDistance + 1);

                        queue.addLast(next);
                    });
        }

        if (!visited.containsKey(end)) {
            return null;
        }

        return Stream.iterate(visited.get(end), node -> node.getPrevious() != null, PathNode::getPrevious)
                .sorted(Comparator.comparing(PathNode::getDistance))
                .map(PathNode::getPosition)
                .collect(Collectors.toList());
    }

    @RequiredArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    protected static class PathNode {

        @EqualsAndHashCode.Include
        private final Position position;

        private PathNode previous;
        private int distance = 0;
    }
}
