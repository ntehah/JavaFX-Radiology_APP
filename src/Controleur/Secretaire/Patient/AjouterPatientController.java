/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur.Secretaire.Patient;

import Dao.PatientDao;
import Model.Patient;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
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
public class AjouterPatientController implements Initializable {
	@FXML
	private NotificationPane Notif;
	@FXML
	private JFXTextField prenom;

	@FXML
	private JFXTextField nom;

	@FXML
	private JFXDatePicker date;

	@FXML
	private JFXTextField tel;

	@FXML
	private JFXComboBox<String> type;

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

		tel.setTextFormatter(textFormatter);
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
		prenom.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				String prenomString = prenom.getText();

				if (arg1) {
					try {

						if (prenomString.matches("[A-Za-z]*") == false) {

							prenom_valide = false;
						} else if (prenomString.matches("") == true) {

							prenom_valide = false;
						} else {

							prenom_valide = true;
						}

					} catch (Exception e) {

						prenom_valide = false;
					}

				}
			}

		});

		date.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {

				if (arg1)
					try {

						if (date.getValue() == null) {

							date_valide = false;
							
						}

						else {

							date_valide = true;
						}

					} catch (Exception e) {

						date_valide = false;
					}

			}

		});
	}

	public boolean nom_valide = false;
	public boolean prenom_valide = false;
	public boolean date_valide = false;

	private boolean verifAjoutform(String nom, String prenom, Date date, String type) {

		return (nom_valide == false || prenom_valide == false || type.length() == 0 || date_valide==false ? false : true);
	}

	@FXML
	void handleReset(ActionEvent event) {
		nom.setText(null);
		prenom.setText(null);
		tel.setText(null);

		date.setValue(null);
	}

	@FXML
	private void addPatient(ActionEvent event) {
		if (!this.tel.getText().equals("") && verifAjoutform(this.nom.getText(), this.prenom.getText(),
				Date.valueOf(date.getValue()), type.getSelectionModel().getSelectedItem())) {
			int telephone = Integer.parseInt(this.tel.getText());
			String nomForm = this.nom.getText();
			String prenomForm = this.prenom.getText();
			String typeForm = type.getSelectionModel().getSelectedItem();
			Date d = Date.valueOf(date.getValue());

			Patient p = new Patient(nomForm, prenomForm, typeForm, telephone, d);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Adding Patient Confirmation");
			alert.setHeaderText("New Patient will be added");
			alert.setContentText("Are you ok with this?");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				PatientDao.AjouterPatient(p);
				Notif.show("New Patient is added !! ");

			} else {
				handleReset(event);
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Formulaire invalide!");
			alert.showAndWait();
		}
	}
}
