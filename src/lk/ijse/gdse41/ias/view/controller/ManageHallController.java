/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.gdse41.ias.controller.ControllerFactory;
import lk.ijse.gdse41.ias.controller.custom.HallController;
import lk.ijse.gdse41.ias.controller.custom.impl.HallControllerImpl;
import lk.ijse.gdse41.ias.dto.HallDTO;
import lk.ijse.gdse41.ias.tablemodel.HallTableModel;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class ManageHallController implements Initializable {

    @FXML
    private TableView tblHall;

    @FXML
    private TableColumn colHName;

    @FXML
    private TableColumn colSeating;

    @FXML
    private TableColumn colAC;

    @FXML
    private JFXTextField txtHID;

    @FXML
    private Spinner spnrSeatCap;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXToggleButton tglBtnAC;

    @FXML
    private JFXButton btnClear;
    
    /**
     * Initializes the controller class.
     */
    
    private final ObservableList<HallTableModel> htmList=FXCollections.observableArrayList();
    private HallController ctrl;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ctrl=(HallController) ControllerFactory.getInstance().getController(ControllerFactory.ControllerTypes.HALL);
        
        loadExistingHalls();
        spnrSeatCap.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3000, 100));
        
        colHName.setCellValueFactory(new PropertyValueFactory("hid"));
        colSeating.setCellValueFactory(new PropertyValueFactory("seatingCap"));
        colAC.setCellValueFactory(new PropertyValueFactory("ac"));
        
        tblHall.setItems(htmList);
    }    
    
    private void loadExistingHalls(){
        try {
            ArrayList<HallDTO> dtoList=ctrl.getAll();
            for (HallDTO hallDTO : dtoList) {
                String ac="Not Available";
                if(hallDTO.isAc()){
                    ac="Available";
                }
                HallTableModel htm=new HallTableModel(
                        hallDTO.getHid(),
                        hallDTO.getSeatingCap(),
                        ac
                );
                htmList.add(htm);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageHallController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    int selectedIndex;
    boolean update=false;
    
    @FXML
    void tblHallClickAction(MouseEvent event) {
        HallTableModel htm=(HallTableModel) tblHall.getSelectionModel().getSelectedItem();
        selectedIndex=htmList.indexOf(htm);
        txtHID.setText(htm.getHid());
        spnrSeatCap.getValueFactory().setValue(htm.getSeatingCap());
        if(htm.getAc().equals("Available")){
            tglBtnAC.setSelected(true);
        }else{
            tglBtnAC.setSelected(false);
        }
        btnNew.setText("Update Details");
        btnNew.setId("btnBlue");
        update=true;
    }
    
    @FXML
    void btnClearAction(){
        txtHID.setText(null);
        spnrSeatCap.getValueFactory().setValue(100);
        tglBtnAC.setSelected(false);
        btnNew.setText("New Hall");
        btnNew.setId("btnGreen");
        update=false;
    }
    
    @FXML void btnNewAction(){
        System.out.println((int) spnrSeatCap.getValue());
        HallDTO hdto=new HallDTO(
                txtHID.getText(), 
                (int) spnrSeatCap.getValue(), 
                tglBtnAC.isSelected());
        try {
            if(!update){
                boolean isAdded=ctrl.add(hdto);
                if(isAdded){
                    String ac="Not Available";
                    if(tglBtnAC.isSelected()){
                        ac="Available";
                    }
                    HallTableModel htm=new HallTableModel(
                            txtHID.getText(), 
                            (int) spnrSeatCap.getValue(), 
                            ac);
                    htmList.add(htm);
                }
            }else{
                boolean isUpdated=ctrl.update(hdto);
                if(isUpdated){
                    String ac="Not Available";
                    if(tglBtnAC.isSelected()){
                        ac="Available";
                    }
                    HallTableModel htm=htmList.get(selectedIndex);
                    htm.setSeatingCap((int) spnrSeatCap.getValue());
                    htm.setAc(ac);
                }
            }
            tblHall.refresh();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ManageHallController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
