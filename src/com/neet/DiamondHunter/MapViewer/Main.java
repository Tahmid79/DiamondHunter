package com.neet.DiamondHunter.MapViewer;
import javax.swing.*;
public class Main extends JFrame {

    GamePanel gp ;//Creating instances for GamePanel.

    public Main(){
        gp = new GamePanel();
        setSize(1200,1100);//Width and Height of application.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);//Setting visibility to true.
        setResizable(false);//Setting sizable property of application interface of application to false.
        add(gp);
    }

    public static void main(String[] args){

        Main mn = new Main();
    }


}

