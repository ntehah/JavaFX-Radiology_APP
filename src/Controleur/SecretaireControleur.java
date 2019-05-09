/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.PFA2019;

/**
 *
 * @author asus
 */
public class SecretaireControleur implements Initializable{
    
    @FXML
    private AnchorPane main;
    
    @FXML
    private ScrollPane mainpane;

    @FXML
    private VBox menu;
    
    
    @FXML
    void handleHome(ActionEvent event) throws IOException {
        menu.getChildren().removeAll(menu.getChildren());
        menu.getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/MenuSecretaire.fxml")));
    }
    @FXML
    void handleLogOut(ActionEvent event) throws IOException {
        Stage st = PFA2019.getStage();

        Scene scene = new Scene((AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("View/FXML.fxml")));
        st.setScene(scene);
        st.show();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuAccueilController.initSecretaire(this);
      try {
           menu.getChildren().add((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/MenuSecretaire.fxml")));
       } catch (IOException ex) {
            Logger.getLogger(SecretaireControleur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public VBox getMenu(){
        return this.menu;
    }

    public ScrollPane getMainpane() {
        return mainpane;
    }
    
}

