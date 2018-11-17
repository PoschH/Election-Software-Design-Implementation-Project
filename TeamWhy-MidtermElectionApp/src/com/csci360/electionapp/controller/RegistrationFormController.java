package com.csci360.electionapp.controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.csci360.electionapp.TestDriverRegistrationForm;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.csci360.electionapp.model.Registrant;
import com.csci360.electionapp.model.RegistrantList;
import com.csci360.electionapp.model.RegisteringSession;

public class RegistrationFormController {
	
	@FXML
    private ChoiceBox usCitizenChoiceBox;
	@FXML
    private ChoiceBox ageChoiceBox;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField midIntField;
    @FXML
    private TextField suffixField;
    @FXML
    private ChoiceBox sexChoiceBox;
    @FXML
    private ChoiceBox raceChoiceBox;
    @FXML
    private TextField socSecField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField homeAddStreetField;
    @FXML
    private TextField homeAddAptNumField;
    @FXML
    private TextField homeAddCityField;
    @FXML
    private TextField homeAddStateField;
    @FXML
    private TextField homeAddZipField;
    @FXML
    private TextField mailAddStreetField;
    @FXML
    private TextField mailAddAptNumField;
    @FXML
    private TextField mailAddCityField;
    @FXML
    private TextField mailAddStateField;
    @FXML
    private TextField mailAddZipField;
    @FXML
    private TextField homePhoneField;
    @FXML
    private TextField cellPhoneField;
    @FXML
    private Button submitButton;
    @FXML
    private Button goBackButton;
    
    private Stage dialogueStage;
    private Registrant registrant;
    private RegistrantList registrantList;
    private RegisteringSession rSession;
    private boolean submitClicked = false;

    private TestDriverRegistrationForm testDriveRegForm;
    
    @FXML
    private void initialize(){
	
    }

    public void setDialogueStage(Stage dialogueStage){
        this.dialogueStage = dialogueStage;
    }
    
    public void setTestDriverRegistrationForm(TestDriverRegistrationForm tesDrRegForm) {
        this.testDriveRegForm = tesDrRegForm;

        // Add registrantList data to the controller
        registrantList = tesDrRegForm.getRegistrantList();
    }

    public boolean isSubmitClicked(){
        return submitClicked;
    }
    
    @FXML
    private void handleSubmitQuestion(){
        if (isInputValid()){
        	boolean willAppend = true;
        	try(BufferedWriter regQues = new BufferedWriter(new FileWriter("out/registrationInfo.txt", willAppend))){
           
            submitClicked = true;
            System.out.println("Questions submitted successfully.");
            
            
            regQues.append(usCitizenChoiceBox.getValue() + ", ");	
            regQues.append(ageChoiceBox.getValue() + ", ");
            testDriveRegForm.showForm02Registrant();
        	}
        	catch(IOException ex) {
        		
        	}

        }
    }
    @FXML
    private void handleSubmitForm() throws Exception{
        if (isInputValid()){
        	boolean willAppend = true;
        	try(BufferedWriter regForm = new BufferedWriter(new FileWriter("out/registrationInfo.txt", willAppend))){
           
            submitClicked = true;
            System.out.println("Form submitted successfully.");
            
            regForm.append(lastNameField.getText() + ", ");
            regForm.append(firstNameField.getText() + ", ");
            regForm.append(midIntField.getText() + ", ");
            regForm.append(suffixField.getText() + ", ");
            regForm.append(sexChoiceBox.getValue() + ", ");
            regForm.append(raceChoiceBox.getValue() + ", ");
            regForm.append(socSecField.getText() + ", ");
            regForm.append(birthDateField.getText() + ", ");
            regForm.append(homeAddStreetField.getText() + ", ");
            regForm.append(homeAddAptNumField.getText() + ", ");
            regForm.append(homeAddCityField.getText() + ", ");
            regForm.append(homeAddStateField.getText() + ", ");
            regForm.append(homeAddZipField.getText() + ", ");
            regForm.append(mailAddStreetField.getText() + ", ");
            regForm.append(mailAddAptNumField.getText() + ", ");
            regForm.append(mailAddCityField.getText() + ", ");
            regForm.append(mailAddStateField.getText() + ", ");
            regForm.append(mailAddZipField.getText() + ", ");
            regForm.append(homePhoneField.getText() + ", ");
            regForm.append(cellPhoneField.getText());
            regForm.newLine();
            testDriveRegForm.showForm03Registrant();
            
            
        	}
        	catch(IOException ex) {
        		
        	}

        }
    }
    @FXML
    private void handleSubmitInfoConfirm(){
        if (isInputValid()){
           
            submitClicked = true;
            System.out.println("Info Confirm submitted successfully.");
            
            
            testDriveRegForm.showLoginRegistrant();

        }
    }
    
    public boolean isInputValid(){
        String errorMessage = "";
        /*if(usCitizenCheckBoxYes.isSelected() == true && usCitizenCheckBoxNo.isSelected() == true ) {
        	errorMessage += "Please only check Yes or No.\n";
        }
        if(ageCheckBoxYes.isSelected() == true && ageCheckBoxNo.isSelected() == true ) {
        	errorMessage += "Please only check Yes or No.\n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage += "No valid first name provided.\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMessage += "No valid last name provided.\n";
        }
        if (socSecField.getText() == null || socSecField.getText().length() == 0){
            errorMessage += "No valid social security number.\n";
        }
        if (birthDateField.getText() == null || birthDateField.getText().length() == 0){
            errorMessage += "No valid birthdate provided.\n";
        }
        if (homeAddStreetField.getText() == null || homeAddStreetField.getText().length() == 0){
            errorMessage += "No valid home address street provided.\n";
        }
        if (homeAddCityField.getText() == null || homeAddCityField.getText().length() == 0){
            errorMessage += "No valid home address city provided.\n";
        }
        if (homeAddStateField.getText() == null || homeAddStateField.getText().length() == 0){
            errorMessage += "No valid home address state provided.\n";
        }
        if (homeAddZipField.getText() == null || homeAddZipField.getText().length() == 0){
            errorMessage += "No valid home address zip code provided.\n";
        }
        if (mailAddStreetField.getText() == null || mailAddStreetField.getText().length() == 0){
            errorMessage += "No valid mailing address street provided.\n";
        }
        if (mailAddCityField.getText() == null || mailAddCityField.getText().length() == 0){
            errorMessage += "No valid mailing address city provided.\n";
        }
        if (mailAddStateField.getText() == null || mailAddStateField.getText().length() == 0){
            errorMessage += "No valid mailing address state provided.\n";
        }
        if (mailAddZipField.getText() == null || mailAddZipField.getText().length() == 0){
            errorMessage += "No valid mailing address zip code provided.\n";
        }
        if (homePhoneField.getText() == null || homePhoneField.getText().length() == 0){
            errorMessage += "No valid home phone number provided.\n";
        }
        if (cellPhoneField.getText() == null || cellPhoneField.getText().length() == 0){
            errorMessage += "No valid cell phone number provided.\n";
        }*/
        
     

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogueStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
