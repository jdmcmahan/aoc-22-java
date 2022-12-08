package com.aoc.jdmcmahan.nospaceleftondevice.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Directory extends AbstractFilesystemObject {

    private final Map<String, Directory> subdirectories = new HashMap<>();
    private final Map<String, File> files = new HashMap<>();

    private long size = 0;

    public Directory(String name) {
        super(name);
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
                this.parent.removeDirectory(this.name);
            }

            this.parent = parent;

            if (parent != null) {
                parent.addDirectory(this);
            }
        }

        this.invalidatePath();
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

        this.descendantObjectAdded(directory);
    }

    public void removeDirectory(String name) {
        Directory directory = subdirectories.remove(name);
        if (directory == null) {
            return;
        }

        directory.setParent(null);

        this.descendantObjectRemoved(directory);
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

        this.descendantObjectAdded(file);
    }

    public void removeFile(String name) {
        File file = files.remove(name);
        if (file == null) {
            return;
        }

        file.setParent(null);

        this.descendantObjectRemoved(file);
    }

    protected void descendantObjectAdded(FilesystemObject object) {
        this.size += object.getSize();

        if (parent != null) {
            parent.descendantObjectAdded(object);
        }
    }

    protected void descendantObjectRemoved(FilesystemObject object) {
        this.size -= object.getSize();

        if (parent != null) {
            parent.descendantObjectRemoved(object);
        }
    }

    @Override
    protected void invalidatePath() {
        super.invalidatePath();

        this.getFiles().forEach(File::invalidatePath);
        this.getSubdirectories().forEach(Directory::invalidatePath);
    }
}
