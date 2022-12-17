package com.aoc.jdmcmahan.distresssignal;

import com.aoc.jdmcmahan.distresssignal.model.Packet;
import lombok.Builder;
import lombok.Singular;

import java.util.Collection;
import java.util.TreeSet;

public class PacketSet {

    private final TreeSet<Packet> packets = new TreeSet<>();

    @Builder
    public PacketSet(@Singular Collection<Packet> packets) {
        this.packets.addAll(packets);
    }

    public int indexOf(Packet packet) {
        if (!packets.contains(packet)) {
            return -1;
        }

        return packets.headSet(packet).size();
    }
}
