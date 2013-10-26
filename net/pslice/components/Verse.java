package net.pslice.components;

import net.pslice.basics.Chord;
import net.pslice.basics.Scales;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Verse extends Intro{

    private static boolean madeVerse = false;

    private static int[] scale = Scales.cMajorScale;
    public static int[] bars = {
            8, 8, 16, 16, 16, 32
    };
    public static int[] noteLength = {
            4, 4, 8, 8, 8, 16, 16, 32, 32
    };
    public static int[] ret;

    final static Random rand = new Random();

    public static void generate(){
        int totalBars = bars[rand.nextInt(bars.length)];
        int totalBeats = totalBars * 64;
        int currentBeats = 0;
        int lastChord= -1;

        List<Integer> sequence = new ArrayList<Integer>();

        Chord chord = new Chord();
        if (madeVerse){
            for(int i = 0;i < ret.length;i+=2)
                SongWriter.noteChord(ret[i], ret[i+1], 127);
            System.out.println("============== Generated same Verse");
        }
        else{
            while (currentBeats < totalBeats){
                int length = noteLength[rand.nextInt(noteLength.length)];
                if (currentBeats + length > totalBeats)
                    length = totalBeats - currentBeats;
                chord.setRandomChord(lastChord);
                int newChord = chord.getNewChord();

                if (isVerbose)
                    System.out.println(newChord);

                sequence.add(newChord);
                sequence.add(length);

                SongWriter.noteChord(newChord, length, 127);
                lastChord = newChord;
                currentBeats = currentBeats + length;
            }
            ret = new int[sequence.size()];
            for(int i = 0;i < ret.length;i++)
                ret[i] = sequence.get(i);
            System.out.println("============== Generated new Verse (" + totalBars + " bars)");
            madeVerse = true;
        }
    }
}
