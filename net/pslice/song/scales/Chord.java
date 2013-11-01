package net.pslice.song.scales;

import java.util.Random;

public class Chord extends Scales {

    public static int[] chordScale = Scales.scale;
    public static int newChord;

    final static Random rand = new Random();

    public static void setRandomChord(int lastChord) {
        int first = chordScale[0];
        int second = chordScale[1];
        int third = chordScale[2];
        int fourth = chordScale[3];
        int fifth = chordScale[4];
        int sixth = chordScale[5];
        int seventh = chordScale[6];
        int octave = chordScale[7];

        int lowSeventh = first - 1;

        if (lastChord == -1)
            newChord = first;
        else if (lastChord == first || lastChord == octave) {
            int[] nextChord = {
                    fifth, fifth, fifth, fifth,
                    fourth, fourth, fourth,
                    third, third,
                    second, second,
                    first,
                    sixth,
                    octave
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == second) {
            int[] nextChord = {
                    fifth, fifth, fifth,
                    fourth, fourth,
                    first, first,
                    third,
                    second,
                    lowSeventh
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == third) {
            int[] nextChord = {
                    first, first,
                    fifth, fifth,
                    fourth,
                    second
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == fourth) {
            int[] nextChord = {
                    fifth, fifth, fifth,
                    second, second, second,
                    first, first,
                    third,
                    fourth,
                    sixth,
                    octave
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == fifth) {
            int[] nextChord = {
                    first, first, first, first,
                    fourth, fourth, fourth,
                    second, second,
                    octave, octave,
                    lowSeventh, lowSeventh,
                    seventh,
                    sixth
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == sixth) {
            int[] nextChord = {
                    fourth, fourth, fourth,
                    fifth, fifth,
                    third,
                    second,
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == seventh) {
            int[] nextChord = {
                    octave, octave,
                    second, second,
                    lowSeventh, lowSeventh,
                    fifth,
                    first
            };
            newChord = nextChord[rand.nextInt(nextChord.length)];
        } else if (lastChord == lowSeventh) {
            newChord = first;
        }
    }

    public static int getNewChord() {
        return newChord;
    }
}
