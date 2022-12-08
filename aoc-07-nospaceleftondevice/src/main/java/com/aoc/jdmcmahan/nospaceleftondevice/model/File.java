package com.aoc.jdmcmahan.nospaceleftondevice.model;

import lombok.Getter;

@Getter
public class File extends AbstractFilesystemObject {

    private final long size;

    public File(String name, long size) {
        super(name);

        this.size = size;
    }

    public void setParent(Directory parent) {
        if (this.parent != parent) {
            if (this.parent != null) {
                this.parent.removeFile(this.name);
            }

            this.parent = parent;

            if (parent != null) {
                parent.addFile(this);
            }
        }

        this.invalidatePath();
    }
}
