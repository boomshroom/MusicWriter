package net.pslice.assembly;

import java.util.Vector;

public class SongWriter {

    private static Vector<int[]> songFile = Song.songFile;

    public static void noteOn(int delta, int note, int velocity){
        int[] data = new int[4];
        data[0] = delta;
        data[1] = 0x90;
        data[2] = note;
        data[3] = velocity;
        songFile.add(data);
    }
    public static void noteOff(int delta, int note){
        int[] data = new int[4];
        data[0] = delta;
        data[1] = 0x80;
        data[2] = note;
        data[3] = 0;
        songFile.add(data);
    }

    public static void noteSequenceFixedVelocity(int[] sequence, int velocity){
        boolean lastWasRest = false;
        int restDelta = 0;
        for (int i = 0; i < sequence.length; i+=2){
            int note = sequence[i];
            int duration = sequence[i+1];
            if (note < 0){
                restDelta += duration;
                lastWasRest = true;
            }
            else {
                if (lastWasRest){
                    noteOn(restDelta, note, velocity);
                    noteOff(duration, note);
                }
                else {
                    noteOn(0, note, velocity);
                    noteOff(duration, note);
                }
                restDelta = 0;
                lastWasRest = false;
            }
        }
    }
}
