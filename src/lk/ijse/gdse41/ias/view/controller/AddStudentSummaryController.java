/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.tablemodel.StudentRegistrationTableModel;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.StudentController;
import lk.ijse.gdse41.ias.view.DashBoardController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class AddStudentSummaryController implements Initializable {
    
    @FXML
    Label summary;
    @FXML
    JFXButton btnConfirm;
    @FXML
    JFXButton btnAddNewStu;
    @FXML
    JFXButton btnBack;
    @FXML
    private TableView tblStuRegistration;
    @FXML
    private TableColumn colTutor;
    @FXML
    private TableColumn colSub;
    @FXML
    private TableColumn colSection;
    @FXML
    private TableColumn colBatch;
    @FXML
    private TableColumn colFee;
    

    private final ObservableList<StudentRegistrationTableModel> stuReg=AddStudentSubjectsController.stuReg;
    private final ArrayList<RegistrationDTO> registrationList=AddStudentSubjectsController.registrationList;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        StudentDTO s=RegisterStudentController.s;
        String gen;
        if (s.getGender()){
            gen="Male";
        }else{
            gen="Female";
        }
        int age=LocalDate.parse(s.getDOB()).compareTo(LocalDate.now());
        String address=s.getAddress();
        address=address.replace('\n', ',');
        String sum=String.format("Student Details\n"
                + "ID\t\t: %s\tNIC : %s\n"
                + "Name\t: %s\t%s\n"
                + "DOB\t: %s\t Age : %s\n"
                + "In Case of Emergency\n"
                + "Gurdian\t: %s\n"
                + "Student Tel\t: %s\t\tGuardian Tel\t: %s\n"
                + "Address\t: %s",
                s.getSid(),s.getNIC(),s.getfName()+" "+s.getlName(),gen,s.getDOB(),age,s.getGuardian(),
                s.getStudentTele(),s.getGuardianTele(),address);
        summary.setText(sum);
        
        colTutor.setCellValueFactory(new PropertyValueFactory("tutor"));
        colSub.setCellValueFactory(new PropertyValueFactory("subject"));
        colSection.setCellValueFactory(new PropertyValueFactory("section"));
        colBatch.setCellValueFactory(new PropertyValueFactory("batch"));
        colFee.setCellValueFactory(new PropertyValueFactory("fee"));
        
        tblStuRegistration.setItems(stuReg);
    }
    
    @FXML
    public void btnConfirmAction(){
        try {
            StudentDTO s=RegisterStudentController.s;
            String address=s.getAddress();
            s.setAddress(address.replace('\n', ','));
            StudentController ctrl=(StudentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.STUDENT);
            Boolean val=ctrl.add(s, registrationList);
            if(val){
                Alert a=new Alert(Alert.AlertType.INFORMATION, "Student was successfully registered", ButtonType.OK);
                a.show();
            }else{
                Alert a=new Alert(Alert.AlertType.ERROR, "Couldn't complete relevant task", ButtonType.OK);
            }
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSummaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnAddNewStAction(){
        AnchorPane containPane;
        try {
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/RegisterStudent.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
            RegisterStudentController.s=null;
            stuReg.clear();
            registrationList.clear();
        } catch (IOException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnBackAction(){
        AnchorPane containPane;
        try {
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/AddStudentSubjects.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);            
        } catch (IOException ex) {
            Logger.getLogger(RegisterStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
