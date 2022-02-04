import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class PenManager implements MouseInputListener {
    private JPanel cvs;
    private Graphics2D g;

    private JTextArea textArea;

    private ToolStatus status;

    public PenManager(JPanel cvs, JTextArea textArea){
        this.cvs = cvs;
        this.g = (Graphics2D) cvs.getGraphics();

        this.textArea = textArea;

        this.status = new ToolStatus(new Color(0,0,0), 5);
    }

    public void clearCvs(){
        g = (Graphics2D) cvs.getGraphics();
        g.setColor(LayoutManager.CVSBACKCOLOR);
        g.fillRect(0, 0, LayoutManager.CVSWIDTH, LayoutManager.CVSHEIGHT);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        status.getCurrentPen().mouseClicked(e, g, status);
        status.setText(this.textArea.getText());
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

    public ToolStatus getStatus() {
        return status;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}
