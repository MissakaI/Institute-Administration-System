/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import lk.ijse.gdse41.ias.controller.custom.impl.ClassScheduleControllerImpl;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.dto.SubjectDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.AttendanceController;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.controller.custom.PaymentController;
import lk.ijse.gdse41.ias.controller.custom.RegistrationController;
import lk.ijse.gdse41.ias.controller.custom.StudentController;
import lk.ijse.gdse41.ias.controller.custom.SubjectController;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.dto.AttendanceDTO;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;
import lk.ijse.gdse41.ias.tablemodel.StudentAttendanceTableModel;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class MarkStudentAttendanceController implements Initializable {
    @FXML
    private JFXComboBox cmbTutor;

    @FXML
    private JFXComboBox cmbYear;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXComboBox cmbSubject;

    @FXML
    private JFXComboBox cmbSection;

    @FXML
    private JFXTextField txtSID;
    
    @FXML
    private JFXButton btnMakePayment;
    
    @FXML
    private JFXButton btnRemove;
    
    @FXML
    private Label lblFName;
    
    @FXML
    private Label lblLName;
    
    @FXML
    private Label lblNIC;
    
    @FXML
    private Label lblPaymentStatus;
    
    @FXML
    private Label lblPaymentDate;
    
    @FXML
    private ImageView imgStudent;
    
    @FXML
    private TableView tblAttendance;
    
    @FXML
    private TableColumn colRid;
    
    @FXML
    private TableColumn colSid;
    
    @FXML
    private TableColumn colDate;
    
    @FXML
    private TableColumn colCheckIn;
    
    @FXML
    private TableColumn colPayment;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ctrlBatch=(BatchController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.BATCH);
        aci=(AttendanceController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.ATTENDANCE);
        ctrlPayment=(PaymentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.PAYMENT);
        ctrlSubject=(SubjectController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.SUBJECT);
        
        loadSections();
        tutorList=new ArrayList<>();
        subjectList=new ArrayList<>();
        
        colRid.setCellValueFactory(new PropertyValueFactory("rid"));
        colSid.setCellValueFactory(new PropertyValueFactory("sid"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory("checkIn"));
        colPayment.setCellValueFactory(new PropertyValueFactory("paymentStatus"));
        
        tblAttendance.setItems(attendanceList);
        
        txtSID.setText("S");
    }
    
    private static MarkStudentAttendanceController attendanceController;
    
    private MarkStudentAttendanceController(){
        
    }
    
    public static MarkStudentAttendanceController getInstance(){
        if (attendanceController==null){
            attendanceController=new MarkStudentAttendanceController();
        }
        return attendanceController;
    }
    
    
    private String bid;
    private ArrayList<TutorDTO> tutorList;
    private ArrayList<SubjectDTO> subjectList;
    private String cid;
    private PaymentDTO payment;
    private RegistrationDTO registration;
    private ObservableList<StudentAttendanceTableModel> attendanceList=FXCollections.observableArrayList();
    private AttendanceController aci;
    private BatchController  ctrlBatch;
    private PaymentController ctrlPayment;
    private SubjectController ctrlSubject;
    
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
        getRelavantCID();
        loadAttendance();
    }
    
    @FXML
    private void txtSidKeyReleaseAction(KeyEvent keyEvent){
        refresh();
    }

    public void refresh(){
        String sid=txtSID.getText();
        if(sid.length()==8){
            try {
                StudentController ctrlStudent=(StudentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.STUDENT);
                StudentDTO s=ctrlStudent.search(new StudentDTO(sid, "", "", "", "", false, "", "", "", "", "", ""));
                lblFName.setText(s.getfName());
                lblLName.setText(s.getlName());
                lblNIC.setText(s.getNIC());
                btnAdd.setDisable(false);
                btnMakePayment.setDisable(false);
                getPayment();
                
                if (!(payment==null)){
                    lblPaymentStatus.setText("Paid");
                    lblPaymentDate.setText(payment.getDate());
                }else{
                    lblPaymentStatus.setText("Not-Paid");
                    lblPaymentDate.setText("");
                }
            } catch (Exception ex) {
                Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void getPayment(){
        try {
            RegistrationController ctrl=(RegistrationController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.REGISTRATION);
            registration=ctrl.search(new RegistrationDTO("", txtSID.getText(), cid));
            if (registration==null){
                Alert alert=new Alert(Alert.AlertType.ERROR, "No registration found for the Student for this class!" , ButtonType.OK);
                alert.show();
                btnAdd.setDisable(true);
                btnMakePayment.setDisable(true);
            }
            int month=LocalDate.now().getMonth().getValue();
            payment=ctrlPayment.search(new PaymentDTO("", registration.getRid(), month, "", false));
        } catch (Exception ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getRelevantBID(){
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        int year=Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString());
        try{
            BatchDTO bdto=ctrlBatch.search(new BatchDTO("", "", year, section));
            return bdto.getBid();
        } catch (Exception ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getRelavantCID(){
        TutorDTO t=tutorList.get(cmbTutor.getSelectionModel().getSelectedIndex());
        SubjectDTO s=subjectList.get(cmbSubject.getSelectionModel().getSelectedIndex());
        try {
            ClassScheduleDTO cs=ClassScheduleControllerImpl.getClassSchedule(bid, t.getTid(), s.getSubID());
            cid=cs.getCid();
            return cid;
        } catch (SQLException ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @FXML
    private void btnAddAction(){
        String rid=registration.getRid();
        String sid=txtSID.getText();
        String a_date=LocalDate.now().toString();
        String checkIn=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("rid :"+rid+" sid : "+sid+" date : "+a_date+" checkIn : "+checkIn);
        String paymentStatus;
        if (!(payment==null)){
            if (payment.isFree_card()){
                paymentStatus="Free";
            }else{
                paymentStatus="Paid";
            }
        }else{
            paymentStatus="Not-Paid";
        }
        AttendanceDTO attendance=new AttendanceDTO(rid, a_date, checkIn);
        boolean isAdded=false;
        try {
            isAdded=aci.add(attendance);
        } catch (Exception ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (isAdded){
            StudentAttendanceTableModel satm=new StudentAttendanceTableModel(rid, sid, a_date, checkIn, paymentStatus);
            attendanceList.add(satm);
            tblAttendance.refresh();
        }
    }
    
    Stage paymentStage;
    @FXML
    private void btnMakePaymentAction(){
        try {
            Parent makePayement=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/MakeClassPayment.fxml"));
            if (paymentStage==null){
                paymentStage=new Stage();
                Scene s=new Scene(makePayement);
                paymentStage.setScene(s);
                paymentStage.getIcons().add(new Image(getClass().getResourceAsStream("/lk/ijse/gdse41/ias/resources/icons/Sathra_logo_final.png")));
            }
            paymentStage.show();
            paymentStage.toFront();
            
        } catch (IOException ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //requires fix on sid
    private void loadAttendance() {
        String date=LocalDate.now().toString();
        AttendanceDTO adto=new AttendanceDTO(cid, date);
        try {
            ArrayList<AttendanceDTO> attendedlist=aci.get(adto);
            if (!attendedlist.isEmpty()){
                for (AttendanceDTO attendanceDTO : attendedlist) {
                    String rid=attendanceDTO.getId();
                    String sid=txtSID.getText();
                    String a_date=attendanceDTO.getA_date();
                    String checkIn=attendanceDTO.getCheck_in();
                    int month=LocalDate.now().getMonth().getValue();
                    payment=ctrlPayment.search(new PaymentDTO("", rid, month, "", false));
                    String paymentStatus;
                    if (!(payment==null)){
                        if (payment.isFree_card()){
                            paymentStatus="Free";
                        }else{
                            paymentStatus="Paid";
                        }
                    }else{
                        paymentStatus="Not-Paid";
                    }
                StudentAttendanceTableModel satm=new StudentAttendanceTableModel(rid, sid, a_date, checkIn, paymentStatus);
                attendanceList.add(satm);
                }
            }
            tblAttendance.refresh();
        } catch (Exception ex) {
            Logger.getLogger(MarkStudentAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
