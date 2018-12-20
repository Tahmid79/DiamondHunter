package com.neet.DiamondHunter.MapViewer;
import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {

        private Image dbImage ;
        private Graphics dbg;

        static final int GWIDTH = 1200 , GHEIGHT = 1100;
        static final Dimension gameDim = new Dimension(GWIDTH,GHEIGHT);

        private Thread game;
        private volatile boolean running = false;

        //Game objects

    World1 world;



    public GamePanel(){


        world = new World1();
        world.loadTiles(); //calling loadtiles,loadmap functions.
        world.loadMap();

        setPreferredSize(gameDim);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();


    }
    //Boolean to store running state
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


}
