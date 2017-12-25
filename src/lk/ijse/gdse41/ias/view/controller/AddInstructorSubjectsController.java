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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.impl.SubjectControllerImpl;
import lk.ijse.gdse41.ias.dto.SubjectDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;
import lk.ijse.gdse41.ias.other.IDGenarator;
import lk.ijse.gdse41.ias.tablemodel.InstructorRegistrationTableModel;
import lk.ijse.gdse41.ias.view.DashBoardController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class AddInstructorSubjectsController implements Initializable {

    @FXML
    private JFXTextField txtTutId;

    @FXML
    private JFXTextField txtTutName;

    @FXML
    private JFXComboBox cmbSection;

    @FXML
    private JFXComboBox cmbSubject;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnAddMissingSub;

    @FXML
    private TableView tblSubjects;

    @FXML
    private TableColumn colTutId;

    @FXML
    private TableColumn colSubId;

    @FXML
    private TableColumn colSub;

    @FXML
    private TableColumn colSection;

    @FXML
    private JFXButton btnNext;

    @FXML
    private JFXButton btnBack;
    
    @FXML
    private JFXTextField txtSubject;
    
    @FXML
    private JFXTextField txtSection;
    
    @FXML
    private JFXComboBox cmbSection1;
    
    @FXML
    private JFXButton btnCancel;
    
    @FXML
    private AnchorPane pnlAddMissingSub;
    
    private TutorDTO tutor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subCtrl=(SubjectControllerImpl) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.SUBJECT);
        
        colTutId.setCellValueFactory(new PropertyValueFactory("tid"));
        colSubId.setCellValueFactory(new PropertyValueFactory("subId"));
        colSub.setCellValueFactory(new PropertyValueFactory("subject"));
        colSection.setCellValueFactory(new PropertyValueFactory("section"));
        
        tblSubjects.setItems(rtm);
        
        tutor=RegisterInstructorController.tutor;
        
        txtTutId.setText(tutor.getTid());
        txtTutName.setText(tutor.getTitleName()+" "+tutor.getfName()+" "+tutor.getlName());
        
        loadSections();
        
        pnlAddMissingSub.setVisible(false);
    }

    ArrayList<SubjectDTO> subjectList=new ArrayList<>();
    SubjectControllerImpl subCtrl;
    public static ObservableList<InstructorRegistrationTableModel> rtm=FXCollections.observableArrayList();
    public static ArrayList<TutorSubjectDTO> tsdtolist=new ArrayList<>();
    
    
    private boolean cmbAction=true;
    private void loadSections(){
        try {
            cmbAction=false;
            subjectList=subCtrl.getAll();
            cmbSection.getItems().clear();
            String section="";
            for (SubjectDTO subjectDTO : subjectList) {
                String newSection=subjectDTO.getGrade();
                System.out.print(newSection+" : ");
                System.out.println(!section.equals(newSection));
                if(!section.equals(newSection)){
                    cmbSection.getItems().add(newSection);
                    cmbSection1.getItems().add(newSection);
                }
                section=newSection;
            }
            cmbAction=true;
            //cmbSection.getSelectionModel().select(studentSection);
        } catch (Exception ex) {
            Logger.getLogger(AddInstructorSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSections(String section){
        loadSections();
        cmbSection.getSelectionModel().select(section);
    }
    
    int sectionStart=0;
    SubjectDTO sdto;
    
    private void loadSubjects(){
        String section=cmbSection.getSelectionModel().getSelectedItem().toString();
        cmbAction=false;
        cmbSubject.getItems().clear();
        sectionStart=0;
        boolean isSection=false;
        String subject="";
        for (int i=0; i<subjectList.size(); i++) {
            SubjectDTO subjectDTO=subjectList.get(i);
            if(subjectDTO.getGrade().equals(section)){
                subject=subjectDTO.getSubName();
                if (!isSection){
                    sectionStart=i;
                }
                isSection=true;
                
                cmbSubject.getItems().add(subject);
                if(i<(subjectList.size()-1)){
                    if(!subjectList.get(i+1).getGrade().equals(section)){
                        break;
                    }
                }
            }
        }
        //String subject=subjectDTO.getSubName();
        cmbAction=true;
        cmbSubject.getSelectionModel().select(subject);
    }
    
    @FXML
    private void cmbSectionAction(){
        if(cmbAction){
            loadSubjects();
            sectionStart=0;
        }
    }
    
    @FXML
    private void cmbSubjectsAction(){
        if(cmbAction){
            sdto=subjectList.get(sectionStart+cmbSubject.getSelectionModel().getSelectedIndex());
        }
    }
    
    @FXML
    private void btnAddAction(){
        if(newSubject){
            try {
                SubjectDTO subject=new SubjectDTO(
                        IDGenarator.getNewID("Subject", "subId", "S"),
                        txtSubject.getText(),
                        txtSection.getText());
                boolean isAdded=subCtrl.add(subject);
                if(isAdded){
                    loadSections(subject.getGrade());
                    //loadSubjects();
                    sdto=subject;
                }else{
                    return;
                }
            } catch (Exception ex) {
                Logger.getLogger(AddInstructorSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        InstructorRegistrationTableModel irtm=new InstructorRegistrationTableModel(
                tutor.getTid(), 
                sdto.getSubID(), 
                sdto.getSubName(), 
                sdto.getGrade());
        boolean isAvailable=false;
        if(rtm.isEmpty()){
            rtm.add(irtm);
            TutorSubjectDTO tsdto=new TutorSubjectDTO("", tutor.getTid(), sdto.getSubID());
            tsdtolist.add(tsdto);
            tblSubjects.refresh();
            isAvailable=true;
        }else{
            for (InstructorRegistrationTableModel irtml : rtm) {
                if (irtml.getSubId().equals(irtm.getSubId())){
                    isAvailable=true;
                    break;
                }
            }
        }
        if(!isAvailable){
            rtm.add(irtm);
            TutorSubjectDTO tsdto=new TutorSubjectDTO("", tutor.getTid(), sdto.getSubID());
            tsdtolist.add(tsdto);
            tblSubjects.refresh();
        }
    }
    
    @FXML
    public void btnRemoveAction(){
        int selectedIndex=tblSubjects.getSelectionModel().getSelectedIndex();
        rtm.remove(selectedIndex);
        tsdtolist.remove(selectedIndex+1);
        tblSubjects.refresh();
    }
    
    @FXML
    public void btnBackAction(ActionEvent actionEvent){
        AnchorPane containPane;
        try{
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/RegisterInstructor.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void btnNextAct(ActionEvent actionEvent){
        AnchorPane containPane;
        try{
            containPane = FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/AddInstructorSummary.fxml"));
            DashBoardController.containP.getChildren().setAll(containPane);
        } catch (IOException ex) {
            Logger.getLogger(AddStudentSubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    boolean newSubject=false;
    
    @FXML
    public void btnCancelAction(){
        newSubject=false;
        FadeTransition fadeOut=new FadeTransition(Duration.millis(800),pnlAddMissingSub);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setCycleCount(1);
                fadeOut.play();
                fadeOut.setOnFinished(l -> {
                    pnlAddMissingSub.setVisible(false); 
                });
    }
    
    @FXML
    public void btnAddMissingSubAction(){
        newSubject=true;
        pnlAddMissingSub.setVisible(true);
        FadeTransition fadeIn=new FadeTransition(Duration.millis(800),pnlAddMissingSub);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
    }
    
    @FXML
    public void cmbSection1Action(){
        String section=cmbSection1.getSelectionModel().getSelectedItem().toString();
        txtSection.setText(section);
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
