package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;
import com.aoc.jdmcmahan.notenoughmaterials.model.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FactoryMinMaxTest {

    @Test
    void testMinMax_withExampleInput() throws IOException {
        try (InputStream input = FactoryMinMaxTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            BlueprintParser parser = new BlueprintParser();
            List<BlueprintSet> blueprintSets = parser.parse(input);

            Comparator<Factory> comparator = Comparator.comparingInt(factory -> factory.getResources().getStock(Resource.GEODE));

            Factory optimal = blueprintSets.stream()
                    .map(Factory::new)
                    .map(factory -> factory.minmax(comparator.reversed(), 24))
                    .max(comparator)
                    .orElseGet(Assertions::fail);

            assertEquals(2, optimal.getBlueprints().getId());
            assertEquals(12, optimal.getResources().getStock(Resource.GEODE));
        }
    }

    @Test
    void testMinMax_withChallengeInput() throws IOException {
        try (InputStream input = FactoryMinMaxTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            BlueprintParser parser = new BlueprintParser();
            List<BlueprintSet> blueprintSets = parser.parse(input);

            Comparator<Factory> comparator = Comparator.<Factory>comparingInt(factory -> factory.getResources().getStock(Resource.GEODE))
                    .reversed();

            int qualityLevel = blueprintSets.stream()
                    .map(Factory::new)
                    .map(factory -> factory.minmax(comparator, 24))
                    .mapToInt(this::qualityLevel)
                    .sum();

            assertEquals(12, qualityLevel);
        }
    }

    protected int qualityLevel(Factory factory) {
        return factory.getBlueprints().getId() * factory.getResources().getStock(Resource.GEODE);
    }
}
