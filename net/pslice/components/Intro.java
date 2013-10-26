package net.pslice.components;

import net.pslice.basics.*;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Intro extends Info{

    public static int[] bars = {
            2, 4, 4, 8
    };
    public static int[] noteLength = {
            16, 16, 32, 32, 32, 64, 64
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
        System.out.println("============== Generated Intro (" + totalBars + " bars)");
    }
}


/*
**The following code is used to create standard-length chords following specific random structure
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



**The following code is used to generate (semi)melodic sequences rather than chord patterns.
    int[] chordScale = Scales.chordScale;

        int totalBars = bars[rand.nextInt(bars.length)];
        int totalBeats = totalBars * 32;
        int currentBeats = 0;
        List<Integer> sequence = new ArrayList<Integer>();

        while (currentBeats < totalBeats) {
            int note = chordScale[rand.nextInt(chordScale.length)];
            int length = noteLength[rand.nextInt(noteLength.length)];
            if (currentBeats + length <= totalBeats){
                //sequence.add(note);
                //sequence.add(length);
                SongWriter.noteChord(note, length, 127);
                currentBeats = currentBeats + length;
            }
        }
        //int[] ret = new int[sequence.size()];
        //for(int i = 0;i < ret.length;i++)
            //ret[i] = sequence.get(i);
        //SongWriter.noteSequenceFixedVelocity(ret, 127);
        */