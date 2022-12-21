package com.aoc.jdmcmahan.notenoughmaterials.model;

import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.Map;

@NoArgsConstructor
public class ResourceStockpile {

    private final Map<Resource, Integer> resources = new EnumMap<>(Resource.class);

    public ResourceStockpile(ResourceStockpile other) {
        this.resources.putAll(other.resources);
    }

    public int getStock(Resource resource) {
        return resources.getOrDefault(resource, 0);
    }

    public void debit(ResourceLot lot) {
        this.debit(lot.resource(), lot.quantity());
    }

    public synchronized void debit(Resource resource, int quantity) {
        if (this.getStock(resource) < quantity) {
            throw new IllegalArgumentException("Not enough stock of resource " + resource + ". Current stock: " + resources.get(resource) + ", needed: " + quantity);
        }

        this.credit(resource, -1 * quantity);
    }

    public void credit(ResourceLot lot) {
        this.credit(lot.resource(), lot.quantity());
    }

    public synchronized void credit(Resource resource, int quantity) {
        resources.merge(resource, quantity, Integer::sum);
    }
}
