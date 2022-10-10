
/**
 * Write a description of class SimpleAudioPlayer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
    import java.io.File;
import java.io.IOException;
import java.util.Scanner;
  
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
  
public class SimpleAudioPlayer 
{
  
    // to store current position
    Long currentFrame;
    Clip clip;
      
    // current status of clip
    String status;
      
    AudioInputStream audioInputStream;
    static String filePath="C:\\Users\\Devon\\Downloads\\bumpintowall_X5CNQPB.wav";
  
    // constructor to initialize streams and clip
    public SimpleAudioPlayer()
        throws UnsupportedAudioFileException,
        IOException, LineUnavailableException 
    {
        // create AudioInputStream object
        audioInputStream = 
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        
        // create clip reference
        clip = AudioSystem.getClip();
          
        // open audioInputStream to the clip
        clip.open(audioInputStream);
        this.currentFrame=0L;
    }
  
    public static void main(String[] args) 
    {
        try
        {
            SimpleAudioPlayer audioPlayer = 
                            new SimpleAudioPlayer();
            audioPlayer.play();
            
        } 
          
        catch (Exception ex) 
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
          
          }
    }
      
    // Method to play the audio
    public void play() 
    {
        //start the clip
        clip.start();
        clip.loop(0);
        status = "play";
    }
      
    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
                                            UnsupportedAudioFileException 
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }
      
    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
    IOException, LineUnavailableException 
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
      
    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
                                                        LineUnavailableException 
    {
        if (c > 0 && c < clip.getMicrosecondLength()) 
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }
      // Method to pause the audio
    public void pause() 
    {
        if (status.equals("paused")) 
        {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame = 
        this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
      
    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
                                IOException, LineUnavailableException 
    {
        clip.close();
        resetAudioStream();
        this.play();
    }
    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
                                            LineUnavailableException 
    {
        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        this.play();
    }
  
}
