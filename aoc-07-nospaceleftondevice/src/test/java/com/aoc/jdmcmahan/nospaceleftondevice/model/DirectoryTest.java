package com.aoc.jdmcmahan.nospaceleftondevice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    private Directory directory;

    @BeforeEach
    void initializeDirectory() {
        this.directory = new Directory("test");
    }

    @Test
    void testGetPath_withStandaloneDirectory() {
        assertEquals("test", directory.getPath());
    }

    @Test
    void testGetPath_withParentDirectories() {
        Directory grandparent = new Directory("grandparent");
        Directory parent = new Directory("parent");

        grandparent.addDirectory(parent);
        parent.addDirectory(directory);

        assertEquals("grandparent/parent/test", directory.getPath());
    }

    @Test
    void testGetPath_withFilesystemChanges() {
        Directory grandparent = new Directory("grandparent");
        Directory parent = new Directory("parent");

        grandparent.addDirectory(parent);
        parent.addDirectory(directory);

        assertEquals("grandparent/parent/test", directory.getPath());

        Directory anotherParent = new Directory("anotherParent");
        anotherParent.addDirectory(directory);

        assertEquals("anotherParent/test", directory.getPath());

        anotherParent.removeDirectory("test");

        assertEquals("test", directory.getPath());
    }

    @Test
    void testGetSize_withLeafDirectory() {
        directory.addFile(new File("file1", 1000));
        directory.addFile(new File("file2", 2000));

        assertEquals(3000, directory.getSize());
    }

    @Test
    void testGetSize_withSubdirectories() {
        directory.addFile(new File("file1", 1000));
        directory.addFile(new File("file2", 2000));

        Directory subdirectory = new Directory("subdirectory");
        subdirectory.addFile(new File("subFile1", 3000));
        subdirectory.addFile(new File("subFile2", 4000));
        subdirectory.addFile(new File("subFile3", 5000));

        directory.addDirectory(subdirectory);

        assertEquals(15000, directory.getSize());
    }

    @Test
    void testGetSize_withFilesystemChanges() {
        directory.addFile(new File("file1", 1000));
        directory.addFile(new File("file2", 2000));

        Directory subdirectory = new Directory("subdirectory");
        subdirectory.addFile(new File("subFile1", 3000));
        subdirectory.addFile(new File("subFile2", 4000));
        subdirectory.addFile(new File("subFile3", 5000));

        directory.addDirectory(subdirectory);

        assertEquals(15000, directory.getSize());

        subdirectory.addFile(new File("subFile4", 10000));

        assertEquals(25000, directory.getSize());

        subdirectory.removeFile("subFile2");

        assertEquals(21000, directory.getSize());

        directory.removeDirectory("subdirectory");

        assertEquals(3000, directory.getSize());
    }

    @Test
    void testGetSubdirectories() {
        Directory subdirectory1 = new Directory("subdirectory1");
        Directory subdirectory2 = new Directory("subdirectory2");

        directory.addDirectory(subdirectory1);
        directory.addDirectory(subdirectory2);

        Collection<Directory> subdirectories = directory.getSubdirectories();

        assertEquals(2, subdirectories.size());
        assertThat(subdirectories)
                .containsExactlyInAnyOrder(subdirectory1, subdirectory2);
    }

    @Test
    void testGetSubdirectory() {
        Directory subdirectory1 = new Directory("subdirectory1");
        Directory subdirectory2 = new Directory("subdirectory2");

        directory.addDirectory(subdirectory1);
        directory.addDirectory(subdirectory2);

        Directory subdirectory = directory.getSubdirectory("subdirectory1")
                .orElseGet(Assertions::fail);

        assertSame(subdirectory1, subdirectory);
    }

    @Test
    void testGetSubdirectory_forNonexistentSubdirectory() {
        Directory subdirectory1 = new Directory("subdirectory1");
        Directory subdirectory2 = new Directory("subdirectory2");

        directory.addDirectory(subdirectory1);
        directory.addDirectory(subdirectory2);

        Optional<Directory> subdirectory = directory.getSubdirectory("subdirectory3");
        assertTrue(subdirectory.isEmpty());
    }

    @Test
    void testGetFiles() {
        File file1 = new File("file1", 100);
        File file2 = new File("file2", 200);

        directory.addFile(file1);
        directory.addFile(file2);

        Collection<File> files = directory.getFiles();

        assertEquals(2, files.size());
        assertThat(files)
                .containsExactlyInAnyOrder(file1, file2);
    }

    @Test
    void testGetFile() {
        File file1 = new File("file1", 100);
        File file2 = new File("file2", 200);

        directory.addFile(file1);
        directory.addFile(file2);

        File file = directory.getFile("file1")
                .orElseGet(Assertions::fail);

        assertSame(file1, file);
    }

    @Test
    void testGetFile_forNonexistentFile() {
        File file1 = new File("file1", 100);
        File file2 = new File("file2", 200);

        directory.addFile(file1);
        directory.addFile(file2);

        Optional<File> file = directory.getFile("file3");
        assertTrue(file.isEmpty());
    }

    @Test
    void testSetParent_synchronizesRelationship() {
        Directory parent = new Directory("parent");
        directory.setParent(parent);

        assertSame(parent, directory.getParent());
        assertThat(parent.getSubdirectories())
                .containsExactly(directory);
    }

    @Test
    void testSetNullParent_synchronizesRelationship() {
        Directory parent = new Directory("parent");
        directory.setParent(parent);

        assertSame(parent, directory.getParent());
        assertThat(parent.getSubdirectories())
                .containsExactly(directory);

        directory.setParent(null);

        assertNull(directory.getParent());
        assertTrue(parent.getSubdirectories().isEmpty());
    }

    @Test
    void testSetDifferentParent_synchronizesRelationship() {
        Directory parent1 = new Directory("parent1");
        directory.setParent(parent1);

        assertSame(parent1, directory.getParent());
        assertThat(parent1.getSubdirectories())
                .containsExactly(directory);

        Directory parent2 = new Directory("parent2");
        directory.setParent(parent2);

        assertSame(parent2, directory.getParent());
        assertTrue(parent1.getSubdirectories().isEmpty());
        assertThat(parent2.getSubdirectories())
                .containsExactly(directory);
    }

    @Test
    void testAddDirectory_synchronizesRelationship() {
        Directory subdirectory = new Directory("subdirectory");
        directory.addDirectory(subdirectory);

        assertSame(directory, subdirectory.getParent());
        assertThat(directory.getSubdirectories())
                .containsExactly(subdirectory);
    }

    @Test
    void testAddDirectory_synchronizesRelationship_withExistingParent() {
        Directory other = new Directory("other");

        Directory subdirectory = new Directory("subdirectory");
        other.addDirectory(subdirectory);

        assertSame(other, subdirectory.getParent());

        directory.addDirectory(subdirectory);

        assertSame(directory, subdirectory.getParent());
        assertThat(directory.getSubdirectories())
                .containsExactly(subdirectory);

        assertTrue(other.getSubdirectories().isEmpty());
    }

    @Test
    void testRemoveDirectory_synchronizesRelationship() {
        Directory subdirectory = new Directory("subdirectory");
        directory.addDirectory(subdirectory);

        assertSame(directory, subdirectory.getParent());
        assertThat(directory.getSubdirectories())
                .containsExactly(subdirectory);

        directory.removeDirectory("subdirectory");

        assertNull(subdirectory.getParent());
        assertTrue(directory.getSubdirectories().isEmpty());
    }

    @Test
    void testAddFile_synchronizesRelationship() {
        File file = new File("file", 100);
        directory.addFile(file);

        assertSame(directory, file.getParent());
        assertThat(directory.getFiles())
                .containsExactly(file);
    }

    @Test
    void testAddFile_synchronizesRelationship_withExistingParent() {
        Directory other = new Directory("other");

        File file = new File("file", 100);
        other.addFile(file);

        assertSame(other, file.getParent());

        directory.addFile(file);

        assertSame(directory, file.getParent());
        assertThat(directory.getFiles())
                .containsExactly(file);

        assertTrue(other.getFiles().isEmpty());
    }

    @Test
    void testRemoveFile_synchronizesRelationship() {
        File file = new File("file", 100);
        directory.addFile(file);

        assertSame(directory, file.getParent());
        assertThat(directory.getFiles())
                .containsExactly(file);

        directory.removeFile("file");

        assertNull(file.getParent());
        assertTrue(directory.getFiles().isEmpty());
    }
}
