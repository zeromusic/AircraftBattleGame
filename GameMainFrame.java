package com.view;

import com.bean.Bullet;
import com.bean.Hero;
import com.controller.GameController;
import com.thread.CollideThread;
import com.thread.CreateEnemyBulletThread;
import com.thread.CreateEnemyPlaneThread;
import com.thread.EnemyPlaneThread;
import com.util.ImageData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameMainFrame extends JFrame {

    public GameController gameController;
    public GameMainFrame(GameController gameController){
        this.gameController=gameController;


        //设置大小
        this.setSize(900,900);
        //设置标题
        this.setTitle("雷霆战机");
        //设置界面的布局为BorderLayout
        this.setLayout(new BorderLayout());
        /***  菜单栏的实现      ***/
        //菜单栏
        JMenuBar jMenuBar=new JMenuBar();
        //菜单
        JMenu  gameMenu=new JMenu("游戏");
        JMenu helpMenu=new JMenu("帮助");
        gameMenu.setFont(new Font("宋体",Font.BOLD,18));
        helpMenu.setFont(new Font("宋体",Font.BOLD,18));
        jMenuBar.add(gameMenu);
        jMenuBar.add(helpMenu);
        this.setJMenuBar(jMenuBar);
        //游戏里的二级菜单
        JMenuItem beginItem=new JMenuItem("开始游戏");
        beginItem.setFont(new Font("宋体",Font.BOLD,18));
        gameMenu.add(beginItem);
        beginItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //隐藏欢迎界面
                GameMainPanel.gamePanel.setVisible(false);
                //游戏区界面显示
                GameMainPanel.gameStartPanel.setVisible(true);

                //启动生成敌方飞机的线程
                CreateEnemyPlaneThread createEnemyPlaneThread =new CreateEnemyPlaneThread(gameController);
                Thread thread=new Thread(createEnemyPlaneThread);
                thread.start();

                //启动敌方飞机自动移动的线程
                EnemyPlaneThread enemyPlaneThread=new EnemyPlaneThread(gameController);
                Thread thread1=new Thread(enemyPlaneThread);
                thread1.start();

                //启动敌方子弹自动发射的线程
                CreateEnemyBulletThread createEnemyBulletThread=new CreateEnemyBulletThread(gameController);
                Thread thread2=new Thread(createEnemyBulletThread);
                thread2.start();

                //启动碰撞体积检测的线程
                CollideThread collideThread=new CollideThread(gameController);
                Thread thread3=new Thread(collideThread);
                thread3.start();

                //给面板加上键盘监听
                gameController.gameMainFrame.addKeyListener(new KeyAdapter() {
                    //键入某个键时调用此方法
                    @Override
                    public void keyTyped(KeyEvent e) {
                    }
                    //按下某个键时调用此方法
                    @Override
                    public void keyPressed(KeyEvent e) {
                        //System.out.println(e.getKeyCode());
                        //System.out.println(e.getKeyChar());
                        Hero hero=gameController.hero;
                        switch (e.getKeyChar()){
                            case 'w'://向上移动
                                //y>0时，飞机可以继续向上移动
                                if(hero.y>0){
                                    hero.y=hero.y-hero.speed;
                                }
                                break;
                            case's'://向下移动
                                if(hero.y+hero.height<=820){
                                    hero.y=hero.y+hero.speed;
                                }
                                break;
                            case'a'://向左移动
                                if(hero.x>0){
                                    hero.x=hero.x- hero.speed;
                                }
                                break;
                            case'd'://向右移动
                                if(hero.x+hero.width<600){
                                    hero.x=hero.x+hero.speed;
                                }
                                break;
                            case'j'://发射子弹
                                //子弹x轴的计算，需要在英雄机的基础上加上半个飞机的宽度，确保飞机可以成功从中间位置生成子弹
                                //子弹y轴的计算，需要在英雄机的基础上扣除子弹本身的高度，确保飞机可以成功从飞机的上面生成子弹
                                Bullet bullet= new Bullet(ImageData.HERO_BULLET,hero.x+hero.width/2,hero.y-20,10,20,10);
                                        gameController.heroBulletList.add(bullet);
                                break;
                        }
                        //刷新界面并进行重新绘制
                        gameController.gameMainFrame.repaint();
                    }
                    //释放某个键是触发此方法
                    @Override
                    public void keyReleased(KeyEvent e) {
                        
                        //System.out.println("1");
                    }
                });
            }
        });
        JMenuItem pauseItem=new JMenuItem("暂停");
        pauseItem.setFont(new Font("宋体",Font.BOLD,18));
        gameMenu.add(pauseItem);
        JMenuItem continueItem=new JMenuItem("继续");;
        continueItem.setFont(new Font("宋体",Font.BOLD,18));
        gameMenu.add(continueItem);
        JMenuItem againItem=new JMenuItem("重新开始");;
        againItem.setFont(new Font("宋体",Font.BOLD,18));
        gameMenu.add(againItem);
        JMenuItem customizationItem=new JMenuItem("自定义");;
        customizationItem.setFont(new Font("宋体",Font.BOLD,18));
        gameMenu.add(customizationItem);
        JMenuItem exitItem=new JMenuItem("退出游戏");;
        exitItem.setFont(new Font("宋体",Font.BOLD,18));
        gameMenu.add(exitItem);
        JMenuItem operateItem=new JMenuItem("操作信息");;
        operateItem.setFont(new Font("宋体",Font.BOLD,18));
        helpMenu.add(operateItem);
        JMenuItem aboutItem=new JMenuItem("关于游戏");;
        aboutItem.setFont(new Font("宋体",Font.BOLD,18));
        helpMenu.add(aboutItem);
        /***游戏区***/
        //游戏区主界面
        GameMainPanel gameMainPanel=new GameMainPanel(gameController);
        //游戏信息主界面
        GameInfoPanel gameIfoPanel=new GameInfoPanel();
        this.add(gameMainPanel,BorderLayout.CENTER);
        this.add(gameIfoPanel,BorderLayout.EAST);
        //设置窗口不可改变大小
        this.setResizable(false);
        //设置窗口正在屏幕的居中位置
        this.setLocationRelativeTo(null);
        //设置窗口不可见
        this.setVisible(false);
    }
}
