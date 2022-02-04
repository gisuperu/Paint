import java.awt.*;
import java.awt.event.MouseEvent;

public class CrossStampPen extends Pen{

    public CrossStampPen(){
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status){
        int bold = status.getBold()/2;
        int size = status.getBold();

        int[] point = new int[]{e.getX(), e.getY()};
        g.setColor(status.getColor());
        g.setStroke(new BasicStroke(bold, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(point[0], point[1], point[0] + size, point[1]);
        g.drawLine(point[0], point[1], point[0] - size, point[1]);
        g.drawLine(point[0], point[1], point[0], point[1] + size);
        g.drawLine(point[0], point[1], point[0], point[1] - size);
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "十字スタンプ";
    }
}
