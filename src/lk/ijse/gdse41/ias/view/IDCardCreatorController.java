/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author midda
 */
public class IDCardCreatorController implements Initializable {
    
    @FXML
    private Spinner spnrStudentCount;

    @FXML
    private JFXButton btnGenTemp;

    @FXML
    private JFXTextField txtSid;

    @FXML
    private Label summary;

    @FXML
    private TableView tblPrintList;

    @FXML
    private TableColumn colSid;

    @FXML
    private TableColumn colName;

    @FXML
    private JFXTextField txtProfPic;

    @FXML
    private JFXButton btnFilePicker;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private ImageView imgVwrStudent;

    @FXML
    private JFXButton btnRemove;

    @FXML
    private JFXButton btnGenID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    void btnAddActn(ActionEvent event) {

    }

    @FXML
    void btnFilePickerActn(ActionEvent event) {

    }

    @FXML
    void btnGenIDActn(ActionEvent event) {

    }

    @FXML
    void btnGenTempActn(ActionEvent event) {

    }

    @FXML
    void btnRemoveActn(ActionEvent event) {

    }
    
}
