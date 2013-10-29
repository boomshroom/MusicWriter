package net.pslice.song;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        Song.setKeySig("D");
        Song.setTempo();
        Song.setTimeSig();
        Song.setVerbose(true);
        Song.generate();
    }

    public static void returnComponents(int i, int v, int c, int b, int s, int o){
        if (Song.isVerbose){
            System.out.println("Generated " + i + " intros");
            System.out.println("Generated " + v + " verses");
            System.out.println("Generated " + c + " choruses");
            System.out.println("Generated " + b + " bridges");
            System.out.println("Generated " + s + " solos");
            System.out.println("Generated " + o + " outros");
        }
    }
}
