/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.controller.custom.ClassScheduleController;
import lk.ijse.gdse41.ias.controller.custom.RegistrationController;
import lk.ijse.gdse41.ias.controller.custom.StudentController;
import lk.ijse.gdse41.ias.controller.custom.SubjectController;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.controller.custom.impl.ClassScheduleControllerImpl;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.dto.SubjectDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.tablemodel.StudentPaymentsTableModel;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class MakeClassPaymentController implements Initializable {
    
    @FXML
    private JFXComboBox cmbTutor;

    @FXML
    private JFXComboBox cmbYear;

    @FXML
    private JFXComboBox cmbSubject;

    @FXML
    private JFXComboBox cmbSection;

    @FXML
    private JFXTextField txtSID;
    
    @FXML
    private JFXButton btnMakePayment;
    
    @FXML
    private JFXTextField txtAmount;
    
    @FXML
    private TableView tblPayment;
    
    @FXML
    private TableColumn colStudentId;
    
    @FXML
    private TableColumn colClass;
    
    @FXML
    private TableColumn colAmount;
    
    @FXML
    private JFXToggleButton tglbtnFree;
    
    /**
     * Initializes the controller class.
     */
    
    BatchController ctrlBatch;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctrlBatch=(BatchController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.BATCH);
        ctrlSubject=(SubjectController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.SUBJECT);
        
        loadSections();
        tutorList=new ArrayList<>();
        subjectList=new ArrayList<>();
        
        colStudentId.setCellValueFactory(new PropertyValueFactory("sid"));
        colClass.setCellValueFactory(new PropertyValueFactory("classDetails"));
        colAmount.setCellValueFactory(new PropertyValueFactory("amount"));
        
        tblPayment.setItems(tblModel);
    }

    private String bid;
    private ArrayList<TutorDTO> tutorList;
    private ArrayList<SubjectDTO> subjectList;
    private String cid;
    private ClassScheduleDTO cs;
    private PaymentDTO payment;
    private RegistrationDTO registration;
    private SubjectController ctrlSubject;
    
    private ObservableList<StudentPaymentsTableModel> tblModel=FXCollections.observableArrayList();
    
    @FXML
    public void cmbSectionAction(){
        loadSectionYears();
    }
    
    @FXML
    public void cmbYearsAction(ActionEvent actionEvent){
        loadClassSubjects();
    }
    
    @FXML
    public void cmbSubjectAction(ActionEvent actionEvent){
        if (!cmbSubject.getSelectionModel().isEmpty())
            loadSubjectTutors();
        else
            cmbTutor.getItems().clear();
    }
    
    @FXML
    public void cmbTutorAction(ActionEvent actionEvent){
        getRelavantClass();
        txtAmount.setText(""+cs.getM_fee());
    }
    
    @FXML
    public void txtSidKeyReleaseAction(KeyEvent keyEvent){
        String sid=txtSID.getText();
        if(sid.length()==8){
            try {
                StudentController ctrlStudent=(StudentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.STUDENT);
                StudentDTO s=ctrlStudent.search(new StudentDTO(sid, "", "", "", "", false, "", "", "", "", "", ""));
                btnMakePayment.setDisable(false);
                RegistrationController ctrlReg=(RegistrationController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.REGISTRATION);
                registration=ctrlReg.search(new RegistrationDTO("", sid, cid));
                if (registration==null){
                    Alert alert=new Alert(Alert.AlertType.ERROR, "No registration found for the Student for this class!" , ButtonType.OK);
                    alert.show();
                    btnMakePayment.setDisable(true);
                }
            } catch (Exception ex) {
                Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    public void btnMakePaymentAction(ActionEvent actionEvent){
        PaymentDTO paymentdto=new PaymentDTO(registration.getRid(), tglbtnFree.isSelected());
        try {
            boolean payDone=ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.PAYMENT).add(paymentdto);
            if (payDone){
                double amount=Double.parseDouble(txtAmount.getText());
                if (tglbtnFree.isSelected()){
                    amount=0;
                }
                StudentPaymentsTableModel sptm=new StudentPaymentsTableModel(
                        registration.getSid(), 
                        cmbSubject.getSelectionModel().getSelectedItem().toString()+"-"+cmbSection.getSelectionModel().getSelectedItem().toString(),
                        amount);
                tblModel.add(sptm);
            }
        } catch (Exception ex) {
            Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            MarkStudentAttendanceController.getInstance().refresh();
            tglbtnFree.setSelected(false);
        }
    }
    
    private void loadSections(){
        try {
            ArrayList<String> sectionList=new ArrayList<>();
            sectionList=ctrlBatch.getSections();
            cmbSection.getItems().clear();
            for(String section:sectionList){
                cmbSection.getItems().add(section);
            }
            //cmbSection.getSelectionModel().select(studentSection);
        } catch (Exception ex) {
            Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
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
            
            //cmbYear.getSelectionModel().select(studentYear);
        } catch (Exception ex) {
            Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSubjectTutors(){
        //String bid=getRelevantBID();
        ArrayList<SubjectDTO> subjectList=new ArrayList<>();
        int sub=cmbSubject.getSelectionModel().getSelectedIndex();
        
        
        try {
            subjectList=ctrlSubject.get(new SubjectDTO(bid));
            String subID=subjectList.get(sub).getSubID();
            tutorList.clear();
            TutorController ctrlTutor=(TutorController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR);
            tutorList=ctrlTutor.get(new TutorDTO(subID, bid));
            
            cmbTutor.getItems().clear();
            for(TutorDTO tutor:tutorList){
                cmbTutor.getItems().add(tutor.getfName()+" "+tutor.getlName());
            }
        } catch (Exception ex) {
            Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRelevantBID(){
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        int year=Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString());
        try{
            BatchDTO bdto=ctrlBatch.search(new BatchDTO("", "", year, section));
            return bdto.getBid();
        } catch (Exception ex) {
            Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void loadClassSubjects(){
        bid=getRelevantBID();
        
        try {
            subjectList.clear();
            subjectList=ctrlSubject.get(new SubjectDTO(bid));
            
            cmbSubject.getItems().clear();
            for(SubjectDTO subject:subjectList){
                cmbSubject.getItems().add(subject.getSubName());
            }
        } catch (Exception ex) {
            Logger.getLogger(MakeClassPaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getRelavantClass(){
        TutorDTO t=tutorList.get(cmbTutor.getSelectionModel().getSelectedIndex());
        SubjectDTO s=subjectList.get(cmbSubject.getSelectionModel().getSelectedIndex());
        try {
            ClassScheduleController ctrlCS=(ClassScheduleController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.CLASS_SCHEDULE);
            cs=ClassScheduleControllerImpl.getClassSchedule(bid, t.getTid(), s.getSubID());
            //cs=ctrlCS
            cid=cs.getCid();
            //return cid;
        } catch (SQLException ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return null;
    }
    
    
}
