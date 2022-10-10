
/**
 * Write a description of class KeyManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class KeyManager implements KeyListener
{
   
     GridPlotter grid;
    boolean upPressed;
    boolean downPressed;
    boolean leftPressed;
    boolean rightPressed;
    public KeyManager(GridPlotter grid)
    {
      this.grid=grid;
    }
    public void run(){
    }
    public void keyTyped(KeyEvent ev){
        
    }
    public void keyPressed(KeyEvent ev){
        int e=ev.getKeyCode();
        switch(e){
            case (KeyEvent.VK_W) :
                upPressed=true;
                break;
            case (KeyEvent.VK_S) :
                downPressed=true;
                break;
            case (KeyEvent.VK_A) :
                leftPressed=true;
                break;
            case (KeyEvent.VK_D) :
                rightPressed=true;
                break;
            default:
                break;
        }
    }
    public void keyReleased(KeyEvent ev){
        int e=ev.getKeyCode();
        switch(e){
            case (KeyEvent.VK_W) :
                upPressed=false;
                break;
            case (KeyEvent.VK_S) :
                downPressed=false;
                break;
            case (KeyEvent.VK_A) :
                leftPressed=false;
                break;
            case (KeyEvent.VK_D) :
                rightPressed=false;
                break;
            default:
                break;
        }
    }
}
