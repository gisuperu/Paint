import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args){

        //レイアウト生成
        LayoutManager manager = new LayoutManager();

        JFrame frame = new JFrame("ぺいんと");
        frame.setSize(manager.WORKWIDTH+manager.MENUWIDTH, manager.WORKHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(null);      //サイズ変化に伴う自動レイアウト禁止
        frame.setResizable(false);  //サイズ変更を禁止


        //メニューバーの実装
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("test"));
        frame.setJMenuBar(menuBar);


        frame.add(manager.getFrame(), BorderLayout.CENTER);

        //frameの表示
        frame.setVisible(true);

        // 遺産
//        //color picker
//        JColorChooser colorChooser = new JColorChooser();
//        colorChooser.setPreferredSize(new Dimension(MENUWIDTH, MENUHEIGHT));
//        menu.add(colorChooser);
//
//        //text box
//        JTextArea textArea = new JTextArea();
//        menu.add(textArea);

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

    }
}