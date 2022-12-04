package com.aoc.jdmcmahan.rucksackreorganization;

import com.aoc.jdmcmahan.rucksackreorganization.model.Item;
import com.aoc.jdmcmahan.rucksackreorganization.model.ItemType;
import com.aoc.jdmcmahan.rucksackreorganization.model.Rucksack;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.stream.Stream;

public class RucksackListParser {

    public Stream<Rucksack> parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    try {
                        int middle = row.length() / 2;
                        List<Item> compartment1 = stringToItems(row.substring(0, middle));
                        List<Item> compartment2 = stringToItems(row.substring(middle));

                        return new Rucksack(compartment1, compartment2);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid data on line " + reader.getLineNumber() + ": " + row);
                    }
                });
    }

    protected static List<Item> stringToItems(String s) {
        return s.chars()
                .mapToObj(c -> new Item(ItemType.valueOf((char) c)))
                .toList();
    }
}
