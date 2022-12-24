package com.aoc.jdmcmahan.notenoughmaterials.blueprint.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.ClayRobot;

public record ClayRobotBlueprint(int oreCost) implements RobotBlueprint<ClayRobot> {

    @Override
    public int cost(Resource resource) {
        if (resource == Resource.ORE) {
            return this.oreCost;
        }

        return 0;
    }

    @Override
    public ClayRobot build(ResourceStockpile resources) {
        resources.debit(Resource.ORE, this.oreCost);
        return new ClayRobot();
    }
}
