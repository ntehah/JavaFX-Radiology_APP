/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class MenuGestionRDVController implements Initializable {
     private static SecretaireControleur mainControler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
     
    @FXML
    void handleAjoutRDV(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/RDV/FormulaireAjoutRDV.fxml")));
    }
    
    @FXML
    void handleConsultRDV(ActionEvent event) throws IOException  {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/Fiches/ConsulterRDV.fxml")));
    
    }
 
    @FXML
    void handleModifierRDV(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaire/RDV/Patient/ConsulterRdvPatient.fxml")));

    }

    @FXML
    void handleSupprimerRDV(ActionEvent event) throws IOException {
        mainControler.getMainpane().setContent((Node)FXMLLoader.load(getClass().getClassLoader().getResource("View/Secretaie/RDV/FormulaireSupprimerRDV.fxml")));
    }
    
    public static void init(SecretaireControleur controleur){
        mainControler=controleur;
    }
}
