package com.aoc.jdmcmahan.supplystacks.model;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.IntStream;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CrateStack {

    private final String id;
    private final Deque<Crate> crates;

    public String id() {
        return id;
    }

    public Collection<Crate> crates() {
        return Collections.unmodifiableCollection(crates);
    }

    public Crate top() {
        return crates.peek();
    }

    public void stack(Crate crate) {
        stack(Collections.singletonList(crate));
    }

    public void stack(List<Crate> crates) {
        ListIterator<Crate> iterator = crates.listIterator(crates.size());
        while (iterator.hasPrevious()) {
            Crate crate = iterator.previous();
            this.crates.push(crate);
        }
    }

    public Crate unstack() {
        return crates.pop();
    }

    public List<Crate> unstack(int count) {
        if (count > crates.size()) {
            throw new IllegalArgumentException("Attempting to unstack " + count + " crate(s) when only " + crates.size() + " crates exist in this stack");
        }

        return IntStream.range(0, count)
                .mapToObj(i -> this.unstack())
                .toList();
    }

    public static CrateStackBuilder builder() {
        return new CrateStackBuilder();
    }

    public static class CrateStackBuilder {

        private final Deque<Crate> crates = new LinkedList<>();
        private String id;

        public CrateStackBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CrateStackBuilder stack(Crate crate) {
            crates.push(crate);
            return this;
        }

        public CrateStackBuilder insertBottom(Crate crate) {
            crates.addLast(crate);
            return this;
        }

        public CrateStack build() {
            return new CrateStack(id, crates);
        }
    }
}
