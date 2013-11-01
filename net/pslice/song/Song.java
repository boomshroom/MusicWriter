package net.pslice.song;

import net.pslice.song.assembly.FileSaver;
import net.pslice.song.assembly.SongComponent;
import net.pslice.song.assembly.Writer;
import net.pslice.song.components.*;
import net.pslice.song.scales.Scales;

import java.io.IOException;
import java.util.Vector;

public class Song {

    public static Vector<int[]> songBackground;
    public static Vector<int[]> songMelody;
    public static Vector<int[]> songBass;

    private static int[] tempo;
    private static int[] timeSig;
    private static int[] keySig;

    public static boolean isVerbose;

    private static String title;

    public static void setVerbose(boolean args) {
        isVerbose = args;
    }

    public static void setTempo() {
        tempo = new int[]{
                0x00, 0xFF, 0x51, 0x03,
                0x07, 0xA1, 0x20
        };
    }

    public static int[] getTempo() {
        return tempo;
    }

    public static void setTimeSig() {
        timeSig = new int[]{
                0x00, 0xFF, 0x58, 0x04,
                0x04,
                0x02,
                0x30,
                0x08
        };
    }

    public static int[] getTimeSig() {
        return timeSig;
    }

    public static void setKeySig(String key) {
        Scales.setKey(key);
        if (isVerbose)
            System.out.println("In the key of " + key + "...");
        int songKey = 0x00;
        if (key.equals("C"))
            songKey = 0x00;
        keySig = new int[]{
                0x00, 0xFF, 0x59, 0x02,
                songKey,
                0x00
        };
    }

    public static int[] getKeySig() {
        return keySig;
    }

    public static void setTitle(String title){
        Song.title = title;
    }

    public static void generate() throws IOException {
        songBackground = new Vector<int[]>();
        songMelody = new Vector<int[]>();
        songBass = new Vector<int[]>();


        Writer.setProg(Launcher.bgi, Launcher.mdi, Launcher.bsi);

        SongComponent song = new SongComponent();

        int i = 0;  //# of intros (Will always end up as 1)
        int v = 0;  //# of verses (Always at least one, usually is 2-3)
        int c = 0;  //# of choruses (Should always be at least one, usually is 2-3)
        int b = 0;  //# of bridges (Anywhere from 0-2 usually)
        int s = 0;  //# of solos (Either 0 or 1)
        int o = 0;  //# of outros (Will always end up as 1)

        while (o != 1) {

            if (song.getNewComponent().equals("Intro") && (i == 0)) {
                Intro.generate();
                i += 1;
                song.setNextComponent("Intro");
            } else if (song.getNewComponent().equals("Verse") && (v <= 2)) {
                if (v == 0)
                    Verse.generate();
                else
                    Verse.add();
                v += 1;
                song.setRandomComponent("Verse");
            } else if (song.getNewComponent().equals("Chorus") && (c <= 2) && (v >= 1)) {
                if (c == 0)
                    Chorus.generate();
                else
                    Chorus.add();
                c += 1;
                song.setRandomComponent("Chorus");
            } else if (song.getNewComponent().equals("Bridge") && (b <= 2)) {
                Bridge.generate();
                b += 1;
                song.setRandomComponent("Bridge");
            } else if (song.getNewComponent().equals("Solo") && (s == 0) && (c >= 1)) {
                Solo.generate();
                s += 1;
                song.setRandomComponent("Solo");
            } else if (song.getNewComponent().equals("Outro") && o == 0 && c >= 1) {
                Outro.generate();
                o += 1;
            } else
                song.setRandomComponent("Generic");
        }
        FileSaver.saveFile(title + ".mid");
        Launcher.returnComponents(i, v, c, b, s, o);
    }
}
