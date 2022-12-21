package com.aoc.jdmcmahan.notenoughmaterials.model;

import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class BlueprintSet implements Iterable<RobotBlueprint<?>> {

    private final int id;

    private final OreRobotBlueprint oreRobotBlueprint;
    private final ClayRobotBlueprint clayRobotBlueprint;
    private final ObsidianRobotBlueprint obsidianRobotBlueprint;
    private final GeodeRobotBlueprint geodeRobotBlueprint;

    public Stream<RobotBlueprint<?>> stream() {
        return Stream.of(oreRobotBlueprint, clayRobotBlueprint, obsidianRobotBlueprint, geodeRobotBlueprint);
    }

    @Override
    public Iterator<RobotBlueprint<?>> iterator() {
        return this.stream().iterator();
    }
}
