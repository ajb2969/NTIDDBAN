package com.company;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Observable;

public class WiperModel extends Observable{

    private ArrayList<Drive> drives;
    private int driveWiped;
    private int howManyWiped;
    public WiperModel() throws Exception {
        pullDrives();
        this.driveWiped = 0;
        System.out.println("Drives have been pulled");
    }

    private void pullDrives() throws Exception{
        File[] roots = File.listRoots();
        drives = new ArrayList<Drive>();
        for (int i = 0; i < roots.length; i++) {
            //System.out.println("Root[" + i + "]: " + roots[i]);
            FileSystemView fsv = FileSystemView.getFileSystemView();
            Path path = Paths.get(roots[i].toString());
            FileStore test = Files.getFileStore(path);
            String format = test.type();
            Drive drive = new Drive(roots[i].toString(), fsv.getSystemDisplayName(roots[i]), fsv.getSystemTypeDescription(roots[i]), format, (int) roots[i].getTotalSpace());
            drives.add(drive);
        }
        for (Drive d : drives) {
            //System.out.println(d.getLetterName() + " " + d.getNamed() + " "+ d.getModel() +" "+ d.getSize()+ " " + d.getFormat());
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

            for(Drive d: drives){
                //call wipe function from c executable
                //put name of executable in parenteses
                Process p = Runtime.getRuntime().exec("./wiper");
                announce("Finished wiping Drive " + d.getLetterName());
            }
        }
        catch (Exception e){
            this.driveWiped = 0;
            this.howManyWiped = 0;
        }
        this.driveWiped = 1;
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
