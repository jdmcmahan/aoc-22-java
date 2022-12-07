package com.aoc.jdmcmahan.nospaceleftondevice.model;

public interface FilesystemObject {

    Directory getParent();

    String getName();

    long getSize();
}
