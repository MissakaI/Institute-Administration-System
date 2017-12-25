/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import lk.ijse.gdse41.ias.view.controller.AddStudentSubjectsController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.gdse41.ias.other.IDGenarator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.other.FieldValidator;
import lk.ijse.gdse41.ias.view.DashBoardController;
import lk.ijse.gdse41.ias.view.controller.InnerSidePaneController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class RegisterInstructorController implements Initializable {

    @FXML
    JFXTextField txtTutId;
    @FXML
    JFXTextField txtFName;
    @FXML
    JFXTextField txtLName;
    @FXML
    JFXTextField txtNIC;
    @FXML
    JFXTextField txtUsername;
    @FXML
    JFXTextField txtPassword;
    @FXML
    JFXTextField txtTelPri;
    @FXML
    JFXTextField txtTelSec;
    @FXML
    JFXTextField txtAddNo;
    @FXML
    JFXTextField txtAddLane1;
    @FXML
    JFXTextField txtAddLane2;
    @FXML
    JFXTextField txtAddCity;
    @FXML
    JFXButton btnNext;
    @FXML
    JFXComboBox cmbTitle;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtTutId.setText(IDGenarator.getNewID("tutor", "tid", "T"));
        
        
        String[] titles=new String[]{"Mr.", "Ms.", "Mrs.", "Prof.", "Dr.", "Rev"};
        for (String title : titles) {
            cmbTitle.getItems().add(title);
        }
        
        txtNIC.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            System.out.println("Validating");
            if (!newValue){
                FieldValidator.validateNIC(txtNIC);
            }
        });
        
    }

    public static TutorDTO tutor;
    
    @FXML
    private void btnNextAction(){
        String tutId=txtTutId.getText();
        String nic=txtNIC.getText();
        String fname=txtFName.getText();
        String lname=txtLName.getText();
        String title=cmbTitle.getSelectionModel().getSelectedItem().toString();
        String telPri=txtTelPri.getText();
        String telSec=txtTelSec.getText();
        String address=String.format("%s, %s, %s, %s", txtAddNo.getText(), txtAddLane1.getText(), txtAddLane2.getText(), txtAddCity.getText());
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        
        tutor=new TutorDTO(tutId, title, fname, lname, address, nic, telPri, telSec, username, password);
        
        AnchorPane containPane;
        try{
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/AddInstructorSubjects.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void txtNICAction(KeyEvent event) {
        if(!(event.getCharacter().matches("[VvXx]")))
            FieldValidator.onlyNumeric(event);
            //FieldValidator.validateNIC(txtNIC);
    }
    
    @FXML
    private ImageView imgSchedule;

    @FXML
    private ImageView imgRegister;
    
    @FXML
    private ImageView imgPayments;
    
    @FXML
    void imgRegisterAction(MouseEvent event){
        InnerSidePaneController.getInstance().imgTutor(event);
        //System.out.println("Press reg");
    }
    
    @FXML
    void imgScheduleAction(MouseEvent event){
        InnerSidePaneController.getInstance().imgSchedule(event);
    }

    @FXML
    void imgPaymentAction(MouseEvent event) {
        InnerSidePaneController.getInstance().imgPayment(event);
    }
}
