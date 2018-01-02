package com.company;

import com.company.Drive;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

public class Main extends Application implements Observer {
    private WiperModel model;


    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init() throws Exception {
        System.out.println("inti: Initialize and connect to model!");
        model = new WiperModel();
        model.addObserver(this);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
