/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import lk.ijse.gdse41.ias.main.StartProject;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class DashBoardController implements Initializable {

    @FXML
    private JFXDrawer drwr;
    @FXML
    private JFXHamburger hmbrgr;
    @FXML
    public AnchorPane root;
    @FXML
    public AnchorPane contain;
    @FXML
    public AnchorPane shade;

    public static AnchorPane rootP;
    public static AnchorPane containP;
    public static AnchorPane shadeP;
    public static JFXHamburger hmbrgrP;
    public static JFXDrawer drwrP;
    public static HamburgerBackArrowBasicTransition transitionP;




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(!StartProject.isSplashLoaded){
            loadSpalashScreeen();
        }

        rootP=root;
        containP=contain;
        shadeP=shade;
        hmbrgrP=hmbrgr;
        drwrP=drwr;
        transitionP = new HamburgerBackArrowBasicTransition(hmbrgr);
        shade.setVisible(false);
        
        AnchorPane sideBar=new AnchorPane();
        try{
            sideBar=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/SidePane.fxml"));
            drwr.setSidePane(sideBar);
            drwr.close();
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            AnchorPane containPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/RegisterStudent.fxml"));
            contain.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        transitionP.setRate(-0.7);
        hmbrgr.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            hmbrgClickAction();
        });
        
//        shade.focusedProperty().addListener(((observable, oldValue, newValue) -> {
//            if(!newValue){
//                System.out.println("Focus");
//                hmbrgClickAction();
//            }
//        }));
        ///////////////////////////////////
    }
    
    private void hmbrgClickAction(){
        transitionP.setRate(transitionP.getRate() * -1);
        transitionP.play();

        if (drwr.isShown()) {
            drwr.close();

            FadeTransition fadeOut=new FadeTransition(Duration.millis(800),shade);
            fadeOut.setFromValue(0.8);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            fadeOut.play();
            fadeOut.setOnFinished(l -> {
                shade.setVisible(false); 
            });
            drwr.setDisable(true);
        } else {
            try {
                AnchorPane sideBar = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/SidePane.fxml"));
                drwr.setDisable(false);
                drwr.setSidePane(sideBar);
                drwr.open();
                shade.setVisible(true);
                FadeTransition fadeIn=new FadeTransition(Duration.millis(800),shade);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(0.8);
                fadeIn.setCycleCount(1);
                fadeIn.play();
               // fade.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    private void loadSpalashScreeen(){
        StartProject.isSplashLoaded=true;
        
        try {
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/SplashScreen.fxml"));
            root.getChildren().setAll(pane);
            
            FadeTransition fadeIn=new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            
            FadeTransition fadeOut=new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);
            
            fadeIn.play();
            
            fadeIn.setOnFinished((e) ->{
                fadeOut.play();
            });
            
            fadeOut.setOnFinished((e) ->{
                try {
                    AnchorPane parentPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/DashBoard.fxml"));
                    root.getChildren().setAll(parentPane);
//                    FadeTransition fadeInPP=new FadeTransition(Duration.seconds(3), parentPane);
//                    fadeInPP.setFromValue(0);
//                    fadeInPP.setToValue(1);
//                    fadeInPP.setCycleCount(1);
//                    fadeIn.play();s
                } catch (IOException ex) {
                    Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (IOException ex) {
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void humburgAction(ActionEvent actionEvent){
            
            transitionP.setRate(transitionP.getRate() * -1);
            transitionP.play();

            if (drwrP.isShown()) {
                drwrP.close();
                
                    FadeTransition fadeOut=new FadeTransition(Duration.millis(800),shadeP);
                    fadeOut.setFromValue(0.8);
                    fadeOut.setToValue(0);
                    fadeOut.setCycleCount(1);
                    fadeOut.play();
                    fadeOut.setOnFinished(l -> {
                        shadeP.setVisible(false); 
                    });
                   
            }
    }
    
    public static ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
        System.out.println("Triggered");
        double stageHeight=StartProject.stage.getHeight();
        double stageWidth=StartProject.stage.getWidth();
        drwrP.setPrefHeight(stageHeight);
        containP.setPrefHeight(stageHeight);
        drwrP.setPrefWidth(stageWidth);
        containP.setPrefWidth(stageWidth);
    };
}
