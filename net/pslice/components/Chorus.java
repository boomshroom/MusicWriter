package net.pslice.components;

import net.pslice.basics.*;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Chorus extends Info {

    private static boolean madeChorus = false;

    public static int[] bars = {
            8, 8, 16, 16, 16
    };
    public static int[] noteLength = {
            8, 8, 8, 16, 16, 16, 32
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
        if (madeChorus){
            for(int i = 0;i < ret.length;i+=2)
                SongWriter.noteChord(ret[i], ret[i+1], 127);
            System.out.println("============== Generated same Chorus");
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
            System.out.println("============== Generated new Chorus (" + totalBars + " bars)");
            madeChorus = true;
        }
    }
}

/*
**The following code is used to generate (semi)melodic sequences rather than chord patterns.
     if (madeChorus){
            SongWriter.noteSequenceFixedVelocity(ret, 127);
        }
        else {
            int[] chordScale = Scales.chordScale;

            int totalBars = bars[rand.nextInt(bars.length)];
            int totalBeats = totalBars * 32;
            int currentBeats = 0;
            List<Integer> sequence = new ArrayList<Integer>();
            while (currentBeats < totalBeats) {
                int note = chordScale[rand.nextInt(chordScale.length)];
                int length = noteLength[rand.nextInt(noteLength.length)];
                if (currentBeats + length <= totalBeats){
                    sequence.add(note);
                    sequence.add(length);
                    SongWriter.noteChord(note, length, 127);
                    currentBeats = currentBeats + length;
                }
            }
            ret = new int[sequence.size()];
            for(int i = 0;i < ret.length;i++)
                ret[i] = sequence.get(i);
            SongWriter.noteSequenceFixedVelocity(ret, 127);
            madeChorus = true;
        }
*/
