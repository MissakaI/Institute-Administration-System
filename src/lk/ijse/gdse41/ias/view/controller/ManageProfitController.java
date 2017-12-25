/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import lk.ijse.gdse41.ias.view.controller.JasperReportViewerController;
import lk.ijse.gdse41.ias.view.controller.ManageClassController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.ClassScheduleController;
import lk.ijse.gdse41.ias.controller.custom.PaymentController;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.controller.custom.TutorPaymentController;
import lk.ijse.gdse41.ias.controller.custom.TutorSubjectController;
import lk.ijse.gdse41.ias.dto.ClassScheduleDTO;
import lk.ijse.gdse41.ias.dto.PaymentDTO;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorPaymentDTO;
import lk.ijse.gdse41.ias.dto.TutorPaymentReportDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;
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
public class ManageProfitController implements Initializable {
    
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
    private Spinner spnrCommision;

    @FXML
    private JFXTextField txtRegStu;

    @FXML
    private JFXTextField txtMonthlyFee;

    @FXML
    private JFXTextField txtMonthlyPasses;

    @FXML
    private JFXTextField txtFreePasses;

    @FXML
    private JFXTextField txtAmountRecieved;

    @FXML
    private TableView tblPayments;
    
    @FXML
    private TableColumn colCid2;

    @FXML
    private TableColumn colAmountRecieved;

    @FXML
    private TableColumn colCommision;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnProceed;
    
    @FXML
    private JFXComboBox cmbMonths;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTblTutor();
        initTblClass();
        initTblPayments();
        
        loadTutors();
        
        pnlClassDetails.setVisible(false);
        
        spnrCommision.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 100.0, 20.0, 0.5));
        
        String[] months=DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        cmbMonths.getItems().addAll(Arrays.asList(months));
        cmbMonths.getSelectionModel().select(LocalDate.now().getMonthValue()-1);
        System.out.println(cmbMonths.getSelectionModel().getSelectedIndex());
    }    
    
    ArrayList<TutorPaymentReportDTO> tpdtos=new ArrayList<>();
    
    @FXML
    void btnAddAction(ActionEvent event) {
        boolean isAvailable=false;
        if(!tpdtos.isEmpty()){
            for (TutorPaymentDTO dto : tpdtos) {
                if(dto.getCid().equals(tpdto.getCid()) && dto.getPay_month()==tpdto.getPay_month()){
                    isAvailable=true;
                    break;
                }
            }
        }
        
        if(!isAvailable){
            ManagePaymentPaymentsTableModel mpptm=new ManagePaymentPaymentsTableModel(tpdto.getCid(), tpdto.getTotal_amount(), tpdto.getCommission());
            tpdtos.add(tpdto);
            tblMdlPayList.add(mpptm);
            tblPayments.refresh();
        }
    }

    @FXML
    void btnProceedAction(ActionEvent event) {
        ArrayList<TutorPaymentDTO> tpdtoList=new ArrayList<>();
        String tpid="";
        for (TutorPaymentReportDTO dto : tpdtos) {
            if(tpdtos.indexOf(dto)==0){
                tpid=IDGenarator.getNewID("tutor_payment", "tpid", "TP");
            }else{
                tpid=IDGenarator.getNewID(tpid, "TP");
            }
            dto.setTpid(tpid);
            tpdtoList.add(dto.getTutorPaymentDTO());
        }
        
        
        TutorPaymentController ctrlTutorPaymentController=(TutorPaymentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR_PAYMENT);
        boolean isAdded=false;
        try {
            if (ctrlTutorPaymentController.add(tpdtoList)) {
                isAdded=true;
                //Alert a=new Alert(Alert.AlertType.ERROR)
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageProfitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (isAdded){
            try {
                ReportFactory.dtoList.addAll(tpdtos);
                JasperReport compiledReport=(JasperReport) JRLoader.loadObject(ManageProfitController.class.getResourceAsStream("/lk/ijse/gdse41/ias/reports/TutorPayment.jasper"));
                HashMap<String, Object> parameters=new HashMap<>();
                parameters.put("Tutor_Name", tutor.getTitleName()+" "+tutor.getfName()+" "+tutor.getlName());
                JasperPrint filledReport=JasperFillManager.fillReport(compiledReport, parameters, new JRBeanCollectionDataSource(ReportFactory.generateCollection()));
                FXMLLoader fXMLLoader=new FXMLLoader(getClass().getResource("/lk/ijse/gdse41/ias/view/JasperReportViewer.fxml"));

                Parent root=(Parent)fXMLLoader.load();

                JasperReportViewerController cont=fXMLLoader.getController();
                Stage stage=new Stage();
                Scene scene=new Scene(root);
                stage.setScene(scene);
                cont.setView(filledReport);
                stage.setResizable(false);

                stage.setTitle("Tutor Payment Report");
                stage.requestFocus();
                //stage.getIcons().add(new Image(getClass().getResourceAsStream("/edu/ijse/gdse41/StudentMngSys/resources/icons/LogoIcon.png")));
                stage.show();
            } catch (JRException ex) {
                Alert alert=new Alert(Alert.AlertType.ERROR, "Report Generation Error", ButtonType.OK);
                alert.show();
                Logger.getLogger(ManageProfitController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ManageProfitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        refresh();
    }

    @FXML
    void btnRemoveAction(ActionEvent event) {
        int index=tblMdlPayList.indexOf(tblPayments.getSelectionModel().getSelectedItem());
        tblMdlPayList.remove(index);
        tpdtos.remove(index);
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
        loadPayments();
    }

    @FXML
    void tblPaymentsClickAction(MouseEvent event) {
        
    }
    
    @FXML
    void cmbMonthsAction(ActionEvent event){
        refresh();
        loadPayments();
    }

    TutorDTO tutor;
    @FXML
    void tblTutorClickAction(MouseEvent event) {
        refresh();
        ManagePaymentTutorTableModel mpttm=(ManagePaymentTutorTableModel) tblTutor.getSelectionModel().getSelectedItem();
        int selectedIndex=tblMdlTutorList.indexOf(mpttm);
        tutor=tutorList.get(selectedIndex);
        loadClasses(tutor);
        tblMdlPayList.clear();
        tblPayments.refresh();
        pnlClassDetails.setVisible(true);
        FadeTransition fadeIn=new FadeTransition(Duration.millis(800),pnlClassDetails);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);
            fadeIn.play();
            
    }
    
    private final ObservableList<ManagePaymentTutorTableModel> tblMdlTutorList=FXCollections.observableArrayList();
    private final ObservableList<ManagePaymentClassTableModel> tblMdlClassList=FXCollections.observableArrayList();
    private final ObservableList<ManagePaymentPaymentsTableModel> tblMdlPayList=FXCollections.observableArrayList();
            
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
    
    private void initTblPayments(){
        colCid2.setCellValueFactory(new PropertyValueFactory("cid"));
        colAmountRecieved.setCellValueFactory(new PropertyValueFactory("amountRecieved"));
        colCommision.setCellValueFactory(new PropertyValueFactory("commission"));
        
        tblPayments.setItems(tblMdlPayList);
    }
    
    ArrayList<TutorDTO> tutorList;
    private void loadTutors(){
        //cmbTutor.getItems().clear();
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
    
    ArrayList<ClassScheduleDTO> csdtoList;
    private void loadClasses(TutorDTO tutor){
        TutorSubjectController ctrlTS=(TutorSubjectController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR_SUBJECTS);
        ClassScheduleController ctrlCS=(ClassScheduleController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.CLASS_SCHEDULE);
        try {
            tblMdlClassList.clear();
            ArrayList<TutorSubjectDTO> tsdtoList=ctrlTS.get(new TutorSubjectDTO("", tutor.getTid(), ""));
            for (TutorSubjectDTO tsdto : tsdtoList) {
                System.out.println(tsdto.getTid()+"-"+tsdto.getSubId());
                ClassScheduleDTO cs=new ClassScheduleDTO();
                cs.setTsid(tsdto.getTsid());
                csdtoList=ctrlCS.get(cs);
                for (ClassScheduleDTO csdto : csdtoList) {
                    System.out.println(csdto.getCid());
                    ManagePaymentClassTableModel mpctm=new ManagePaymentClassTableModel(csdto.getCid(), tsdto.getSubName(), tsdto.getGrade(), csdto.getYear());
                    tblMdlClassList.add(mpctm);
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageProfitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ClassScheduleDTO csdto;
    private TutorPaymentReportDTO tpdto;
    
    private void loadPayments(){
        ManagePaymentClassTableModel mpctm=(ManagePaymentClassTableModel) tblClasses.getSelectionModel().getSelectedItem();
        PaymentController ctrlPayment=(PaymentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.PAYMENT);
        TutorPaymentController ctrlTutorPayment=(TutorPaymentController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR_PAYMENT);
        csdto=csdtoList.get(tblMdlClassList.indexOf(mpctm));
        double commissionRate=(double) spnrCommision.getValue();
        int month=cmbMonths.getSelectionModel().getSelectedIndex()+1;
        try {
            PaymentDTO paymentDTO=ctrlPayment.getTutorsMonthlyPayment(mpctm.getCid(), month);
            TutorPaymentDTO temp=new TutorPaymentDTO();
            temp.setCid(csdto.getCid());
            temp.setPay_month(month);
            TutorPaymentDTO tutorPaymentDTO=ctrlTutorPayment.search(temp);
            if(tutorPaymentDTO!=null){
                commissionRate=tutorPaymentDTO.getCommission();
                spnrCommision.getValueFactory().setValue(commissionRate);
            }
            
            BigDecimal amount=BigDecimal.valueOf(csdto.getM_fee()).multiply(BigDecimal.valueOf(paymentDTO.getPidCount()-paymentDTO.getFree_cardCount()));
            amount=amount.round(new MathContext(2));
            BigDecimal commission=amount.multiply(BigDecimal.valueOf(commissionRate)).divide(BigDecimal.valueOf(100));
            commission=commission.round(new MathContext(2));
            BigDecimal payment=amount.subtract(commission);
            System.out.println(commission+"");
            
            txtMonthlyFee.setText(csdto.getM_fee()+"");
            txtRegStu.setText(paymentDTO.getRidCount()+"");
            txtMonthlyPasses.setText(paymentDTO.getPidCount()+"");
            txtFreePasses.setText(paymentDTO.getFree_cardCount()+"");
            txtAmountRecieved.setText(amount+"");
            
            String class_Name=String.format("%s - %s - %s (%s)", mpctm.getB_section(), mpctm.getSubject(), mpctm.getB_year(), csdto.getCid());
            
            tpdto=new TutorPaymentReportDTO(
                    class_Name, 
                    (String) cmbMonths.getSelectionModel().getSelectedItem(),
                    "", 
                    tutor.getTid(), 
                    csdto.getCid(), 
                    LocalDate.now().toString(), 
                    month, 
                    paymentDTO.getPidCount(), 
                    paymentDTO.getFree_cardCount(), 
                    amount.doubleValue(), 
                    commissionRate, 
                    commission.doubleValue(), 
                    payment.doubleValue()
            );
            if (tutorPaymentDTO!=null){
                tpdto.setPrev_pay_Date(tutorPaymentDTO.getPay_date());
                tpdto.setPrev_pay_amount(tutorPaymentDTO.getPay_amount());
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManageProfitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void refresh(){
        txtRegStu.clear();
        txtAmountRecieved.clear();
        txtFreePasses.clear();
        txtMonthlyFee.clear();
        txtMonthlyPasses.clear();
        cmbMonths.getSelectionModel().select(LocalDate.now().getMonthValue()-1);
        tblMdlPayList.clear();
        tblPayments.refresh();
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