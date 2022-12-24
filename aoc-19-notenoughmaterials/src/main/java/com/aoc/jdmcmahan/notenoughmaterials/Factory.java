package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.RobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;
import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import com.aoc.jdmcmahan.notenoughmaterials.model.ResourceStockpile;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.OreRobot;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.Robot;
import com.aoc.jdmcmahan.notenoughmaterials.robot.model.RobotOutput;
import lombok.Getter;

import java.util.*;

@Getter
public class Factory {

    private final Factory parent;

    private final BlueprintSet blueprints;

    private final ResourceStockpile resources;

    private final List<Robot> robots = new LinkedList<>();
    private Robot pending;

    private final int maxTicks;
    private int currentTick;

    public Factory(BlueprintSet blueprints, int maxTicks) {
        this.parent = null;
        this.blueprints = blueprints;
        this.resources = new ResourceStockpile();

        this.robots.add(new OreRobot());
        this.pending = null;

        this.maxTicks = maxTicks;
        this.currentTick = 0;
    }

    protected Factory(Factory parent, RobotBlueprint<?> toBuild) {
        this.parent = parent;
        this.blueprints = parent.blueprints;
        this.resources = new ResourceStockpile(parent.resources);

        this.robots.addAll(parent.robots);

        this.pending = toBuild != null
                ? toBuild.build(this.resources)
                : null;

        this.maxTicks = parent.maxTicks;
        this.currentTick = parent.currentTick;
    }

    public int getRemainingTicks() {
        return this.maxTicks - this.currentTick;
    }

    public List<Factory> path() {
        LinkedList<Factory> path = new LinkedList<>();
        Factory factory = this;

        do {
            path.addFirst(factory);
        } while ((factory = factory.getParent()) != null);

        return path;
    }

    public Factory minmax() {
        return this.minmax(0);
    }

    protected Factory minmax(int bestScore) {
        this.tick();

        if (this.getRemainingTicks() == 0) {
            return this;
        }

        // If we can't possibly beat the best score, stop now
        if (this.optimisticBest(Resource.GEODE) < bestScore) {
            return this;
        }

        Factory best = this;
        for (Factory future : this.futures()) {
            future = future.minmax(bestScore);

            int score = future.resources.getStock(Resource.GEODE);
            if (score > bestScore) {
                best = future;
                bestScore = score;
            }
        }

        return best;
    }

    protected void tick() {
        if (this.getRemainingTicks() <= 0) {
            throw new IllegalArgumentException("No more ticks!");
        }

        robots.stream()
                .map(Robot::output)
                .forEach(resources::credit);

        if (pending != null) {
            robots.add(pending);
            pending = null;
        }

        this.currentTick++;
    }

    protected Collection<Factory> futures() {
        int remainingTicks = this.getRemainingTicks();

        if (remainingTicks <= 0) {
            return Collections.emptyList();
        } else if (remainingTicks == 1) {
            // No sense in building anything if there's not enough time left to affect production
            return Collections.singletonList(new Factory(this, null));
        }

        // Always build a geode robot if we can
        RobotBlueprint<?> geodeBlueprint = blueprints.getBlueprint(Resource.GEODE);
        if (geodeBlueprint.canAfford(resources)) {
            return Collections.singletonList(new Factory(this, geodeBlueprint));
        }

        List<Factory> futures = new LinkedList<>();
        for (Resource resource : Arrays.asList(Resource.OBSIDIAN, Resource.CLAY, Resource.ORE)) {

            // Only build this robot if we could use more output
            if (this.currentProduction(resource) < maxSpending(resource)) {
                RobotBlueprint<?> blueprint = blueprints.getBlueprint(resource);

                // If we skipped this blueprint last time and didn't build something else instead, prune this branch
                if (parent != null && blueprint.canAfford(parent.resources) && parent.robots.size() == this.robots.size()) {
                    continue;
                }

                if (blueprint.canAfford(resources)) {
                    futures.add(new Factory(this, blueprint));
                }
            }
        }

        // Non-ideal case - do nothing while we wait for more resources
        // This is added last so that it can be pruned more readily
        futures.add(new Factory(this, null));

        return futures;
    }

    protected int currentProduction(Resource resource) {
        return robots.stream()
                .map(Robot::output)
                .filter(output -> output.resource() == resource)
                .mapToInt(RobotOutput::quantity)
                .sum();
    }

    protected int maxSpending(Resource resource) {
        return blueprints.stream()
                .mapToInt(blueprint -> blueprint.cost(resource))
                .max()
                .orElse(0);
    }

    protected int optimisticBest(Resource resource) {
        int remaining = this.getRemainingTicks();
        int total = 0;

        // Current stock...
        total += this.resources.getStock(resource);

        // ... plus production from existing robots...
        total += remaining * robots.stream()
                .filter(robot -> robot.output().resource() == resource)
                .count();

        // ... plus future production from the robot currently being built (if applicable)...
        total += (pending != null && pending.output().resource() == resource)
                ? remaining - 1
                : 0;

        // ... plus future production under the (rather optimistic) assumption that we can build another robot every turn
        total += (remaining * (remaining - 1)) / 2;

        return total;
    }
}
