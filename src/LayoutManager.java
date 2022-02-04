import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

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


    private JPanel frame;
    private JPanel workspace;
    private JPanel menu;

    private JPanel cvs;
    private JFrame colorPickerFrame;

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

        //selector of pens
        JComboBox<Pen> selector = new JComboBox<>(penManager.getStatus().getPens());
        selector.addActionListener(e -> {
            Pen p = (Pen)((JComboBox)e.getSource()).getSelectedItem();
            penManager.getStatus().setCurrentPen(p);
        });
        menu.add(selector);


        //bold option
        int defaultBold = 5;

        JPanel boldOptions = new JPanel();
        boldOptions.setLayout(new BorderLayout());
        boldOptions.setBackground(LayoutManager.MENUBACKCOLOR);
        boldOptions.setPreferredSize(new Dimension(MENUWIDTH-10, 80));
        boldOptions.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "bold"));

        JLabel boldValue = new JLabel(String.valueOf(defaultBold));
        boldValue.setPreferredSize(new Dimension(MENUWIDTH-10, 30));
        boldValue.setHorizontalAlignment(JLabel.CENTER);
        JScrollBar boldBar = new JScrollBar(JScrollBar.HORIZONTAL, defaultBold, 0, 1, 30);
        boldBar.setPreferredSize(new Dimension(MENUWIDTH-20, 30));
        boldBar.addAdjustmentListener(e -> {
            int bold = e.getValue();
            boldValue.setText(String.valueOf(bold));
            penManager.getStatus().setBold(bold);
        });

        boldOptions.add(boldValue, BorderLayout.CENTER);
        boldOptions.add(boldBar, BorderLayout.SOUTH);
        menu.add(boldOptions);


        //color option
        String[] presetColors = new String[]{"黒", "赤", "黄", "緑", "水", "青", "紫"};

        JPanel colorOptions = new JPanel();
        colorOptions.setLayout(new BorderLayout());
        colorOptions.setBackground(LayoutManager.MENUBACKCOLOR);
        colorOptions.setPreferredSize(new Dimension(MENUWIDTH-10, 80));
        colorOptions.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "color"));

        JPanel presetColorOptions = new JPanel();
//        presetColorOptions.setPreferredSize(new Dimension(MENUWIDTH-10, 40));
        presetColorOptions.setLayout(new GridLayout(1, presetColors.length));
        for(String c : presetColors){
            JButton color = new JButton(c);
            color.setPreferredSize(new Dimension((MENUWIDTH-10)/presetColors.length, 40));
            color.addActionListener(e -> {
                Color cc = penManager.getStatus().getPresetColor(c + "色");
                penManager.getStatus().setColor(cc);
            });
            presetColorOptions.add(color);
        }
        JButton colorPicker = new JButton("Color Picker");
        createColorPicker(penManager.getStatus());
        colorPicker.addActionListener(e -> {
            colorPickerFrame.setVisible(true);
        });

        colorOptions.add(presetColorOptions, BorderLayout.CENTER);
        colorOptions.add(colorPicker, BorderLayout.SOUTH);

        menu.add(colorOptions);


    }
    public void createColorPicker(ToolStatus status){
        this.colorPickerFrame = new JFrame("Color Picker");
        colorPickerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        colorPickerFrame.setSize(700, 500);
//        colorPicker.setLayout(null);      //サイズ変化に伴う自動レイアウト禁止
        colorPickerFrame.setResizable(false);  //サイズ変更を禁止

        JColorChooser colorPicker = new JColorChooser();

        JButton submit = new JButton("決定");
        submit.addActionListener(e -> {
            status.setColor(colorPicker.getColor());
        });

        colorPickerFrame.add(colorPicker, BorderLayout.CENTER);
        colorPickerFrame.add(submit, BorderLayout.SOUTH);

//        frame.setVisible(true);

    }

    public JPanel getFrame(){
        return this.frame;
    }

    public JPanel getMenu() {
        return menu;
    }

    public JPanel getWorkspace() {
        return workspace;
    }

    public PenManager getPenManager() {
        return penManager;
    }
}
