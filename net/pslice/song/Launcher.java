package net.pslice.song;

import java.io.IOException;

public class Launcher {

    public static int bgi = 0;
    public static int mdi = 0;
    public static int bsi = 0;

    public static void main(String[] args){
        Window.newWindow();
    }

    public static void beginSong(String songTitle, boolean isVerbose, String key, int bgi, int mdi) throws IOException {
        Launcher.bgi = bgi;
        Launcher.mdi = mdi;

        Song.setKeySig(key);
        Song.setTitle(songTitle);
        Song.setTempo();
        Song.setTimeSig();
        Song.setVerbose(isVerbose);
        Song.generate();
    }

    public static void returnComponents(int i, int v, int c, int b, int s, int o) {
        if (Song.isVerbose) {
            System.out.println("Generated " + i + " intros");
            System.out.println("Generated " + v + " verses");
            System.out.println("Generated " + c + " choruses");
            System.out.println("Generated " + b + " bridges");
            System.out.println("Generated " + s + " solos");
            System.out.println("Generated " + o + " outros");
            System.out.println();
            System.out.println("Successfully generated new song!");
        }
    }
}
