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
    private ArrayList<Drive> drives;

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
        File[] roots = File.listRoots();
        drives = new ArrayList<Drive>();
        for (int i = 0; i < roots.length; i++) {
            System.out.println("Root[" + i + "]: " + roots[i]);
            FileSystemView fsv = FileSystemView.getFileSystemView();
            Path path = Paths.get(roots[i].toString());
            FileStore test = Files.getFileStore(path);
            String format = test.type();
            Drive drive = new Drive(roots[i].toString(), fsv.getSystemDisplayName(roots[i]), fsv.getSystemTypeDescription(roots[i]), format, (int) roots[i].getTotalSpace());
            drives.add(drive);
        }
        for (Drive d : drives) {
            System.out.println(d.getLetterName() + " " + d.getNamed() + " "+ d.getModel() +" "+ d.getSize()+ " " + d.getFormat());
        }
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
