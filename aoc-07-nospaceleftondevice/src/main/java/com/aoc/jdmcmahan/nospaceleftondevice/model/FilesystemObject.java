package com.aoc.jdmcmahan.nospaceleftondevice.model;

public interface FilesystemObject {

    Directory getParent();

    String getPath();

    String getName();

    long getSize();
}
