/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse41.ias.view.DashBoardController;

/**
 *
 * @author midda
 */
public class InnerSidePaneController {
    private static InnerSidePaneController ctrlInner;

    private InnerSidePaneController() {
        
    }
    
    public static InnerSidePaneController getInstance(){
        if (ctrlInner==null){
            ctrlInner=new InnerSidePaneController();
        }
        return ctrlInner;
    }
    
    
    public void imgSchedule(MouseEvent actionEvent){
        load("/lk/ijse/gdse41/ias/view/ManageClass.fxml");
    }
    
    public void imgTutor(MouseEvent actionEvent){
        load("/lk/ijse/gdse41/ias/view/RegisterInstructor.fxml");
    }
    
    public void imgPayment(MouseEvent actionEvent){
        load("/lk/ijse/gdse41/ias/view/ManageProfit.fxml");
    }
    
    private void load(String resourcePath){
        AnchorPane containPane;
        try{
            containPane = FXMLLoader.load(getClass().getResource(resourcePath));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
