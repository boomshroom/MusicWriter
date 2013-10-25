package net.pslice.basics;

public class Info {

    private static int[] tempo;
    private static int[] timeSig;
    private static int[] keySig;

    public static String songKey;

    public static void setTempo(){
        Info.tempo = new int[]{
                0x00, 0xFF, 0x51, 0x03,
                0x0F, 0x42, 0x40
        };
    }
    public static int[] getTempo(){
        return tempo;
    }

    public static void setTimeSig(){
        Info.timeSig = new int[]{
                0x00, 0xFF, 0x58, 0x04,
                0x04,
                0x02,
                0x30,
                0x08
        };
    }
    public static int[] getTimeSig(){
        return timeSig;
    }

    public static void setKeySig(String key){
        Info.songKey = key;
        int songKey = 0x00;
        if (key.equals("C"))
            songKey = 0x00;
        Info.keySig = new int[]{
                0x00, 0xFF, 0x59, 0x02,
                songKey,
                0x00
        };
    }
    public static int[] getKeySig(){
        return keySig;
    }
}
