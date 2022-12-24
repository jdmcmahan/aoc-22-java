package com.aoc.jdmcmahan.monkeymath.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConstantValueResolver implements ValueResolver {

    private final long value;

    @Override
    public long resolve() {
        return value;
    }
}
