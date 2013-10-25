package net.pslice.components;

import net.pslice.basics.Scales;
import net.pslice.assembly.SongWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bridge {

    private static int[] scale = Scales.cMajorScale;
    public static int[] bars = {
            2, 2, 4, 4
    };
    public static int[] noteLength = {
            4, 4, 4, 8, 8, 16, 16, 32,
    };

    final static Random rand = new Random();

    public static void generate(){
        int totalBars = bars[rand.nextInt(bars.length)];
        int totalBeats = totalBars * 32;
        int currentBeats = 0;
        List<Integer> sequence = new ArrayList<Integer>();
        do {
            int note = scale[rand.nextInt(scale.length)];
            int length = noteLength[rand.nextInt(noteLength.length)];
            if (currentBeats + length <= totalBeats){
                sequence.add(note);
                sequence.add(length);
                currentBeats = currentBeats + length;
            }
        }  while (currentBeats < totalBeats);
        int[] ret = new int[sequence.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = sequence.get(i);
        SongWriter.noteSequenceFixedVelocity(ret, 127);
    }
}
