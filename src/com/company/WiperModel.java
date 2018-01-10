package com.company;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class WiperModel extends Observable{

    private ArrayList<Drive> drives;
    private int driveWiped;
    private int howManyWiped;
    private final String EXECUTABLE = "wiper";
    private String directory = "/Users/alexbrown/IdeaProjects/DBAN/src/com/company";
    private List<String> printer;
    private int shellExitStatus;

    public WiperModel() throws Exception {
        pullDrives();
        this.shellExitStatus = 1;
        this.driveWiped = 0;
        printer = new ArrayList<String>();
    }

    private void pullDrives() throws Exception{
        File[] root = File.listRoots();
        drives = new ArrayList<Drive>();
        FileSystemView fs = FileSystemView.getFileSystemView();
        for(File f: root){
            if(f.isDirectory()){
                for(File file: f.listFiles()){
                    if(file.getName().equals("Volumes")){
                        for(File vol: file.listFiles()){
                            if(!(vol.getName().equals("Macintosh HD"))){
                                Path path = Paths.get(vol.toString());
                                FileStore test = Files.getFileStore(path);
                                String format = test.type();
                                Drive drive = new Drive(vol.toString(), fs.getSystemDisplayName(vol), fs.getSystemTypeDescription(vol), format, (int) vol.getTotalSpace());
                                drives.add(drive);
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Drive> getDrives(){
        return drives;
    }


    public void wipe(ArrayList<Drive> drives)throws Exception{
        try{
            for(Drive d: drives){
                String revisedDrive = URLDecoder.decode(d.getLetterName(), "UTF-8");//fixDrivePath(d.getLetterName());//drive path with esc seq.
                ProcessBuilder execute = new ProcessBuilder().command("./" + EXECUTABLE);
                Map<String, String> env = execute.environment();
                directory = env.get("PWD") + "/src/com/company";
                execute.directory(new File(directory));
                execute.redirectErrorStream(true);
                execute.redirectOutput(ProcessBuilder.Redirect.INHERIT);

                try {
                    final Process p = execute.start();
                    shellExitStatus = p.exitValue();
                    if(shellExitStatus == 1){//unsuccessful termination
                        this.driveWiped = 0;
                        printer.add("No drive was provided or too many drives were provided. Please try again\n");
                        //did not have drive selected/ did not have an cmd line arg
                    }
                    else if(shellExitStatus == -1){
                        this.driveWiped = 0;
                        printer.add(d.getLetterName() + " was unable to be opened or read\n");
                        //file could not be opened
                    }
                    else{
                        printer.add(d.getLetterName() + " was successfully wiped\n");
                        System.out.println("Finished wiping Drive " + d.getLetterName());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            announce("Finished Wiping");
        }
        catch (Exception e){
            this.driveWiped = 0;
        }
    }

    public void clearStream(){
        this.printer.clear();
    }
    private void announce(String arg) {
        System.out.println(arg);
        setChanged();
        notifyObservers();
    }

    public void setDriveWiped(int driveWiped) {
        this.driveWiped = driveWiped;
    }

    public int getDriveWiped() {
        return driveWiped;
    }

    public String getOutput(){
        StringBuilder sb = new StringBuilder();
        this.printer.stream().forEach(element -> sb.append(element));
        return sb.toString();
    }
}
