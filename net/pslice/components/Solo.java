package net.pslice.components;

import net.pslice.basics.Chord;
import net.pslice.basics.Scales;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solo {

    public static int[] bars = {
            4, 8, 16, 16
    };
    public static int[] noteLength = {
            2, 2, 4, 4, 4, 8, 8, 16
    };

    final static Random rand = new Random();

    public static void generate(){
        int totalBars = bars[rand.nextInt(bars.length)];
        int totalBeats = totalBars * 16;
        int currentBeats = 0;
        int lastChord= -1;

        Chord chord = new Chord();

        while (currentBeats < totalBeats){
            chord.setRandomChord(lastChord);
            int newChord = chord.getNewChord();
            SongWriter.noteChord(newChord, 16, 127);
            lastChord = newChord;
            currentBeats = currentBeats + 8;
        }
    }
}
