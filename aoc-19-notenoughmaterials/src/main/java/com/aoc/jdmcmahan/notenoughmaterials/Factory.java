package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.OreRobot;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.Robot;

import java.util.*;

public class Factory {

    private final BlueprintSet blueprints;
    private final ResourceStockpile resources;

    private final List<Robot> robots = new LinkedList<>();
    private final List<Robot> pending = new LinkedList<>();

    public Factory(BlueprintSet blueprints) {
        this.blueprints = blueprints;
        this.resources = new ResourceStockpile();

        this.robots.add(new OreRobot());
    }

    protected Factory(Factory other) {
        this.blueprints = other.blueprints;
        this.resources = new ResourceStockpile(other.resources);

        this.robots.addAll(other.robots);
        this.pending.addAll(other.pending);
    }

    public Factory maximize(Comparator<Factory> comparator, int remaining) {
        if (remaining < 0) {
            //return Collections.singletonList(this);
        }

        /*List<Robot> toBuild = blueprints.stream()
                .filter(blueprint -> blueprint.canAfford(resources))
                .map()
                .collect(Collectors.toList());*/

        robots.stream()
                .map(Robot::produce)
                .forEach(resources::credit);

        robots.addAll(pending);
        pending.clear();

        //pending.addAll(toBuild);

        Factory next = new Factory(this);
        return comparator.compare(this, next) > 0
                ? next.maximize(comparator, remaining - 1)
                : this;
    }

    public Collection<Factory> futures(int remaining) {
        if (remaining == 0) {
            return Collections.emptyList();
        }

        return null;
    }

    /*public void tick() {
        if (remaining == 0) {
            return;
        }

        this.remaining--;

        for (RobotBlueprint<?> blueprint : blueprints) {
            if (blueprint.canAfford(resources) && )
        }

        robots.stream()
                .map(Robot::produce)
                .forEach(resources::credit);

        robots.addAll(pending);
        pending.clear();
    }*/
}
