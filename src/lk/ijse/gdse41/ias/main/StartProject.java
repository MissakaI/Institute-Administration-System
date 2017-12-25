/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.gdse41.ias.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lk.ijse.gdse41.ias.view.DashBoardController;

/**
 *
 * @author midda
 */
public class StartProject extends Application {
    
    public static boolean isSplashLoaded=false;
    
    public static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("/lk/ijse/gdse41/ias/view/DashBoard.fxml"));
        
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        
        stage=primaryStage;
        stage.heightProperty().addListener(DashBoardController.stageSizeListener);
        stage.widthProperty().addListener(DashBoardController.stageSizeListener);
        
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/lk/ijse/gdse41/ias/resources/icons/Sathra_logo_final.png")));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
