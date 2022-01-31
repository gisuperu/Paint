import java.awt.*;

public class ToolStatus {
    private Color color;
    private int bold;
    private final int BOLDMAX = 40;
    private int tool;
    /* tool status
     * -1 : eraser
     * 0  : don't draw
     * 1  : free draw
     * 2  : draw line
     * 3  : draw triangle
     */

    public ToolStatus(Color color, int bold, int tool){
        this.color = color;
        this.bold = bold;
        this.tool = tool;
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
    public void setTool(int tool) {
        this.tool = tool;
    }


    public Color getColor(){
        return this.color;
    }
    public int getBold(){
        return this.bold;
    }
    public int getTool(){
        return this.tool;
    }

}
