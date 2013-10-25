package net.pslice.components;

import net.pslice.basics.*;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Intro {

    public static int[] bars = {
            2, 4, 4, 8
    };
    public static int[] noteLength = {
            4, 4, 8, 8, 8, 16, 16, 32, 32, 64
    };

    final static Random rand = new Random();

    public static void generate(){
        int[] scale = Scales.scale;

        int totalBars = bars[rand.nextInt(bars.length)];
        int totalBeats = totalBars * 32;
        int currentBeats = 0;
        List<Integer> sequence = new ArrayList<Integer>();

        while (currentBeats < totalBeats) {
            int note = scale[rand.nextInt(scale.length)];
            int length = noteLength[rand.nextInt(noteLength.length)];
            if (currentBeats + length <= totalBeats){
                sequence.add(note);
                sequence.add(length);
                currentBeats = currentBeats + length;
            }
        }
        int[] ret = new int[sequence.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = sequence.get(i);
        SongWriter.noteSequenceFixedVelocity(ret, 127);
    }
}
