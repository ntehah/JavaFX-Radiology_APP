/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Admin.Materiel;

import Dao.MaterielDao;
import Model.Materiel;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextFormatter;
import org.controlsfx.control.NotificationPane;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjoutMaterielController implements Initializable {

	@FXML
	private JFXTextField nom;

	@FXML
	private JFXTextField description;

	@FXML
	private JFXTextField prix;

	@FXML
	private JFXComboBox<String> type;

	@FXML
	private NotificationPane Notif;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		UnaryOperator<TextFormatter.Change> filter = change -> {
			String text = change.getText();

			if (text.matches("[0-9]*")) {
				return change;
			}

			return null;
		};
		TextFormatter<String> textFormatter = new TextFormatter<>(filter);
		prix.setTextFormatter(textFormatter);

		nom.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String nomString = nom.getText();

				if (arg1) {
					try {

						if (nomString.matches("[A-Za-z]*") == false) {

							nom_valide = false;
						} else if (nomString.matches("") == true) {
							nom_valide = false;
						} else {

							nom_valide = true;
						}

					} catch (Exception e) {
						nom_valide = false;
					}

				}
			}

		});
		prix.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String prixtext = prix.getText();
				if (arg1)
					try {

						if (prixtext.matches("[0-9.]*") == false) {
							prix_valide = false;
						} else if (prix.getText().isEmpty()) {
							prix_valide = false;

						}

						else {
							prix_valide = true;
						}

					} catch (Exception e) {
					}

			}

		});
	}

	@FXML
	void handleReset(ActionEvent event) {
		nom.setText(null);
		description.setText(null);
		type.setValue(null);
		prix.setText(null);
	}

	public boolean prix_valide = false;
	public boolean nom_valide = false;

	private boolean verifAjoutform(String Type, String nom, String description) {

		return (Type == null || description.length() == 0 || nom_valide==false || prix_valide==false ? false : true);
	}

	@FXML
	void handleAjouter(ActionEvent event) {

		String Type = type.getSelectionModel().getSelectedItem();
		String nomF = this.nom.getText();
		String descriptionF = this.description.getText();
		if (!this.prix.getText().equals("") && verifAjoutform(Type, nomF, descriptionF)) {
			int prixF = Integer.parseInt(this.prix.getText());
			Materiel materiel = new Materiel(nomF, descriptionF, Type, prixF, "Nouveau");

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Adding Materiel Confirmation");
			alert.setHeaderText("New Materiel will be added");
			alert.setContentText("Are you ok with this?");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				MaterielDao.AjouterMateriel(materiel);
				handleReset(event);
			} else {
				handleReset(event);
			}
		} else {
			Notif.show("Formulaire invalide ");

		}
	}
}
