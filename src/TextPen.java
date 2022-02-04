import java.awt.*;
import java.awt.event.MouseEvent;

public class TextPen extends Pen{

    public TextPen(){
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status){
        g.setColor(status.getColor());
        g.setStroke(new BasicStroke(status.getBold(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setFont(status.getFont());
        g.drawString(status.getText(), e.getX(), e.getY());
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "文字挿入";
    }
}
