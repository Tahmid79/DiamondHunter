package com.neet.DiamondHunter.MapViewer;
import com.neet.DiamondHunter.MapViewer.*;
import com.neet.DiamondHunter.TileMap.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

        private Image dbImage ;
        private Graphics dbg;

        static final int GWIDTH = 1200 , GHEIGHT = 1100;
        static final Dimension gameDim = new Dimension(GWIDTH,GHEIGHT);

        private Thread game;
        private volatile boolean running = false;

        //Game objects

    World1 world;

    public TileMap tileMap;



    public GamePanel(){


        world = new World1();
        world.loadTiles();
        world.loadMap();

        setPreferredSize(gameDim);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();

        //Handle all key inputs from user
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }

    public void run(){
        while(running){
            gameUpdate();
            gameRender();
            paintScreen();
        }
    }

    private void gameUpdate(){
        if(running  && game != null){
            //update game state
        }
    }

    private void gameRender(){
        if(dbImage == null){  // create the buffer
          dbImage = createImage( GWIDTH, GHEIGHT );
          if(dbImage==null){
              System.err.println("dbImage is still null");
              return;
          }else{
              dbg = dbImage.getGraphics();
          }

        }
        //Clear the screen
        dbg.setColor(Color.white);
        dbg.fillRect( 0,0,GWIDTH, GHEIGHT );
        draw(dbg);
    }

    //Draw all game content in this method
    public void draw(Graphics g){

        world.draw(g);
    }

    private void paintScreen(){
        Graphics g  ;

        try{
            g  = this.getGraphics();
            if(dbImage != null   &&  g!=null)
                g.drawImage(dbImage,0,0,null);

        g.dispose();
    }catch (Exception e){
        System.err.println(e);
    }
    }

    public void addNotify(){
       super.addNotify();
       startGame();
   }

    private void startGame(){

        if( game == null ||  !running){
            game  = new Thread(this);
            game.start();
            running = true;
        }
    }

    public void stopGame(){
        if(running){
            running = false;
        }
    }



    private void log(String s){
        System.out.println(s);
    }
}
