package com.aoc.jdmcmahan.supplystacks.command;

import com.aoc.jdmcmahan.supplystacks.model.CrateStack;
import com.aoc.jdmcmahan.supplystacks.model.Ship;
import com.aoc.jdmcmahan.supplystacks.command.model.MoveCratesCommand;

import java.util.stream.IntStream;

public class CrateMover9000CommandExecutor implements CommandExecutor {

    @Override
    public void executeMoveCommand(Ship ship, MoveCratesCommand command) {
        CrateStack from = ship.stack(command.from())
                .orElseThrow(() -> new IllegalArgumentException("Attempting to move " + command.count() + " crate(s) from nonexistent stack " + command.from()));

        CrateStack to = ship.stack(command.to())
                .orElseThrow(() -> new IllegalArgumentException("Attempting to move " + command.count() + " crate(s) to nonexistent stack " + command.to()));

        IntStream.range(0, command.count())
                .forEach(i -> to.stack(from.unstack()));
    }
}
