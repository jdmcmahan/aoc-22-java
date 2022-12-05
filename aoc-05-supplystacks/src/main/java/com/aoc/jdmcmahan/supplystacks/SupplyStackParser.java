package com.aoc.jdmcmahan.supplystacks;

import com.aoc.jdmcmahan.supplystacks.command.CommandExecutor;
import com.aoc.jdmcmahan.supplystacks.command.model.MoveCratesCommand;
import com.aoc.jdmcmahan.supplystacks.model.Crate;
import com.aoc.jdmcmahan.supplystacks.model.CrateStack;
import com.aoc.jdmcmahan.supplystacks.model.Ship;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class SupplyStackParser {

    protected static final Pattern CRATE_PATTERN = Pattern.compile("\\[(\\w)]");
    protected static final Pattern STACK_COLUMN_HEADER_PATTERN = Pattern.compile("(\\d+)");
    protected static final Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^move (\\d+) from (\\d+) to (\\d+)$");

    private final CommandExecutor commandExecutor;

    public SupplyStackParser() {
        this(null);
    }

    public Ship parse(InputStream input) {
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(input));

        Ship ship = parseStacks(reader);

        if (commandExecutor != null) {
            List<MoveCratesCommand> moveCommands = parseCommands(reader);
            moveCommands.forEach(command -> commandExecutor.executeMoveCommand(ship, command));
        }

        return ship;
    }

    protected Ship parseStacks(LineNumberReader reader) {
        try {
            Map<String, CrateStack.CrateStackBuilder> stackBuilders = new HashMap<>();

            String line;
            Matcher matcher;
            while ((line = reader.readLine()) != null && (matcher = CRATE_PATTERN.matcher(line)).find()) {
                do {
                    String column = String.valueOf((matcher.start() / 4) + 1);
                    String tag = matcher.group(1);

                    stackBuilders.compute(column, (key, builder) -> {
                        if (builder == null) {
                            builder = CrateStack.builder()
                                    .id(key);
                        }

                        return builder.insertBottom(new Crate(tag));
                    });
                } while (matcher.find());
            }

            if (line == null) {
                throw new IllegalArgumentException("Unexpected end of input");
            }

            matcher = STACK_COLUMN_HEADER_PATTERN.matcher(line);
            List<CrateStack> stacks = matcher.results()
                    .map(result -> result.group(1))
                    .map(id -> stackBuilders.getOrDefault(id, CrateStack.builder().id(id)))
                    .map(CrateStack.CrateStackBuilder::build)
                    .toList();

            // skip empty line
            reader.readLine();

            return new Ship(stacks);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Error encountered at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
        }
    }

    protected List<MoveCratesCommand> parseCommands(LineNumberReader reader) {
        return reader.lines()
                .map(line -> {
                    try {
                        Matcher matcher = MOVE_COMMAND_PATTERN.matcher(line);
                        if (!matcher.find()) {
                            throw new IllegalArgumentException("Unrecognized move command: " + line);
                        }

                        int count = Integer.parseInt(matcher.group(1));
                        String from = matcher.group(2);
                        String to = matcher.group(3);

                        return new MoveCratesCommand(from, to, count);
                    } catch (RuntimeException e) {
                        throw new IllegalArgumentException("Error encountered at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
                    }
                })
                .toList();
    }
}
