/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import lk.ijse.gdse41.ias.view.controller.ManageClassController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.ClassScheduleController;
import lk.ijse.gdse41.ias.controller.custom.PaymentController;
import lk.ijse.gdse41.ias.controller.custom.StudentController;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.controller.custom.TutorPaymentController;
import lk.ijse.gdse41.ias.controller.custom.TutorSubjectController;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.dto.StudentDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorPaymentDTO;
import lk.ijse.gdse41.ias.dto.TutorPaymentReportDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;
import lk.ijse.gdse41.ias.messaging.MessagingFactory;
import lk.ijse.gdse41.ias.other.IDGenarator;
import lk.ijse.gdse41.ias.reports.ReportFactory;
import lk.ijse.gdse41.ias.tablemodel.ManagePaymentClassTableModel;
import lk.ijse.gdse41.ias.tablemodel.ManagePaymentPaymentsTableModel;
import lk.ijse.gdse41.ias.tablemodel.ManagePaymentTutorTableModel;
import lk.ijse.gdse41.ias.view.controller.InnerSidePaneController;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class SendSMS implements Initializable {
    
    @FXML
    private TableView tblTutor;

    @FXML
    private TableColumn colTid;

    @FXML
    private TableColumn colFName;

    @FXML
    private TableColumn colLName;

    @FXML
    private AnchorPane pnlClassDetails;

    @FXML
    private TableView tblClasses;

    @FXML
    private TableColumn colCid;

    @FXML
    private TableColumn colSubject;

    @FXML
    private TableColumn colSection;

    @FXML
    private TableColumn colBatch;

    @FXML
    private ImageView imgBack;

    @FXML
    private JFXButton btnProceed;
    
    @FXML
    private TextArea txtMessage;
    
    @FXML
    private JFXTextField txtRegStu;
    
    @FXML
    private Label lblChar;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTblTutor();
        initTblClass();
        
        loadTutors();
        
        pnlClassDetails.setVisible(false);
        
    }    
    
    ArrayList<TutorPaymentReportDTO> tpdtos=new ArrayList<>();

    @FXML
    void btnProceedAction(ActionEvent event) {
        MessagingFactory mf=MessagingFactory.getInstance();
        boolean isLogged=mf.login();
        String msg=txtMessage.getText();
        if(isLogged){
            System.out.println("Logged");
            for (String t : telNo) {
                try {
                    mf.sendMessage(t, msg);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Sent");
            try {
                mf.logout();
            } catch (InterruptedException ex) {
                Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    void imgBackClickAction(MouseEvent event) {
        FadeTransition fadeOut=new FadeTransition(Duration.millis(800),pnlClassDetails);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setCycleCount(1);
                fadeOut.play();
                fadeOut.setOnFinished(l -> {
                    pnlClassDetails.setVisible(false); 
                });
    }
    
    @FXML
    void tblClassesClickAction(MouseEvent event) {
        refresh();
        loadClassStudents();
    }


    TutorDTO tutor;
    @FXML
    void tblTutorClickAction(MouseEvent event) {
        refresh();
        ManagePaymentTutorTableModel mpttm=(ManagePaymentTutorTableModel) tblTutor.getSelectionModel().getSelectedItem();
        int selectedIndex=tblMdlTutorList.indexOf(mpttm);
        tutor=tutorList.get(selectedIndex);
        loadClasses(tutor);
        pnlClassDetails.setVisible(true);
        FadeTransition fadeIn=new FadeTransition(Duration.millis(800),pnlClassDetails);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            
    }
    
    @FXML
    void txtMessageAction(KeyEvent event) {
        lblChar.setText(txtMessage.getText().length()+"");
    }
    
    private final ObservableList<ManagePaymentTutorTableModel> tblMdlTutorList=FXCollections.observableArrayList();
    private final ObservableList<ManagePaymentClassTableModel> tblMdlClassList=FXCollections.observableArrayList();
            
    private void initTblTutor(){
        colTid.setCellValueFactory(new PropertyValueFactory("tid"));
        colFName.setCellValueFactory(new PropertyValueFactory("fName"));
        colLName.setCellValueFactory(new PropertyValueFactory("lName"));
        
        tblTutor.setItems(tblMdlTutorList);
    }
    
    private void initTblClass(){
        colCid.setCellValueFactory(new PropertyValueFactory("cid"));
        colSubject.setCellValueFactory(new PropertyValueFactory("subject"));
        colSection.setCellValueFactory(new PropertyValueFactory("b_section"));
        colBatch.setCellValueFactory(new PropertyValueFactory("b_year"));
        
        tblClasses.setItems(tblMdlClassList);
    }
    
    
    ArrayList<TutorDTO> tutorList;
    private void loadTutors(){
        TutorController ctrl=(TutorController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR);
        try {
            tutorList=ctrl.getAll();
            for (TutorDTO t : tutorList) {
                ManagePaymentTutorTableModel mpttm=new ManagePaymentTutorTableModel(t.getTid(), t.getfName(), t.getlName());
                tblMdlTutorList.add(mpttm);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ArrayList<ClassScheduleDTO> csdtoList=new ArrayList<>();
    ArrayList<ClassScheduleDTO> csdtoListMain=new ArrayList<>();
    private void loadClasses(TutorDTO tutor){
        TutorSubjectController ctrlTS=(TutorSubjectController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR_SUBJECTS);
        ClassScheduleController ctrlCS=(ClassScheduleController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.CLASS_SCHEDULE);
        try {
            tblMdlClassList.clear();
            csdtoListMain.clear();
            ArrayList<TutorSubjectDTO> tsdtoList=ctrlTS.get(new TutorSubjectDTO("", tutor.getTid(), ""));
            for (TutorSubjectDTO tsdto : tsdtoList) {
                System.out.println(tsdto.getTid()+"-"+tsdto.getSubId());
                ClassScheduleDTO cs=new ClassScheduleDTO();
                cs.setTsid(tsdto.getTsid());
                csdtoList=ctrlCS.get(cs);
                if (!csdtoList.isEmpty())
                for (ClassScheduleDTO csDTO : csdtoList) {
                    System.out.println(csDTO.getCid());
                    ManagePaymentClassTableModel mpctm=new ManagePaymentClassTableModel(csDTO.getCid(), tsdto.getSubName(), tsdto.getGrade(), csDTO.getYear());
                    tblMdlClassList.add(mpctm);
                    csdtoListMain.add(csDTO);
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ClassScheduleDTO csdto;
    private ArrayList<String> telNo=new ArrayList<>();
    
    private void loadClassStudents(){
        ManagePaymentClassTableModel mpctm=(ManagePaymentClassTableModel) tblClasses.getSelectionModel().getSelectedItem();
        StudentController ctrlStudentController=(StudentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.STUDENT);
        StudentDTO dto=new StudentDTO();
        csdto=csdtoListMain.get(tblMdlClassList.indexOf(mpctm));
        System.out.println(csdto.getCid());
        dto.setCid(csdto.getCid());
        try {
            ArrayList<StudentDTO> studentList=ctrlStudentController.get(dto);
            for (StudentDTO s : studentList) {
                String tel="";
                if(!s.getStudentTele().equals("") && s.getStudentTele().matches("^((\\d{2}|\\d{3})[-]?(\\d{7}))$")){
                    tel=s.getStudentTele();
                    tel=tel.replace("-", "");
                }else if(s.getGuardianTele().matches("^((\\d{2}|\\d{3})[-]?(\\d{7}))$")){
                    tel=s.getGuardianTele();
                    tel=tel.replace("-", "");
                }
                System.out.println(tel);
                if (!tel.equals("")){
                    telNo.add(tel);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SendSMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        txtRegStu.setText(telNo.size()+"");
    }
    
    private void refresh(){
        telNo.clear();
        txtRegStu.setText(0+"");
    }
    
    
    
    
    
    
    @FXML
    private ImageView imgSchedule;

    @FXML
    private ImageView imgRegister;
    
    @FXML
    private ImageView imgPayments;
    
    @FXML
    void imgRegisterAction(MouseEvent event) {
        InnerSidePaneController.getInstance().imgTutor(event);
    }

    @FXML
    void imgScheduleAction(MouseEvent event) {
        InnerSidePaneController.getInstance().imgSchedule(event);
    }
    
    @FXML
    void imgPaymentAction(MouseEvent event) {
        InnerSidePaneController.getInstance().imgPayment(event);
    }
    
}