import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LayoutManager {
    static final int WORKWIDTH = 700;
    static final int WORKHEIGHT = 700;
    static final int MENUWIDTH = 200;
    static final int MENUHEIGHT = WORKHEIGHT;

    final Color WORKBACKCOLOR = new Color(127, 127, 127);
    final Color MENUBACKCOLOR = new Color(150,150,150);


    private JPanel frame;
    private JPanel workspace;
    private JPanel menu;

    private Canvas cnv;

    private ToolStatus status = new ToolStatus(new Color(0,0,0), 10);

    public LayoutManager() {
        startUp();
    }

    private void startUp(){
        this.frame = new JPanel();
        frame.setLayout(new BorderLayout());
//        frame.addMouseListener(new MousePaintListener(frame));

        this.workspace = new JPanel();
        workspace.setLayout(new BorderLayout());
        workspace.setPreferredSize(new Dimension(WORKWIDTH, WORKHEIGHT));
        workspace.setBackground(WORKBACKCOLOR);
        workspace.setOpaque(true);
        //tmp listener
        MousePaintListener listener = new MousePaintListener(workspace);
        workspace.addMouseListener(listener);
        workspace.addMouseMotionListener(listener);
        //-------------
//        workspace.addMouseListener(new MouseInputListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                System.out.println("dragged");
//                status.getCurrentPen().mouseDragged(e, (Graphics2D) workspace.getGraphics(), status);
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//
//            }
//        });

        this.menu = new JPanel();
        menu.setLayout(new FlowLayout());
        menu.setPreferredSize(new Dimension(MENUWIDTH, MENUHEIGHT));
        menu.setBackground(MENUBACKCOLOR);
        menu.setOpaque(true);

        frame.add(workspace, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.EAST);
    }

    public JPanel getFrame(){
        return this.frame;
    }
}
