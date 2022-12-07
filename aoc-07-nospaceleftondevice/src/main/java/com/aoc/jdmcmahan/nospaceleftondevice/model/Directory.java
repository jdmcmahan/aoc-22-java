package com.aoc.jdmcmahan.nospaceleftondevice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public class Directory implements FilesystemObject {

    private final String name;
    private final Map<String, Directory> subdirectories = new HashMap<>();
    private final Map<String, File> files = new HashMap<>();

    private Directory parent;

    @Override
    public long getSize() {
        return Stream.concat(subdirectories.values().stream(), files.values().stream())
                .mapToLong(FilesystemObject::getSize)
                .sum();
    }

    public Collection<Directory> getSubdirectories() {
        return Collections.unmodifiableCollection(subdirectories.values());
    }

    public Optional<Directory> getSubdirectory(String name) {
        return Optional.ofNullable(subdirectories.get(name));
    }

    public Collection<File> getFiles() {
        return Collections.unmodifiableCollection(files.values());
    }

    public Optional<File> getFile(String name) {
        return Optional.ofNullable(files.get(name));
    }

    public void setParent(Directory parent) {
        if (this.parent != parent) {
            if (this.parent != null) {
                this.parent.removeDirectory(this);
            }

            this.parent = parent;

            if (parent != null) {
                parent.addDirectory(this);
            }
        }
    }

    public void addDirectory(Directory directory) {
        Directory existing = subdirectories.get(directory.getName());
        if (existing != null) {
            if (directory == existing) {
                return;
            }

            throw new IllegalArgumentException("Directory " + directory.getName() + " already exists in directory " + this.name);
        }

        subdirectories.put(directory.getName(), directory);
        directory.setParent(this);
    }

    public void removeDirectory(Directory directory) {
        Directory existing = subdirectories.get(directory.getName());
        if (existing != directory) {
            return;
        }

        subdirectories.remove(directory.getName());
        directory.setParent(null);
    }

    public void addFile(File file) {
        File existing = files.get(file.getName());
        if (existing != null) {
            if (file == existing) {
                return;
            }

            throw new IllegalArgumentException("File " + file.getName() + " already exists in directory " + this.name);
        }

        files.put(file.getName(), file);
        file.setParent(this);
    }

    public void removeFile(File file) {
        File existing = files.get(file.getName());
        if (existing != file) {
            return;
        }

        files.remove(file.getName());
        file.setParent(null);
    }
}
