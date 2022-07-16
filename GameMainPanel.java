package com.view;

import com.controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏的主界面：承载游戏区欢迎界面以及游戏区之间的切换
 */
public class GameMainPanel extends JPanel {
    public static GamePanel gamePanel=new GamePanel();
    public static GameStartPanel gameStartPanel;
    private  GameController gameController;
    public GameMainPanel(GameController gameController){
        this.gameController=gameController;
        this.setPreferredSize(new Dimension(600,900));
        gameStartPanel =new GameStartPanel(gameController);
        this.add(gamePanel);
        this.add(gameStartPanel);
    }
}
