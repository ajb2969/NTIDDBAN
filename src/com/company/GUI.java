package com.company;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class GUI extends Application implements EventHandler<ActionEvent> {

    private Button buttonNuke;
    private ComboBox comboBoxDrives;
    private String[] drives = {"A","B","X","Y","Z"};

    private HBox center = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("NTID NUKING SERVICE");
        buttonNuke = new Button("NUKE");
        buttonNuke.setOnAction(this);
        comboBoxDrives = new ComboBox<>();
        comboBoxDrives.getItems().addAll(drives);
        center.getChildren().addAll(comboBoxDrives, buttonNuke);
        StackPane root = new StackPane();
        root.getChildren().add(center);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == buttonNuke) {
            System.out.println("NUKE EM ALL");
        }
    }
}
