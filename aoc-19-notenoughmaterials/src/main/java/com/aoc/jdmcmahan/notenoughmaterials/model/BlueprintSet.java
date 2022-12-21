package com.aoc.jdmcmahan.notenoughmaterials.model;

import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BlueprintSet implements Iterable<RobotBlueprint<?>> {

    @Getter
    private final int id;

    private final Map<Resource, RobotBlueprint<?>> blueprints = new EnumMap<>(Resource.class);

    @Builder
    protected BlueprintSet(int id, OreRobotBlueprint oreRobotBlueprint, ClayRobotBlueprint clayRobotBlueprint, ObsidianRobotBlueprint obsidianRobotBlueprint, GeodeRobotBlueprint geodeRobotBlueprint) {
        this.id = id;

        this.blueprints.put(Resource.ORE, oreRobotBlueprint);
        this.blueprints.put(Resource.CLAY, clayRobotBlueprint);
        this.blueprints.put(Resource.OBSIDIAN, obsidianRobotBlueprint);
        this.blueprints.put(Resource.GEODE, geodeRobotBlueprint);
    }

    public RobotBlueprint<?> getBlueprint(Resource production) {
        return blueprints.get(production);
    }

    public Stream<RobotBlueprint<?>> stream() {
        return blueprints.values().stream();
    }

    @Override
    public Iterator<RobotBlueprint<?>> iterator() {
        return this.stream().iterator();
    }
}
