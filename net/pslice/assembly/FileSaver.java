package net.pslice.assembly;

import net.pslice.basics.Info;

import java.io.*;

public class FileSaver extends Song {

    public static void saveFile(String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);

        fos.write(intArrayToByteArray(header));

        int size = Info.getTempo().length + Info.getKeySig().length + Info.getTimeSig().length + footer.length;

        for (int i = 0; i <songFile.size(); i++)
            size += songFile.elementAt(i).length;

        int high = size / 256;
        int low = size - (high * 256);
        fos.write((byte) 0);
        fos.write((byte) 0);
        fos.write((byte) high);
        fos.write((byte) low);

        fos.write(intArrayToByteArray(Info.getTempo()));
        fos.write(intArrayToByteArray(Info.getKeySig()));
        fos.write(intArrayToByteArray(Info.getTimeSig()));

        for (int i = 0; i < songFile.size(); i++){
            fos.write(intArrayToByteArray(songFile.elementAt(i)));
        }

        fos.write(intArrayToByteArray(footer));
        fos.close();
    }
}
