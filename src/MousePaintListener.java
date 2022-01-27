import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class MousePaintListener implements MouseInputListener {

    private JPanel panel;
    private Graphics graphics;
    private Color color;

    private int bold;
    private ArrayList<int[]> points;

    private int tool;
    /* tool status
    * 0 : don't draw
    * 1 : free draw
    * 2 : draw line
    * 3 : draw triangle
    */

    public MousePaintListener(JPanel panel){
        this.panel = panel;
        this.graphics = panel.getGraphics();
        this.color = new Color(0, 0, 0);

        this.bold = 2;
        this.points = new ArrayList<int[]>();

        this.tool = 1;

    }

    public void setColor(Color color){
        this.color = color;
    }
    public boolean setTool(int tool){
        if(0 <= tool && tool <= 3){
            this.tool = tool;
            this.points = new ArrayList<int[]>();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("mouse clicked");
//        System.out.println(points);
        switch (this.tool){
            case 1: // free draw
                break;
            case 2: // draw line
                points.add(new int[]{e.getX(), e.getY()});
                if(points.size() >= 2){
                    int[] start = points.get(0);
                    int[] end = points.get(1);
                    graphics.setColor(this.color);
                    graphics.drawLine(start[0], start[1], end[0], end[1]);
                    points = new ArrayList<>();
                }
                break;
            case 3: // draw triangle
                points.add(new int[]{e.getX(), e.getY()});
                if(points.size() >= 3){
                    int[] first = points.get(0);
                    int[] second = points.get(1);
                    int[] third = points.get(2);
                    graphics.setColor(this.color);
                    graphics.drawLine(first[0], first[1], second[0], second[1]);
                    graphics.drawLine(second[0], second[1], third[0], third[1]);
                    graphics.drawLine(third[0], third[1], first[0], first[1]);
                    points = new ArrayList<>();
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("mouse Pressed");
        switch (this.tool){
            case 1: // free draw
                points = new ArrayList<int[]>();
                break;
            case 2: // draw line
                break;
            case 3: // draw triangle
                break;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("mouse released");
        switch (this.tool){
            case 1: // free draw
                break;
            case 2: // draw line
                break;
            case 3: // draw triangle
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("entered");
        this.graphics = panel.getGraphics();

    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("exited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("Dragged");
        switch (this.tool){
            case 1: // free draw
                if(points.size() >= 1){
                    int[] p1 = points.get(0);
                    int[] p2 = {e.getX(), e.getY()};
                    try{
                        graphics.setColor(this.color);
                        graphics.drawLine(p1[0], p1[1], p2[0], p2[1]);
                    }catch(NullPointerException ex){
                        System.err.println("Frame haven't been done setVisible yet!");
                    }
                    points.set(0, p2);
                }else{
                    points.add(new int[]{e.getX(), e.getY()});

                }
                break;
            case 2: // draw line
                break;
            case 3: // draw triangle
                break;
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println("moved");
    }
}
