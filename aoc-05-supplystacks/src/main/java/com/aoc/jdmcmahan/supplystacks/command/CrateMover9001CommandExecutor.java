package com.aoc.jdmcmahan.supplystacks.command;

import com.aoc.jdmcmahan.supplystacks.model.Crate;
import com.aoc.jdmcmahan.supplystacks.model.Ship;
import com.aoc.jdmcmahan.supplystacks.command.model.MoveCratesCommand;

import java.util.List;

public class CrateMover9001CommandExecutor implements CommandExecutor {

    @Override
    public void executeMoveCommand(Ship ship, MoveCratesCommand command) {
        List<Crate> unstacked = ship.stack(command.from())
                .map(stack -> stack.unstack(command.count()))
                .orElseThrow(() -> new IllegalArgumentException("Attempting to move " + command.count() + " crate(s) from nonexistent stack " + command.from()));

        ship.stack(command.to())
                .orElseThrow(() -> new IllegalArgumentException("Attempting to move " + command.count() + " crate(s) to nonexistent stack " + command.to()))
                .stack(unstacked);
    }
}
