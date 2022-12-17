package com.aoc.jdmcmahan.distresssignal;

import com.aoc.jdmcmahan.distresssignal.model.IntegerPacket;
import com.aoc.jdmcmahan.distresssignal.model.ListPacket;
import com.aoc.jdmcmahan.distresssignal.model.Packet;
import com.aoc.jdmcmahan.distresssignal.model.PacketPair;
import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PacketPairParser {

    public List<PacketPair> parse(InputStream input) throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(input))) {
            try {
                List<PacketPair> pairs = new LinkedList<>();
                String line1, line2;

                while ((line1 = reader.readLine()) != null && (line2 = reader.readLine()) != null) {
                    Packet left = this.parse(line1);
                    Packet right = this.parse(line2);

                    pairs.add(new PacketPair(pairs.size() + 1, left, right));

                    reader.readLine();
                }

                return pairs;
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("Error at line " + reader.getLineNumber() + ": " + e.getMessage(), e);
            }
        }
    }

    protected Packet parse(String data) {
        return data.startsWith("[")
                ? this.parseListPacket(new JSONArray(data))
                : this.parseIntegerPacket(Integer.parseInt(data));
    }

    protected ListPacket parseListPacket(JSONArray json) {
        if (json.isEmpty()) {
            return new ListPacket(Collections.emptyList());
        }

        ListPacket.ListPacketBuilder builder = ListPacket.builder();
        for (int i = 0; i < json.length(); i++) {
            JSONArray childArray = json.optJSONArray(i);
            if (childArray != null) {
                builder.content(this.parseListPacket(childArray));
            } else {
                builder.content(this.parseIntegerPacket(json.getInt(i)));
            }
        }

        return builder.build();
    }

    protected IntegerPacket parseIntegerPacket(int value) {
        return new IntegerPacket(value);
    }
}
