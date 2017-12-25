/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalTimeStringConverter;
import jfxtras.scene.control.agenda.Agenda;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.BatchController;
import lk.ijse.gdse41.ias.controller.custom.ClassScheduleController;
import lk.ijse.gdse41.ias.controller.custom.HallController;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.controller.custom.TutorSubjectController;
import lk.ijse.gdse41.ias.dto.BatchDTO;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.HallDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;
import lk.ijse.gdse41.ias.other.FieldValidator;
import lk.ijse.gdse41.ias.view.controller.InnerSidePaneController;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class ManageClassController implements Initializable {
    
    @FXML
    private JFXComboBox cmbTutor;

    @FXML
    private JFXComboBox cmbBatch;

    @FXML
    private JFXComboBox cmbSubject;

    @FXML
    private JFXComboBox cmbSection;

    @FXML
    private JFXButton btnAppoinment;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXComboBox cmbDay;

    @FXML
    private JFXDatePicker tmpckr;

    @FXML
    private Spinner spnrDuration;

    @FXML
    private JFXComboBox cmbHalls;

    @FXML
    private Agenda agenda;

    @FXML
    private Spinner spnrNoOfStudents;

    @FXML
    private JFXToggleButton tglBtnAC;
    
    @FXML
    private JFXDrawer drwrHall;

    @FXML
    private JFXComboBox cmbHalls2;

    @FXML
    private ImageView imgExpandHall;

    @FXML
    private ImageView imgExpandFees;

    @FXML
    private JFXDrawer drwrFees;

    @FXML
    private JFXTextField txtRegFee;

    @FXML
    private JFXTextField txtMonthFee;
    
    @FXML
    private VBox vbxFees;
    
    @FXML
    private Pane pnlHall;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ctrlHall=(HallController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.HALL);
        ctrlClassSchedule=(ClassScheduleController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.CLASS_SCHEDULE);
        
        spnrDuration.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(120, 900, 120, 30));
        spnrNoOfStudents.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4000, 150));
        
        String[] weekdays=DateFormatSymbols.getInstance(Locale.ENGLISH).getWeekdays();
        cmbDay.getItems().addAll(Arrays.asList(weekdays));
        
        drwrFees.close();
        drwrHall.close();
        imgExpandHall.setVisible(false);
        
        
        drwrFees.setSidePane(vbxFees);
        drwrHall.setSidePane(pnlHall);
        drwrFees.setDisable(true);
        drwrHall.setDisable(true);
        
        classScheduleDTO=new ClassScheduleDTO();
        batchDTO=new BatchDTO();
        tutorSubjectDTO=new TutorSubjectDTO();
        
        loadTutors();
        
        agNew=new Agenda.AppointmentGroupImpl().withDescription("New Appoinments").withStyleClass("group8");
        agUpdate=new Agenda.AppointmentGroupImpl().withDescription("Updated Appoinments").withStyleClass("group11");
        agH1=new Agenda.AppointmentGroupImpl().withDescription("Hall_1").withStyleClass("group15");
        agH2=new Agenda.AppointmentGroupImpl().withDescription("Hall_2").withStyleClass("group16");
        LocalDateTime dateTime=agenda.displayedLocalDateTime().get();
        
        int days=LocalDate.now().getDayOfWeek().getValue();
        if(days==7){
            days=0;
        }
        dtSunday=LocalDate.now().minusDays(days);
        System.out.println(dtSunday+""+dtSunday.getDayOfWeek().toString());
        
        agenda.actionCallbackProperty().set((appoinment) -> {
            if(!appoinment.getAppointmentGroup().getDescription().equals("New Appoinments")){
                int index=Integer.parseInt(appoinment.getDescription());
            }
            return null; //To change body of generated lambdas, choose Tools | Templates.
        });
        
    }
    
    boolean isNewBatch=false;
    boolean isNewClass=true;
    int studentCount;
    HallController ctrlHall;
    ClassScheduleController ctrlClassSchedule;
    int hall1Cap;
    ArrayList<HallDTO> hdtoList1;
    ArrayList<HallDTO> hdtoList2;
    LocalDate dtSunday;
    Agenda.AppointmentGroup agNew;
    Agenda.AppointmentGroup agUpdate;
    
    @FXML
    void cmbSectionAction(ActionEvent event) {
        loadSubjects();
        loadBatches();
    }

    @FXML
    void cmbSubjectAction(ActionEvent event) {
        loadClasses();
    }

    @FXML
    void cmbTutorAction(ActionEvent event) {
        loadSections();
    }
    
    private BatchDTO batchDTO;
    private ClassScheduleDTO classScheduleDTO;
    @FXML
    void cmbBatchAction(ActionEvent event) {
        String year=cmbBatch.getEditor().getText();
        cmbBatch.getSelectionModel().select(year);
        
        if(!cmbBatch.getSelectionModel().isEmpty()){
            batchDTO = bdtoList.get(cmbBatch.getSelectionModel().getSelectedIndex());
            isNewClass=true;
            isNewBatch=false;
            for (ClassScheduleDTO cs : csdtoList) {
                if(cs.getBid().equals(batchDTO.getBid())){
                    classScheduleDTO=cs;
                    spnrNoOfStudents.getValueFactory().setValue(cs.getStudentCount());
                    cmbDay.getSelectionModel().select(cs.getC_day());
                    tmpckr.setTime(new LocalTimeStringConverter().fromString(cs.getTime()));
                    spnrDuration.getValueFactory().setValue(cs.getDuration());
                    cmbHalls.getSelectionModel().select(cs.getHid_1());
                    if (!cs.getHid_2().equals("")){
                        cmbHalls2.getSelectionModel().select(cs.getHid_2());
                        imgExpandHall.setVisible(true);
                    }
                    txtRegFee.setText(cs.getReg_fee()+"");
                    txtMonthFee.setText(cs.getM_fee()+"");
                    isNewClass=false;
                    break;
                }
            }
        }else{
            isNewBatch=true;
            //bid="";
        }
        
    }
    
    @FXML
    void cmbBatchKeyEvent(KeyEvent event) {
        FieldValidator.onlyNumeric(event);
    }


    @FXML
    void spnrNoOFStudentsAction(KeyEvent event) {
        loadHall1();
    }

    @FXML
    void tglBtnACAction(ActionEvent event) {
        loadHall1();
    }
    
     @FXML
    void imgExpandFeesAction(MouseEvent event) {
        if(!drwrFees.isShown()){
            drwrFees.setDisable(false);
            drwrFees.open();
        }else{
            drwrFees.close();
            drwrFees.setDisable(true);
        }
    }

    @FXML
    void imgExpandHall(MouseEvent event) {
        if(!drwrHall.isShown()){
            drwrHall.setDisable(false);
            drwrHall.open();
        }else{
            drwrHall.close();
            drwrHall.setDisable(true);
        }
    }
    
    Agenda.AppointmentGroup agH1;
    Agenda.AppointmentGroup agH2;
    
    @FXML
    void cmbHall2Action(ActionEvent event) {
        HallDTO hdto=hdtoList2.get(cmbHalls2.getSelectionModel().getSelectedIndex());
        ClassScheduleDTO csdto=new ClassScheduleDTO();
        csdto.setHid_1(hdto.getHid());
        csdto.setHid_2(hdto.getHid());
        ArrayList<Agenda.Appointment> apnmntList=new ArrayList<>();
        for (Agenda.Appointment appointment : agenda.appointments()) {
            if(appointment.getAppointmentGroup().equals(agH2))
                apnmntList.add(appointment);
        }
        agenda.appointments().removeAll(apnmntList);
        try {
            ArrayList<ClassScheduleDTO> hallCSdtoList=ctrlClassSchedule.getHallSchedule(csdto);
            System.out.println(hallCSdtoList.size());
            loadScheduleAppoinments(hallCSdtoList, agH2);
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void cmbHallAction(ActionEvent event) {
        loadHall2();
        HallDTO hdto=hdtoList1.get(cmbHalls.getSelectionModel().getSelectedIndex());
        System.out.println(hdto.getHid());
        ClassScheduleDTO csdto=new ClassScheduleDTO();
        csdto.setHid_1(hdto.getHid());
        csdto.setHid_2(hdto.getHid());
        ArrayList<Agenda.Appointment> apnmntList=new ArrayList<>();
        for (Agenda.Appointment appointment : agenda.appointments()) {
            if(appointment.getAppointmentGroup().equals(agH1))
                apnmntList.add(appointment);
        }
        agenda.appointments().removeAll(apnmntList);
        try {
            ArrayList<ClassScheduleDTO> hallCSdtoList=ctrlClassSchedule.getHallSchedule(csdto);
            System.out.println(hallCSdtoList.size());
            loadScheduleAppoinments(hallCSdtoList, agH1);
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ArrayList<ClassScheduleDTO> csDTOsNew=new ArrayList<>();
    ArrayList<ClassScheduleDTO> csDTOsUpdate=new ArrayList<>();
    @FXML
    void btnAppoinmentAction(ActionEvent event) {
        System.out.println("Button pressed");                                   //print
        String year=cmbBatch.getEditor().getText();
        String section=(String) cmbSection.getSelectionModel().getSelectedItem();
        String subject=(String) cmbSubject.getSelectionModel().getSelectedItem();
        LocalDateTime startTime=LocalDateTime.of(
                dtSunday.plusDays(cmbDay.getSelectionModel().getSelectedIndex()-1), 
                tmpckr.getTime());
        String description=String.format("%s\n%s-%s\n%s", subject,section,year,tutorSubjectDTO.getTsid());
        
        
        boolean isAvailable=false; 
        if(!csDTOsNew.isEmpty()){
            for (ClassScheduleDTO csdto : csDTOsNew) {            
                if (csdto.getTsid().equals(tutorSubjectDTO.getTsid()) && csdto.getYear()==Integer.parseInt(year)) {
                    isAvailable=true;
                    System.out.println("Class already added");                 //Print
                    break;
                }
            }
        }
        
        if (!isAvailable){
            System.out.println("Class is new");                                 //print
            String Hall_2="";
            if(imgExpandHall.isVisible()){
                Hall_2=hdtoList2.get(cmbHalls2.getSelectionModel().getSelectedIndex()).getHid();
            }
            
            
            ClassScheduleDTO csdto=new ClassScheduleDTO(
                    classScheduleDTO.getCid(),
                    tutorSubjectDTO.getTsid(),
                    batchDTO.getBid(), 
                    hdtoList1.get(cmbHalls.getSelectionModel().getSelectedIndex()).getHid(), 
                    Hall_2, 
                    (int)cmbDay.getSelectionModel().getSelectedIndex()-1, 
                    tmpckr.getTime().format(DateTimeFormatter.ISO_LOCAL_TIME),
                    (int) spnrDuration.getValue(), 
                    Double.parseDouble(txtMonthFee.getText()), 
                    Double.parseDouble(txtRegFee.getText()), 
                    0);
            csdto.setSection(section);
            csdto.setYear(Integer.parseInt(year));
            csdto.setbName(section+"-"+year);
            Agenda.AppointmentGroup ag;
            if (isNewBatch){
                csdto.setBid("");
                System.out.println("Is a new batch");                           //print
            }
            if (isNewClass){
                csdto.setCid("");
                csDTOsNew.add(csdto);
                System.out.println("Is new Class");
                ag=agNew;
            }else{
                csDTOsUpdate.add(csdto);
                ag=agUpdate;
            }
            
            
            System.out.println("agenda");
            agenda.appointments().addAll(
                    new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(startTime)
                    .withEndLocalDateTime(startTime.plusMinutes((int) spnrDuration.getValue()))
                    .withSummary(description)
                    .withDescription(description)
                    .withAppointmentGroup(ag)
            );
            System.out.println("Agenda added");
        }
        refresh();
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        refresh();
    }

    @FXML
    void btnConfirmAction(ActionEvent event) {
        try {
            if(ctrlClassSchedule.add(csDTOsNew)){
                if (ctrlClassSchedule.update(csDTOsUpdate)){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION, "Schedule(s) created successfully", ButtonType.OK);
                    alert.show();
                }else{
                    Alert alert=new Alert(Alert.AlertType.ERROR, "Could not complete the task", ButtonType.OK);
                    alert.show();
                }
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR, "Could not complete the task", ButtonType.OK);
                alert.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    ArrayList<TutorDTO> tutorList;
    private void loadTutors(){
        cmbTutor.getItems().clear();
        TutorController ctrl=(TutorController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR);
        try {
            tutorList=ctrl.getAll();
            for (TutorDTO t : tutorList) {
                cmbTutor.getItems().add(t.getTitleName()+t.getfName()+" "+t.getlName());
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ArrayList<TutorSubjectDTO> tsdtoList;
    private void loadSections(){
        cmbSection.getItems().clear();
        TutorDTO tutor=tutorList.get(cmbTutor.getSelectionModel().getSelectedIndex());
        TutorSubjectController ctrlTS=(TutorSubjectController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR_SUBJECTS);
        try {
            tsdtoList=ctrlTS.get(new TutorSubjectDTO("", tutor.getTid(), ""));
            String section="";
            for (TutorSubjectDTO tsdto : tsdtoList) {
                String newSection=tsdto.getGrade();
                if(!section.equals(newSection)){
                    cmbSection.getItems().add(newSection);
                }
                section=newSection;             
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadSubjects(){
        cmbSubject.getItems().clear();
        String section=(String) cmbSection.getSelectionModel().getSelectedItem();
        for (TutorSubjectDTO tsdto : tsdtoList) {
            if (tsdto.getGrade().equals(section)){
                cmbSubject.getItems().add(tsdto.getSubName());
            }
        }
    }
    
    ArrayList<BatchDTO> bdtoList;
    private void loadBatches(){
        String section=(String) cmbSection.getSelectionModel().getSelectedItem();
        BatchController ctrlBatch=(BatchController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.BATCH);
        cmbBatch.getItems().clear();
        try {
            bdtoList=ctrlBatch.get(new BatchDTO("", "", 0, section));
            for (BatchDTO batchDTO : bdtoList) {
                cmbBatch.getItems().add(batchDTO.getYear()+"");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    ArrayList<ClassScheduleDTO> csdtoList;
    TutorSubjectDTO tutorSubjectDTO;
    private void loadClasses(){
        String section=(String) cmbSection.getSelectionModel().getSelectedItem();
        String subject=(String) cmbSubject.getSelectionModel().getSelectedItem();
        String tsid="";
        for (TutorSubjectDTO tsdto : tsdtoList) {
            if(tsdto.getSubName().equals(subject) && tsdto.getGrade().equals(section)){
                tsid=tsdto.getTsid();
                tutorSubjectDTO=tsdto;
                break;
            }
        }
        
        ClassScheduleDTO csdto=new ClassScheduleDTO();
        csdto.setTsid(tsid);
        try {
            csdtoList=ctrlClassSchedule.get(csdto);
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void loadHall1(){
        studentCount=(int) spnrNoOfStudents.getValue();
        try {
            hdtoList1=ctrlHall.get(new HallDTO("", studentCount, tglBtnAC.isSelected()));
            if (hdtoList1.isEmpty()){
                hdtoList1=ctrlHall.getAll();
            }
            cmbHalls.getItems().clear();
            for (HallDTO hdto : hdtoList1) {
                String ac="";
                if(hdto.isAc())
                    ac="-A/C";
                cmbHalls.getItems().add(hdto.getHid()+"-"+hdto.getSeatingCap()+ac);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadHall2(){
        HallDTO hdto=hdtoList1.get(cmbHalls.getSelectionModel().getSelectedIndex());
        if (!(hdto.getSeatingCap()>=studentCount)){
            //hdtoList2.clear();
            try {
                hdtoList2=ctrlHall.get(new HallDTO("", studentCount-hdto.getSeatingCap(), tglBtnAC.isSelected()));
                cmbHalls2.getItems().clear();
                for (HallDTO hallDTO : hdtoList2) {
                    String ac="";
                    if(hallDTO.isAc())
                        ac="-A/C";
                    cmbHalls2.getItems().add(hallDTO.getHid()+"-"+hallDTO.getSeatingCap()+ac);
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageClassController.class.getName()).log(Level.SEVERE, null, ex);
            }
            imgExpandHall.setVisible(true);
        }else{
            imgExpandHall.setVisible(false);
        }
    }
    
    private void loadScheduleAppoinments(ArrayList<ClassScheduleDTO> hallCSdtoList, Agenda.AppointmentGroup ag){
        
        int i=0;
        for (ClassScheduleDTO cs : hallCSdtoList) {
            System.out.println(cs.getCid());
 //           System.out.println(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("HH:mm:ss"), null).fromString(cs.)        //.ofPatten("HH:mm:ss").fromString(cs.getTime()));
            LocalDateTime startTime=LocalDateTime.of(dtSunday.plusDays(cs.getC_day()), new LocalTimeStringConverter(DateTimeFormatter.ISO_TIME, null).fromString(cs.getTime()));
            String summary=String.format("%s\n%s\n%s", cs.getTsid(),cs.getBid(),cs.getYear());
            agenda.appointments().addAll(
                    new Agenda.AppointmentImplLocal()
                    .withStartLocalDateTime(startTime)
                    .withEndLocalDateTime(startTime.plusMinutes(cs.getDuration()))
                    .withSummary(summary)
                    .withDescription(i+"")
                    .withAppointmentGroup(ag)
            );
            i++;
        }
    }
    
    private void refresh(){
        tmpckr.setTime(LocalTime.NOON);
        cmbDay.getSelectionModel().clearSelection();
        spnrNoOfStudents.getValueFactory().setValue(150);
        spnrDuration.getValueFactory().setValue(120);
        drwrHall.close();
        drwrFees.close();
        txtRegFee.clear();
        txtMonthFee.clear();
        cmbHalls2.getSelectionModel().clearSelection();
        cmbHalls.getSelectionModel().clearSelection();
        cmbBatch.getSelectionModel().clearSelection();
        cmbBatch.getEditor().clear();
        cmbTutor.getSelectionModel().clearSelection();
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
