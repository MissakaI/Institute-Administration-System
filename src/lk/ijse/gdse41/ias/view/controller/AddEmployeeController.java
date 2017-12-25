/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.dto.EmployeeDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class AddEmployeeController implements Initializable {
    
    
    @FXML
    private JFXTextField txtEmpId;

    @FXML
    private JFXTextField txtNIC;

    @FXML
    private JFXComboBox cmbTitle;

    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXTextField txtTelPri;

    @FXML
    private JFXTextField txtTelSec;

    @FXML
    private JFXTextField txtAddNo;

    @FXML
    private JFXTextField txtAddLane1;

    @FXML
    private JFXTextField txtAddLane2;

    @FXML
    private JFXTextField txtAddCity;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXComboBox cmbAccess;

    @FXML
    private JFXButton btnNext;
    
    @FXML
    private DatePicker dtpckrDOB;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] titles=new String[]{"Mr.", "Ms.", "Mrs.", "Prof.", "Dr.", "Rev"};
        for (String title : titles) {
            cmbTitle.getItems().add(title);
        }
        
        String[] accessLevels=new String[]{"Reception","Manager","Class Manager","System Admin"};
        for (String access : accessLevels) {
            cmbAccess.getItems().add(access);
        }
        
        dtpckrDOB.setValue(LocalDate.of(2000, 1, 1));
        txtEmpId.setText(IDGenarator.getNewID("employee", "eid", "E"));
    }
    
    //private void 
    
    @FXML
    private void btnNextAction(){
        String empID=txtEmpId.getText();
        String nic=txtNIC.getText();
        String fname=txtFName.getText();
        String lname=txtLName.getText();
        String title=cmbTitle.getSelectionModel().getSelectedItem().toString();
        LocalDate dateDOB=dtpckrDOB.getValue();
        int age=LocalDate.now().compareTo(dateDOB);
        String dob=dateDOB.toString();
        String telPri=txtTelPri.getText();
        String telSec=txtTelSec.getText();
        String address=String.format("%s, %s, %s, %s", txtAddNo.getText(), txtAddLane1.getText(), txtAddLane2.getText(), txtAddCity.getText());
        String username=txtUsername.getText();
        String password=txtPassword.getText();
        String access=cmbAccess.getSelectionModel().getSelectedItem().toString();
        
        EmployeeDTO emp=new EmployeeDTO(empID, title, fname, lname, address, dob, nic, age, telPri, telSec, username, password, access);
        
        try {
            Boolean isAdded=ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.EMPLOYEE).add(emp);
        } catch (Exception ex) {
            Logger.getLogger(AddEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
