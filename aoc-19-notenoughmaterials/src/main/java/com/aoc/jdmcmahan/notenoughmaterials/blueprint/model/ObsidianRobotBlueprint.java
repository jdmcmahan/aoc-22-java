package com.aoc.jdmcmahan.notenoughmaterials.blueprint.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.ObsidianRobot;

public record ObsidianRobotBlueprint(int oreCost, int clayCost) implements RobotBlueprint<ObsidianRobot> {

    @Override
    public int cost(Resource resource) {
        return switch (resource) {
            case ORE -> this.oreCost;
            case CLAY -> this.clayCost;
            default -> 0;
        };
    }

    @Override
    public ObsidianRobot build(ResourceStockpile resources) {
        resources.debit(Resource.ORE, this.oreCost);
        resources.debit(Resource.CLAY, this.clayCost);

        return new ObsidianRobot();
    }
}
