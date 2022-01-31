import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Pen {

    public Pen(){
        startUp();
    }

    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status) {

    }

    public void mousePressed(MouseEvent e, Graphics2D g, ToolStatus status) {

    }

    public void mouseReleased(MouseEvent e, Graphics2D g, ToolStatus status) {

    }

    public void mouseEntered(MouseEvent e, Graphics2D g, ToolStatus status) {

    }

    public void mouseExited(MouseEvent e, Graphics2D g, ToolStatus status) {

    }

    public void mouseDragged(MouseEvent e, Graphics2D g, ToolStatus status) {

    }

    public void mouseMoved(MouseEvent e, Graphics2D g, ToolStatus status) {

    }



    abstract public void startUp();

    abstract public String toString();
}
