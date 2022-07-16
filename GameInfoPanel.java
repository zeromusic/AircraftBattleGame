package com.view;

import com.util.ImageData;

import javax.swing.*;
import java.awt.*;

public class GameInfoPanel extends JPanel {
    public GameInfoPanel() {
        this.setLayout(null);
        //游戏信息页面的大小
        this.setPreferredSize(new Dimension(260,900));
        ImageIcon image =new ImageIcon("images/radar.gif");
        JLabel label=new JLabel(image);
        label.setPreferredSize(new Dimension(200,200));
        label.setBounds(25,640,200,200);
        this.add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("宋体",Font.BOLD,16));
        g.drawString("得分:0",20,50);
        g.drawString("生命值:0",20,90);
        g.drawString("当前第1关",20,130);
        g.drawString("拥有核弹数0枚",20,170);
        g.drawString("越过防线的敌机0架",20,210);
        g.drawString("未出现的敌机0架",20,250);
        //初级敌机
        g.drawImage(ImageData.enemy1_1,30,300,50,50,null);
        g.drawString("初级敌机:0架",120,300);
        //中级敌机
        g.drawImage(ImageData.enemy2_1,30,370,50,50,null);
        g.drawString("中级敌机:0架",120,410);
        //敌机BOSS
        g.drawImage(ImageData.boss,30,470,50,50,null);
        g.drawString("敌机BOSS:0架",120,500);
        //雷达上方的文字颜色
        g.setColor(Color.RED);
        g.drawString("我方电子侦察雷达",50,620);

    }
}