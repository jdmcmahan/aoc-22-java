package com.aoc.jdmcmahan.notenoughmaterials.robot.model;

import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;

public record ClayRobot() implements Robot {

    @Override
    public RobotOutput output() {
        return new RobotOutput(Resource.CLAY, 1);
    }
}
