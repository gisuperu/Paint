import java.awt.*;
import java.awt.event.MouseEvent;

public class EraserPen extends Pen{
    private Color backColor;

    public EraserPen(){
        super();
        this.backColor = LayoutManager.CVSBACKCOLOR;
    }

    @Override
    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status){
        status.addPoints(new int[]{e.getX(), e.getY()});
        if(status.getPointsSize() >= 2){
            int[] start = status.popPoints();
            int[] end = status.popPoints();
            g.setColor(this.backColor);
            g.setStroke(new BasicStroke(status.getBold()));
            g.drawLine(start[0], start[1], end[0], end[1]);
            status.clearPoints();
        }
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "消しゴム";
    }
}
