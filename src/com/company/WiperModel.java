package com.company;

import java.util.Observable;

public class WiperModel extends Observable{

    public WiperModel(){}

    private void announce(String arg) {
        setChanged();
        notifyObservers(arg);
    }
}
