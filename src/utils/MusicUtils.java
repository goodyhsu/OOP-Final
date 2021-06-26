package utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicUtils {

    public boolean isPlaying = false;
    private Clip clip;

    public void playMusic(String musicLocation, boolean isLoop) {
        try  {
            if (isPlaying)
                endMusic();
            File musicPath = new File(musicLocation);
            if(musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                isPlaying = true;
                if (isLoop)
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                //if (!clip.isActive())
                //    endMusic();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void endMusic() {
        clip.close();
        isPlaying = false;
    }
}
