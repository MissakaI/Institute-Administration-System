/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.other.FieldValidator;
import lk.ijse.gdse41.ias.view.DashBoardController;
import lk.ijse.gdse41.ias.view.controller.InnerSidePaneController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class RegisterStudentController implements Initializable {

    @FXML
    private JFXTextField txtStuId;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtTelPri;
    @FXML
    private JFXTextField txtTelSec;
    @FXML
    private JFXTextArea txtAddress;
    @FXML
    private JFXTextField txtGuardian;
    @FXML
    private JFXComboBox cmbSection;
    @FXML
    private JFXComboBox cmbYear;
    @FXML
    private JFXButton btnNext;
    @FXML
    private DatePicker dtpckrDOB;
    @FXML
    private ImageView imgProPic;
    @FXML
    private JFXRadioButton rBtnMale;
    @FXML
    private JFXRadioButton rBtnFemale;
    @FXML
    private ToggleGroup grpGender;
    
    public static StudentDTO s;
    
    
    
    BatchController ctrlBatch;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ctrlBatch=(BatchController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.BATCH);
        
        grpGender=new ToggleGroup();
        rBtnMale.setToggleGroup(grpGender);
        rBtnFemale.setToggleGroup(grpGender);
        dtpckrDOB.setValue(LocalDate.of(2000, 1, 1));
        loadSections();
        cmbYear.setDisable(true);
        txtStuId.setEditable(false);
        String prefix=""+LocalDate.now().getYear();

        txtStuId.setText(IDGenarator.getNewID("student", "sid", prefix));

        if (s!=null){
            txtStuId.setText(s.getSid());
            txtFName.setText(s.getfName());
            txtLName.setText(s.getlName());
            txtGuardian.setText(s.getGuardian());
            txtTelPri.setText(s.getStudentTele());
            txtTelSec.setText(s.getGuardianTele());
            txtAddress.setText(s.getAddress());
            txtNIC.setText(s.getNIC());
            if(s.getGender()){
                rBtnMale.setSelected(true);
            }else{
                rBtnFemale.setSelected(true);
            }
        }else{
            //txtStuId.setText(null);
            txtFName.setText(null);
            txtLName.setText(null);
            txtGuardian.setText(null);
            txtTelPri.setText(null);
            txtTelSec.setText(null);
            txtAddress.setText(null);
            txtNIC.setText(null);
        }
        
        txtNIC.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            System.out.println("Validating");
            if (!newValue){
                FieldValidator.validateNIC(txtNIC);
            }
        });
        
        txtTelPri.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            System.out.println("Validating");
            if (!newValue){
                FieldValidator.validateTelephoneNumber(txtTelPri);
            }
        });
        
        txtTelSec.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            System.out.println("Validating");
            if (!newValue){
                FieldValidator.validateTelephoneNumber(txtTelSec);
            }
        });
        
    }

    public void nxtBtnAction(ActionEvent actionEvent){
        LocalDate dateDOB=dtpckrDOB.getValue();
        String bid=getRelevantBID();
        String DOB=dateDOB.toString();
        String address=txtAddress.getText();
        boolean isMale=getGender();
        System.out.println(isMale);
        s=new StudentDTO(txtStuId.getText(), txtFName.getText(), txtLName.getText(), 
                address, DOB, isMale, txtNIC.getText(), txtTelPri.getText(), 
                txtGuardian.getText(),txtTelSec.getText(), bid, "");
        
        AnchorPane containPane;
        try {
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/AddStudentSubjects.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    public void cmbSectionAction(ActionEvent actionEvent){
        loadSectionYears();
    }
    
    private void loadSections(){
        try {
            ArrayList<String> sectionList=new ArrayList<>();
            sectionList=ctrlBatch.getSections();
            cmbSection.getItems().clear();
            for(String section:sectionList){
                cmbSection.getItems().add(section);
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void loadSectionYears(){
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        try {
            ArrayList<Integer> sectionYearList=new ArrayList<>();
            sectionYearList=ctrlBatch.getSectionYears(section);
            if (!sectionYearList.isEmpty()){
                cmbYear.setDisable(false);
            }
            cmbYear.getItems().clear();
            for(int year:sectionYearList){
                cmbYear.getItems().add(year);
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbYear.getSelectionModel().selectFirst();
    }
    
    public String getRelevantBID(){
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        int year=Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString());
        try{
            BatchDTO bdto=ctrlBatch.search(new BatchDTO("", "", year, section));
            return bdto.getBid();
        } catch (Exception ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private boolean getGender(){
        boolean isMale;
        if (grpGender.getSelectedToggle().equals(rBtnMale)){
            isMale=true;
        }else{
            isMale=false;
        }
        return isMale;
    } 
    
    //Validation
    @FXML
    void txtNICAction(KeyEvent event) {
        if(!(event.getCharacter().matches("[VvXx]")))
            FieldValidator.onlyNumeric(event);
    }
    
    @FXML
    void txtTelAction(KeyEvent event) {
        if(!(event.getCharacter().matches("[+-]")))
            FieldValidator.onlyNumeric(event);
    }
    
    @FXML
    void txtNameAction(KeyEvent event) {
        if(!(event.getCharacter().matches("[ ]")))
            FieldValidator.onlyAlpha(event);
    }
    
    @FXML
    void txtAddressAction(KeyEvent event) {
        if(!(event.getCharacter().matches("[\\W\n]")))
            FieldValidator.onlyAlpha(event);
    }
}
