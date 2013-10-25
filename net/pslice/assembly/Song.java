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
        int intros = 0;
        int verses = 0;
        int choruses = 0;
        int bridges = 0;
        int solos = 0;
        int outros = 0;

        SongComponent song = new SongComponent();
        song.setNextComponent("None");

        while (outros != 1) {
            if (intros == 0
                    && song.getNewComponent().equals("Intro")){
                Intro.generate();
                intros = intros + 1;
                song.setNextComponent("Intro");
            }
            else if (verses <= 3
                    && song.getNewComponent().equals("Verse")){
                Verse.generate();
                verses = verses + 1;
                song.setRandomComponent("Verse");
            }
            else if (choruses <= 3
                    && song.getNewComponent().equals("Chorus")){
                Chorus.generate();
                choruses = choruses + 1;
                song.setRandomComponent("Chorus");
            }
            else if (bridges <= 2
                    && song.getNewComponent().equals("Bridge")){
                Bridge.generate();
                bridges = bridges + 1;
                song.setRandomComponent("Bridge");
            }
            else if (solos == 0
                    && song.getNewComponent().equals("Solo")){
                Solo.generate();
                solos = solos + 1;
                song.setRandomComponent("Solo");
            }
            else if (outros == 0
                    && song.getNewComponent().equals("Outro")){
                Outro.generate();
                outros = outros + 1;
            }
            else{
                song.setRandomComponent("Generic");
            }
        }
        FileSaver.saveFile("Demo.mid");
        Launcher.returnComponents(intros, verses, choruses, bridges, solos, outros);
    }
}
