package nodes;

import javafx.scene.media.AudioClip;

public class Sound {
    public String name;
    public AudioClip clip;

    public Sound(String name, AudioClip clip) {
        this.name = name;
        this.clip = clip;
    }
}
