package com.aoc.jdmcmahan.nospaceleftondevice.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public abstract class AbstractFilesystemObject implements FilesystemObject {

    protected final String name;

    protected Directory parent;

    private String path;

    @Override
    public String getPath() {
        if (path == null) {
            if (parent == null) {
                return name;
            }

            String parentPath = parent.getPath();
            return parentPath + (parentPath.endsWith("/")
                    ? name
                    : "/" + name);
        }

        return path;
    }

    @Override
    public String toString() {
        return this.getPath();
    }

    protected void invalidatePath() {
        this.path = null;
    }
}
