package com.view;

import com.controller.GameController;
import com.util.ImageData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 欢迎界面
 */
public class WelcomeFrame extends JFrame{
    public GameController gameController;
   //实例化的时候需要调用的方法
    public WelcomeFrame(GameController gameController){
        //this表示的是当前的类
        this.gameController=gameController;
        //设置标题
        this.setTitle("雷霆战机");
        //取消布局方式，绝对布局
        this.setLayout(null);
        //设置界面的大小
        this.setSize(1000,800);
        //设置界面不能最大化
        this.setResizable(false);
        //设置界面默认展示在屏幕中间
        this.setLocationRelativeTo(null);
        //按钮
        JButton butStart=new JButton("开始游戏");
        butStart.setBounds(400,600,150,30);
       //按钮的监听事件
        butStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                1.关闭欢迎界面
                2.游戏主界面的显示
                 */
                //关闭欢迎界面，吧界面的显示状态变为隐藏
                gameController.welcomeFrame.setVisible(false);
                //把游戏的主界面显示出来
                gameController.gameMainFrame.setVisible(true);
            }
        });
        this.add(butStart);
        //所有的JFrame默认情况下都是隐藏
        this.setVisible(true);
    }
    //在JFrame中绘制容器的背景图片，需要调用paint方法
     public void paint(Graphics g){
        super.paint(g);
        //Image是一个抽象类，不能自己去实现对应的方法，需要子类来实现
                g.drawImage(ImageData.image,0,0,1000,800,null);
     }
}