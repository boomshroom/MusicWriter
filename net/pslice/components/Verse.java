package net.pslice.components;

import net.pslice.basics.Chord;
import net.pslice.basics.Scales;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Verse {

    private static boolean madeVerse = false;

    public static int[] bars = {
            8, 8, 16, 16, 16, 32
    };
    public static int[] noteLength = {
            4, 4, 8, 8, 8, 16, 16, 32, 32
    };
    public static int[] ret;

    final static Random rand = new Random();

    public static void generate(){
        if (madeVerse){
            for(int i = 0;i < ret.length;i++)
                SongWriter.noteChord(ret[i], 16, 127);
        }
        else {
            int totalBars = bars[rand.nextInt(bars.length)];
            int totalBeats = totalBars * 16;
            int currentBeats = 0;
            int lastChord= -1;

            List<Integer> sequence = new ArrayList<Integer>();

            Chord chord = new Chord();

            while (currentBeats < totalBeats){
                chord.setRandomChord(lastChord);
                int newChord = chord.getNewChord();
                sequence.add(newChord);
                SongWriter.noteChord(newChord, 16, 127);
                lastChord = newChord;
                currentBeats = currentBeats + 8;
            }
            ret = new int[sequence.size()];
            for(int i = 0;i < ret.length;i++)
                ret[i] = sequence.get(i);
            madeVerse = true;
        }
    }
}
