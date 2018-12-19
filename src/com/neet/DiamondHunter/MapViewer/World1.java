package com.neet.DiamondHunter.MapViewer;

import com.neet.DiamondHunter.Main.GamePanel;
import com.neet.DiamondHunter.MapViewer.*;
import com.neet.DiamondHunter.TileMap.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class World1 {


    // position
    public int x;
    public int y;
    public int xdest;
    public int ydest;
    public int speed;
    public boolean moving;

    // bounds
    public int xmin;
    public int ymin;
    public int xmax;
    public int ymax;

    // map
    public int[][] map;
    public int tileSize;
    public int numRows;
    public int numCols;
    public int width;
    public int height;

    // tileset
    public BufferedImage tileset;
    public int numTilesAcross;
    public Tile[][] tiles;

    // drawing
    public int rowOffset;
    public int colOffset;
    public int numRowsToDraw;
    public int numColsToDraw;

    //constructor
    public World1(){

        int tileSize = 16;
        this.tileSize = tileSize;
        numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        speed = 4;


    }

    public void loadTiles( ) {

        File img = new File("Resources/Tilesets/testtileset.gif");

        try {

            tileset = ImageIO.read(img);

            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subimage;
            for(int col = 0; col < numTilesAcross; col++) {
                subimage = tileset.getSubimage(
                        col * tileSize,
                        0,
                        tileSize,
                        tileSize
                );
                tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                subimage = tileset.getSubimage(
                        col * tileSize,
                        tileSize,
                        tileSize,
                        tileSize
                );
                tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap( ) {

        try {
            InputStreamReader mpfl = new InputStreamReader(getClass().getResourceAsStream("/Maps/testmap.map"));

           // InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(mpfl);

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            xmin = GamePanel.WIDTH - width;
            xmin = -width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymin = -height;
            ymax = 0;

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("in");
        }

    }

    public void draw(Graphics g) {

        for(int row = 0; row < 40; row++) {



            for(int col = 0; col < 40; col++) {


                if(map[row][col] == 0) continue;

                int rc = map[row][col];
                 int r = rc / numTilesAcross;
                 int c = rc % numTilesAcross;

                g.drawImage(
                        tiles[r][c].getImage().getScaledInstance(24,24,Image.SCALE_SMOOTH),
                        x + col * (tileSize+8),
                        y + row * (tileSize+8),
                        null
                );

            }

        }

    }


}
