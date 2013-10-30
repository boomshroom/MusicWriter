package net.pslice.song.scales;

import java.util.Random;

public class Melody extends Scales{

    public static int newNote;

    final static Random rand = new Random();

    public static void setRandomNote(int lastNote){
        int first = noteScale[0];
        int second = noteScale[1];
        int third = noteScale[2];
        int fourth = noteScale[3];
        int fifth = noteScale[4];
        int sixth = noteScale[5];
        int seventh = noteScale[6];
        int octave = noteScale[7];

        int lowSeventh = first - 1;

        if (lastNote == -1){
            int[] nextNote = {
                    first, first,
                    octave, octave,
                    fifth
            };
        newNote = nextNote[rand.nextInt(nextNote.length)];
        }
        else if (lastNote == first || lastNote == octave){
            int[] nextNote = {
                    fifth, fifth, fifth, fifth,
                    fourth, fourth, fourth,
                    third, third,
                    second, second,
                    first,
                    sixth,
                    octave
            };
            newNote = nextNote[rand.nextInt(nextNote.length)];
        }
        else if (lastNote == second){
            int[] nextChord = {
                    fifth, fifth, fifth,
                    fourth, fourth,
                    first, first,
                    third,
                    second,
                    lowSeventh
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        }
        else if (lastNote == third){
            int[] nextChord = {
                    first, first,
                    fifth, fifth,
                    fourth,
                    second
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        }
        else if (lastNote == fourth){
            int[] nextChord = {
                    fifth, fifth, fifth,
                    second, second, second,
                    first, first,
                    third,
                    fourth,
                    sixth,
                    octave
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        }
        else if (lastNote == fifth){
            int[] nextChord = {
                    first, first, first, first,
                    fourth, fourth, fourth,
                    second, second,
                    octave, octave,
                    lowSeventh, lowSeventh,
                    seventh,
                    sixth
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        }
        else if (lastNote == sixth){
            int[] nextChord = {
                    fourth, fourth, fourth,
                    fifth, fifth,
                    third,
                    second,
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        }
        else if (lastNote == seventh){
            int[] nextChord = {
                    octave, octave,
                    second, second,
                    lowSeventh, lowSeventh,
                    fifth,
                    first
            };
            newNote = nextChord[rand.nextInt(nextChord.length)];
        }
        else if (lastNote == lowSeventh){
            newNote = first;
        }
    }
    public static int getNewNote(){
        return newNote;
    }
}
