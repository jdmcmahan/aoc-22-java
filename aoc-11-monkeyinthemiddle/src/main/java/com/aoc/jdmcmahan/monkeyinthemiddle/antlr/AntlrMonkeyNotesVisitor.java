package com.aoc.jdmcmahan.monkeyinthemiddle.antlr;

import com.aoc.jdmcmahan.monkeyinthemiddle.model.Item;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.Monkey;
import com.aoc.jdmcmahan.monkeyinthemiddle.model.MonkeyRegistry;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class AntlrMonkeyNotesVisitor extends MonkeyNotesParserBaseVisitor<Object> {

    public <T> T visit(ParseTree tree, Class<T> type) {
        Object o = this.visit(tree);
        if (o == null) {
            return null;
        } else if (type.isAssignableFrom(o.getClass())) {
            return type.cast(o);
        }

        throw new IllegalArgumentException(String.format("Incompatible types (expected: %s, actual: %s)", type.getSimpleName(), o.getClass().getSimpleName()));
    }

    @Override
    public MonkeyRegistry visitNotes(MonkeyNotesParser.NotesContext ctx) {
        MonkeyRegistry registry = new MonkeyRegistry();
        ctx.monkeyNotes().stream()
                .map(this::visitMonkeyNotes)
                .forEach(registry::registerMonkey);

        return registry;
    }

    @Override
    public Monkey visitMonkeyNotes(MonkeyNotesParser.MonkeyNotesContext ctx) {
        Monkey.MonkeyBuilder monkeyBuilder = Monkey.builder()
                .id(this.visitMonkeyHeader(ctx.monkeyHeader()));

        this.visitMonkeyStartingItems(ctx.monkeyStartingItems())
                .forEach(monkeyBuilder::item);

        monkeyBuilder.operation(this.visitMonkeyWorryOperation(ctx.monkeyWorryOperation()));

        monkeyBuilder.test(this.visitMonkeyActionTest(ctx.monkeyActionTest()));

        return monkeyBuilder.build();
    }

    @Override
    public String visitMonkeyHeader(MonkeyNotesParser.MonkeyHeaderContext ctx) {
        return Objects.toString(this.visit(ctx.id), null);
    }

    @Override
    public Stream<Item> visitMonkeyStartingItems(MonkeyNotesParser.MonkeyStartingItemsContext ctx) {
        return ctx.items.intLiteral().stream()
                .map(node -> this.visit(node, Long.class))
                .map(Item::new);
    }

    @Override
    public Monkey.WorryOperation visitMonkeyWorryOperation(MonkeyNotesParser.MonkeyWorryOperationContext ctx) {
        Function<Long, Long> leftOperandMapper = this.operandMapper(ctx.left);
        Function<Long, Long> rightOperandMapper = this.operandMapper(ctx.right);

        BiFunction<Long, Long, Long> operationMapper = switch (ctx.op.getType()) {
            case MonkeyNotesLexer.PLUS -> Long::sum;
            case MonkeyNotesLexer.MINUS -> (l, r) -> l - r;
            case MonkeyNotesLexer.MUL -> (l, r) -> l * r;
            case MonkeyNotesLexer.DIV -> (l, r) -> l / r;
            default -> throw new IllegalArgumentException("Unrecognized operator: " + ctx.op.getText());
        };

        return old -> operationMapper.apply(leftOperandMapper.apply(old), rightOperandMapper.apply(old));
    }

    @Override
    public Monkey.WorryLevelDivisibilityMonkeyActionTest visitMonkeyActionTest(MonkeyNotesParser.MonkeyActionTestContext ctx) {
        long divisor = this.visit(ctx.logic, Long.class);
        Monkey.MonkeyAction trueAction = this.visit(ctx.trueAction, Monkey.MonkeyAction.class);
        Monkey.MonkeyAction falseAction = this.visit(ctx.falseAction, Monkey.MonkeyAction.class);

        return new Monkey.WorryLevelDivisibilityMonkeyActionTest(divisor, trueAction, falseAction);
    }

    @Override
    public Monkey.MonkeyAction visitThrowAction(MonkeyNotesParser.ThrowActionContext ctx) {
        String targetId = Objects.toString(this.visit(ctx.target), null);
        return (item, monkey, registry) -> {
            Monkey target = registry.getMonkey(targetId)
                    .orElseThrow(() -> new IllegalArgumentException("No monkey found with id " + targetId));

            monkey.throwItem(item);
            target.catchItem(item);
        };
    }

    @Override
    public Long visitDivisibleByTest(MonkeyNotesParser.DivisibleByTestContext ctx) {
        return this.visit(ctx.right, Long.class);
    }

    @Override
    public Object visitMonkeyActionIfTrue(MonkeyNotesParser.MonkeyActionIfTrueContext ctx) {
        return super.visitMonkeyActionIfTrue(ctx);
    }

    @Override
    public Long visitIntegerLiteral(MonkeyNotesParser.IntegerLiteralContext ctx) {
        return Long.valueOf(ctx.INTEGER_LITERAL().getText());
    }

    protected Function<Long, Long> operandMapper(MonkeyNotesParser.OperandContext ctx) {
        if (ctx.OLD() != null) {
            return Function.identity();
        }

        return old -> this.visit(ctx, Long.class);
    }
}
