package com.neet.DiamondHunter.MapViewer;
import com.neet.DiamondHunter.MapViewer.*;
import com.neet.DiamondHunter.TileMap.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class World {

    private Rectangle[] blocks;
    private Image[] blockImg ;
    private int arraynum = 100;

    private Image tilemap ;
    public BufferedImage tls ;

    private int x , y ;

    public World(){

    //tilemap = new ImageIcon("C:\\Users\\Tahmid Farhan\\Desktop\\LC\\CW2-Diamond-Hunter\\DiamondHunter\\Resources\\Tilesets\\testtileset.gif") .getImage() ;

    File img = new File("Resources/Tilesets/testtileset.gif");

    try {
        tls = ImageIO.read(img);
    }catch (IOException e){
      e.printStackTrace();
    }


    blocks  = new Rectangle[500] ;
    blockImg = new Image[500];


    loadarray();

    }

    private void loadarray(){

      for(int i=0 ; i<arraynum ; i++){
         if(x>=800) {
            x=0 ;
           y+=40;

         }
          if(i>=0  &&  i<=100){

                blockImg[i] =  tls.getSubimage(0,16,16,16);
                blocks[i]  =new Rectangle(x,y,100,40);
          }

          x+=100 ;
      }

    }

    public void draw(Graphics g){

        for(int i=0 ; i<arraynum ; i++ ) {

            g.drawImage(blockImg[i], 34*i, 32, null);

        }
    }


}
