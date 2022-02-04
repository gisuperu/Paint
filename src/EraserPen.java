import java.awt.*;
import java.awt.event.MouseEvent;

public class EraserPen extends Pen{
    private Color backColor;

    public EraserPen(){
        super();
    }

    @Override
    public void mouseDragged(MouseEvent e, Graphics2D g, ToolStatus status) {
        if(status.getPointsSize() >= 1){
            int[] p1 = status.popPoints();
            int[] p2 = {e.getX(), e.getY()};
            try{
                g.setColor(this.backColor);
                g.setStroke(new BasicStroke(status.getBold(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g.drawLine(p1[0], p1[1], p2[0], p2[1]);
            }catch(NullPointerException ex){
                System.err.println("Frame haven't been done setVisible yet!");
            }
            status.addPoints(p2);
        }else{
            status.addPoints(new int[]{e.getX(), e.getY()});

        }
    }

    @Override
    public void mouseReleased(MouseEvent e, Graphics2D g, ToolStatus status){
        status.clearPoints();
    }

    @Override
    public void startUp() {
        this.backColor = LayoutManager.CVSBACKCOLOR;
    }

    @Override
    public String toString() {
        return "消しゴム";
    }
}
