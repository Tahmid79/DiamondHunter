package com.neet.DiamondHunter.MapViewer;
import com.neet.DiamondHunter.TileMap.*;

import com.neet.DiamondHunter.MapViewer.*;
import com.neet.DiamondHunter.TileMap.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.swing.*;

public class Main extends JFrame {

    GamePanel gp ;

    public Main(){
        gp = new GamePanel();
        setSize(1200,1100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(gp);
    }

    public static void main(String[] args){

        Main mn = new Main();
    }


}

