package net.pslice.assembly;

import net.pslice.components.*;

import java.io.IOException;
import java.util.*;

public class Song {

    public static Vector<int[]> songFile;

    public Song(){
        songFile = new Vector<int[]>();
    }

    static final int header[] = new int[]{
            0x4d, 0x54, 0x68, 0x64, 0x00, 0x00, 0x00, 0x06,
            0x00, 0x00,
            0x00, 0x01,
            0x00, 0x10,
            0x4d, 0x54, 0x72, 0x6B
    };

    static final int footer[] = new int[]{
            0x01, 0xFF, 0x2F, 0x00
    };

    protected static byte[] intArrayToByteArray(int[] ints){
        int l = ints.length;
        byte[] out = new byte[ints.length];
        for (int i = 0; i < l; i++){
            out[i] = (byte) ints[i];
        }
        return out;
    }

    public static void generate() throws IOException {
        int i = 0;  //# of intros (Will always end up as 1)
        int v = 0;  //# of verses (Always at least one, usually is 3-4)
        int c = 0;  //# of choruses (Should always be at least one, usually is 3-4)
        int b = 0;  //# of bridges (Anywhere from 0-3 usually)
        int s = 0;  //# of solos (Either 0 or 1)
        int o = 0;  //# of outros (Will always end up as 1)

        SongComponent song = new SongComponent();
        song.setNextComponent("None");

        while (o != 1) {
            if (i == 0
                    && song.getNewComponent().equals("Intro")){
                Intro.generate();
                i = i + 1;
                song.setNextComponent("Intro");
            }
            else if (v <= 2
                    && song.getNewComponent().equals("Verse")){
                Verse.generate();
                v = v + 1;
                song.setRandomComponent("Verse");
            }
            else if (c <= 2
                    && song.getNewComponent().equals("Chorus")){
                Chorus.generate();
                c = c + 1;
                song.setRandomComponent("Chorus");
            }
            else if (b <= 1
                    && song.getNewComponent().equals("Bridge")){
                Bridge.generate();
                b = b + 1;
                song.setRandomComponent("Bridge");
            }
            else if (s == 0
                    && song.getNewComponent().equals("Solo")){
                Solo.generate();
                s = s + 1;
                song.setRandomComponent("Solo");
            }
            else if (o == 0
                    && song.getNewComponent().equals("Outro")){
                Outro.generate();
                o = o + 1;
            }
            else{
                song.setRandomComponent("Generic");
            }
        }
        FileSaver.saveFile("Demo.mid");
        Launcher.returnComponents(i, v, c, b, s, o);
    }
}
