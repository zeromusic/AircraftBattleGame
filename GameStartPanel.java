package com.view;

import com.bean.EnemyPlane;
import com.bean.Hero;
import com.bean.Bullet;
import com.controller.GameController;
import com.util.ImageData;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏区
 */
public class GameStartPanel extends JPanel {
    private GameController gameController;
    public GameStartPanel(GameController gameController){
        this.gameController=gameController;
        this.setPreferredSize(new Dimension(600,820));
        this.setVisible(false);
    }
    //游戏区背景
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageData.image2,0,0,600,800,null);
        Hero hero =gameController.hero;
        g.drawImage(hero.image, hero.x, hero.y,  hero.width, hero.height,null);
        //绘制英雄子弹
        for(Bullet bullet:gameController.heroBulletList){
            g.drawImage(bullet.image,bullet.x,bullet.y,bullet.width,bullet.height,null);
        }
        //绘制敌方战机
        for(int i=0;i<gameController.enemyPlaneList.size();i++){
            EnemyPlane enemyPlane=gameController.enemyPlaneList.get(i);
            g.drawImage(enemyPlane.image,enemyPlane.x,enemyPlane.y,enemyPlane.width, enemyPlane.height, null);
        }
        //绘制敌方子弹
        for(Bullet bullet:gameController.enemyBulletList){
            if(bullet!=null){
                g.drawImage(bullet.image, bullet.x, bullet.y,bullet.width, bullet.height, null);
            }
        }
    }
}