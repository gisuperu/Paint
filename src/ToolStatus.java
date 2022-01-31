import java.awt.*;
import java.util.ArrayList;

public class ToolStatus {
    private Color color;
    private int bold;
    private final int BOLDMAX = 40;
    private Pen currentPen;
    private Pen[] pens = {
            new FreePen(),
            new TrianglePen()
    };
//    /* tool status
//     * 0  : don't draw
//     * 1  : free draw
//     * 2  : draw line
//     * 3  : draw triangle
//     */

    public ToolStatus(Color color, int bold){
        this.color = color;
        this.bold = bold;

        this.currentPen = pens[0];
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


    public Color getColor(){
        return this.color;
    }
    public int getBold(){
        return this.bold;
    }
    public Pen getCurrentPen(){
        return this.currentPen;
    }
}
