package com.aoc.jdmcmahan.notenoughmaterials.blueprint.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.GeodeRobot;

public record GeodeRobotBlueprint(int oreCost, int obsidianCost) implements RobotBlueprint<GeodeRobot> {

    @Override
    public int cost(Resource resource) {
        return switch (resource) {
            case ORE -> this.oreCost;
            case OBSIDIAN -> this.obsidianCost;
            default -> 0;
        };
    }

    @Override
    public GeodeRobot build(ResourceStockpile resources) {
        resources.debit(Resource.ORE, this.oreCost);
        resources.debit(Resource.OBSIDIAN, this.obsidianCost);

        return new GeodeRobot();
    }
}
