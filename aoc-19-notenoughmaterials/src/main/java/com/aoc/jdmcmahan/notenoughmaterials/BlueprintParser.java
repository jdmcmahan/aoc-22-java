package com.aoc.jdmcmahan.notenoughmaterials;

import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.ClayRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.GeodeRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.ObsidianRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.blueprint.model.OreRobotBlueprint;
import com.aoc.jdmcmahan.notenoughmaterials.model.BlueprintSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlueprintParser {

    protected static final Pattern BLUEPRINT_ID_PATTERN = Pattern.compile("Blueprint (\\d+):");
    protected static final Pattern ORE_ROBOT_PATTERN = Pattern.compile("Each ore robot costs (\\d+) ore.");
    protected static final Pattern CLAY_ROBOT_PATTERN = Pattern.compile("Each clay robot costs (\\d+) ore.");
    protected static final Pattern OBSIDIAN_ROBOT_PATTERN = Pattern.compile("Each obsidian robot costs (\\d+) ore and (\\d+) clay.");
    protected static final Pattern GEODE_ROBOT_PATTERN = Pattern.compile("Each geode robot costs (\\d+) ore and (\\d+) obsidian.");

    public List<BlueprintSet> parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                List<BlueprintSet> sets = new LinkedList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    BlueprintSet.BlueprintSetBuilder builder = BlueprintSet.builder();

                    Matcher idMatcher = BLUEPRINT_ID_PATTERN.matcher(line);
                    if (!idMatcher.find()) {
                        throw new IllegalArgumentException("No id specified");
                    }

                    int id = Optional.ofNullable(idMatcher.group(1))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No id specified"));

                    builder.id(id);

                    Matcher oreRobotMatcher = ORE_ROBOT_PATTERN.matcher(line);
                    if (!oreRobotMatcher.find()) {
                        throw new IllegalArgumentException("Missing ore robot specifications");
                    }

                    int oreCost = Optional.ofNullable(oreRobotMatcher.group(1))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No ore cost specified for ore robot"));

                    builder.oreRobotBlueprint(new OreRobotBlueprint(oreCost));

                    Matcher clayRobotMatcher = CLAY_ROBOT_PATTERN.matcher(line);
                    if (!clayRobotMatcher.find()) {
                        throw new IllegalArgumentException("Missing clay robot specifications");
                    }

                    oreCost = Optional.ofNullable(clayRobotMatcher.group(1))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No ore cost specified for clay robot"));

                    builder.clayRobotBlueprint(new ClayRobotBlueprint(oreCost));

                    Matcher obsidianRobotMatcher = OBSIDIAN_ROBOT_PATTERN.matcher(line);
                    if (!obsidianRobotMatcher.find()) {
                        throw new IllegalArgumentException("Missing obsidian robot specifications");
                    }

                    oreCost = Optional.ofNullable(obsidianRobotMatcher.group(1))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No ore cost specified for obsidian robot"));

                    int clayCost = Optional.ofNullable(obsidianRobotMatcher.group(2))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No clay cost specified for obsidian robot"));

                    builder.obsidianRobotBlueprint(new ObsidianRobotBlueprint(oreCost, clayCost));

                    Matcher geodeRobotMatcher = GEODE_ROBOT_PATTERN.matcher(line);
                    if (!geodeRobotMatcher.find()) {
                        throw new IllegalArgumentException("Missing geode robot specifications");
                    }

                    oreCost = Optional.ofNullable(geodeRobotMatcher.group(1))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No ore cost specified for geode robot"));

                    int obsidianCost = Optional.ofNullable(geodeRobotMatcher.group(2))
                            .map(Integer::parseInt)
                            .orElseThrow(() -> new IllegalArgumentException("No obsidian cost specified for geode robot"));

                    builder.geodeRobotBlueprint(new GeodeRobotBlueprint(oreCost, obsidianCost));

                    sets.add(builder.build());
                }

                return sets;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }
}
