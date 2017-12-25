/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import lk.ijse.gdse41.ias.controller.custom.impl.ClassScheduleControllerImpl;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.RegistrationDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.dto.SubjectDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;
import lk.ijse.gdse41.ias.tablemodel.StudentRegistrationTableModel;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.controller.custom.SubjectController;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.view.DashBoardController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class AddStudentSubjectsController implements Initializable {

    @FXML
    private JFXButton btnNext;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnBack;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtSID;
    @FXML
    private JFXComboBox cmbSection;
    @FXML
    private JFXComboBox cmbYear;
    @FXML
    private JFXComboBox cmbSubject;
    @FXML
    private JFXComboBox cmbTutor;
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
    

    public static final ObservableList<StudentRegistrationTableModel> stuReg=FXCollections.observableArrayList();
    public static final ArrayList<RegistrationDTO> registrationList=new ArrayList<>();
    
    BatchController ctrlBatch;
    SubjectController ctrlSubject;
    TutorController ctrlTutor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctrlBatch=(BatchController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.BATCH);
        ctrlSubject=(SubjectController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.SUBJECT);
        ctrlTutor=(TutorController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR);
        
        try {
            StudentDTO s=RegisterStudentController.s;
            txtSID.setText(s.getSid());
            txtName.setText(s.getfName()+" "+s.getlName());
            BatchDTO b=ctrlBatch.search(new BatchDTO(s.getBID(), "", 0, ""));
                        
            loadSections(b.getSection());
            loadSectionYears();
            
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        colTutor.setCellValueFactory(new PropertyValueFactory("tutor"));
        colSub.setCellValueFactory(new PropertyValueFactory("subject"));
        colSection.setCellValueFactory(new PropertyValueFactory("section"));
        colBatch.setCellValueFactory(new PropertyValueFactory("batch"));
        colFee.setCellValueFactory(new PropertyValueFactory("fee"));
        
        tblStuRegistration.setItems(stuReg);
        
        
        
    }
    
    private void loadSections(String studentSection){
        try {
            ArrayList<String> sectionList=new ArrayList<>();
            sectionList=ctrlBatch.getSections();
            cmbSection.getItems().clear();
            for(String section:sectionList){
                cmbSection.getItems().add(section);
            }
            cmbSection.getSelectionModel().select(studentSection);
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadClassSubjects(){
        String bid=getRelevantBID();
        
        try {
            ArrayList<SubjectDTO> subjectList=new ArrayList<>();
            subjectList=ctrlSubject.get(new SubjectDTO(bid));
            
            cmbSubject.getItems().clear();
            for(SubjectDTO subject:subjectList){
                cmbSubject.getItems().add(subject.getSubName());
            }
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getRelevantBID(){
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        int year=Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString());
        try{
            BatchDTO bdto=ctrlBatch.search(new BatchDTO("", "", year, section));
            return bdto.getBid();
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void loadSubjectTutors(){
        String bid=getRelevantBID();
        ArrayList<SubjectDTO> subjectList=new ArrayList<>();
        int sub=cmbSubject.getSelectionModel().getSelectedIndex();
        
        
        try {
            subjectList=ctrlSubject.get(new SubjectDTO(bid));
            String subID=subjectList.get(sub).getSubID();
            ArrayList<TutorDTO> tutorList=new ArrayList<>();
            tutorList=ctrlTutor.get(new TutorDTO(subID, bid));
            
            cmbTutor.getItems().clear();
            for(TutorDTO tutor:tutorList){
                cmbTutor.getItems().add(tutor.getfName()+" "+tutor.getlName());
            }
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void cmbSectionAction(ActionEvent actionEvent){
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
    public void btnAddAction(ActionEvent actionEvent){
        String tutor=cmbTutor.getSelectionModel().getSelectedItem().toString();
        String subject=cmbSubject.getSelectionModel().getSelectedItem().toString();
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        int year=Integer.parseInt(cmbYear.getSelectionModel().getSelectedItem().toString());
        
        StudentRegistrationTableModel srtm=new StudentRegistrationTableModel(tutor,subject,section,year, 0);
        boolean isAvailable=false;
        try{
            String bid=getRelevantBID();
            ArrayList<SubjectDTO> subjectList=new ArrayList<>();
            int sub=cmbSubject.getSelectionModel().getSelectedIndex();
            subjectList=ctrlSubject.get(new SubjectDTO(bid));
            String subID=subjectList.get(sub).getSubID();

            ArrayList<TutorDTO> tutorList=new ArrayList<>();
            tutorList=ctrlTutor.get(new TutorDTO(subID, bid));
            int tut=cmbTutor.getSelectionModel().getSelectedIndex();
            String tid=tutorList.get(tut).getTid();

            ClassScheduleDTO cs=ClassScheduleControllerImpl.getClassSchedule(bid, tid, subID);
            srtm.setFee(cs.getReg_fee());
            
            
            String sid=txtSID.getText();
            String cid=cs.getCid();
            
            if (stuReg.isEmpty()){
                stuReg.add(srtm);
                String rid="";
                rid=IDGenarator.getNewID("registration", "rid", "R");
                RegistrationDTO r=new RegistrationDTO(rid, sid, cid);
                System.out.println(rid);
                
                registrationList.add(r);
                tblStuRegistration.refresh();
                isAvailable=true;
                
            }else{
                for (StudentRegistrationTableModel asrtm:stuReg){
                    if ((tutor.equals(asrtm.getTutor()) && subject.equals(asrtm.getSubject()) && section.equals(asrtm.getSection()) && year==(asrtm.getBatch()))){
                        isAvailable=true;
                        break;
                    }
                }
            }
            if(!isAvailable){
                stuReg.add(srtm);
                
                String rid="";
                rid=registrationList.get(registrationList.size()-1).getRid();
                rid=IDGenarator.getNewID(rid, "R");
                RegistrationDTO r=new RegistrationDTO(rid, sid, cid);
                System.out.println(rid);
                
                registrationList.add(r);
                tblStuRegistration.refresh();
            }
        } catch (Exception ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnRemoveAction(ActionEvent actionEvent){
        int selectedIndex=tblStuRegistration.getSelectionModel().getSelectedIndex();
        stuReg.remove(selectedIndex);
        registrationList.remove(selectedIndex);
        tblStuRegistration.refresh();
    }
    
    @FXML
    public void btnBackAction(ActionEvent actionEvent){
        AnchorPane containPane;
        try{
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/RegisterStudent.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnNextAct(ActionEvent actionEvent){
        AnchorPane containPane;
        try{
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/AddStudentSummary.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


