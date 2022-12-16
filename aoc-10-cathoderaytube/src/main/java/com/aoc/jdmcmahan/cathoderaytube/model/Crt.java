package com.aoc.jdmcmahan.cathoderaytube.model;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.OutputStream;

@RequiredArgsConstructor
public class Crt {

    private final char[][] pixels;

    public Crt(Cpu cpu, int spriteLength, int width, int height) {
        this.pixels = new char[height][width];

        cpu.addListener((cycle, x, signalStrength) -> {
            int pixelX = ((cycle - 1) % width);
            int pixelY = ((cycle - 1) / width) % height;

            int spriteStart = x - 1;
            int spriteEnd = spriteStart + spriteLength - 1;

            if (pixelX >= spriteStart && pixelX <= spriteEnd) {
                pixels[pixelY][pixelX] = '#';
            } else {
                pixels[pixelY][pixelX] = '.';
            }
        });
    }

    public void print(OutputStream out) throws IOException {
        for (char[] row : pixels) {
            for (char c : row) {
                out.write(c);
            }

            out.write('\n');
        }
    }
}
