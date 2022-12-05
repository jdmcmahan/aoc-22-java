package com.aoc.jdmcmahan.supplystacks.command;

import com.aoc.jdmcmahan.supplystacks.model.Ship;
import com.aoc.jdmcmahan.supplystacks.command.model.MoveCratesCommand;

public interface CommandExecutor {
    void executeMoveCommand(Ship ship, MoveCratesCommand command);
}
