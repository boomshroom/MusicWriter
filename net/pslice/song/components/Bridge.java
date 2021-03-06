package net.pslice.song.components;

import net.pslice.song.Song;
import net.pslice.song.assembly.Writer;
import net.pslice.song.scales.Chord;
import net.pslice.song.scales.Melody;
import net.pslice.song.scales.Scales;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bridge extends Song {

    public static int[] bars = {
            2, 2, 4, 4
    };
    final static Random rand = new Random();

    private static int totalBars = bars[rand.nextInt(bars.length)];
    private static int totalBeats = totalBars * 64;

    public static int[] backgroundInfo;

    public static void generate() {
        addBackground();

        if (isVerbose) {
            System.out.println("Generated Bridge (" + totalBars + " bars)");
            System.out.println("==============");
        }
    }

    public static void addBackground() {
        int[] noteLength = {
                16, 32, 32, 32, 64, 64, 64, 64
        };
        int currentBeats = 0;
        int lastChord = -1;

        List<Integer> sequence = new ArrayList<Integer>();

        while (currentBeats < totalBeats) {
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
        for (int i = 0; i < backgroundInfo.length; i++)
            backgroundInfo[i] = sequence.get(i);
        addMelody();
    }

    public static void addMelody() {
        int[] noteLength = {
                8, 8, 8, 16, 16, 16, 32
        };
        int lastNote = -1;

        for (int i = 0; i < backgroundInfo.length; i += 2) {
            int currentBeats = 0;
            int chord = backgroundInfo[i];
            int chordLength = backgroundInfo[i + 1];

            while (currentBeats < chordLength) {
                Scales.setChord(chord);
                Melody.setRandomNote(lastNote);

                int note = Melody.getNewNote();

                int length = noteLength[rand.nextInt(noteLength.length)];

                if (currentBeats + length > chordLength)
                    length = chordLength - currentBeats;

                Writer.noteOn(0, note, 127, 1);
                Writer.noteOff(length, note, 1);
                lastNote = note;
                currentBeats = currentBeats + length;
            }
            lastNote = -1;
        }
    }
}
