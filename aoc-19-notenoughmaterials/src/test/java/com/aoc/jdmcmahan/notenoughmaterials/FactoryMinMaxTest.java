package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;
import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FactoryMinMaxTest {

    @Test
    void testMinMax_withExampleInput() throws IOException {
        try (InputStream input = FactoryMinMaxTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            BlueprintParser parser = new BlueprintParser();
            List<BlueprintSet> blueprintSets = parser.parse(input);

            Map<Integer, Factory> minmax = blueprintSets.stream()
                    .map(blueprints -> new Factory(blueprints, 24))
                    .map(Factory::minmax)
                    .collect(Collectors.toMap(factory -> factory.getBlueprints().getId(), Function.identity()));

            assertEquals(2, minmax.size());

            Factory f1 = minmax.get(1);

            assertNotNull(f1);
            assertEquals(1, f1.getBlueprints().getId());
            assertEquals(9, f1.getResources().getStock(Resource.GEODE));
            assertEquals(9, this.qualityLevel(f1));

            Factory f2 = minmax.get(2);

            assertNotNull(f2);
            assertEquals(2, f2.getBlueprints().getId());
            assertEquals(12, f2.getResources().getStock(Resource.GEODE));
            assertEquals(24, this.qualityLevel(f2));
        }
    }

    @Test
    void testMinMax_withChallengeInput() throws IOException {
        try (InputStream input = FactoryMinMaxTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            BlueprintParser parser = new BlueprintParser();
            List<BlueprintSet> blueprintSets = parser.parse(input);

            int qualityLevel = blueprintSets.stream()
                    .map(blueprints -> new Factory(blueprints, 24))
                    .map(Factory::minmax)
                    .mapToInt(this::qualityLevel)
                    .sum();

            assertEquals(1675, qualityLevel);
        }
    }

    @Test
    void testMinMaxPostElephantSatiety_withExampleInput() throws IOException {
        try (InputStream input = FactoryMinMaxTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            BlueprintParser parser = new BlueprintParser();
            List<BlueprintSet> blueprintSets = parser.parse(input);

            Map<Integer, Factory> minmax = blueprintSets.stream()
                    .map(blueprints -> new Factory(blueprints, 32))
                    .map(Factory::minmax)
                    .collect(Collectors.toMap(factory -> factory.getBlueprints().getId(), Function.identity()));

            assertEquals(2, minmax.size());

            Factory f1 = minmax.get(1);

            assertNotNull(f1);
            assertEquals(1, f1.getBlueprints().getId());
            assertEquals(56, f1.getResources().getStock(Resource.GEODE));

            Factory f2 = minmax.get(2);

            assertNotNull(f2);
            assertEquals(2, f2.getBlueprints().getId());
            assertEquals(62, f2.getResources().getStock(Resource.GEODE));
        }
    }

    @Test
    void testMinMaxPostElephantSatiety_withChallengeInput() throws IOException {
        try (InputStream input = FactoryMinMaxTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            BlueprintParser parser = new BlueprintParser();
            List<BlueprintSet> blueprintSets = parser.parse(input);

            blueprintSets = blueprintSets.subList(0, 3);

            int qualityLevel = blueprintSets.stream()
                    .map(blueprints -> new Factory(blueprints, 32))
                    .map(Factory::minmax)
                    .mapToInt(factory -> factory.getResources().getStock(Resource.GEODE))
                    .reduce(1, (l, r) -> l * r);

            assertEquals(6840, qualityLevel);
        }
    }

    protected int qualityLevel(Factory factory) {
        return factory.getBlueprints().getId() * factory.getResources().getStock(Resource.GEODE);
    }
}
