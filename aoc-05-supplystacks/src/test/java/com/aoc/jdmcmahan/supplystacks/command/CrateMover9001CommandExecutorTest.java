package com.aoc.jdmcmahan.supplystacks.command;

import com.aoc.jdmcmahan.supplystacks.command.model.MoveCratesCommand;
import com.aoc.jdmcmahan.supplystacks.model.Crate;
import com.aoc.jdmcmahan.supplystacks.model.CrateStack;
import com.aoc.jdmcmahan.supplystacks.model.Ship;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CrateMover9001CommandExecutorTest {

    private Ship ship;

    @BeforeEach
    void initializeShip() {
        CrateStack stack1 = CrateStack.builder()
                .id("1")
                .stack(new Crate("A"))
                .stack(new Crate("B"))
                .stack(new Crate("C"))
                .build();

        CrateStack stack2 = CrateStack.builder()
                .id("2")
                .stack(new Crate("D"))
                .stack(new Crate("E"))
                .build();

        ship = new Ship(Arrays.asList(stack1, stack2));
    }

    @Test
    void testExecuteMoveCommand_forSingleCrate() {
        CrateMover9001CommandExecutor executor = new CrateMover9001CommandExecutor();

        MoveCratesCommand command = new MoveCratesCommand("1", "2", 1);
        executor.executeMoveCommand(ship, command);

        assertEquals("B", ship.stack("1")
                .map(CrateStack::top)
                .map(Crate::tag)
                .orElseGet(Assertions::fail));

        assertEquals("C", ship.stack("2")
                .map(CrateStack::top)
                .map(Crate::tag)
                .orElseGet(Assertions::fail));
    }

    @Test
    void testExecuteMoveCommand_forMultipleCrates() {
        CrateMover9001CommandExecutor executor = new CrateMover9001CommandExecutor();

        MoveCratesCommand command = new MoveCratesCommand("1", "2", 2);
        executor.executeMoveCommand(ship, command);

        assertEquals("A", ship.stack("1")
                .map(CrateStack::top)
                .map(Crate::tag)
                .orElseGet(Assertions::fail));

        assertEquals("C", ship.stack("2")
                .map(CrateStack::top)
                .map(Crate::tag)
                .orElseGet(Assertions::fail));
    }
}
