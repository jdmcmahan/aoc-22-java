package com.aoc.jdmcmahan.notenoughmaterials.robot.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceLot;

public record GeodeRobot() implements Robot {

    @Override
    public ResourceLot produce() {
        return new ResourceLot(Resource.GEODE, 1);
    }
}
