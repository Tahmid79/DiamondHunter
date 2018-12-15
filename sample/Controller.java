package com.neet.DiamondHunter.sample;

import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements  Initializable  {

   @FXML
   Button btn ,btn2;
   @FXML
    Label label;
   @FXML
    AnchorPane anc ;
   @FXML
    ImageView bt ;
   @FXML
    StackPane stp;

   boolean bt_movable=false;
   int bt_count=0;

   @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
      //Stage stage = (Stage) root.getScene().getWindow();
        //you can use label instead of root.
        Stage stage= (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void jp() {

      anc.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              final double x = event.getX();
              final double y = event.getY();
              label.setText("X = " + (Math.round(x)/12)+" Y = " + (Math.round(y)/12) );



             }

             //stp.removeEventHandler(MouseEvent.MOUSE_CLICKED,this::handle);


      });

    }


    public void np(){

      stp.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {


            }


      });

    }



    @Override
    public void initialize(URL location, ResourceBundle resources)  {

    jp();
    np();

  }

}
