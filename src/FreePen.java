import java.awt.*;
import java.awt.event.MouseEvent;

public class FreePen extends Pen{

    public FreePen(){
        super();
    }

    @Override
    public void mouseDragged(MouseEvent e, Graphics2D g, ToolStatus status) {
//        g.setColor(new Color(255, 40,40));
//        g.fillOval(e.getX(), e.getY(), status.getBold(), status.getBold());
        if(status.getPointsSize() >= 1){
            int[] p1 = status.popPoints();
            int[] p2 = {e.getX(), e.getY()};
            try{
                g.setColor(status.getColor());
                g.setStroke(new BasicStroke(status.getBold()));
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

    }

    @Override
    public String toString() {
        return "自由";
    }
}
