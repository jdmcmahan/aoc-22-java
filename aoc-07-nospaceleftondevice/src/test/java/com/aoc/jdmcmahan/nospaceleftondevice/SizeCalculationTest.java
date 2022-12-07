package com.aoc.jdmcmahan.nospaceleftondevice;

import com.aoc.jdmcmahan.nospaceleftondevice.model.Directory;
import com.aoc.jdmcmahan.nospaceleftondevice.model.Filesystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SizeCalculationTest {

    @Test
    void testCalculateSize_withExampleInput() throws IOException {
        try (InputStream input = SizeCalculationTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            FilesystemCommandParser parser = new FilesystemCommandParser();
            Filesystem filesystem = parser.parse(input);

            assertEquals(95437, calculateTotalSize(Collections.singleton(filesystem.root()), (directory -> directory.getSize() <= 100000)));
        }
    }

    @Test
    void testCalculateSize_withChallengeInput() throws IOException {
        try (InputStream input = SizeCalculationTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            FilesystemCommandParser parser = new FilesystemCommandParser();
            Filesystem filesystem = parser.parse(input);

            assertEquals(1141028, calculateTotalSize(Collections.singleton(filesystem.root()), (directory -> directory.getSize() <= 100000)));
        }
    }

    @Test
    void testOptimalDelete_withExampleInput() throws IOException {
        try (InputStream input = SizeCalculationTest.class.getClassLoader().getResourceAsStream("example.txt")) {
            FilesystemCommandParser parser = new FilesystemCommandParser();
            Filesystem filesystem = parser.parse(input);

            long spaceNeeded = 30000000 - (70000000 - filesystem.root().getSize());
            Directory candidate = findOptimalDeletionCandidate(filesystem.root(), spaceNeeded)
                    .orElseGet(Assertions::fail);

            assertEquals(24933642L, candidate.getSize());
        }
    }

    @Test
    void testOptimalDelete_withChallengeInput() throws IOException {
        try (InputStream input = SizeCalculationTest.class.getClassLoader().getResourceAsStream("challenge.txt")) {
            FilesystemCommandParser parser = new FilesystemCommandParser();
            Filesystem filesystem = parser.parse(input);

            long spaceNeeded = 30000000 - (70000000 - filesystem.root().getSize());
            Directory candidate = findOptimalDeletionCandidate(filesystem.root(), spaceNeeded)
                    .orElseGet(Assertions::fail);

            assertEquals(8278005L, candidate.getSize());
        }
    }

    private long calculateTotalSize(Collection<Directory> directories, Predicate<Directory> predicate) {
        return directories.stream()
                .mapToLong(directory -> {
                    long size = predicate.test(directory)
                            ? directory.getSize()
                            : 0;

                    return size + calculateTotalSize(directory.getSubdirectories(), predicate);
                })
                .sum();
    }

    private Optional<Directory> findOptimalDeletionCandidate(Directory root, long spaceNeeded) {
        return flattenDirectories(root, directory -> directory.getSize() >= spaceNeeded)
                .min(Comparator.comparing(Directory::getSize));
    }

    private Stream<Directory> flattenDirectories(Directory parent, Predicate<Directory> predicate) {
        return Stream.concat(Stream.of(parent), parent.getSubdirectories().stream().flatMap(directory -> flattenDirectories(directory, predicate)))
                .filter(predicate);
    }
}
