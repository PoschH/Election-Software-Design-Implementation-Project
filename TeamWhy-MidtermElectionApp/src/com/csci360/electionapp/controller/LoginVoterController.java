package com.csci360.electionapp.controller;

import com.csci360.electionapp.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import com.csci360.electionapp.model.Ballot;
import com.csci360.electionapp.model.Voter;
import com.csci360.electionapp.model.VoterList;


import java.io.IOException;
import java.util.ArrayList;

public class LoginVoterController {

    @FXML
    private TextField voterIDField;
    @FXML
    private TextField socSecField;
    @FXML
    private Button submitButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button adminMenu;

    private Stage dialogueStage;
    //private Voter voter;
    private VoterList voterList;
    private boolean submitClicked = false;

    private MainApp mainApp;

    @FXML
    private void initialize(){
    }

    public void setDialogueStage(Stage dialogueStage){
        this.dialogueStage = dialogueStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add voterList list data to the list in the controller
        voterList = mainApp.getVoterList();
    }

    public boolean isSubmitClicked(){
        return submitClicked;
    }

    @FXML
    private void handleSubmit() throws  IOException {
        if (isInputValid()){
                Voter voter = voterList.getVoterByLoginInfo(voterIDField.getText(),socSecField.getText());

                submitClicked = true;
                System.out.println("Login successful.");
                
                //sends the voter to the mainapp for later use
                mainApp.setVoter(voter);

                // After Log in confirmation Sends user to can_select ---- Levi ----
                mainApp.showLoginConfirm();

            }
    }


    private boolean isInputValid(){
        String errorMessage = "";
        if (voterIDField.getText() == null || voterIDField.getText().length() == 0){
            errorMessage += "No valid voter ID.\n";
        }
        if (socSecField.getText() == null || socSecField.getText().length() == 0){
            errorMessage += "No valid social security number.\n";
        }

        if (errorMessage.length() == 0 && (!voterList.checkForVoter(voterIDField.getText(),socSecField.getText()))){
            errorMessage += "Invalid login.\n";
        }
        
        ArrayList<Ballot> voteList = mainApp.getElection().getBallotList();
        for (Ballot vote : voteList) {
        	if (errorMessage.length() == 0 && (voterList.getVoterByLoginInfo(voterIDField.getText(), socSecField.getText())).equals(vote.getVoter())) {
        		errorMessage += "You have already voted.\n";
        	}
        }
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

    // Show Help Menu on Click ---- Levi ----

    public void helpButtonClick(ActionEvent event) throws IOException {

        mainApp.showHelp();

    }

    public void adminBClick () throws IOException {

        mainApp.showAdminLogin();

    }


}
