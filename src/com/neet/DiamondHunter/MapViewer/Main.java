package  com.neet.DiamondHunter.MapViewer;
import com.neet.DiamondHunter.TileMap.*;


import javax.swing.*;

public class Main extends JFrame {

    GamePanel gp ;

    public Main(){
        gp = new GamePanel();
        setSize(800,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(gp);
    }

    public static void main(String[] args){

        Main mn = new Main();
    }


}

