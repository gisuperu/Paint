import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class ToolStatus {

    private Color color;
    private int bold;
    private final int BOLDMAX = 40;
    private Deque<int[]> points;


    private Pen currentPen;
    private Pen[] pens = {
            new FreePen(),
            new LinerPen(),
            new TrianglePen(),
            new EraserPen()
    };


    public ToolStatus(Color color, int bold){
        this.color = color;
        this.bold = bold;
        clearPoints();

        this.currentPen = pens[0];

    }

    public void clearPoints(){
        this.points = new ArrayDeque<>();
    }
    public boolean addPoints(int[] p){
        try {
            if(p[0] <= LayoutManager.CVSWIDTH && p[1] <= LayoutManager.CVSHEIGHT){
                this.points.add(p);
                return true;
            }else{
                return false;
            }

        }catch(IllegalStateException e){
            System.err.println(e.getMessage());
            return false;
        }
    }
    public int[] popPoints(){
        if(!this.points.isEmpty()){
            return this.points.poll();
        }else{
            return null;
        }
    }
    public int getPointsSize(){
        return this.points.size();
    }



    public void setColor(Color color) {
        this.color = color;
    }
    public boolean setBold(int bold) {
        if(bold <= 0){
            this.bold = 1;
            return false;
        }else if(bold > BOLDMAX){
            this.bold = BOLDMAX;
            return false;
        }else{
            this.bold = bold;
            return true;
        }
    }

    public void setCurrentPen(Pen currentPen) {
        this.currentPen = currentPen;
    }

    public Color getColor(){
        return this.color;
    }
    public int getBold(){
        return this.bold;
    }
    public Pen getCurrentPen(){
        return this.currentPen;
    }
    public Pen[] getPens() {
        return pens;
    }

}
