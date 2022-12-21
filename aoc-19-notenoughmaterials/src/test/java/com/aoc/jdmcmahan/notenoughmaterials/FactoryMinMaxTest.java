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

            Comparator<Factory> comparator = Comparator.comparing(factory -> factory.getResources().getStock(Resource.GEODE));

            Factory optimal = blueprintSets.stream()
                    .map(Factory::new)
                    .map(factory -> factory.minmax(comparator, 24))
                    .min(comparator)
                    .orElseGet(Assertions::fail);

            assertEquals(2, optimal.getBlueprints().getId());
            assertEquals(12, optimal.getResources().getStock(Resource.GEODE));
        }
    }
}
