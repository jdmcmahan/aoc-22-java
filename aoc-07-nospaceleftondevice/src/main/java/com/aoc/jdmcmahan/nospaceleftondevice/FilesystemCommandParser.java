package com.aoc.jdmcmahan.nospaceleftondevice;

import com.aoc.jdmcmahan.nospaceleftondevice.model.Directory;
import com.aoc.jdmcmahan.nospaceleftondevice.model.File;
import com.aoc.jdmcmahan.nospaceleftondevice.model.Filesystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesystemCommandParser {

    protected static final Pattern CD_COMMAND_PATTERN = Pattern.compile("^\\$?\\s*cd\\s(\\w+|/|\\.\\.)");
    protected static final Pattern LS_COMMAND_PATTERN = Pattern.compile("^\\$?\\s*ls");
    protected static final Pattern LS_DIR_OUTPUT_PATTERN = Pattern.compile("^dir\\s+(\\w+)");
    protected static final Pattern LS_FILE_OUTPUT_PATTERN = Pattern.compile("^(\\d+)\\s+([\\w.]+)");

    public Filesystem parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                Filesystem filesystem = new Filesystem(new Directory("/"));

                Directory currentDirectory = filesystem.root();
                boolean parsingLs = false;

                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher;

                    if (parsingLs) {
                        if ((matcher = LS_DIR_OUTPUT_PATTERN.matcher(line)).find()) {
                            String name = matcher.group(1);

                            currentDirectory.addDirectory(new Directory(name));
                            continue;
                        } else if ((matcher = LS_FILE_OUTPUT_PATTERN.matcher(line)).find()) {
                            long size = Long.parseLong(matcher.group(1));
                            String name = matcher.group(2);

                            currentDirectory.addFile(new File(name, size));
                            continue;
                        }
                    }

                    if (LS_COMMAND_PATTERN.matcher(line).find()) {
                        parsingLs = true;
                    } else if ((matcher = CD_COMMAND_PATTERN.matcher(line)).find()) {
                        String argument = matcher.group(1);

                        currentDirectory = switch (argument) {
                            case "/" -> filesystem.root();
                            case ".." -> currentDirectory.getParent();
                            default -> currentDirectory.getSubdirectory(argument)
                                    .orElseThrow(() -> new IllegalArgumentException(argument + ": No such directory"));
                        };
                    } else {
                        throw new IllegalArgumentException("Unrecognized command: " + line);
                    }
                }

                return filesystem;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }
}
