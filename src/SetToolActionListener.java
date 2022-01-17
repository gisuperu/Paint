import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetToolActionListener implements ActionListener {

    private MousePaintListener listener;
    private int tool;

    public SetToolActionListener(MousePaintListener listener, int tool) {
        this.listener = listener;
        this.tool = tool;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.setTool(tool);
    }
}
