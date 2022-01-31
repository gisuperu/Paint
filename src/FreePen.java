import java.awt.*;
import java.awt.event.MouseEvent;

public class FreePen extends Pen{

    public FreePen(){
        super();
    }

    @Override
    public void mouseDragged(MouseEvent e, Graphics2D g, ToolStatus status) {
        g.setColor(new Color(255, 40,40));
        g.fillOval(e.getX(), e.getY(), status.getBold(), status.getBold());
//        if(points.size() >= 1){
//            int[] p1 = points.get(0);
//            int[] p2 = {e.getX(), e.getY()};
//            try{
//                graphics.setColor(this.color);
//                graphics.drawLine(p1[0], p1[1], p2[0], p2[1]);
//            }catch(NullPointerException ex){
//                System.err.println("Frame haven't been done setVisible yet!");
//            }
//            points.set(0, p2);
//        }else{
//            points.add(new int[]{e.getX(), e.getY()});
//
//        }
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "自由";
    }
}
