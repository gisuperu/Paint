import java.awt.*;
import java.awt.event.MouseEvent;

public class TrianglePen extends Pen{

    public TrianglePen(){
        super();
    }

    @Override
    public void mouseClicked(MouseEvent e, Graphics2D g, ToolStatus status) {
//        status.
//        points.add(new int[]{e.getX(), e.getY()});
//        if(points.size() >= 3){
//            int[] first = points.get(0);
//            int[] second = points.get(1);
//            int[] third = points.get(2);
//            g.setColor(status.getColor());
//            g.drawLine(first[0], first[1], second[0], second[1]);
//            g.drawLine(second[0], second[1], third[0], third[1]);
//            g.drawLine(third[0], third[1], first[0], first[1]);
//            points = new ArrayList<>();
//        }
    }

    @Override
    public void startUp() {

    }

    @Override
    public String toString() {
        return "三角形";
    }
}
