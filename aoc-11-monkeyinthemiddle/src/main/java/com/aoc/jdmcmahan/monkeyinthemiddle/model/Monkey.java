package com.aoc.jdmcmahan.monkeyinthemiddle.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Monkey {

    @Getter
    private final String id;
    private final LinkedList<Item> items = new LinkedList<>();

    private final WorryOperation operation;

    private final WorryLevelDivisibilityMonkeyActionTest test;

    @Getter
    private long monkeyBusiness = 0;

    @Builder
    protected Monkey(String id, @Singular Collection<Item> items, WorryOperation operation, WorryLevelDivisibilityMonkeyActionTest test) {
        this.id = id;
        this.operation = operation;
        this.test = test;

        this.items.addAll(items);
    }

    public long getDivisor() {
        return test.divisor();
    }

    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Item inspectNext() {
        if (items.isEmpty()) {
            return null;
        }

        Item item = items.peekFirst();

        long worryLevel = item.getWorryLevel();
        worryLevel = operation.calculateWorryLevel(worryLevel);

        item.setWorryLevel(worryLevel);

        monkeyBusiness++;
        return item;
    }

    public void performAction(Item item, MonkeyRegistry monkeyRegistry) {
        MonkeyAction action = this.test.test(item);
        action.performAction(item, this, monkeyRegistry);
    }

    public void throwItem(Item item) {
        this.items.remove(item);
    }

    public void catchItem(Item item) {
        this.items.addLast(item);
    }

    @FunctionalInterface
    public interface WorryOperation {
        long calculateWorryLevel(long currentWorryLevel);
    }

    public record WorryLevelDivisibilityMonkeyActionTest(long divisor, MonkeyAction trueAction, MonkeyAction falseAction) {

        public MonkeyAction test(Item item) {
            return item.getWorryLevel() % divisor == 0
                    ? trueAction
                    : falseAction;
        }
    }

    @FunctionalInterface
    public interface MonkeyAction {
        void performAction(Item item, Monkey monkey, MonkeyRegistry monkeyRegistry);
    }
}
