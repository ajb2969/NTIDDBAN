package com.company;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;

public class WiperModel extends Observable{

    private ArrayList<Drive> drives;
    private int driveWiped;
    private int howManyWiped;
    private final String EXECUTABLE = "wiper";
    private  String directory = "/Users/alexbrown/IdeaProjects/DBAN/src/com/company";
    public WiperModel() throws Exception {
        pullDrives();
        this.driveWiped = 0;
        System.out.println("Drives have been pulled");
    }

    private void notifyListener(Object string) {
        // TODO implement your handling here. System.out printing is just an
        // example.
        System.out.println(String.valueOf(string));
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
        for (Drive d : drives) {
            System.out.println(d.getLetterName() + " " + d.getNamed() + " "+ d.getModel() +" "+ d.getSize()+ " " + d.getFormat());
        }
    }

    public ArrayList<Drive> getDrives(){
        return drives;
    }


    public void wipe(ArrayList<Drive> drives)throws Exception{
        try{
            if(drives.size() > 1){
                this.howManyWiped = 2;
            }
            else if(drives.size() == 1){
                this.howManyWiped = 1;
            }
            else{
                this.howManyWiped = 0;
            }
            int shellExitStatus = 0;
            for(Drive d: drives){
                //call wipe function from c executable
                //put name of executable in parenteses

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
                        announce("Error");
                    }
                    else{
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
            this.howManyWiped = 0;
        }

    }
    private void announce(String arg) {
        System.out.println(arg);
        setChanged();
        notifyObservers(arg);
    }

    public void setDriveWiped(int driveWiped) {
        this.driveWiped = driveWiped;
    }

    public int getDriveWiped() {
        return driveWiped;
    }

    public void setHowManyWiped(int howManyWiped){
        this.howManyWiped = howManyWiped;
    }

    public int getHowManyWiped(){
        return this.howManyWiped;
    }
}
