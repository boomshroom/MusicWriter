package net.pslice.song.assembly;

import net.pslice.song.Song;

public class Writer extends Song {

    public static void setProg(int a, int b, int c){
        int[] backgrounds = new int[3];
            backgrounds[0] = 0;
            backgrounds[1] = 0xC0;
            backgrounds[2] = a;
        songBackground.add(backgrounds);

        int[] melody = new int[3];
            melody[0] = 0;
            melody[1] = 0xC1;
            melody[2] = b;
        songMelody.add(melody);

        int[] bass = new int[3];
            bass[0] = 2;
            bass[1] = 0xC2;
            bass[2] = c;
        songBass.add(bass);
    }

    public static void noteOn(int delta, int note, int velocity, int channel){
        int[] data = new int[4];
        data[0] = delta;
        data[1] = 0x90+channel;
        data[2] = note;
        data[3] = velocity;
        if (channel == 0)
            songBackground.add(data);
        else if (channel == 1)
            songMelody.add(data);
        else if (channel == 2)
            songBass.add(data);
    }
    public static void noteOff(int delta, int note, int channel){
        int[] data = new int[4];
        data[0] = delta;
        data[1] = 0x80+channel;
        data[2] = note;
        data[3] = 0;
        if (channel == 0)
            songBackground.add(data);
        else if (channel == 1)
            songMelody.add(data);
        else if (channel == 2)
            songBass.add(data);
    }

    public static void noteChord(int baseNote, int duration, int velocity, int channel){
        //currently will only play a power chord (1,5,8) since they're the easiest to work with
        noteOn(0, baseNote, velocity, channel);
        noteOn(0, baseNote + 7, velocity, channel);
        noteOn(0, baseNote + 12, velocity, channel);

        noteOff(duration, baseNote, channel);
        noteOff(0, baseNote + 7, channel);
        noteOff(0, baseNote + 12, channel);
    }

    protected static byte[] convertToByte(int[] ints){
        int l = ints.length;
        byte[] out = new byte[ints.length];
        for (int i = 0; i < l; i++){
            out[i] = (byte) ints[i];
        }
        return out;
    }
}