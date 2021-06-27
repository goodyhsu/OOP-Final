package utils;

import javax.sound.sampled.*;
import java.io.File;

public class MusicUtils {

    public boolean isPlaying = false;
    private Clip clip;

    public void playMusic(String musicLocation, boolean isLoop, boolean isCover) {
        try  {
            if (isPlaying) {
                if (isCover) {
                    endMusic();
                } else {
                    return;
                }
            }
            File musicPath = new File(musicLocation);
            if(musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                if (isLoop) {
                    FloatControl gainControl =
                            (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(-10.0f);
                }
                clip.start();
                isPlaying = true;
                if (isLoop) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    clip.addLineListener(new LineListener() {
                        @Override
                        public void update(final LineEvent event) {
                            if (event.getType().equals(LineEvent.Type.STOP)) {
                                endMusic();
                            }
                        }
                    });
                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void endMusic() {
        clip.close();
        clip = null;
        isPlaying = false;
    }
}
