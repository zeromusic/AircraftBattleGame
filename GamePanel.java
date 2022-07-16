package com.view;

import com.util.ImageData;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏区开始界面
 */
public class GamePanel extends JPanel {
    public GamePanel(){
        //设置游戏去开始界面大小
        this.setPreferredSize(new Dimension(600,900));

    }

    //JPanel用于绘制背景图
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.image1,0,0,600,900,null);
    }
}
