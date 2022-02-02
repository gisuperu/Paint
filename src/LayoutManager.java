import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class LayoutManager {
    public static final int WORKWIDTH = 700;
    public static final int WORKHEIGHT = 700;
    public static final int MENUWIDTH = 200;
    public static final int MENUHEIGHT = WORKHEIGHT;
    public static final int CVSWIDTH = WORKWIDTH;
    public static final int CVSHEIGHT = WORKHEIGHT;

    public static final Color WORKBACKCOLOR = new Color(127, 127, 127);
    public static final Color MENUBACKCOLOR = new Color(150, 150, 150);
    public static final Color CVSBACKCOLOR = new Color(255, 255, 255);

    private static LayoutManager instance;

    private JPanel frame;
    private JPanel workspace;
    private JPanel menu;

    private JPanel cvs;

    private PenManager penManager;

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
        this.penManager = new PenManager(cvs);
        workspace.addMouseListener(penManager);
        workspace.addMouseMotionListener(penManager);



        // side menu
        this.menu = new JPanel();
//        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setLayout(new FlowLayout());
        menu.setPreferredSize(new Dimension(MENUWIDTH, MENUHEIGHT));
        menu.setBackground(MENUBACKCOLOR);
        menu.setOpaque(true);
        designMenu(menu);


        frame.add(workspace, BorderLayout.CENTER);
        frame.add(menu, BorderLayout.EAST);
    }

    private void designMenu(JPanel menu) {
        JComboBox<Pen> selector = new JComboBox<>(penManager.getStatus().getPens());
        selector.addActionListener(e -> {
            Pen p = (Pen)((JComboBox)e.getSource()).getSelectedItem();
            penManager.getStatus().setCurrentPen(p);
        });
        menu.add(selector);
    }

    public JPanel getFrame(){
        return this.frame;
    }

    public JPanel getMenu() {
        return menu;
    }

    public static LayoutManager getInstance() {
        if(instance == null){
            instance = new LayoutManager();
        }
        return instance;
    }
}
