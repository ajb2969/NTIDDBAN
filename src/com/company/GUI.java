package com.company;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GUI extends Application implements EventHandler<ActionEvent>, Observer{

    private Button buttonNuke;
    private Button nukeAll;
    private ComboBox comboBoxDrives;
    private ArrayList<Drive> drives;
    private HBox center = new HBox();
    private WiperModel model;
    private TextArea tf;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        model = new WiperModel();
        System.out.println("init: Initialize and connect to model!");
        this.model.addObserver(this);
        drives = model.getDrives();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("NTID NUKING SERVICE");
        primaryStage.setMinWidth(500);
        buttonNuke = new Button("NUKE");
        buttonNuke.setOnAction(e -> System.out.println());
        nukeAll = new Button("Nuke All");
        nukeAll.setOnAction(e -> System.out.println());

        comboBoxDrives = new ComboBox<>();
        for(Drive d: model.getDrives()){
            comboBoxDrives.getItems().add(d.getLetterName());
        }
        center.getChildren().addAll(comboBoxDrives, buttonNuke,nukeAll);

        tf = new TextArea();
        GridPane root = new GridPane();
        GridPane.setMargin(center, new Insets(0, 0, 10, 0));
        root.addRow(0,center);
        root.addRow(1,tf);
        root.setHgap(10); //horizontal gap in pixels => that's what you are asking for
        root.setVgap(10); //vertical gap in pixels
        root.setPadding(new Insets(10, 10, 10, 10));
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }


    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == buttonNuke) {
            try{
                ArrayList<Drive> driveInfo = new ArrayList<Drive>();
                ArrayList<Drive> d = model.getDrives();
                String driveLetter = comboBoxDrives.getSelectionModel().getSelectedItem().toString();
                for(Drive e : d){
                    if(e.getLetterName().equals(driveLetter)){
                        driveInfo.add(e);
                    }
                }
                buttonNuke.setDisable(true);
                nukeAll.setDisable(true);
                model.wipe(driveInfo);
                //buttonNuke.setDisable(false);
                //nukeAll.setDisable(false);
            }
            catch(NullPointerException e){
                Alert noValDisplay = new Alert(Alert.AlertType.ERROR);
                noValDisplay.setTitle("No Value Present");
                noValDisplay.setHeaderText("There appears to have been a user error");
                noValDisplay.setContentText("You have not yet entered a drive you wish to wipe.\n" +
                        "Please select a drive to wipe, or select Nuke All");
                noValDisplay.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(event.getSource() == nukeAll){
            buttonNuke.setDisable(true);
            nukeAll.setDisable(true);
            ArrayList<Drive> d = model.getDrives();
            try {
                model.wipe(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(model.getDriveWiped() == 1){ // drives were wiped
            String driveListing = "";

            switch (model.getHowManyWiped()){
                case 1:
                    Alert driveWipedSuccess = new Alert(Alert.AlertType.ERROR);
                    driveWipedSuccess.setTitle("Sucessful Wipe");
                    driveWipedSuccess.setHeaderText("The drive was able to be wiped");
                    driveListing += model.getDrives().get(0).getLetterName();
                    driveWipedSuccess.setContentText("Drive " + driveListing + "was able to be wiped");
                    driveWipedSuccess.showAndWait();
                    break;
                default:
                    for(Drive d: model.getDrives()){
                        driveListing += d.getLetterName() + "\n";
                    }
                    Alert drivesWipedSuccess = new Alert(Alert.AlertType.ERROR);
                    drivesWipedSuccess.setTitle("Successful Wipes");
                    drivesWipedSuccess.setHeaderText("The drives were able to be wiped");
                    drivesWipedSuccess.setContentText("The following Drives were able to be wiped \n" + driveListing);
                    drivesWipedSuccess.showAndWait();
                    break;

            }
        }
        else{//drives weren't wiped
            Alert noValDisplay = new Alert(Alert.AlertType.ERROR);
            noValDisplay.setTitle("Unexpected Error");
            noValDisplay.setHeaderText("The drives were unable to be wiped");
            noValDisplay.setContentText("It appears that one or more of the selected drives were unable to be wiped \n Please try again.");
            noValDisplay.showAndWait();
        }
    }
}
