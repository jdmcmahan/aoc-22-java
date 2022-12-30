package com.aoc.jdmcmahan.regolithreservoir;

import com.aoc.jdmcmahan.regolithreservoir.model.Position;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.LinkedList;
import java.util.List;

public class RockPathParser {

    public List<Position> parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                List<Position> rocks = new LinkedList<>();

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(" -> ");
                    if (tokens.length <= 1) {
                        throw new IllegalArgumentException("Invalid format: " + line);
                    }

                    Position previous = this.parsePosition(tokens[0]);
                    for (int i = 1; i < tokens.length; i++) {
                        Position current = this.parsePosition(tokens[i]);
                        rocks.addAll(this.path(previous, current));

                        previous = current;
                    }
                }

                return rocks;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }

    protected Position parsePosition(String s) {
        String[] tokens = s.split(",");
        if (tokens.length != 2) {
            throw new IllegalArgumentException("Invalid position format: " + s);
        }

        return Position.valueOf(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
    }

    protected List<Position> path(Position from, Position to) {
        List<Position> path = new LinkedList<>();
        path.add(from);

        Position current = from;
        while (!current.equals(to)) {
            if (current.getX() != to.getX()) {
                current = current.getX() < to.getX()
                        ? current.right()
                        : current.left();
            } else if (current.getY() != to.getY()) {
                current = current.getY() < to.getY()
                        ? current.down()
                        : current.up();
            } else {
                throw new IllegalArgumentException("Positions are not aligned (from: " + current + ", to: " + to + ")");
            }

            path.add(current);
        }

        return path;
    }
}
