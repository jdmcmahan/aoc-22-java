package com.aoc.jdmcmahan.notenoughmaterials.robot.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;

public record ObsidianRobot() implements Robot {

    @Override
    public RobotOutput output() {
        return new RobotOutput(Resource.OBSIDIAN, 1);
    }
}
