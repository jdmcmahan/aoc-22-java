package com.aoc.jdmcmahan.notenoughmaterials.robot.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceLot;

public record ObsidianRobot() implements Robot {

    @Override
    public ResourceLot produce() {
        return new ResourceLot(Resource.OBSIDIAN, 1);
    }
}
