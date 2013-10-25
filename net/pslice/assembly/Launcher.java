package net.pslice.assembly;

import net.pslice.basics.Info;

import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException {
        Info.setKeySig("C");
        Info.setTempo();
        Info.setTimeSig();
        Song song = new Song();
        song.generate();
    }

    public static void returnComponents(int i, int v, int c, int b, int s, int o){
        System.out.println("Generated " + i + " intros");
        System.out.println("Generated " + v + " verses");
        System.out.println("Generated " + c + " choruses");
        System.out.println("Generated " + b + " bridges");
        System.out.println("Generated " + s + " solos");
        System.out.println("Generated " + o + " outros");
    }
}
