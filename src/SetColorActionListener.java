import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetColorActionListener implements ActionListener {

    private MousePaintListener listener;
    private Color color;

    public SetColorActionListener(MousePaintListener listener, Color color) {
        this.listener = listener;
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listener.setColor(this.color);
    }
}
