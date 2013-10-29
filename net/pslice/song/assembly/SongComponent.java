package net.pslice.song.assembly;

import java.util.Random;

public class SongComponent {

    public String newComponent;

    final static Random rand = new Random();

    public SongComponent(){}

    public void setNextComponent(String lastComponent){
        if (lastComponent.equals("None")){
            newComponent = "Intro";
            return;
        }
        if (lastComponent.equals("Intro")){
            newComponent = "Outro";
            return;
        }
    }

    public void setRandomComponent(String lastComponent){
        if (lastComponent.equals("Verse")){
            String[] nextComponent = {
                    "Chorus", "Chorus", "Chorus", "Chorus",
                    "Verse", "Verse",
                    "Bridge",
                    "Solo"
            };
            newComponent = nextComponent[rand.nextInt(nextComponent.length)];
        }
        if (lastComponent.equals("Chorus")){
            String[] nextComponent = {
                    "Verse", "Verse", "Verse",
                    "Bridge", "Bridge",
                    "Solo", "Solo",
                    "Chorus",
                    "Outro"
            };
            newComponent = nextComponent[rand.nextInt(nextComponent.length)];
        }
        if (lastComponent.equals("Bridge")){
            String[] nextComponent = {
                    "Chorus", "Chorus", "Chorus",
                    "Verse", "Verse", "Verse",
                    "Solo"
            };
            newComponent = nextComponent[rand.nextInt(nextComponent.length)];
        }
        if (lastComponent.equals("Solo")){
            String[] nextComponent = {
                    "Chorus", "Chorus", "Chorus",
                    "Verse", "Verse", "Verse"
            };
            newComponent = nextComponent[rand.nextInt(nextComponent.length)];
        }
        if (lastComponent.equals("Generic")){
            String[] nextComponent = {
                    "Verse", "Verse", "Verse", "Verse",
                    "Chorus", "Chorus",
                    "Bridge",
                    "Outro",
            };
            newComponent = nextComponent[rand.nextInt(nextComponent.length)];
        }
    }

    public String getNewComponent(){
        return newComponent;
    }
}
