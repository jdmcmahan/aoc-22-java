package com.aoc.jdmcmahan.monkeyinthemiddle;

import com.aoc.jdmcmahan.monkeyinthemiddle.model.Item;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.Monkey;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.MonkeyRegistry;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class KeepAway {

    private final MonkeyRegistry monkeys;

    private final Function<Long, Long> reliefModifier;

    @Getter
    private int currentRound = 1;

    public void playRound() {
        monkeys.getMonkeys().forEach(this::playTurn);
        currentRound++;
    }

    protected void playTurn(Monkey monkey) {
        Item item;
        while ((item = monkey.inspectNext()) != null) {
            item.setWorryLevel(reliefModifier.apply(item.getWorryLevel()));
            monkey.performAction(item, monkeys);
        }
    }
}
