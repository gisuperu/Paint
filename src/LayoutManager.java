import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    private JTextArea textArea;
    private JPanel previewArea;
    private JLabel previewPen;
    private JLabel previewBold;

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
        this.penManager = new PenManager(cvs, textArea);
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
            updatePreviewArea(penManager.getStatus());
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
            updatePreviewArea(penManager.getStatus());
        });

        boldOptions.add(boldValue, BorderLayout.CENTER);
        boldOptions.add(boldBar, BorderLayout.SOUTH);
        menu.add(boldOptions);


        //color option
        String[] presetColors = new String[]{"???", "???", "???", "???", "???", "???", "???"};

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
                Color cc = penManager.getStatus().getPresetColor(c);
                penManager.getStatus().setColor(cc);
                updatePreviewArea(penManager.getStatus());
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


        //text option
        JPanel textOptions = new JPanel();
        textOptions.setLayout(new BorderLayout());
        textOptions.setBackground(LayoutManager.MENUBACKCOLOR);
        textOptions.setPreferredSize(new Dimension(MENUWIDTH-10, 100));
        textOptions.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "text"));

        this.textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(MENUWIDTH-20, 80));
        textArea.setLineWrap(true);
        penManager.setTextArea(textArea);
//        textArea.addActionListener(e -> {
//            String tt = (String)e.getSource();
//            penManager.getStatus().setText(tt);
//            updatePreviewArea(penManager.getStatus());
//        });
        JComboBox<String> fontBox = new JComboBox<>(penManager.getStatus().getPresetFont());
        fontBox.addActionListener(e -> {
            Font f = new Font((String)((JComboBox)e.getSource()).getSelectedItem(), Font.PLAIN, penManager.getStatus().getBold());
            penManager.getStatus().setFont(f);
            penManager.getStatus().setText(textArea.getText());
            updatePreviewArea(penManager.getStatus());
        });

        textOptions.add(textArea, BorderLayout.CENTER);
        textOptions.add(fontBox, BorderLayout.SOUTH);
        menu.add(textOptions);


        //preview
        JPanel preview = new JPanel();
        preview.setLayout(new BorderLayout());
        preview.setBackground(LayoutManager.MENUBACKCOLOR);
        preview.setPreferredSize(new Dimension(MENUWIDTH-10, 180));
        preview.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "preview"));

        JPanel statusValues = new JPanel();
        statusValues.setBackground(MENUBACKCOLOR);
        statusValues.setLayout(new GridLayout(2,1));
        statusValues.setPreferredSize(new Dimension(MENUWIDTH-20, 50));

            this.previewPen = new JLabel();
            previewPen.setText("Pen: " + penManager.getStatus().getCurrentPen().toString());
            this.previewBold = new JLabel();
            previewBold.setText("Bold: " + penManager.getStatus().getBold());

        statusValues.add(previewPen, 0);
        statusValues.add(previewBold, 1);
        preview.add(statusValues, BorderLayout.NORTH);

        this.previewArea = new JPanel();
        previewArea.setPreferredSize(new Dimension(MENUWIDTH-20, 100));
        preview.add(previewArea, BorderLayout.CENTER);

        menu.add(preview);

        //clear button
        JButton clear = new JButton("?????????");
        clear.addActionListener(e -> {
            penManager.clearCvs();
        });
        menu.add(clear);



    }
    public void createColorPicker(ToolStatus status){
        this.colorPickerFrame = new JFrame("Color Picker");
        colorPickerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        colorPickerFrame.setSize(700, 500);
//        colorPicker.setLayout(null);      //???????????????????????????????????????????????????
        colorPickerFrame.setResizable(false);  //????????????????????????

        JColorChooser colorPicker = new JColorChooser();

        JButton submit = new JButton("??????");
        submit.addActionListener(e -> {
            status.setColor(colorPicker.getColor());
            updatePreviewArea(penManager.getStatus());
        });

        colorPickerFrame.add(colorPicker, BorderLayout.CENTER);
        colorPickerFrame.add(submit, BorderLayout.SOUTH);

//        frame.setVisible(true);

    }
    public void createFileManager(){

    }

    public void updatePreviewArea(ToolStatus status){
        previewPen.setText("Pen: " + status.getCurrentPen().toString());
        previewBold.setText("Bold: " + status.getBold());

        Graphics2D g = (Graphics2D) previewArea.getGraphics();
        g.setColor(CVSBACKCOLOR);
        g.fillRect(0, 0, MENUWIDTH, MENUHEIGHT);
        int[] center = new int[]{(int)previewArea.getSize().getWidth()/2, (int)previewArea.getSize().getHeight()/2};
//        System.out.println(status.getColor().toString());
        g.setColor(status.getColor());
        if(status.getCurrentPen() instanceof CrossStampPen){
            int bold = status.getBold()/2;
            int size = status.getBold();
            g.setStroke(new BasicStroke(bold, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(center[0], center[1], center[0] + size, center[1]);
            g.drawLine(center[0], center[1], center[0] - size, center[1]);
            g.drawLine(center[0], center[1], center[0], center[1] + size);
            g.drawLine(center[0], center[1], center[0], center[1] - size);
        }else if(status.getCurrentPen() instanceof TextPen){
            String sampleText = "aA?????????";

            g.setStroke(new BasicStroke(status.getBold(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.setFont(status.getFont());

            FontMetrics fm = g.getFontMetrics();
            Rectangle rectText = fm.getStringBounds(sampleText, g).getBounds();
            g.drawString(sampleText, center[0]-rectText.width/2, center[1]-rectText.height/2+fm.getMaxAscent());
        }else{
            g.setStroke(new BasicStroke(status.getBold(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g.drawLine(center[0], center[1], center[0], center[1]);
        }


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
