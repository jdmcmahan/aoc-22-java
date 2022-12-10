package com.aoc.jdmcmahan.treetoptreehouse.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Forest {

    private final Table<Integer, Integer, Tree> trees;

    private final Map<Integer, List<Tree>> visibleFromLeft = new HashMap<>();
    private final Map<Integer, List<Tree>> visibleFromRight = new HashMap<>();
    private final Map<Integer, List<Tree>> visibleFromTop = new HashMap<>();
    private final Map<Integer, List<Tree>> visibleFromBottom = new HashMap<>();

    private final Map<Tree, Integer> scenicScores = new HashMap<>();

    public Stream<Tree> trees() {
        return trees.values().stream();
    }

    public List<Tree> visibleFromLeft(int row) {
        return visibleFromLeft.computeIfAbsent(row, r -> this.findVisibleTrees(this.rightwardIterator(r, 0)));
    }

    public List<Tree> visibleFromRight(int row) {
        return visibleFromRight.computeIfAbsent(row, r -> this.findVisibleTrees(this.leftwardIterator(r, trees.row(r).size() - 1)));
    }

    public List<Tree> visibleFromBottom(int column) {
        return visibleFromBottom.computeIfAbsent(column, c -> this.findVisibleTrees(this.upwardIterator(trees.column(c).size() - 1, c)));
    }

    public List<Tree> visibleFromTop(int column) {
        return visibleFromTop.computeIfAbsent(column, c -> this.findVisibleTrees(this.downwardIterator(0, c)));
    }

    public Stream<Tree> visible() {
        Stream<Tree> rows = trees.rowKeySet().stream()
                .flatMap(r -> Stream.concat(this.visibleFromLeft(r).stream(), this.visibleFromRight(r).stream()));

        Stream<Tree> columns = trees.columnKeySet().stream()
                .flatMap(c -> Stream.concat(this.visibleFromTop(c).stream(), this.visibleFromBottom(c).stream()));

        return Stream.concat(rows, columns)
                .distinct();
    }

    public int scenicScore(Tree tree) {
        return scenicScores.computeIfAbsent(tree, t -> {
            int row = t.row();
            if (row == 0 || row >= trees.row(row).size() - 1) {
                return 0;
            }

            int column = t.column();
            if (column == 0 || column >= trees.column(column).size() - 1) {
                return 0;
            }

            int leftwardScore = this.findScenicTrees(this.leftwardIterator(row, column - 1), t.height()).size();
            int rightwardScore = this.findScenicTrees(this.rightwardIterator(row, column + 1), t.height()).size();
            int upwardScore = this.findScenicTrees(this.upwardIterator(row - 1, column), t.height()).size();
            int downwardScore = this.findScenicTrees(this.downwardIterator(row + 1, column), t.height()).size();

            return leftwardScore * rightwardScore * upwardScore * downwardScore;
        });
    }

    protected List<Tree> findVisibleTrees(Iterator<Tree> vector) {
        LinkedList<Tree> visible = new LinkedList<>();
        while (vector.hasNext()) {
            Tree current = vector.next();
            if (current == null) {
                continue;
            }

            Tree previous = visible.peekLast();
            if (previous == null || current.height() > previous.height()) {
                visible.add(current);
            }

            if (current.height() == 9) {
                break;
            }
        }

        return visible;
    }

    protected List<Tree> findScenicTrees(Iterator<Tree> vector, int fromHeight) {
        LinkedList<Tree> visible = new LinkedList<>();
        while (vector.hasNext()) {
            Tree current = vector.next();
            if (current == null) {
                continue;
            }

            visible.add(current);

            if (current.height() >= fromHeight) {
                break;
            }
        }

        return visible;
    }

    protected Iterator<Tree> leftwardIterator(int row, int columnStart) {
        Map<Integer, Tree> treeRow = trees.row(row);
        return IntStream.rangeClosed(0, columnStart)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(treeRow::get)
                .iterator();
    }

    protected Iterator<Tree> rightwardIterator(int row, int columnStart) {
        Map<Integer, Tree> treeRow = trees.row(row);
        return IntStream.rangeClosed(columnStart, treeRow.size() - 1)
                .mapToObj(treeRow::get)
                .iterator();
    }

    protected Iterator<Tree> upwardIterator(int rowStart, int column) {
        Map<Integer, Tree> treeColumn = trees.column(column);
        return IntStream.rangeClosed(0, rowStart)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(treeColumn::get)
                .iterator();
    }

    protected Iterator<Tree> downwardIterator(int rowStart, int column) {
        Map<Integer, Tree> treeColumn = trees.column(column);
        return IntStream.rangeClosed(rowStart, treeColumn.size() - 1)
                .mapToObj(treeColumn::get)
                .iterator();
    }

    public static ForestBuilder builder() {
        return new ForestBuilder();
    }

    public static class ForestBuilder {

        private final Table<Integer, Integer, Tree> trees = HashBasedTable.create();

        private int currentRow = 0;

        public ForestBuilder row(int... heights) {
            IntStream.range(0, heights.length)
                    .mapToObj(i -> new Tree(currentRow, i, heights[i]))
                    .forEach(tree -> trees.put(tree.row(), tree.column(), tree));

            currentRow++;
            return this;
        }

        public Forest build() {
            return new Forest(trees);
        }
    }
}
