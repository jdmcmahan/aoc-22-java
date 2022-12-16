package com.aoc.jdmcmahan.cathoderaytube.model;

import java.util.LinkedList;
import java.util.List;

public class Cpu {

    private final List<CycleListener> listeners = new LinkedList<>();

    protected int cycle = 0;
    protected int x = 1;

    public int getCycle() {
        return cycle;
    }

    public int getX() {
        return x;
    }

    public int getSignalStrength() {
        return cycle * x;
    }

    public void addx(int value) {
        this.tick();
        this.tick();

        this.x += value;
    }

    public void noop() {
        this.tick();
    }

    public void addListener(CycleListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(CycleListener listener) {
        this.listeners.remove(listener);
    }

    protected void tick() {
        this.cycle++;
        listeners.forEach(l -> l.cycleExecuting(this.cycle, this.x, this.getSignalStrength()));
    }

    @FunctionalInterface
    public interface CycleListener {
        void cycleExecuting(int cycle, int x, int signalStrength);
    }
}
