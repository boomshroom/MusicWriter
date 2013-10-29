package net.pslice.song.assembly;

import net.pslice.song.Song;

import java.io.*;

public class FileSaver extends Writer{

    protected static final int[] header = new int[]{
            0x4d, 0x54, 0x68, 0x64, 0x00, 0x00, 0x00, 0x06,
            0x00, 0x01,
            0x00, 0x02,
            0x00, 0x10,
    };
    protected static final int[] trackHeader = new int[]{
            0x4d, 0x54, 0x72, 0x6B
    };

    protected static final int[] footer = new int[]{
            0x01, 0xFF, 0x2F, 0x00
    };

    public static void saveFile(String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);

        fos.write(convertToByte(header));

        fos.write(convertToByte(trackHeader));
        int backgroundSize = Song.getTempo().length + Song.getKeySig().length + Song.getTimeSig().length + footer.length;

        for (int i = 0; i < songBackground.size(); i++)
            backgroundSize += songBackground.elementAt(i).length;

        int backgroundHigh = backgroundSize / 256;
        int backgroundLow = backgroundSize - (backgroundHigh * 256);
        fos.write((byte) 0);
        fos.write((byte) 0);
        fos.write((byte) backgroundHigh);
        fos.write((byte) backgroundLow);

        fos.write(convertToByte(Song.getTempo()));
        fos.write(convertToByte(Song.getKeySig()));
        fos.write(convertToByte(Song.getTimeSig()));

        for (int i = 0; i < songBackground.size(); i++){
            fos.write(convertToByte(songBackground.elementAt(i)));
        }
        fos.write(convertToByte(footer));

        fos.write(convertToByte(trackHeader));
        int melodySize = Song.getTempo().length + Song.getKeySig().length + Song.getTimeSig().length + footer.length;

        for (int i = 0; i < songMelody.size(); i++)
            melodySize += songMelody.elementAt(i).length;

        int melodyHigh = melodySize / 256;
        int melodyLow = melodySize - (melodyHigh * 256);
        fos.write((byte) 0);
        fos.write((byte) 0);
        fos.write((byte) melodyHigh);
        fos.write((byte) melodyLow);

        fos.write(convertToByte(Song.getTempo()));
        fos.write(convertToByte(Song.getKeySig()));
        fos.write(convertToByte(Song.getTimeSig()));

        for (int i = 0; i < songMelody.size(); i++){
            fos.write(convertToByte(songMelody.elementAt(i)));
        }
        fos.write(convertToByte(footer));



        //Bass track remains empty for now, I'll add it in later...
        fos.write(convertToByte(trackHeader));
        int bassSize = Song.getTempo().length + Song.getKeySig().length + Song.getTimeSig().length + footer.length;

        for (int i = 0; i < songBass.size(); i++)
            bassSize += songBass.elementAt(i).length;

        int bassHigh = bassSize / 256;
        int bassLow = bassSize - (bassHigh * 256);
        fos.write((byte) 0);
        fos.write((byte) 0);
        fos.write((byte) bassHigh);
        fos.write((byte) bassLow);

        fos.write(convertToByte(Song.getTempo()));
        fos.write(convertToByte(Song.getKeySig()));
        fos.write(convertToByte(Song.getTimeSig()));

        for (int i = 0; i < songBass.size(); i++){
            fos.write(convertToByte(songBass.elementAt(i)));
        }
        fos.write(convertToByte(footer));

        fos.close();
    }
}
