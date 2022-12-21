package com.aoc.jdmcmahan.monkeymath;

import com.aoc.jdmcmahan.monkeymath.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyMathParser {

    public static final RootResolverParser ROOT_AS_VERBATIM_PARSER = MonkeyMathParser::parseArithmeticYellValueResolver;
    public static final RootResolverParser ROOT_AS_EQUALITY_PARSER = (left, right, operator) -> new EqualityValueResolver(left, right);

    public static final HumanResolverParser HUMAN_AS_VERBATIM_PARSER = (registry, value) -> new ConstantValueResolver(value);
    public static final HumanResolverParser HUMAN_AS_UNISOLATED_PARSER = (registry, value) -> new UnisolatedValueResolver(registry);

    protected static final Pattern CONSTANT_PATTERN = Pattern.compile("(\\d+)");
    protected static final Pattern ARITHMETIC_PATTERN = Pattern.compile("(\\w+)\\s*([+\\-*/])\\s*(\\w+)");

    public MonkeyRegistry parse(InputStream input) throws IOException {
        return this.parse(input, ROOT_AS_VERBATIM_PARSER, HUMAN_AS_VERBATIM_PARSER);
    }

    public MonkeyRegistry parse(InputStream input, RootResolverParser rootParser, HumanResolverParser humanParser) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                MonkeyRegistry registry = new MonkeyRegistry();

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(": ");
                    if (tokens.length != 2) {
                        throw new IllegalArgumentException("Invalid input: " + line);
                    }

                    String id = tokens[0];

                    ValueResolver resolver;
                    Matcher matcher;
                    if ((matcher = CONSTANT_PATTERN.matcher(tokens[1])).find()) {
                        long constant = Long.parseLong(matcher.group(0));

                        resolver = id.equals("humn")
                                ? humanParser.parse(registry, constant)
                                : new ConstantValueResolver(constant);
                    } else if ((matcher = ARITHMETIC_PATTERN.matcher(tokens[1])).find()) {
                        String leftId = matcher.group(1);
                        String op = matcher.group(2);
                        String rightId = matcher.group(3);

                        ValueResolver leftResolver = new DelegatingValueResolver(registry, leftId);
                        ValueResolver rightResolver = new DelegatingValueResolver(registry, rightId);

                        resolver = id.equals("root")
                                ? rootParser.parse(leftResolver, rightResolver, op)
                                : parseArithmeticYellValueResolver(leftResolver, rightResolver, op);
                    } else {
                        throw new IllegalArgumentException("Invalid yell logic: " + tokens[1]);
                    }

                    Monkey monkey = new Monkey(id, resolver);
                    registry.registerMonkey(monkey);
                }

                return registry;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }

    protected static ArithmeticValueResolver parseArithmeticYellValueResolver(ValueResolver left, ValueResolver right, String operator) {
        return switch (operator) {
            case "+" -> ArithmeticValueResolver.adding(left, right);
            case "-" -> ArithmeticValueResolver.subtracting(left, right);
            case "*" -> ArithmeticValueResolver.multiplying(left, right);
            case "/" -> ArithmeticValueResolver.dividing(left, right);
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }

    @FunctionalInterface
    public interface RootResolverParser {
        ValueResolver parse(ValueResolver left, ValueResolver right, String operator);
    }

    @FunctionalInterface
    public interface HumanResolverParser {
        ValueResolver parse(MonkeyRegistry registry, long value);
    }
}
