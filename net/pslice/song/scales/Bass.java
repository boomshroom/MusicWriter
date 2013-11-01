package net.pslice.song.scales;

import java.util.Random;

public class Bass extends Scales {

    public static int newNote;

    final static Random rand = new Random();

    public static void setRandomNote(int lastNote) {
        int first = noteScale[0];
        int second = noteScale[1];
        int fourth = noteScale[3];
        int fifth = noteScale[4];
        int octave = noteScale[7];

        int lowSeventh = first - 1;

        if (lastNote == -1) {
            int[] nextNote = {
                    first, first,
                    octave, octave,
                    fifth
            };
            newNote = nextNote[rand.nextInt(nextNote.length)];
        } else if (lastNote == first || lastNote == octave) {
            int[] nextNote = {
                    first, first, first,
                    fifth, fifth,
                    fourth,
                    second
            };
            newNote = nextNote[rand.nextInt(nextNote.length)];
        } else if (lastNote == second) {
            int[] nextChord = {
                    fifth, fifth, fifth,
                    fourth, fourth,
                    first, first,
                    second,
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastNote == fourth) {
            int[] nextChord = {
                    fifth, fifth, fifth,
                    second, second, second,
                    first, first,
                    fourth, fourth,
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastNote == fifth) {
            int[] nextChord = {
                    first, first, first, first,
                    fifth, fifth, fifth,
                    fourth, fourth, fourth,
                    second, second,
                    octave,
                    lowSeventh
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastNote == lowSeventh) {
            newNote = first;
        }
    }

    public static int getNewNote() {
        return newNote;
    }
}
