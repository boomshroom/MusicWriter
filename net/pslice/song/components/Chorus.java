package net.pslice.song.components;

import net.pslice.song.Song;
import net.pslice.song.assembly.Writer;
import net.pslice.song.scales.Chord;
import net.pslice.song.scales.Scales;

import java.util.*;

public class Chorus extends Song {

    public static int[] bars = {
            8, 8, 16, 16, 16
    };
    final static Random rand = new Random();

    private static int totalBars = bars[rand.nextInt(bars.length)];
    private static int totalBeats = totalBars * 64;

    public static int[] backgroundInfo;
    public static int[] melodyInfo;

    public static void generate(){
        addBackground();

        if (isVerbose){
            System.out.println("Generated Chorus (" + totalBars + " bars)");
            System.out.println("==============");
        }
    }

    public static void addBackground(){
        int[] noteLength = {
                32, 32, 32, 64, 64, 64, 64
        };
        int currentBeats = 0;
        int lastChord= -1;

        List<Integer> sequence = new ArrayList<Integer>();

        while (currentBeats < totalBeats){
            int length = noteLength[rand.nextInt(noteLength.length)];

            if (currentBeats + length > totalBeats)
                length = totalBeats - currentBeats;
            Chord.setRandomChord(lastChord);
            int newChord = Chord.getNewChord();

            sequence.add(newChord);
            sequence.add(length);

            Writer.noteChord(newChord, length, 127, 0);
            lastChord = newChord;
            currentBeats = currentBeats + length;
        }
        backgroundInfo = new int[sequence.size()];
        for(int i = 0;i < backgroundInfo.length;i++)
            backgroundInfo[i] = sequence.get(i);
        addMelody();
    }

    public static void addMelody(){
        int[] noteLength = {
                8, 8, 16, 16, 16, 32
        };

        List<Integer> sequence = new ArrayList<Integer>();

        for(int i = 0;i < backgroundInfo.length;i+=2){
            int currentBeats = 0;
            int chord = backgroundInfo[i];
            int chordLength = backgroundInfo[i+1];

            while (currentBeats < chordLength){
                Scales.setChord(chord);
                int note = Scales.noteScale[rand.nextInt(Scales.noteScale.length)];

                int length = noteLength[rand.nextInt(noteLength.length)];

                if (currentBeats + length > chordLength)
                    length = chordLength - currentBeats;

                sequence.add(note);
                sequence.add(length);

                Writer.noteOn(0, note, 127, 1);
                Writer.noteOff(length, note, 1);
                currentBeats = currentBeats + length;
            }
            melodyInfo = new int[sequence.size()];
            for(int q = 0;q < melodyInfo.length;q++)
                melodyInfo[q] = sequence.get(q);
        }
    }

    public static void add(){
        for(int i = 0;i < backgroundInfo.length;i+=2)
            Writer.noteChord(backgroundInfo[i], backgroundInfo[i + 1], 127, 0);
        for(int i = 0;i < melodyInfo.length;i+=2){
            Writer.noteOn(0, melodyInfo[i], 127, 1);
            Writer.noteOff(melodyInfo[i+1], melodyInfo[i], 1);
        }
        if (isVerbose){
            System.out.println("Added Chorus");
            System.out.println("==============");
        }
    }
}

/*
**The following code is used to generate (semi)melodic sequences rather than chord patterns.
     if (madeChorus){
            Writer.noteSequenceFixedVelocity(ret, 127);
        }
        else {
            int[] noteScale = Scales.noteScale;

            int totalBars = bars[rand.nextInt(bars.length)];
            int totalBeats = totalBars * 32;
            int currentBeats = 0;
            List<Integer> sequence = new ArrayList<Integer>();
            while (currentBeats < totalBeats) {
                int note = noteScale[rand.nextInt(noteScale.length)];
                int length = noteLength[rand.nextInt(noteLength.length)];
                if (currentBeats + length <= totalBeats){
                    sequence.add(note);
                    sequence.add(length);
                    Writer.noteChord(note, length, 127);
                    currentBeats = currentBeats + length;
                }
            }
            ret = new int[sequence.size()];
            for(int i = 0;i < ret.length;i++)
                ret[i] = sequence.get(i);
            Writer.noteSequenceFixedVelocity(ret, 127);
            madeChorus = true;
        }
*/
