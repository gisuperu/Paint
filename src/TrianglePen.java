import java.awt.*;
import java.awt.event.MouseEvent;

public class TrianglePen extends Pen{

    public TrianglePen(){
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status) {
        status.addPoints(new int[]{e.getX(), e.getY()});
        if(status.getPointsSize() >= 3){
            int[] first = status.popPoints();
            int[] second = status.popPoints();
            int[] third = status.popPoints();
            g.setColor(status.getColor());
            g.setStroke(new BasicStroke(status.getBold(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(first[0], first[1], second[0], second[1]);
            g.drawLine(second[0], second[1], third[0], third[1]);
            g.drawLine(third[0], third[1], first[0], first[1]);
            status.clearPoints();
        }
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "三角形";
    }
}
