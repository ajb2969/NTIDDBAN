package com.company;

public class Drive {
    private String letterName;
    private String named;
    private String model;
    private String format;
    private int size;

    public Drive(String letter, String namedDrive, String hdModel, String hdFormat, int sizeDrive) {
        letterName = letter;
        if (namedDrive != "") {
            named = namedDrive;
        } else {
            named = "None";
        }
        model = hdModel;
        format = hdFormat;
        size = sizeDrive;
    }

    public String getLetterName() {
        return letterName;
    }

    public String getNamed() {
        return named;
    }

    public String getModel() {
        return model;
    }

    public String getFormat() {
        return format;
    }

    public int getSize() {
        return size;
    }
}
