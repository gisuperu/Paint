import java.awt.*;
import java.awt.event.MouseEvent;

public class RainbowPen extends Pen{
    private Color currentColor;
    private int hue;
    private int saturation;
    private int brightness;

    public RainbowPen(){
        super();
    }

    @Override
    public void mouseDragged(MouseEvent e, Graphics2D g, ToolStatus status) {
        if(status.getPointsSize() >= 1){
            int[] p1 = status.popPoints();
            int[] p2 = {e.getX(), e.getY()};
            try{
                changeColor();
                g.setColor(this.currentColor);
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

    private void changeColor(){
        hue++;
        if(hue > 360) hue = 0;
        currentColor = Color.getHSBColor(hue/360f, saturation/100f, brightness/100f);
    }

    @Override
    public void startUp() {
        this.hue = 0;
        this.saturation = 80;
        this.brightness = 100;
        this.currentColor = Color.getHSBColor(hue/360f, saturation/100f, brightness/100f);
    }

    @Override
    public String toString() {
        return "虹色ペン";
    }
}
