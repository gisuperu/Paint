import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

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
            new RainbowPen(),
            new EraserPen()
    };

    private HashMap<String, Color> presetColor;

    public ToolStatus(Color color, int bold){
        this.color = color;
        this.bold = bold;
        clearPoints();

        this.currentPen = pens[0];

        this.presetColor = new HashMap<>();
        presetColor.put("黒", new Color(0,0,0));
        presetColor.put("赤", new Color(255,0,0));
        presetColor.put("黄", new Color(255,255,0));
        presetColor.put("緑", new Color(0,255,0));
        presetColor.put("水", new Color(0,255,255));
        presetColor.put("青", new Color(0,0,255));
        presetColor.put("紫", new Color(255,0,255));


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
        if(color.getRGB() == 0xffffff){
            this.color = new Color(0xfefefe);
        }else{
            this.color = color;
        }
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
    public Color getPresetColor(String key){
        if(this.presetColor.containsKey(key)){
            return presetColor.get(key);
        }else{
            return new Color(0,0,0);
        }
    }

}
