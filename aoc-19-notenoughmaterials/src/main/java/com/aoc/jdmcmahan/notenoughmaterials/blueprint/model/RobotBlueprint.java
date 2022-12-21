package com.aoc.jdmcmahan.notenoughmaterials.blueprint.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.Robot;

import java.util.Arrays;

public interface RobotBlueprint<R extends Robot> {

    int cost(Resource resource);

    default boolean canAfford(ResourceStockpile resources) {
        return Arrays.stream(Resource.values())
                .allMatch(resource -> resources.getStock(resource) >= this.cost(resource));
    }

    R build(ResourceStockpile resources);
}
