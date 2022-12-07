package com.aoc.jdmcmahan.nospaceleftondevice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class File implements FilesystemObject {

    private final String name;
    private final long size;

    private Directory parent;

    public void setParent(Directory parent) {
        if (this.parent != parent) {
            if (this.parent != null) {
                this.parent.removeFile(this);
            }

            this.parent = parent;

            if (parent != null) {
                parent.addFile(this);
            }
        }
    }
}
