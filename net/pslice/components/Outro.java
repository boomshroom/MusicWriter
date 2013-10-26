package net.pslice.components;

import net.pslice.basics.Chord;
import net.pslice.basics.Info;
import net.pslice.basics.Scales;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Outro extends Info {

    public static int[] bars = {
            2, 4, 4, 8
    };
    public static int[] noteLength = {
            8, 16, 16, 32, 32, 64
    };

    final static Random rand = new Random();

    public static void generate(){
        int totalBars = bars[rand.nextInt(bars.length)];
        int totalBeats = totalBars * 64;
        int currentBeats = 0;
        int lastChord= -1;

        Chord chord = new Chord();

        while (currentBeats < totalBeats){
            int length = noteLength[rand.nextInt(noteLength.length)];
            if (currentBeats + length > totalBeats)
                length = totalBeats - currentBeats;
            chord.setRandomChord(lastChord);
            int newChord = chord.getNewChord();

            if (isVerbose)
                System.out.println(newChord);

            SongWriter.noteChord(newChord, length, 127);
            lastChord = newChord;
            currentBeats = currentBeats + length;
        }
        System.out.println("============== Generated Outro (" + totalBars + " bars)");

        int finalNote = Chord.chordScale[0];
        SongWriter.noteChord(finalNote, 64, 127);
    }
}
