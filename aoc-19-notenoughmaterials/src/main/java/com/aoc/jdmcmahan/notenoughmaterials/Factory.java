package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.RobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;
import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.OreRobot;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.Robot;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.RobotOutput;
import lombok.Getter;

import java.util.*;

public class Factory {

    @Getter
    private final BlueprintSet blueprints;

    @Getter
    private final ResourceStockpile resources;

    private final List<Robot> robots = new LinkedList<>();
    private final Robot pending;

    public Factory(BlueprintSet blueprints) {
        this.blueprints = blueprints;
        this.resources = new ResourceStockpile();

        this.robots.add(new OreRobot());
        this.pending = null;
    }

    protected Factory(Factory other, RobotBlueprint<?> toBuild) {
        this.blueprints = other.blueprints;
        this.resources = new ResourceStockpile(other.resources);

        this.robots.addAll(other.robots);

        this.pending = toBuild != null
                ? toBuild.build(this.resources)
                : null;
    }

    public Factory minmax(Comparator<Factory> comparator, int remaining) {
        robots.stream()
                .map(Robot::output)
                .forEach(resources::credit);

        if (pending != null) {
            robots.add(pending);
        }

        if (remaining == 0) {
            return this;
        }

        return this.futures().stream()
                .map(future -> future.minmax(comparator, remaining - 1))
                .reduce(this, (a, b) -> comparator.compare(a, b) < 0 ? a : b);
    }

    public Collection<Factory> futures() {
        List<Factory> futures = new LinkedList<>();

        for (Resource resource : Resource.values()) {
            if (resource == Resource.GEODE || resources.getStock(resource) + this.currentProduction(resource) <= maxSpending(resource)) {
                RobotBlueprint<?> blueprint = blueprints.getBlueprint(resource);
                if (blueprint.canAfford(resources)) {
                    futures.add(new Factory(this, blueprint));
                }
            }
        }

        if (futures.isEmpty()) {
            futures.add(new Factory(this, null));
        }

        return futures;
    }

    protected int currentProduction(Resource resource) {
        return robots.stream()
                .map(Robot::output)
                .filter(output -> output.resource() == resource)
                .mapToInt(RobotOutput::quantity)
                .sum();
    }

    protected int maxSpending(Resource resource) {
        return blueprints.stream()
                .mapToInt(blueprint -> blueprint.cost(resource))
                .sum();
    }
}
