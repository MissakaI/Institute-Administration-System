/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse41.ias.view.DashBoardController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class SidePaneController implements Initializable {
    
    @FXML
    JFXButton btnRegStu;
    @FXML
    JFXButton btnMarkStuAttend;
    @FXML
    JFXButton btnManageStu;
    @FXML
    JFXButton btnManageEmployees;
    @FXML
    JFXButton btnInstructorHandling;
    @FXML
    JFXButton btnManageHalls;
    @FXML
    JFXButton btnSMS;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void btnRegStuAction(){
        AnchorPane containPane;
        try {
            containPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/RegisterStudent.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnMarkStuAction(){
        AnchorPane containPane;
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/lk/ijse/gdse41/ias/view/MarkStudentAttendance.fxml"));
            fxmlLoader.setController(MarkStudentAttendanceController.getInstance());
            containPane=fxmlLoader.load();
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnManageEmployeesAction(){
        AnchorPane containPane;
        try {
            containPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/AddEmployee.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnInstructorHandlingAction(){
        AnchorPane containPane;
        try {
            containPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/ManageClass.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnManageHallAction(){
        AnchorPane containPane;
        try {
            containPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/ManageHall.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnSMSAction(){
        AnchorPane containPane;
        try {
            containPane=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/SendSMS.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(SidePaneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
