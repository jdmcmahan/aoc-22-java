package com.aoc.jdmcmahan.notenoughmaterials.blueprint.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.OreRobot;

public record OreRobotBlueprint(int oreCost) implements RobotBlueprint<OreRobot> {

    @Override
    public int cost(Resource resource) {
        if (resource == Resource.ORE) {
            return this.oreCost;
        }

        return 0;
    }

    @Override
    public OreRobot build(ResourceStockpile resources) {
        resources.debit(Resource.ORE, this.oreCost);
        return new OreRobot();
    }
}
