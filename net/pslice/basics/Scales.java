package net.pslice.basics;

public class Scales{

    public static int[] cMajorScale = {
            48, 50, 52, 53, 55, 57, 59, 60
    };
    public static int[] cSharpMajorScale = {
            49, 51, 53, 54, 56, 58, 60, 61
    };
    public static int[] dMajorScale = {
            50, 52, 54, 55, 57, 59, 61, 62
    };
    public static int[] dSharpMajorScale = {
            51, 53, 55, 56, 58, 60, 62, 63
    };
    public static int[] eMajorScale = {
            52, 54, 56, 57, 59, 61, 63, 64
    };
    public static int[] fMajorScale = {
            53, 55, 57, 58, 60, 62, 64, 65
    };
    public static int[] fSharpMajorScale = {
            54, 56, 58, 59, 61, 63, 65, 66
    };
    public static int[] gMajorScale = {
            55, 57, 58, 60, 62, 64, 66, 67
    };
    public static int[] gSharpMajorScale = {
            44, 46, 48, 49, 51, 53, 55, 56
    };
    public static int[] aMajorScale = {
            45, 47, 49, 50, 52, 54, 56, 57
    };
    public static int[] aSharpMajorScale = {
            46, 48, 50, 51, 53, 55, 57, 58
    };
    public static int[] bMajorScale = {
            47, 49, 51, 52, 54, 56, 58, 59
    };

    public static int[] scale = {};

    public static void setKey(String key){
        if (key.equals("A"))
            scale = aMajorScale;
        else if (key.equals("A#"))
            scale = aSharpMajorScale;
        else if (key.equals("B"))
            scale = bMajorScale;
        else if (key.equals("C"))
            scale = cMajorScale;
        else if (key.equals("C#"))
            scale = cSharpMajorScale;
        else if (key.equals("D"))
            scale = dMajorScale;
        else if (key.equals("D#"))
            scale = dSharpMajorScale;
        else if (key.equals("E"))
            scale = eMajorScale;
        else if (key.equals("F"))
            scale = fMajorScale;
        else if (key.equals("F#"))
            scale = fSharpMajorScale;
        else if (key.equals("G"))
            scale = gMajorScale;
        else if (key.equals("G#"))
            scale = gSharpMajorScale;
    }
}
