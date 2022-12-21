package com.aoc.jdmcmahan.grovepositioningsystem;

import com.aoc.jdmcmahan.grovepositioningsystem.model.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SequenceMixer {

    private final List<Node> sequence = new ArrayList<>();
    private final Node zeroNode;

    public SequenceMixer(List<Integer> sequence, long decryptionKey) {
        Node zeroNode = null;
        for (int i = 0; i < sequence.size(); i++) {
            int value = sequence.get(i);
            Node node = new Node(value * decryptionKey);

            if (value == 0) {
                if (zeroNode != null) {
                    throw new IllegalArgumentException("Multiple zero nodes in sequence");
                }

                zeroNode = node;
            }

            this.sequence.add(node);

            if (i > 0) {
                node.setPrevious(this.sequence.get(i - 1));
            }
        }

        this.zeroNode = Optional.ofNullable(zeroNode)
                .orElseThrow(() -> new IllegalArgumentException("No zero node found in sequence"));

        this.sequence.get(0).setPrevious(this.sequence.get(this.sequence.size() - 1));
    }

    public List<Long> getSequence() {
        return Stream.iterate(sequence.get(0), Node::getNext)
                .limit(sequence.size())
                .map(Node::getValue)
                .collect(Collectors.toList());
    }

    public Coordinates getCoordinates() {
        Node current = scanTo(zeroNode, Node::getNext, 1000);
        long x = current.getValue();

        current = scanTo(current, Node::getNext, 1000);
        long y = current.getValue();

        current = scanTo(current, Node::getNext, 1000);
        long z = current.getValue();

        return new Coordinates(x, y, z);
    }

    public void mix() {
        for (Node node : sequence) {
            long value = node.getValue();
            if (value == 0) {
                continue;
            }

            Node target = this.scanTo(node, node.getValue());
            if (value > 0) {
                target.setNext(node);
            } else {
                target.setPrevious(node);
            }
        }
    }

    protected Node scanTo(Node start, long offset) {
        UnaryOperator<Node> f = offset < 0
                ? Node::getPrevious
                : Node::getNext;

        return scanTo(start, f, Math.abs(offset) % (sequence.size() - 1));
    }

    protected static Node scanTo(Node start, UnaryOperator<Node> operator, long offset) {
        return Stream.iterate(start, operator)
                .skip(offset)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Failed to scan nodes"));
    }

    @RequiredArgsConstructor
    @Getter
    protected static class Node implements Iterable<Node> {

        private final long value;
        private Node previous;
        private Node next;

        public void setPrevious(Node previous) {
            if (previous != this.previous) {
                if (this.previous == null) {
                    linkBefore(previous, this);
                } else {
                    insert(this.previous, previous, this);
                }
            }
        }

        public void setNext(Node next) {
            if (next != this.next) {
                if (this.next == null) {
                    linkAfter(this, next);
                } else {
                    insert(this, next, this.next);
                }
            }
        }

        @Override
        public NodeIterator iterator() {
            return new NodeIterator(this);
        }

        protected static void insert(Node left, Node inserted, Node right) {
            Objects.requireNonNull(left);
            Objects.requireNonNull(inserted);
            Objects.requireNonNull(right);

            if (inserted.previous != null) {
                inserted.previous.next = inserted.next;
            }

            if (inserted.next != null) {
                inserted.next.previous = inserted.previous;
            }

            inserted.previous = left;
            inserted.next = right;

            left.next = inserted;
            right.previous = inserted;
        }

        protected static void linkBefore(Node appended, Node right) {
            Objects.requireNonNull(right);
            Objects.requireNonNull(appended);

            if (appended.next != null) {
                appended.next.previous = appended.previous;
            }

            right.previous = appended;
            appended.next = right;
        }

        protected static void linkAfter(Node left, Node appended) {
            Objects.requireNonNull(left);
            Objects.requireNonNull(appended);

            if (appended.previous != null) {
                appended.previous.next = appended.next;
            }

            left.next = appended;
            appended.previous = left;
        }

        @AllArgsConstructor
        public static class NodeIterator implements Iterator<Node> {

            private Node current;

            public boolean hasPrevious() {
                return current.getPrevious() != null;
            }

            public Node previous() {
                current = current.getPrevious();
                return current;
            }

            @Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public Node next() {
                current = current.getNext();
                return current;
            }
        }
    }
}
