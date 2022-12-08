package com.aoc.jdmcmahan.nospaceleftondevice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileTest {

    private File file;

    @BeforeEach
    void initializeFile() {
        this.file = new File("test", 100);
    }

    @Test
    void testGetPath_withStandaloneFile() {
        assertEquals("test", file.getPath());
    }

    @Test
    void testGetPath_withParentDirectories() {
        Directory grandparent = new Directory("grandparent");
        Directory parent = new Directory("parent");

        grandparent.addDirectory(parent);
        parent.addFile(file);

        assertEquals("grandparent/parent/test", file.getPath());
    }

    @Test
    void testGetPath_withFilesystemChanges() {
        Directory grandparent = new Directory("grandparent");
        Directory parent = new Directory("parent");

        grandparent.addDirectory(parent);
        parent.addFile(file);

        assertEquals("grandparent/parent/test", file.getPath());

        Directory anotherParent = new Directory("anotherParent");
        anotherParent.addFile(file);

        assertEquals("anotherParent/test", file.getPath());

        anotherParent.removeFile("test");

        assertEquals("test", file.getPath());
    }

    @Test
    void testSetParent_synchronizesRelationship() {
        Directory parent = new Directory("parent");
        file.setParent(parent);

        assertSame(parent, file.getParent());
        assertThat(parent.getFiles())
                .containsExactly(file);
    }

    @Test
    void testSetNullParent_synchronizesRelationship() {
        Directory parent = new Directory("parent");
        file.setParent(parent);

        assertSame(parent, file.getParent());
        assertThat(parent.getFiles())
                .containsExactly(file);

        file.setParent(null);

        assertNull(file.getParent());
        assertTrue(parent.getFiles().isEmpty());
    }

    @Test
    void testSetDifferentParent_synchronizesRelationship() {
        Directory parent1 = new Directory("parent1");
        file.setParent(parent1);

        assertSame(parent1, file.getParent());
        assertThat(parent1.getFiles())
                .containsExactly(file);

        Directory parent2 = new Directory("parent2");
        file.setParent(parent2);

        assertSame(parent2, file.getParent());
        assertTrue(parent1.getFiles().isEmpty());
        assertThat(parent2.getFiles())
                .containsExactly(file);
    }

}
