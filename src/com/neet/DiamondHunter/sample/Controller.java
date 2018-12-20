package com.neet.DiamondHunter.sample;

import com.neet.DiamondHunter.MapViewer.World1;

import javafx.animation.PauseTransition;

import javafx.event.EventHandler;
import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.*;



public class Controller  {
    World1 wr; //Creating instance of World1 class

    /*Creating instance for panes,buttons to give functionalities*/

   @FXML
   Button btn ,btn2;
   @FXML
    Label  blocked , axPos , btPos;
   @FXML
    AnchorPane anc, Parent ;
   @FXML
    ImageView bt , map ;
   @FXML
    StackPane stp , stp2;

   /*Variable declarations*/
   boolean bt_moveable=false , ax_moveable=false,tileblocked=false;
   int bt_count=0;
   int ax_count=0;

   int coord1, coord2;


    int br, bc;
    int ar, ac;





  public void tileblocked(){


      map.addEventHandler (MouseEvent.MOUSE_MOVED,event -> { //Adding mouse listeners for map.
               /*Each tile size is 20*/
               int x = (int) ((event.getX()) / 12); //Set size of image 480*480
               int y = (int) ((event.getY()) / 12); //So, for getting each tile divided by 12.

               if (x >= 40)   //To avoid NULL pointer exceptions.
                   x = 39;
               if (y >= 40)
                   y = 39;


               int p = wr.map[y][x] / 20;

               //Condition for BLOCKED/UNBLOCKED tile.
               if (p == 0) {
                   blocked.setText("R = " + y + " C =  " + x + "\nNot Blocked");
                   tileblocked = false;
               }

               if (p == 1) {
                   blocked.setText("R = " + y + " C =  " + x + "\nBlocked");
                   tileblocked = true;
               }


               });





   }





    @FXML
    /*This class checks condition for mobility of boat and axe across the map.*/
    public void jp() {

      anc.setOnMouseClicked(new EventHandler<MouseEvent>() { //Adding mouse listener to AnchorPane

          @Override
          public void handle(MouseEvent event) {
              final double x = event.getX();
              final double y = event.getY();


             if(bt_moveable && !tileblocked){
                 coord1=((int)Math.round(x)/12)*12;
                 coord2=((int)Math.round(y)/12)*12;


                  stp.setLayoutX( coord1 );
                  /*When mouse is clicked once on map bt_count becomes 2 as bt_count is incremented one in imageview
                  as well as in AnchorPane*/

                  stp.setLayoutY( coord2 );
                  bt_count++;


                if (bt_count == 3 || bt_count > 3) {//When bt_count becomes 3 we are setting it to 0.
                 bt_moveable = false;
                 bt_count = 0;
            }

                            }

////////////////////////////////////////////////////////////////////////////////
              //Similar to above(axe)
              if(ax_moveable && !tileblocked){

                  stp2.setLayoutX(  ( (int)Math.round(x)/12)*12 );
                  stp2.setLayoutY(  ( (int)Math.round(y)/12)*12 );
                  ax_count++;

                  if(ax_count==3 || ax_count>3){
                      ax_moveable=false;
                      ax_count=0;
                  }

              }
             }

      });

    }


    public void np(){ //Stackpane contains image of boat.

      stp.setOnMouseClicked(new EventHandler<MouseEvent>() {//Mobility of boat across the map.
          @Override
          public void handle(MouseEvent event) {
              bt_count++;
              if(bt_count==1)
              bt_moveable=true;
            }
      });

    }



    public void ap(){//Stackpane contains image of axe

        stp2.setOnMouseClicked(new EventHandler<MouseEvent>() {//Mobility of axe across the map.
            @Override
            public void handle(MouseEvent event) {
                ax_count++;
                if(ax_count==1)
                    ax_moveable=true;

            }
        });

    }

    public void showPos() {//DISPLAYS the boat and axe coordinates
        PauseTransition wait = new PauseTransition(Duration.millis(10)); //Updates the mouse position every 10 ms.
        wait.setOnFinished((e) -> {


            br = ((int)   Math.round( stp.getLayoutY()) / 12 );
            bc = ((int)    Math.round( stp.getLayoutX()) / 12   );

            ar = ((int) Math.round( stp2.getLayoutY()) / 12 );
            ac = ((int) Math.round( stp2.getLayoutX()) / 12 );

            btPos.setText("Boat Position\nR = " + br + " C = " + bc);
            axPos.setText("Axe Position\nR = " + ar + " C = " + ac);

            wait.playFromStart();
        });
        wait.play();


    }

    public void setBoatRC ()  throws Exception {
        //Writing the boat coordinates in a file.
        File file = new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr  =new BufferedReader(new FileReader(file));
        int [] arr = new int[4];

        int i = 0;
        String st;

        while( (st=brr.readLine() )!=null ){
            arr[i] = Integer.parseInt(st);
            i++;
        }

        arr[0] = br;
        arr[1] = bc;

        PrintWriter prw = new PrintWriter(new FileWriter(file));

        prw.printf("%d\n%d\n%d\n%d",arr[0],arr[1],arr[2],arr[3]);
        prw.close();

    }

    public void setAxeRC() throws Exception {
        //Writing the axe coordinates in a file.
        File file = new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr  =new BufferedReader(new FileReader(file));
        int [] arr = new int[4];

        int i = 0;
        String st;

        while( (st=brr.readLine() )!=null ){
            arr[i] = Integer.parseInt(st);
            i++;
        }

        arr[2] = ar;
        arr[3] = ac;

        PrintWriter prw = new PrintWriter(new FileWriter(file));

        prw.printf("%d\n%d\n%d\n%d",arr[0],arr[1],arr[2],arr[3]);
        prw.close();

    }


    /*Getting boat and axe coordinates from the file*/
    public static int getBoatRow() throws Exception{
        int row=0;

        File file =new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr  = new BufferedReader(new FileReader(file));

        String st;
        int count=0;

        while ((st=brr.readLine()) !=null ){
            if(count==0){
                row = Integer.parseInt(st);
            }
            count++;
        }
        return row;
    }

    public static int getBoatCol() throws Exception{
        int col=0;

        File file =new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr  = new BufferedReader(new FileReader(file));

        String st;
        int count=0;

        while ((st=brr.readLine()) !=null ){
            if(count==1){
                col = Integer.parseInt(st);
            }
            count++;
        }
        return col;
    }

    public static int getAxeRow() throws Exception{
        int row=0;

        File file =new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr  = new BufferedReader(new FileReader(file));

        String st;
        int count=0;

        while ((st=brr.readLine()) !=null ){
            if(count==2){
                row = Integer.parseInt(st);
            }
            count++;
        }
        return row;
    }

    public static int getAxeCol() throws Exception{
        int col=0;

        File file =new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr  = new BufferedReader(new FileReader(file));

        String st;
        int count=0;

        while ((st=brr.readLine()) !=null ){
            if(count==3){
                col = Integer.parseInt(st);
            }
            count++;
        }
        return col;
    }



    public void initPos() throws Exception{//setting the boat,axe coordinates on map.

       File file = new File("src/com/neet/DiamondHunter/sample/cord.txt");
        BufferedReader brr = new BufferedReader(new FileReader(file));

        int arr[] = new int[4];

        String st;
        int count=0;

        while( (st=brr.readLine()) !=null){
            if(count==0){
                arr[0] = Integer.parseInt(st);
            }
            if(count==1){
                arr[1] = Integer.parseInt(st);
            }
            if(count==2){
                arr[2] = Integer.parseInt(st);
            }
            if(count==3){
                arr[3] = Integer.parseInt(st);
            }

            count++;

        }

        stp.setLayoutX(arr[1]*12);
        stp.setLayoutY(arr[0]*12);

        stp2.setLayoutX(arr[3]*12);
        stp2.setLayoutY(arr[2]*12);

    }



    public void initialize() {//Calling every functions
       //Sets background image for application.
       BackgroundImage bgIMG = new BackgroundImage(new Image("com/neet/DiamondHunter/sample/DRAFT3.jpg",645,586,false,true),BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
       Parent.setBackground(new Background(bgIMG));


        wr = new World1();
        wr.loadMap();

        try{
            initPos();
        }catch (Exception e){
            e.printStackTrace();
        }
        tileblocked();
        jp();
        np();
        ap();
        showPos();


    }

}
