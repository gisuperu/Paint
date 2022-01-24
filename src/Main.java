import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args){
        final int WORKWIDTH = 500;
        final int WORKHEIGHT = 500;
        final int MENUWIDTH = 200;
        final int MENUHEIGHT = WORKHEIGHT;

        JFrame frame = new JFrame("ぺいんと");
        frame.setSize(WORKWIDTH+MENUWIDTH, WORKHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);      //サイズ変化に伴う自動レイアウト禁止
        frame.setResizable(false);  //サイズ変更を禁止
        JPanel frm = new JPanel();
        frm.setLayout(new BorderLayout());

        //レイアウト
        JPanel workspace = new JPanel();
        workspace.setPreferredSize(new Dimension(WORKWIDTH, WORKHEIGHT));
        workspace.setBackground(new Color(127, 127, 127));
        workspace.setOpaque(true);
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(MENUWIDTH, MENUHEIGHT));
        menu.setBackground(new Color(150,150,150));
        menu.setOpaque(true);

        JLabel bt = new JLabel("test");
        bt.setSize(500, 100);
        menu.add(bt);

//        // マウス入力
//        MousePaintListener mouse = new MousePaintListener(frame);
//        frame.addMouseListener(mouse);
//        frame.addMouseMotionListener(mouse);
//
//        // option button
//        JButton redButton = new JButton("赤");
//        JButton bruckButton = new JButton("黒");
//        JButton lineButton = new JButton("直線");
//        JButton triangleButton = new JButton("三角");
//        JButton freeButton = new JButton("自由");
//
//        // 色変更ボタンの設定
//        redButton.setBounds(0, 0, 50, 30);
//        bruckButton.setBounds(50, 0, 50, 30);
//
//        redButton.addActionListener(new SetColorActionListener(mouse, new Color(255, 0,0)));
//        bruckButton.addActionListener(new SetColorActionListener(mouse, new Color(0, 0,0)));
//
//        frame.add(redButton);
//        frame.add(bruckButton);
//
//        // draw toolボタンの設定
//        lineButton.setBounds(100, 0, 50, 30);
//        triangleButton.setBounds(150, 0, 50, 30);
//        freeButton.setBounds(200, 0, 50, 30);
//
//        lineButton.addActionListener(new SetToolActionListener(mouse, 2)); // 2 : line
//        triangleButton.addActionListener(new SetToolActionListener(mouse, 3)); // 3 : triangle
//        freeButton.addActionListener(new SetToolActionListener(mouse, 1)); // 1 : free draw
//
//        frame.add(lineButton);
//        frame.add(triangleButton);
//        frame.add(freeButton);

        frm.add(workspace, BorderLayout.CENTER);
        frm.add(menu, BorderLayout.EAST);

        frame.add(frm, BorderLayout.CENTER);

        //frameの表示
        frame.setVisible(true);
    }
}