import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LinerPen extends Pen{

    public LinerPen(){
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status){
        status.addPoints(new int[]{e.getX(), e.getY()});
        if(status.getPointsSize() >= 2){
            int[] start = status.popPoints();
            int[] end = status.popPoints();
            g.setColor(status.getColor());
            g.setStroke(new BasicStroke(status.getBold(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(start[0], start[1], end[0], end[1]);
            status.clearPoints();
        }
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "直線";
    }
}
