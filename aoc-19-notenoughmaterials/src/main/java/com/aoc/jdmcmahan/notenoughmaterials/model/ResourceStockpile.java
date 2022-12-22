package com.aoc.jdmcmahan.notenoughmaterials.model;

import com.aoc.jdmcmahan.notenoughmaterials.robot.model.RobotOutput;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.EnumMap;
import java.util.Map;

@NoArgsConstructor
@ToString
public class ResourceStockpile {

    private final Map<Resource, Integer> resources = new EnumMap<>(Resource.class);

    public ResourceStockpile(ResourceStockpile other) {
        this.resources.putAll(other.resources);
    }

    public int getStock(Resource resource) {
        return resources.getOrDefault(resource, 0);
    }

    public synchronized void debit(Resource resource, int quantity) {
        if (this.getStock(resource) < quantity) {
            throw new IllegalArgumentException("Not enough stock of resource " + resource + ". Current stock: " + resources.get(resource) + ", needed: " + quantity);
        }

        this.credit(resource, -1 * quantity);
    }

    public void credit(RobotOutput lot) {
        this.credit(lot.resource(), lot.quantity());
    }

    public synchronized void credit(Resource resource, int quantity) {
        resources.merge(resource, quantity, Integer::sum);
    }
}
