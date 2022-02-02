import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LayoutManager {
    static final int WORKWIDTH = 700;
    static final int WORKHEIGHT = 700;
    static final int MENUWIDTH = 200;
    static final int MENUHEIGHT = WORKHEIGHT;
    static final int CVSWIDTH = WORKWIDTH;
    static final int CVSHEIGHT = WORKHEIGHT;

    static final Color WORKBACKCOLOR = new Color(127, 127, 127);
    static final Color MENUBACKCOLOR = new Color(150, 150, 150);
    static final Color CVSBACKCOLOR = new Color(255, 255, 255);

    private JPanel frame;
    private JPanel workspace;
    private JPanel menu;

    private JPanel cvs;

    private ToolStatus status = new ToolStatus(new Color(0,0,0), 10);

    public LayoutManager() {
        startUp();
    }

    private void startUp(){
        this.frame = new JPanel();
        frame.setLayout(new BorderLayout());

        // workspace
        this.workspace = new JPanel();
        workspace.setLayout(new CardLayout());
        workspace.setPreferredSize(new Dimension(WORKWIDTH, WORKHEIGHT));
        workspace.setBackground(WORKBACKCOLOR);
        workspace.setOpaque(true);

        this.cvs = new JPanel();
        cvs.setPreferredSize(new Dimension(CVSWIDTH, CVSHEIGHT));
        cvs.setBackground(CVSBACKCOLOR);
        workspace.add(cvs, "currentCanvas");

        //listener
        PenManager penManager = new PenManager(cvs);
        workspace.addMouseListener(penManager);
        workspace.addMouseMotionListener(penManager);



        // side menu
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
