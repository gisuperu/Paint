import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PenManager implements MouseInputListener {
    private JPanel cvs;
    private Graphics2D g;

    private ToolStatus status;

    public PenManager(JPanel cvs){
        this.cvs = cvs;
        this.g = (Graphics2D) cvs.getGraphics();

        this.status = new ToolStatus(new Color(0,0,0), 5);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        status.getCurrentPen().mouseClicked(e, g, status);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        status.getCurrentPen().mousePressed(e, g, status);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        status.getCurrentPen().mouseReleased(e, g, status);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.g = (Graphics2D) cvs.getGraphics();
        status.getCurrentPen().mouseEntered(e, g, status);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        status.getCurrentPen().mouseExited(e, g, status);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        status.getCurrentPen().mouseDragged(e, g, status);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        status.getCurrentPen().mouseMoved(e, g, status);
    }
}
