package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.ClayRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.GeodeRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.ObsidianRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.OreRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;
import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FactoryTest {

    private BlueprintSet blueprints;
    private List<Factory> factories;

    @BeforeEach
    void initializeFactories() {
        this.blueprints = BlueprintSet.builder()
                .oreRobotBlueprint(new OreRobotBlueprint(4))
                .clayRobotBlueprint(new ClayRobotBlueprint(2))
                .obsidianRobotBlueprint(new ObsidianRobotBlueprint(3, 14))
                .geodeRobotBlueprint(new GeodeRobotBlueprint(2, 7))
                .build();

        this.factories = new ArrayList<>(24);
        Factory current;

        factories.add(current = new Factory(this.blueprints, 24));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.CLAY)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.CLAY)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.CLAY)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.OBSIDIAN)));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.CLAY)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.OBSIDIAN)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.GEODE)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, blueprints.getBlueprint(Resource.GEODE)));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(current = new Factory(current, null));
        current.tick();

        factories.add(new Factory(current, null));
    }

    @Test
    void testTick() {
        Factory factory = new Factory(blueprints, 3);
        factory.tick();

        assertEquals(1, factory.getCurrentTick());
        assertEquals(1, factory.getRobots().size());
        assertEquals(1, factory.getResources().getStock(Resource.ORE));
    }

    @Test
    void testPath() {
        List<Factory> path = factories.get(factories.size() - 1).path();

        assertThat(path)
                .containsExactlyElementsOf(factories);
    }

    @Test
    void testCurrentProduction() {
        Factory f0 = factories.get(0);

        assertEquals(1, f0.currentProduction(Resource.ORE));
        assertEquals(0, f0.currentProduction(Resource.CLAY));
        assertEquals(0, f0.currentProduction(Resource.OBSIDIAN));
        assertEquals(0, f0.currentProduction(Resource.GEODE));

        Factory f23 = factories.get(23);

        assertEquals(1, f23.currentProduction(Resource.ORE));
        assertEquals(4, f23.currentProduction(Resource.CLAY));
        assertEquals(2, f23.currentProduction(Resource.OBSIDIAN));
        assertEquals(2, f23.currentProduction(Resource.GEODE));
    }

    @Test
    void testMaxSpending() {
        factories.forEach(factory -> {
            assertEquals(4, factory.maxSpending(Resource.ORE));
            assertEquals(14, factory.maxSpending(Resource.CLAY));
            assertEquals(7, factory.maxSpending(Resource.OBSIDIAN));
            assertEquals(0, factory.maxSpending(Resource.GEODE));
        });
    }
}
