/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import lk.ijse.gdse41.ias.view.controller.AddInstructorSubjectsController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.TutorController;
import lk.ijse.gdse41.ias.controller.custom.impl.TutorControllerImpl;
import lk.ijse.gdse41.ias.dto.TutorDTO;
import lk.ijse.gdse41.ias.dto.TutorSubjectDTO;
import lk.ijse.gdse41.ias.tablemodel.InstructorRegistrationTableModel;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class AddInstructorSummaryController implements Initializable {

    @FXML
    Label summary;
    @FXML
    JFXButton btnConfirm;
    @FXML
    JFXButton btnAddNewTut;
    @FXML
    JFXButton btnBack;
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

    public static ObservableList<InstructorRegistrationTableModel> rtm=AddInstructorSubjectsController.rtm;
    public static ArrayList<TutorSubjectDTO> tsdtolist=AddInstructorSubjectsController.tsdtolist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colTutId.setCellValueFactory(new PropertyValueFactory("tid"));
        colSubId.setCellValueFactory(new PropertyValueFactory("subId"));
        colSub.setCellValueFactory(new PropertyValueFactory("subject"));
        colSection.setCellValueFactory(new PropertyValueFactory("section"));
        
        tblSubjects.setItems(rtm);
        
        TutorDTO t=RegisterInstructorController.tutor;
        String sum=String.format("Tutor Details\n"
                + "ID\t\t: %s\tNIC : %s\n"
                + "Name\t: %s\n"
                + "Contact Details\n"
                + "Primary\t: %s\tSecondary\t: %s\n"
                + "Address\t: %s\n"
                + "Login Details\n"
                + "Username\t: %s\tPassword\t: %s",
                t.getTid(),t.getNIC(),t.getTitleName()+" "+t.getfName()+" "+t.getlName(),
                t.getTelPrimary(),t.getTelSecondary(),t.getAddress(),t.getUsername(),t.getPassword());
        
        summary.setText(sum);
    }

    @FXML
    private void btnConfirmAction(){
        TutorController ctrl=(TutorController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.TUTOR);
        try {
            boolean isAdded=ctrl.add(RegisterInstructorController.tutor, tsdtolist);
            if(isAdded){
                RegisterInstructorController.tutor=null;
                tsdtolist=new ArrayList<>();
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Instructor added successfully.", ButtonType.OK);
                alert.show();
            }else{
                Alert alert=new Alert(Alert.AlertType.ERROR, "Couldn't complete request.", ButtonType.OK);
                alert.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(AddInstructorSummaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
