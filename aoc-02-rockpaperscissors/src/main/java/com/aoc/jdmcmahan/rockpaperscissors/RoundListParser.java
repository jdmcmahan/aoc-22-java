package com.aoc.jdmcmahan.rockpaperscissors;

import com.aoc.jdmcmahan.rockpaperscissors.model.Outcome;
import com.aoc.jdmcmahan.rockpaperscissors.model.Round;
import com.aoc.jdmcmahan.rockpaperscissors.model.Shape;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class RoundListParser {

    protected static final Map<String, Shape> OPPONENT_SHAPE_KEYS = Map.of(
            "A", Shape.ROCK,
            "B", Shape.PAPER,
            "C", Shape.SCISSORS
    );


    protected static final Map<String, Shape> SELF_SHAPE_KEYS = Map.of(
            "X", Shape.ROCK,
            "Y", Shape.PAPER,
            "Z", Shape.SCISSORS
    );

    protected static final Map<String, Outcome> SELF_OUTCOME_KEYS = Map.of(
            "X", Outcome.LOSS,
            "Y", Outcome.DRAW,
            "Z", Outcome.WIN
    );

    public Stream<Round> parseAsShapes(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    String[] tokens = row.split(" ");
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid input on line " + reader.getLineNumber() + ": " + row);
                    }

                    String opponentToken = tokens[0];
                    Shape opponentShape = Optional.ofNullable(opponentToken)
                            .map(OPPONENT_SHAPE_KEYS::get)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid opponent shape on line " + reader.getLineNumber() + ": " + opponentToken));

                    String selfToken = tokens[1];
                    Shape selfShape = Optional.ofNullable(selfToken)
                            .map(SELF_SHAPE_KEYS::get)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid self shape on line " + reader.getLineNumber() + ": " + selfToken));

                    return new Round(opponentShape, selfShape);
                });
    }

    public Stream<Round> parseAsOutcomes(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));
        return reader.lines()
                .map(row -> {
                    String[] tokens = row.split(" ");
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid input on line " + reader.getLineNumber() + ": " + row);
                    }

                    String opponentToken = tokens[0];
                    Shape opponentShape = Optional.ofNullable(opponentToken)
                            .map(OPPONENT_SHAPE_KEYS::get)
                            .orElseThrow(() -> new IllegalArgumentException("Invalid opponent shape on line " + reader.getLineNumber() + ": " + opponentToken));

                    String selfToken = tokens[1];
                    Shape selfShape = Optional.ofNullable(selfToken)
                            .map(SELF_OUTCOME_KEYS::get)
                            .map(outcome -> switch (outcome) {
                                case WIN -> opponentShape.losingShape();
                                case LOSS -> opponentShape.winningShape();
                                case DRAW -> opponentShape;
                            })
                            .orElseThrow(() -> new IllegalArgumentException("Invalid self outcome on line " + reader.getLineNumber() + ": " + selfToken));

                    return new Round(opponentShape, selfShape);
                });
    }
}
