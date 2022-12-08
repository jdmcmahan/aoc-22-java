package com.aoc.jdmcmahan.nospaceleftondevice;

import com.aoc.jdmcmahan.nospaceleftondevice.model.Directory;
import com.aoc.jdmcmahan.nospaceleftondevice.model.File;
import com.aoc.jdmcmahan.nospaceleftondevice.model.Filesystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilesystemCommandParserTest {

    @Test
    void testParse() throws IOException {
        String input = """
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """;

        FilesystemCommandParser parser = new FilesystemCommandParser();
        Filesystem filesystem = parser.parse(new ByteArrayInputStream(input.getBytes()));

        Directory root = filesystem.root();
        assertEquals("/", root.getName());

        Directory a = root.getSubdirectory("a")
                .orElseGet(Assertions::fail);

        assertEquals("a", a.getName());

        Directory d = root.getSubdirectory("d")
                .orElseGet(Assertions::fail);

        assertEquals("d", d.getName());

        File b = root.getFile("b.txt")
                        .orElseGet(Assertions::fail);

        assertEquals("b.txt", b.getName());
        assertEquals(14848514L, b.getSize());

        File c = root.getFile("c.dat")
                .orElseGet(Assertions::fail);

        assertEquals("c.dat", c.getName());
        assertEquals(8504156L, c.getSize());

        Directory e = a.getSubdirectory("e")
                .orElseGet(Assertions::fail);

        assertEquals("e", e.getName());

        File f = a.getFile("f")
                .orElseGet(Assertions::fail);

        assertEquals("f", f.getName());
        assertEquals(29116L, f.getSize());

        File g = a.getFile("g")
                .orElseGet(Assertions::fail);

        assertEquals("g", g.getName());
        assertEquals(2557L, g.getSize());

        File h = a.getFile("h.lst")
                .orElseGet(Assertions::fail);

        assertEquals("h.lst", h.getName());
        assertEquals(62596L, h.getSize());

        File i = e.getFile("i")
                .orElseGet(Assertions::fail);

        assertEquals("i", i.getName());
        assertEquals(584L, i.getSize());
    }
}
